package com.motoband.businessmanage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class BusinessApplyModel {
public String aid;
public String name;
public String contactname;
public String contactphone;
public int type;//商家类型
public String mainbrand;
public String address;
public int businessarea;
public int staffcount;
public int age;
public String recommendbrand;
public String license;
public String other;
public int state;//状态（0 等待审核  1 审核通过 2 不通过）
public String reason;
public String province;
public String city;
public long addtime;
public double latitude;//纬度
public double longitude;//经度


public String addtimeString;
public String serviceids;
public String recommendbrandNames;
public String mainbrandNames;

public String getRecommendbrandNames() {
	return recommendbrandNames;
}

public void setRecommendbrandNames(String recommendbrandNames) {
	this.recommendbrandNames = recommendbrandNames;
}

public String getMainbrandNames() {
	return mainbrandNames;
}

public void setMainbrandNames(String mainbrandNames) {
	this.mainbrandNames = mainbrandNames;
}

public String getServiceids() {
	return serviceids;
}

public void setServiceids(String serviceids) {
	this.serviceids = serviceids;
}

public String getAddtimeString() {
	return addtimeString;
}

public void setAddtimeString(String addtimeString) {
	this.addtimeString = addtimeString;
}

public List<BusinessServiceModel>   businessservicelist;
public String businessserviceliststr;


public String getAid() {
	return aid;
}

public void setAid(String aid) {
	this.aid = aid;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getContactname() {
	return contactname;
}

public void setContactname(String contactname) {
	this.contactname = contactname;
}

public String getContactphone() {
	return contactphone;
}

public void setContactphone(String contactphone) {
	this.contactphone = contactphone;
}

public int getType() {
	return type;
}

public void setType(int type) {
	this.type = type;
}

public String getMainbrand() {
	return mainbrand;
}

public void setMainbrand(String mainbrand) {
	this.mainbrand = mainbrand;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public int getBusinessarea() {
	return businessarea;
}

public void setBusinessarea(int businessarea) {
	this.businessarea = businessarea;
}

public int getStaffcount() {
	return staffcount;
}

public void setStaffcount(int staffcount) {
	this.staffcount = staffcount;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getRecommendbrand() {
	return recommendbrand;
}

public void setRecommendbrand(String recommendbrand) {
	this.recommendbrand = recommendbrand;
}

public String getLicense() {
	return license;
}

public void setLicense(String license) {
	this.license = license;
}

public String getOther() {
	return other;
}

public void setOther(String other) {
	this.other = other;
}

public int getState() {
	return state;
}

public void setState(int state) {
	this.state = state;
}

public String getReason() {
	return reason;
}

public void setReason(String reason) {
	this.reason = reason;
}

public String getProvince() {
	return province;
}

public void setProvince(String province) {
	this.province = province;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public long getAddtime() {
	return addtime;
}

public void setAddtime(long addtime) {
	this.addtime = addtime;
}

public double getLatitude() {
	return latitude;
}

public void setLatitude(double latitude) {
	this.latitude = latitude;
}

public double getLongitude() {
	return longitude;
}

public void setLongitude(double longitude) {
	this.longitude = longitude;
}

public List<BusinessServiceModel> getBusinessservicelist() {
	return businessservicelist;
}

public void setBusinessservicelist(
		List<BusinessServiceModel> businessservicelist) {
	this.businessservicelist = businessservicelist;
}

public String getBusinessserviceliststr() {
	return businessserviceliststr;
}

public void setBusinessserviceliststr(String businessserviceliststr) {
	this.businessserviceliststr = businessserviceliststr;
}

public static Map<String, String> convertToMap(BusinessApplyModel model) {
	Map<String, String> map = new HashMap<String, String>();

	map.put("aid", model.aid==null?"":model.aid);
	map.put("name", model.name==null? "" : model.name);
	map.put("contactname", model.contactname==null? "" : model.contactname);
	map.put("contactphone", model.contactphone==null? "" : model.contactphone);
	map.put("type", String.valueOf(model.type));
	map.put("mainbrand", model.mainbrand==null? "" : model.mainbrand);
	map.put("address", model.address==null? "" : model.address);
	map.put("businessarea", String.valueOf(model.businessarea));
	map.put("staffcount", String.valueOf(model.staffcount));
	map.put("age", String.valueOf(model.age));

	map.put("recommendbrand", model.recommendbrand==null? "" : model.recommendbrand);
	map.put("license", model.license==null? "" : model.license);
	map.put("other", model.other==null? "" : model.other);
	map.put("state", String.valueOf(model.state));
	map.put("reason", model.reason==null? "" : model.reason);
	map.put("province", model.province==null? "" : model.province);
	map.put("city", model.city==null? "" : model.city);
	map.put("addtime", String.valueOf(model.addtime));
	map.put("latitude", String.valueOf(model.latitude));
	map.put("longitude", String.valueOf(model.longitude));

	if (model.businessservicelist != null) {
		String temp = JSON.toJSONString(model.businessservicelist);
		map.put("businessservicelist", temp);
		model.businessserviceliststr = temp;
	}
	return map;
}

public static BusinessApplyModel convertToModel(Map<String, String> map) {
	if (map == null) {
		return null;
	}

	BusinessApplyModel model = new BusinessApplyModel();

	if (map.containsKey("aid")) {
		if (!map.get("aid").isEmpty())
			model.aid = map.get("aid");
	}
	if (map.containsKey("name")) {
		if (!map.get("name").isEmpty())
			model.name = map.get("name");
	}
	if (map.containsKey("contactname")) {
		if (!map.get("contactname").isEmpty())
			model.contactname = map.get("contactname");
	}
	if (map.containsKey("contactphone")) {
		if (!map.get("contactphone").isEmpty())
			model.contactphone = map.get("contactphone");
	}
	if (map.containsKey("type")) {
		if (!map.get("type").isEmpty())
			model.type = Integer.parseInt(map.get("type"));
	}
	if (map.containsKey("mainbrand")) {
		if (!map.get("mainbrand").isEmpty())
			model.mainbrand = map.get("mainbrand");
	}
	if (map.containsKey("address")) {
		if (!map.get("address").isEmpty())
			model.address = map.get("address");
	}
	if (map.containsKey("businessarea")) {
		if (!map.get("businessarea").isEmpty())
			model.businessarea = Integer.parseInt(map.get("businessarea"));
	}
	if (map.containsKey("staffcount")) {
		if (!map.get("staffcount").isEmpty())
			model.staffcount = Integer.parseInt(map.get("staffcount"));
	}
	if (map.containsKey("age")) {
		if (!map.get("age").isEmpty())
			model.age = Integer.parseInt(map.get("age"));
	}

	if (map.containsKey("recommendbrand")) {
		if (!map.get("recommendbrand").isEmpty())
			model.recommendbrand = map.get("recommendbrand");
	}
	if (map.containsKey("license")) {
		if (!map.get("license").isEmpty())
			model.license = map.get("license");
	}
	if (map.containsKey("other")) {
		if (!map.get("other").isEmpty())
			model.other = map.get("other");
	}

	if (map.containsKey("state")) {
		if (!map.get("state").isEmpty())
			model.state = Integer.parseInt(map.get("state"));
	}
	
	if (map.containsKey("reason")) {
		if (!map.get("reason").isEmpty())
			model.reason = map.get("reason");
	}

	if (map.containsKey("province")) {
		if (!map.get("province").isEmpty())
			model.province = map.get("province");
	}
	if (map.containsKey("city")) {
		if (!map.get("city").isEmpty())
			model.city = map.get("city");
	}


	if (map.containsKey("addtime")) {
		if (!map.get("addtime").isEmpty())
			model.addtime = Long.valueOf(map.get("addtime"));
	}

	if (map.containsKey("latitude")) {
		if (!map.get("latitude").isEmpty())
			model.latitude = Double.valueOf(map.get("latitude"));
	}
	if (map.containsKey("longitude")) {
		if (!map.get("longitude").isEmpty())
			model.longitude = Double.valueOf(map.get("longitude"));
	}
	if (map.containsKey("businessservicelist")) {
		if (!map.get("businessservicelist").isEmpty()) {
			model.businessservicelist = JSON.parseArray(map.get("businessservicelist"), BusinessServiceModel.class);
		}
	}
	return model;
}


}
