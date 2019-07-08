package com.motoband.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import okhttp3.Headers;
import okhttp3.Response;



public class MbUtil {
	
	
	
/**
 *  转换字符编码
* <p>Method:sChangeBytes </p>
* <p>Description: </p>
* <p>Return Type: String</p>
* @author fhb
* @date 2017年1月13日 下午1:42:25
 */
   public static String sChangeBytes(String string) throws UnsupportedEncodingException {
//	 return new String(string.getBytes("ISO-8859-1"), "UTF-8");
   return string;
   }
   
   /**
    * 获得uuid
   * <p>Method:getUUID </p>
   * <p>Description: </p>
   * <p>Return Type: String</p>
   * @author fhb
   * @date 2017年1月13日 下午1:41:45
    */
	public static String getUUID() {

		UUID uuid = UUID.randomUUID();
		// 得到对象产生的ID
		String a = uuid.toString();
		a = a.toUpperCase();
		// 替换 -
		a = a.replaceAll("-", "");
		return a;
	}
	
	public static List<String> createRandomList(List<String> list, int n) {  
	       
	        Map<Integer,String> map = new HashMap<Integer,String>();  
	        List<String> listNew = new ArrayList<String>();  
	        if(list.size()<=n){  
	            return list;  
	        }else{  
	            while(map.size()<n){  
	                int random = (int) (Math.random() * list.size());  
	                if (!map.containsKey(random)) {  
	                    map.put(random, "");  
	               //     System.out.println(random+"==========="+list.get(random));  
	                    listNew.add(list.get(random));  
	                }  
	            }  
	            return listNew;  
	        }  
	    }  

	
	public static String  handleReqAndSendReq(String apiurl,String reqjsonStr){
		String responseStr = null;
		if(StringUtils.isNotBlank(reqjsonStr)){
			Map<String, Object> map = JSON.parseObject(reqjsonStr);
			// 1.请求参数中所有1级key按照a-z的顺序排序,首字母相同看下一位，以此类推，拼接后的key,value字符串可参考ECOM
			String content = RSAUtil.getSignContent(map);
			// 2.将拼接后的字符串用MD5签名,生成MD5值
			String md5str = MD5.stringToMD5(content);
			// 3.用客户端私钥对MD5值进行RSA签名，生成密文
			try {
				String sign = RSAUtil.sign(md5str.getBytes(), Consts.app_privateKey);
				// 4.将密文拼接在请求参数中,key暂定为sign
				map.put("sign", sign);
				
				//腾讯云api  加header里字段
				Calendar cd = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
				sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
				String timeStr = sdf.format(cd.getTime());
				String sig = SignAndSend.sign(Consts.secretKey, timeStr);
				String authen = "hmac id=\"" + Consts.secretId + "\", algorithm=\"hmac-sha1\", headers=\"date source\", signature=\"" + sig + "\"";
				//System.out.println(authen);
				Headers headers = new Headers.Builder().add("Date", timeStr).add("Source", "source").add("Authorization", authen).build();
				
				Response response = OkHttpClientUtil.getInstance().okHttpPost(Consts.ServiceUrl+apiurl, JSON.toJSONString(map),headers);
				if (response.isSuccessful() && response.code() == 200) {
					responseStr = response.body().string();
				} else {
					responseStr = response.body().string();
					System.out.println("error:"+responseStr);
				}
			} catch (Exception e) {
				// 签名失败
				e.printStackTrace();
			}
		}
		
		return responseStr;
	}
	
	public static String  handleResp(String responseStr){
		String resultrespStr=null;
		if(StringUtils.isNotBlank(responseStr)) {
			Map<String, Object> responseMap = JSON.parseObject(responseStr);
			String sign=(String) responseMap.get("sign");
			if(StringUtils.isNotBlank(sign)) {
				try {
					// 1 移除sign_
					responseMap.remove("sign");
					// 2.请求参数中所有1级key按照a-z的顺序排序,首字母相同看下一位，以此类推，拼接后的key,value字符串可参考ECOM
					String waitVerifyStr = RSAUtil.getSignContent(responseMap);
					// 3.将拼接后的字符串用MD5签名,生成MD5值
					String md5Str=MD5.stringToMD5(waitVerifyStr);
					// 4.用服务器公钥对MD5和sign_进行RSA签名校验
					boolean flag=RSAUtil.verify(md5Str.getBytes("utf-8"), Consts.server_publicKey, sign);
					if(!flag) {
						//客户端不予渲染
						System.out.println("未信任的服务器");
					}else {
						resultrespStr=responseStr;
					}
				} catch (Exception e) {
					System.out.println("未信任的服务器");
				}
				
			}else {
				//客户端不予渲染
				System.out.println("未信任的服务器");
			}
		}
		
		return resultrespStr;
		
	}
	
	public static void main(String[] args) {
		
		//原请求数据
		String	reqjsonStr = "{\"userid\":\"751119F86DF74016942241D4C1917074\",\"requestid\":\"1223354556\",\"ctype\":\"1\",\"cversion\":\"3.2.1.18100802\",\"token\":\"5f27a014bbb6771de76e8c3c8e70d435cc\",\"targetid\":\"751119F86DF74016942241D4C1917074\"}";
		String handleReqAndSendReq = MbUtil.handleReqAndSendReq("/user/getuserprofile", reqjsonStr);
		System.out.println(handleReqAndSendReq);
		String handleResp = MbUtil.handleResp(handleReqAndSendReq);
		System.out.println(handleResp);
		
	}
	
}
