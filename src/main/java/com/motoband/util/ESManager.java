package com.motoband.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.motoband.carmanage.NewMotoModel;
import com.motoband.motouser.MBUserModel;
import com.motoband.news.newsModel;
import com.motoband.secondcar.SecondCarModel;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ESManager {
	public static final MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");

	private static OkHttpClient httpClient = new OkHttpClient();
	// 日志记录
	public	static Logger logger = LoggerFactory.getLogger(ESManager.class);
	/**
	 * 
	 * @Description 同步es
	 * @param newsModel
	 * @throws IOException 
	 */
	public static void syncNewsEs(newsModel newsModel) {
		new Thread(){
			public void run() {
				try {
					String urlString = Consts.MOTOBAND_SEARCH + "search/news/add";
					String data = JSON.toJSONString(newsModel);
					
					logger.info("syncNewsEs  request data:[url:"+urlString+"  param:"+data+"]");
					
					RequestBody body = RequestBody.create(MediaType_JSON, data);
					Request req = new Request.Builder().url(urlString).post(body).build();
					Response resp=httpClient.newCall(req).execute();
					String result =new String(resp.body().bytes(), "utf-8");
					
					logger.info("syncNewsEs  response data:[result:"+result+"]");
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("syncNewsEs  Exception:"+e);
				}
			}
		}.start();
		
		
	}

	public static  void delNewsEs(String nid) throws IOException {
		new Thread(){
			public void run() {
				try {
					String urlString = Consts.MOTOBAND_SEARCH + "search/news/del";
					JSONObject obj = new JSONObject();
					obj.put("nid", nid);
					String data = JSON.toJSONString(obj);
					
					logger.info("delNewsEs  request data:[url:"+urlString+"  param:"+data+"]");
					
					RequestBody body = RequestBody.create(MediaType_JSON, data);
					Request req = new Request.Builder().url(urlString).post(body).build();
					Response resp=httpClient.newCall(req).execute();
					String result =new String(resp.body().bytes(), "utf-8");
					
					logger.info("delNewsEs  response data:[result:"+result+"]");
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("delNewsEs  Exception:"+e);
				}
			}
		}.start();
	
	}
	
	public static void syncUserEs(MBUserModel mBUserModel) {
		new Thread(){
			public void run() {
				try {
					String urlString = Consts.MOTOBAND_SEARCH + "search/mbuser/add";
					String data = JSON.toJSONString(mBUserModel);
					
					logger.info("syncUserEs  request data:[url:"+urlString+"  param:"+data+"]");
					
					RequestBody body = RequestBody.create(MediaType_JSON, data);
					Request req = new Request.Builder().url(urlString).post(body).build();
					Response resp=httpClient.newCall(req).execute();
					String result =new String(resp.body().bytes(), "utf-8");
					
					logger.info("syncUserEs  response data:[result:"+result+"]");
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("syncUserEs  Exception:"+e);
				}
			}
		}.start();
		
		
	}
	public static void syncSecondcarEs(SecondCarModel secondCarModel) {
		new Thread(){
			public void run() {
				try {
					String urlString = Consts.MOTOBAND_SEARCH + "search/secondcar/add";
					String data = JSON.toJSONString(secondCarModel);
					
					logger.info("syncSecondcarEs  request data:[url:"+urlString+"  param:"+data+"]");
					
					RequestBody body = RequestBody.create(MediaType_JSON, data);
					Request req = new Request.Builder().url(urlString).post(body).build();
					Response resp=httpClient.newCall(req).execute();
					String result =new String(resp.body().bytes(), "utf-8");
					
					logger.info("syncSecondcarEs  response data:[result:"+result+"]");
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("syncSecondcarEs  Exception:"+e);
				}
			}
		}.start();
		
		
	}
	public static void syncNewMotomodelEs(NewMotoModel newMotoModel) {
		new Thread(){
			public void run() {
				try {
					String urlString = Consts.MOTOBAND_SEARCH + "search/newmoto/add";
					String data = JSON.toJSONString(newMotoModel);
					
					logger.info("syncNewMotomodelEs  request data:[url:"+urlString+"  param:"+data+"]");
					
					RequestBody body = RequestBody.create(MediaType_JSON, data);
					Request req = new Request.Builder().url(urlString).post(body).build();
					Response resp=httpClient.newCall(req).execute();
					String result =new String(resp.body().bytes(), "utf-8");
					
					logger.info("syncNewMotomodelEs  response data:[result:"+result+"]");
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("syncNewMotomodelEs  Exception:"+e);
				}
			}
		}.start();
		
		
	}
	
	public static  void delNewMotoModelEs(String mid) throws IOException {
		new Thread(){
			public void run() {
				try {
					String urlString = Consts.MOTOBAND_SEARCH + "search/newmoto/del";
					JSONObject obj = new JSONObject();
					obj.put("mid", mid);
					String data = JSON.toJSONString(obj);
					
					logger.info("delNewMotoModelEs  request data:[url:"+urlString+"  param:"+data+"]");
					
					RequestBody body = RequestBody.create(MediaType_JSON, data);
					Request req = new Request.Builder().url(urlString).post(body).build();
					Response resp=httpClient.newCall(req).execute();
					String result =new String(resp.body().bytes(), "utf-8");
					
					logger.info("delNewMotoModelEs  response data:[result:"+result+"]");
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("delNewMotoModelEs  Exception:"+e);
				}
			}
		}.start();
	
	}

}
