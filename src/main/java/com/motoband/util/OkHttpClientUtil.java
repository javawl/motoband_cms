package com.motoband.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpClientUtil {
	private static OkHttpClient ok ;
	private OkHttpClientUtil() {
		ok = new OkHttpClient.Builder().connectTimeout(3000, TimeUnit.SECONDS).readTimeout(3000, TimeUnit.SECONDS).build();
	}

	public static class OkHttpClientUtilHolder {
		private static final OkHttpClientUtil INSTANCE = new OkHttpClientUtil();
	}

	public static final OkHttpClientUtil getInstance() {
		return OkHttpClientUtilHolder.INSTANCE;
	}

	/**
	 * 
	 * @param url
	 *            请求url
	 * @param reqJsonContent
	 *            json字符串
	 * @param secretkey 
	 * @param secretid 
	 * @return json格式得response
	 * @throws IOException
	 */
	public  Response okHttpPost(String url, String reqJsonContent) throws Exception {
		RequestBody rb = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), reqJsonContent);
		Request request = new Request.Builder().url(url).post(rb).build();
		return ok.newCall(request).execute();
	}
	
	public  Response okHttpPost(String url, String reqJsonContent,Headers headers) throws Exception {
		RequestBody rb = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), reqJsonContent);
		Request request = new Request.Builder().url(url).headers(headers).post(rb).build();
	//	System.out.println(request.headers());
		return ok.newCall(request).execute();
	}

	/**
	 * 
	 * @Description post请求 key value格式
	 * @param url
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public  Response okHttpPost(String url, Map<String, String> map) throws Exception {
		FormBody.Builder builder = new FormBody.Builder();
		if (null != map) {
			for (Entry<String, String> entry : map.entrySet()) {
				if(entry.getKey()!=null&&entry.getValue()!=null )
				builder.add(entry.getKey(), entry.getValue());

			}
		}
		FormBody body = builder.build();
		Request request = new Request.Builder().url(url).post(body).build();
		return ok.newCall(request).execute();
	}

	/**
	 * 
	 */

	/**
	 * 
	 * @Descriptionp okhttp3请求https
	 * @param ok
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public  byte[] okHttpGet(String url) throws IOException {
		Request request = new okhttp3.Request.Builder().url(url).get().build();
		Response response = ok.newCall(request).execute();
		return response.body().bytes();
	}
	

	/**
	 * 
	 * @Description 黑名单请求专用！
	 * @param url
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public  InputStream okHttpPostBlack(String url, Map<String, Object> map) throws IOException {
		FormBody.Builder builder = new FormBody.Builder();
		if (null != map) {
			for (Entry<String, Object> entry : map.entrySet()) {
				builder.add(entry.getKey(), entry.getValue().toString());
			}
		}
		FormBody body = builder.build();
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = ok.newCall(request).execute();
		return response.body().byteStream();
	}
	
	public  void okHttpPostAsync(String url, String reqJsonContent) throws Exception {
		RequestBody rb = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), reqJsonContent);
		Request request = new Request.Builder().url(url).post(rb).build();
		Call call = ok.newCall(request);//3.使用client去请求  
		call.enqueue(new Callback() {//4.回调方法  
		    @Override  
		    public void onFailure(Call call, IOException e) {  
		    }  
		  
		    @Override  
		    public void onResponse(Call call, Response response) throws IOException {  
		        String result = new String(response.body().bytes(),"utf-8");//5.获得网络数据  
		    }  
		});
		
	}
	
	public  void okHttpPostAsync(String url, String reqJsonContent,Headers headers) throws Exception {
		RequestBody rb = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), reqJsonContent);
		Request request = new Request.Builder().url(url).headers(headers).post(rb).build();
		Call call = ok.newCall(request);//3.使用client去请求  
		call.enqueue(new Callback() {//4.回调方法  
		    @Override  
		    public void onFailure(Call call, IOException e) {  
		    	System.out.println(url+"----"+reqJsonContent+"--------"+e.getMessage());
		    }  
		  
		    @Override  
		    public void onResponse(Call call, Response response) throws IOException {  
		        String result = new String(response.body().bytes(),"utf-8");//5.获得网络数据  
		        try {
		        	  JSONObject json=JSON.parseObject(result);
				        if(json!=null){
				        	if(json.getInteger("code")!=0){
				        		System.out.println(url+"----"+reqJsonContent+"--------"+result);
				        	}
				        }
				} catch (Exception e) {
					System.out.println(url+"----"+reqJsonContent+"--------"+result);
				}
		    }  
		});
		
	}

}
