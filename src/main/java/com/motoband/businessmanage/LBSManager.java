package com.motoband.businessmanage;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.motoband.util.Consts;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LBSManager {
	private static final LBSManager lbsManager = new LBSManager();
	//日志记录
	public	static Logger logger = LoggerFactory.getLogger(LBSManager.class);
	
	private static OkHttpClient httpClient = new OkHttpClient();
	public static final MediaType MediaType_JSON = MediaType.parse("application/x-www-form-urlencoded");

	// 生产环境云图
	public static final String LBSKey = "15d03f15942354bc5cd5a81f493329fe";

	public static final String LBSTableID = Consts.MOTOBAND_LBSTableID;

	public static final String LBS_ADDDATA_URL = "http://yuntuapi.amap.com/datamanage/data/create";
	public static final String LBS_EDITDATA_URL = "http://yuntuapi.amap.com/datamanage/data/update";
	public static final String LBS_SEARCHDATACITY_URL = "http://yuntuapi.amap.com/datasearch/local";
	public static final String LBS_DELETE_URL = "https://yuntuapi.amap.com/datamanage/data/delete";
	

	private LBSManager() {

	}

	public static LBSManager getInstance() {
		return lbsManager;
	}

	// 有lbsid就更新 没有就新增
	public String synLBSDataToGD(BusinessUserModel bum) throws IOException {

		logger.info("synLBSDataToGD request data:[param businessUserModel:"+bum.toString()+"]");
		
		String lbsid = bum.lbsid;

		LBSDataModel data = new LBSDataModel();

		data._id = bum.lbsid;
		data._name = bum.name;
		String longitude = String.valueOf(bum.longitude);
		String latitude = String.valueOf(bum.latitude);
		data._location = longitude + "," + latitude;
		data.coordtype = "1";
		data._address = bum.address;
		data.buid = bum.buid;
		data.province = bum.province;
		data.city = bum.city;
		data.userid = bum.userid;
		data.state=String.valueOf(bum.state);
		data.busikeyword=bum.busikeyword;
		StringBuilder sb = new StringBuilder();

		if (bum.businessservicelist == null) {
			bum.businessservicelist = JSONObject.parseArray(bum.businessserviceliststr, BusinessServiceModel.class);
		}

		for (BusinessServiceModel serviceModel : bum.businessservicelist) {
			sb.append(serviceModel.name);
			sb.append(",");
		}

		data.businessservice = sb.toString();

		data.type = String.valueOf(bum.type);
		data.isapprove = String.valueOf(bum.isapprove);

		if (data._id == null||data._id.isEmpty()) {

			RequestBody body = new FormBody.Builder().add("key", LBSManager.LBSKey).add("tableid", LBSManager.LBSTableID).add("loctype", "1").add("data", JSON.toJSONString(data)).build();

			Request request = new Request.Builder().url(LBS_ADDDATA_URL).post(body).build();

			Response response = httpClient.newCall(request).execute();
			String result = new String(response.body().bytes(), "utf-8");
//			if (_tracer.InfoAvailable())
//				_tracer.Info(this.getClass().getSimpleName() + "  synLBSDataToGD onResponse:" + result);
			if (response.isSuccessful()) {

				JSONObject json = JSON.parseObject(result);
				if (json.get("status").toString().equals("1")) {
					lbsid = json.get("_id").toString();
				} else {
//					if (_tracer.ErrorAvailable())
//						_tracer.Error(this.getClass().getSimpleName() + "  synLBSDataToGD status error:resp result :" + result);
				}

			} else {
//				if (_tracer.ErrorAvailable())
//					_tracer.Error(this.getClass().getSimpleName() + "  synLBSDataToGD is ERROR  result :" + response.code());
			}
		} else {

			RequestBody body = new FormBody.Builder().add("key", LBSManager.LBSKey).add("tableid", LBSManager.LBSTableID).add("loctype", "1").add("data", JSON.toJSONString(data)).build();

			Request request = new Request.Builder().url(LBS_EDITDATA_URL).post(body).build();

			Response response = httpClient.newCall(request).execute();
			String result = new String(response.body().bytes(), "utf-8");
//			if (_tracer.InfoAvailable())
//				_tracer.Info(this.getClass().getSimpleName() + "  synLBSDataToGD onResponse result:" + result);
			if (response.isSuccessful()) {

				JSONObject json = JSON.parseObject(result);
				if (json.get("status").toString().equals("1")) {
					if (json.get("_id") != null)
						lbsid = (String) json.get("_id");
				} else {
//					if (_tracer.ErrorAvailable())
//						_tracer.Error(this.getClass().getSimpleName() + "  synLBSDataToGD status error:resp result :" + result);
				}

			} else {
//				if (_tracer.ErrorAvailable())
//					_tracer.Error(this.getClass().getSimpleName() + "  synLBSDataToGD is ERROR  result :" + response.code());
				throw new IOException("searchLBSInfo Unexpected code " + response);
			}
		}

		return lbsid;

	}

	
	
	
	
}
