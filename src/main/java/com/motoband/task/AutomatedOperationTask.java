package com.motoband.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.motoband.motouser.motouserService;
import com.motoband.util.Consts;
import com.motoband.util.MbUtil;
import com.motoband.util.RedisManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
public class AutomatedOperationTask {
	@Autowired
	private  motouserService motouserService;
	
	public static final String NEWSKEY_MOMENTNEWS = "news_momentnews";
	
	public static final MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");
	private static OkHttpClient httpClient = new OkHttpClient();
	
	// 日志记录
    public	static Logger logger = LoggerFactory.getLogger(AutomatedOperationTask.class);
		
	public void execute_automatedOperation() {
		 List<String> useridlist=getUseridList();
		 List<String> nidlist=getNidList();
		for(int i=0;i<10;i++){
			 int useridrandom = (int) (Math.random() * useridlist.size());
			 int nidrandom = (int) (Math.random() * nidlist.size());
			 String userid=useridlist.get(useridrandom);
			 String nid=nidlist.get(nidrandom);
			 likenews(userid,nid);
		}
	}
	
	public  List<String> getUseridList(){
		 List<String> list=motouserService.getAutomatedOperationUseridList();
		 list.add("86-18236889261");
		 list.add("86-18701561009");
		 list.add("86-15910301209");
		 list.add("6A1EB3B5557C47508448F891D1BB3291");
		 list.add("86-15611589047");
		 list.add("86-18612564736");
		 list.add("86-18701316959");
		 list.add("86-13911174000");
		 list.add("86-18611171457");
		 list.add("86-18810091131");
		 list.add("86-18500063327");
		 list.add("86-18516812686");
		 list.add("86-15010443977");
		 list.add("E2CD901A398C4B66A4BB16CE1603B3B1");
		 list.add("33AE539328F542028966E7AF3AE9A7FF");
		 list.add("86-15010785070");
		 list.add("86-13911000482");
		 list.add("86-18510755366");
		 list.add("86-13810008710");
		 list.add("86-13911000481");
		 list.add("86-13661095631");
		 list.add("585402AECA1C4271850E329249810330");
		 list.add("E2CDE669A6A142FB8FD94592AE614250");
		return list;
		
	}
//	public List<String> getRandomUseridList(){
//		List<String> list=getUseridList();
//		List<String> createRandomList = MbUtil.createRandomList(list, 20);
//		return createRandomList;
//		
//	}
	public List<String> getNidList(){
		List<String> list=new ArrayList<String>();
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, NEWSKEY_MOMENTNEWS)){
			Set<String> tempset = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, NEWSKEY_MOMENTNEWS, 0, 499);
			list=new ArrayList<String>(tempset);
		}
		return list;
		
	}
//	public List<String> getRandomNidList(){
//		List<String> list=getNidList();
//		List<String> createRandomList = MbUtil.createRandomList(list, 20);
//		return createRandomList;
//		
//	}
	public void likenews(String userid,String nid){
	//	System.out.println(userid+"---------------"+nid);
		try {
			JSONObject obj = new JSONObject();
			obj.put("userid", userid);
			obj.put("nid", nid);
//			String data = JSON.toJSONString(obj);
//
//			String urlString = Consts.ServiceUrl + "news/likenews";
//		//	logger.info("likenews  request data:[url:"+urlString+"  param:"+data+"]");
//			
//			RequestBody body = RequestBody.create(MediaType_JSON, data);
//			Request req = new Request.Builder().url(urlString).post(body).build();
//			Response resp=httpClient.newCall(req).execute();
//			String result =new String(resp.body().bytes(), "utf-8");
//	//		logger.info("likenews  response data:[result:"+result+"]");
			obj.put("token", "token");
			obj.put("requestid", "12345678");
			obj.put("cversion", "1");
			obj.put("ctype", "1");
			String data = JSON.toJSONString(obj);
			String responstr = MbUtil.handleReqAndSendReq("/news/likenews", data);
			String resultstr = MbUtil.handleResp(responstr);
			String str ="1";
			if(StringUtils.isNotBlank(resultstr)){
				JSONObject json = JSON.parseObject(resultstr);
				str =(String) json.get("code");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("likenews Exception userid:"+userid+"     nid:"+nid);
		}
		
	}
	
}
