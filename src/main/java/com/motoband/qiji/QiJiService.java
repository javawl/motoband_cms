package com.motoband.qiji;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.motoband.businessmanage.LBSManager;
import com.motoband.util.BeanUtils;
import com.motoband.util.OkHttpClientUtil;

import okhttp3.Response;

@Component
public class QiJiService {
	private static final String tableid = "5c6531607bbf195c07433a0a";
	private static final String key = "15d03f15942354bc5cd5a81f493329fe";
	private static Logger logger = Logger.getLogger(QiJiService.class);

	@Autowired
	private QiJiMapper qijimapper;

	public ArrayList<QiJiInfoModel> getList(int start, int limit, int order, String orderConditions, String province, String city) {
		return qijimapper.getList(start, limit, order, orderConditions,province,city);

	}

	public int getCount(String province, String city) {
		return qijimapper.getCount(province,city);
	}

	public QiJiInfoModel getQijiModelById(String sid) {
		return qijimapper.getQijiModelById(sid);
	}

	public boolean insertOrUpdateQijiModel(QiJiInfoModel model) {
		boolean flag = qijimapper.insertOrUpdateQijiModel(model) > 0;
		if (flag) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					StringBuilder url = new StringBuilder(LBSManager.LBS_SEARCHDATACITY_URL).append("?key=").append(key)
							.append("&tableid=").append(tableid).append("&city=").append("全国")
							.append("&keywords=").append(model.sid);
					String str = null;
					boolean flag = true;
					try {
						str = IOUtils.toString(OkHttpClientUtil.getInstance().okHttpGet(url.toString()));
						JSONObject jsonObject = JSON.parseObject(str);
						if (jsonObject.getString("status").equals("1")) {
							JSONArray datas = jsonObject.getJSONArray("datas");
							Map<String, Object> linkedHashMap = Maps.newLinkedHashMap();
							linkedHashMap.put("lid", model.lid);
							linkedHashMap.put("sid", model.sid);
							linkedHashMap.put("longitude", model.longitude);
							linkedHashMap.put("latitude", model.latitude);
							linkedHashMap.put("province", model.province);
							linkedHashMap.put("city", model.city);
							linkedHashMap.put("state", model.lid);
							linkedHashMap.put("_name", model._name);
							if (datas.size() == 0) {
								flag = true;
							} else {
								linkedHashMap.put("_id", datas.getJSONObject(0).getString("_id"));
//								String _name = datas.getJSONObject(0).getString("_name");
//								if (!_name.equals(linkedHashMap.get("_name"))) {
//									flag = true;
//								}
//								if (!model.province.equals(linkedHashMap.get("province"))) {
//									flag = true;
//								}
//								if (!model.city.equals(linkedHashMap.get("city"))) {
//									flag = true;
//								}
							}
							if (flag) {
								Map<String, String> map = Maps.newHashMap();
								map.put("key", key);
								map.put("tableid", tableid);
								linkedHashMap.put("_location",
										linkedHashMap.get("longitude") + "," + linkedHashMap.get("latitude"));
								map.put("data", JSON.toJSONString(linkedHashMap));
								Response res = null;
								try {
									if (linkedHashMap.get("_id") != null) {
										res = OkHttpClientUtil.getInstance().okHttpPost(LBSManager.LBS_EDITDATA_URL,
												map);
									} else {
										res = OkHttpClientUtil.getInstance().okHttpPost(LBSManager.LBS_ADDDATA_URL,
												map);
									}
									if (res.isSuccessful()) {
										logger.info(IOUtils.toString(res.body().bytes()));
									}
								} catch (Exception e) {
									e.printStackTrace();
									logger.error(ExceptionUtils.getStackTrace(e));
								} finally {
									res.close();
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}).start();
		}

		return flag;
	}

	public boolean delopenqiji(String sid) {
		boolean flag = qijimapper.delopenqiji(sid) > 0;
		if (flag) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					StringBuilder url = new StringBuilder(LBSManager.LBS_SEARCHDATACITY_URL).append("?key=").append(key)
							.append("&tableid=").append(tableid).append("&city=").append("全国")
							.append("&keywords=").append(sid);
					String str = null;
					boolean flag = true;
					try {
						str = IOUtils.toString(OkHttpClientUtil.getInstance().okHttpGet(url.toString()));
						JSONObject jsonObject = JSON.parseObject(str);
						if (jsonObject.getString("status").equals("1")) {
							JSONArray datas = jsonObject.getJSONArray("datas");
							if (datas.size() == 0) {
								flag = false;
							}
							if (flag) {
								Map<String, String> map = Maps.newHashMap();
								map.put("key", key);
								map.put("tableid", tableid);
								map.put("ids", datas.getJSONObject(0).getString("_id"));
								Response res = null;
								try {
										res = OkHttpClientUtil.getInstance().okHttpPost(LBSManager.LBS_DELETE_URL,
												map);
									if (res.isSuccessful()) {
										logger.info(IOUtils.toString(res.body().bytes()));
									}
								} catch (Exception e) {
									e.printStackTrace();
									logger.error(ExceptionUtils.getStackTrace(e));
								} finally {
									res.close();
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}).start();
		}
		return flag;
	}

	public List<String> getProvinceList() {
		return qijimapper.getProvinceList();
	}

	public List<String> getCityBypProvince(String province) {
		return qijimapper.getCityBypProvince(province);
	}



	
}
