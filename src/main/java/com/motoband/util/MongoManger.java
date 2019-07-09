package com.motoband.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.motoband.qiji.QiJiInfoModel;
import com.sun.tools.javac.util.Assert;

/**
 * MongoManager单例
 * Created by junfei.Yang on 2019年7月1日.
 */
public class MongoManger{
	private  final MongoClient mongoClient;
	private  final MongoDatabase database;
	private final static MongoManger mongoManager=new MongoManger();
	
	@SuppressWarnings("static-access")
	private MongoManger() {
      ServerAddress serverAddress = new ServerAddress(Consts.MONGO_IP,Consts.MONGO_PORT);
      List<ServerAddress> seeds = new ArrayList<ServerAddress>();
      seeds.add(serverAddress);
      MongoCredential credentials = MongoCredential.createScramSha1Credential(Consts.MONGO_USERNAME,"admin", Consts.MONGO_PASSWORD.toCharArray());
      List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
      credentialsList.add(credentials);
      mongoClient = new MongoClient(seeds, credentialsList);
      mongoClient.getMongoClientOptions().builder()
      .connectionsPerHost(200)//最大连接数
      .minConnectionsPerHost(10)//最小连接数
      .connectTimeout(10*1000)
      .maxConnectionIdleTime(1*60*1000)//最小空闲时间。
      .maxConnectionLifeTime(10*60*1000)//连接的最大生命时间。
      .threadsAllowedToBlockForConnectionMultiplier(10)//允许阻塞等待连接的线程数量的倍数
      .maxWaitTime(15*1000);
      Assert.checkNonNull(mongoClient,"mongoClient is init null");
      database = mongoClient.getDatabase(Consts.MONGO_DATABASE);
      Assert.checkNonNull(database,"database is init null");

	}
	
	public static MongoManger getInstance() {
		return mongoManager;
	}
	
	/**
	 * 
	 * @Description 根据经纬度和城市查询景点
	 * @param longitude
	 * @param latitude
	 * @param city
	 * @param radius
	 * @param pageNo
	 * @param pageSize
	 * @param order 
	 * @return
	 * @throws Exception
	 */
	public List<QiJiInfoModel> searchQijiLBSForCity(double longitude, double latitude,String province,String city, int radius,int pageNo,int pageSize, int order) throws Exception {
		BasicDBObject eq=new BasicDBObject();
		if(StringUtils.isNotBlank(city)) {
			eq.append("city",new BasicDBObject("$eq",city));	
		}
		if(StringUtils.isNotBlank(province)) {
			eq.append("province",new BasicDBObject("$eq",province));	
		}
		BasicDBObject sort=new BasicDBObject();
		if(order==1){
			sort.append("$sort",new BasicDBObject("signcount",-1));
		}
		return searchQijiLBS(longitude, latitude, eq, radius, pageNo, pageSize,order,sort);
	}
	
	/**
	 * 
	 * @Description 根据条件查询景点
	 * @param longitude
	 * @param latitude
	 * @param query
	 * @param radius
	 * @param pageNo
	 * @param pageSize
	 * @param order 
	 * @param sort 
	 * @return
	 * @throws Exception
	 */
	public List<QiJiInfoModel> searchQijiLBS(double longitude, double latitude,DBObject query, int radius,int pageNo,int pageSize, int order, BasicDBObject sort) throws Exception {
		BigDecimal b = new BigDecimal(radius);
		double newRadius = b.divide(new BigDecimal(1000), 10, BigDecimal.
    			ROUND_HALF_DOWN).doubleValue();
		return search("qiji", new double[] {longitude,latitude}, "location", query, 0, newRadius, pageNo, pageSize, new QiJiInfoModel(),order,sort);
	}
	
	/**
	 * 
	 * @Description 
	 * @param collectionName 查询的表名
	 * @param near 需要查询的坐标数组 new double[]{116.46565, 40.01817}
	 * @param distanceField 坐标字段 location
	 * @param query 额外的条件查询
	 * @param minDistance 最小公里数 默认0
	 * @param maxDistance 最大公里数
	 * @param pageNo 从0开始
	 * @param order 0,distance,1 Heat Rank  
	 * @return
	 */
	public <T> List<T>   search(String collectionName,double[] near,String distanceField,DBObject query,double minDistance,double maxDistance,int pageNo,int pageSize,T clazz,int order,DBObject sort) throws Exception{
		Assert.checkNonNull(collectionName,"collectionName is null");
		Assert.checkNonNull(clazz,"class is null");
		MongoCollection<Document> collection = getCollection(collectionName);
		if(query==null) {
			query=new BasicDBObject();
		}
		if(pageSize==0) {
			pageSize=20;
		}
		if(minDistance>0) {
			BigDecimal b = new BigDecimal(minDistance);
			minDistance = b.divide(new BigDecimal(6371), 20, BigDecimal.
	    			ROUND_HALF_DOWN).doubleValue();
		}
		if(maxDistance>0) {
			BigDecimal b = new BigDecimal(maxDistance);
			maxDistance = b.divide(new BigDecimal(6371), 20, BigDecimal.
	    			ROUND_HALF_DOWN).doubleValue();
		}else if(maxDistance==0) {
			//如果没有传最大公里数默认搜索整个地球
			maxDistance=1;
		}
		List<T> result=Lists.newArrayList();
	     List<BasicDBObject> pipeLine = new ArrayList<>();
	     if(order==0){
	 		Assert.checkNonNull(distanceField,"distanceField is null");
			Assert.checkNonNull(near,"near is null");
	    	 BasicDBObject aggregate = new BasicDBObject("$geoNear",
	 	    		new BasicDBObject("near",near)
	 				        .append("distanceField",distanceField)
	 				        .append("query", query)
	 				        .append("num", 100000)
	 				        .append("minDistance", minDistance)
	 				        .append("maxDistance", maxDistance)
	 				        .append("spherical",true)
	 	    		);
	    	   pipeLine.add(aggregate);
	     }else if(order==1){
	    	 BasicDBObject match  = new BasicDBObject("$match",query);
			pipeLine.add(match);
			pipeLine.add(new BasicDBObject(sort.toMap()));
	     }
	     
	        BasicDBObject skip  = new BasicDBObject("$skip",pageNo*pageSize);
			pipeLine.add(skip);
			BasicDBObject limit  = new BasicDBObject("$limit",pageSize);
			pipeLine.add(limit);
	        MongoCursor<Document> cursor=collection.aggregate(pipeLine).iterator();
	        JsonWriterSettings build = JsonWriterSettings.builder()
                    .outputMode(JsonMode.RELAXED)
//                    .int64Converter((Long value, StrictJsonWriter writer) ->writer.writeString(Long.toString(value)))
//                    .int32Converter((Integer value, StrictJsonWriter writer) ->writer.writeNumber(Integer.toString(value)))
                    .build();
	        while (cursor.hasNext()) {
	        	Document c=cursor.next();
	        	clazz=(T) JSON.parseObject(c.toJson(build),clazz.getClass());
//	        	clazz=(T) JSON.parseObject(c.toJson(),clazz.getClass());
	        	result.add(clazz);
	        }
	       
		return result;
	}

	/**
	 * 
	 * @Description 获取集合
	 * @param collectionName
	 * @return
	 */
	public MongoCollection<Document> getCollection(String collectionName) {
		Assert.checkNonNull(collectionName,"collectionName is null");
		MongoCollection<Document> collection = database.getCollection(collectionName);
		return collection;
	}

	/**
	 * 根据主键修改数据（没有该字段自动新增）
	 * @Description 
	 * @param collectionName 表名
	 * @param primarykeyField 主键字段
	 * @param primarykey 主键值
	 * @param mongoField 修改的字段名称
	 * @param mongoFieldValue 修改的字段值
	 * 
	 */
	public void updateField(String collectionName, String primarykeyField,String primarykey, String mongoField, Object mongoFieldValue) {
		MongoCollection<Document> collection = getCollection(collectionName);
        BasicDBObject doc  = new BasicDBObject(primarykeyField,primarykey);
        BasicDBObject set  = new BasicDBObject("$set",new BasicDBObject(mongoField,mongoFieldValue));
        collection.updateOne(doc, set,new UpdateOptions().upsert(true));
	}

	/**
	 * 
	 * @Description 根据主键插入或修改，全覆盖
	 * @param collectionName
	 * @param model
	 * @param primarykeyField
	 * @param primarykey
	 * @throws Exception
	 */
	public  <T> void  insertOrUpdate(String collectionName, T model,String primarykeyField,String primarykey) throws Exception {
		MongoCollection<Document> collection = getCollection(collectionName);
		Document doc=handleMap(model);
//		UpdateResult res=collection.updateMany(doc, doc, new UpdateOptions().upsert(true));
//		System.out.println(res.toString());
//        BasicDBObject docnew  = new BasicDBObject("sid","123").append("longitude", new Double(10.112));
//
//		collection.insertOne(new Document(docnew));
//		System.out.println("OK");
        BasicDBObject old  = new BasicDBObject(primarykeyField,primarykey);
        BasicDBObject set  = new BasicDBObject("$set",new BasicDBObject(doc));
		collection.updateOne(old, set, new UpdateOptions().upsert(true));
//		collection.insertOne(doc,new InsertOneOptions().bypassDocumentValidation(true));
	}
	
    private static <T> Document handleMap(T model) throws Exception{
    	//解决json paeseObject double类型转成bigdecimal,导致mongo插入失败
        int features = 0;
        features |= Feature.AutoCloseSource.getMask();
//        features |= Feature.InternFieldNames.getMask();
//        features |= Feature.UseBigDecimal.getMask();
        features |= Feature.AllowUnQuotedFieldNames.getMask();
        features |= Feature.AllowSingleQuotes.getMask();
        features |= Feature.AllowArbitraryCommas.getMask();
        features |= Feature.SortFeidFastMatch.getMask();
        features |= Feature.IgnoreNotMatch.getMask();
        JSON.DEFAULT_PARSER_FEATURE = features;
        Document document = null;
        Map<String,Object>  map= (Map<String, Object>) JSON.parse(JSON.toJSONString(model),JSON.DEFAULT_PARSER_FEATURE);
//        Map<String,Object> map=BeanUtils.objectToMapObj(model);
        if(null != map){
            document = new Document();
//            if(model instanceof QiJiInfoModel) {
//            	document.append("sid", map.get("sid"));
//            	document.append("longitude", map.get("longitude"));
//            }else {
                Set<String> sets = map.keySet();
                Iterator<String> iterators = sets.iterator();
                while(iterators.hasNext()){
                    String key = iterators.next();
                    Object value = map.get(key);
                    document.append(key,(value == null ? "" : value));
//                }
            }
        }else{
            document = new Document("","");
        }
        return document;
    }

	public void delete(String collectionName,String primarykeyField,String primarykey) {
		MongoCollection<Document> collection = getCollection(collectionName);
		collection.deleteOne(new Document(primarykeyField, primarykey));
	}
}
