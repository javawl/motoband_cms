package com.motoband.businessmanage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class BusinessUserModel {
public long buid;
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
public int usercount;
public int scorecount;
public int state;//状态（0 等待审核  1 审核通过  2 不通过）
public String reason;
public String headurl;
public String province;
public String city;
public String userid;
public String password;
public String mobileno;
public long addtime;
public String officestarttime;
public String officeendtime;
public String des;
public String desdetail;
public String doorpics;
public String shoppics;
public List<BusinessActivityModel> businessactivitylist;
public String businessactivityliststr;
public double latitude;//纬度
public double longitude;//经度
public List<BusinessServiceModel>   businessservicelist;
public String businessserviceliststr;
public String aid;
public int isapprove;
public String channel;
public String businesscode;
public String lbsid;
public String token;
public String approvepic;
public String busikeyword;// 商家关键词逗号分隔



public String kfuseridlist;//多客服用户id  逗号隔开

//扩展
public String serviceids;
public List<UsecarmaingroupModel> usecarmaingroupModels;
public String mainbrandNames;
public String recommendbrandNames;


public String getMainbrandNames() {
	return mainbrandNames;
}

public void setMainbrandNames(String mainbrandNames) {
	this.mainbrandNames = mainbrandNames;
}

public String getRecommendbrandNames() {
	return recommendbrandNames;
}

public void setRecommendbrandNames(String recommendbrandNames) {
	this.recommendbrandNames = recommendbrandNames;
}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public String getApprovepic() {
	return approvepic;
}

public void setApprovepic(String approvepic) {
	this.approvepic = approvepic;
}

public List<UsecarmaingroupModel> getUsecarmaingroupModels() {
	return usecarmaingroupModels;
}

public void setUsecarmaingroupModels(
		List<UsecarmaingroupModel> usecarmaingroupModels) {
	this.usecarmaingroupModels = usecarmaingroupModels;
}

public String getLbsid() {
	return lbsid;
}

public void setLbsid(String lbsid) {
	this.lbsid = lbsid;
}

public String getServiceids() {
	return serviceids;
}

public void setServiceids(String serviceids) {
	this.serviceids = serviceids;
}

public long getBuid() {
	return buid;
}

public void setBuid(long buid) {
	this.buid = buid;
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

public int getUsercount() {
	return usercount;
}

public void setUsercount(int usercount) {
	this.usercount = usercount;
}

public int getScorecount() {
	return scorecount;
}

public void setScorecount(int scorecount) {
	this.scorecount = scorecount;
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

public String getHeadurl() {
	return headurl;
}

public void setHeadurl(String headurl) {
	this.headurl = headurl;
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

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getMobileno() {
	return mobileno;
}

public void setMobileno(String mobileno) {
	this.mobileno = mobileno;
}

public long getAddtime() {
	return addtime;
}

public void setAddtime(long addtime) {
	this.addtime = addtime;
}

public String getOfficestarttime() {
	return officestarttime;
}

public void setOfficestarttime(String officestarttime) {
	this.officestarttime = officestarttime;
}

public String getOfficeendtime() {
	return officeendtime;
}

public void setOfficeendtime(String officeendtime) {
	this.officeendtime = officeendtime;
}

public String getDes() {
	return des;
}

public void setDes(String des) {
	this.des = des;
}

public String getDesdetail() {
	return desdetail;
}

public void setDesdetail(String desdetail) {
	this.desdetail = desdetail;
}

public String getDoorpics() {
	return doorpics;
}

public void setDoorpics(String doorpics) {
	this.doorpics = doorpics;
}

public String getShoppics() {
	return shoppics;
}

public void setShoppics(String shoppics) {
	this.shoppics = shoppics;
}

public List<BusinessActivityModel> getBusinessactivitylist() {
	return businessactivitylist;
}

public void setBusinessactivitylist(
		List<BusinessActivityModel> businessactivitylist) {
	this.businessactivitylist = businessactivitylist;
}

public String getBusinessactivityliststr() {
	return businessactivityliststr;
}

public void setBusinessactivityliststr(String businessactivityliststr) {
	this.businessactivityliststr = businessactivityliststr;
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

public String getAid() {
	return aid;
}

public void setAid(String aid) {
	this.aid = aid;
}

public int getIsapprove() {
	return isapprove;
}

public void setIsapprove(int isapprove) {
	this.isapprove = isapprove;
}

public String getChannel() {
	return channel;
}

public void setChannel(String channel) {
	this.channel = channel;
}

public String getBusinesscode() {
	return businesscode;
}

public void setBusinesscode(String businesscode) {
	this.businesscode = businesscode;
}
public String getKfuseridlist() {
	return kfuseridlist;
}

public void setKfuseridlist(String kfuseridlist) {
	this.kfuseridlist = kfuseridlist;
}
public static Map<String, String> convertToMap(BusinessUserModel model) {
	Map<String, String> map = new HashMap<String, String>();

	map.put("buid", String.valueOf(model.buid));
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
	map.put("usercount", String.valueOf(model.usercount));
	map.put("scorecount", String.valueOf(model.scorecount));
	map.put("state", String.valueOf(model.state));
	map.put("reason", model.reason==null? "" : model.reason);
	map.put("headurl", model.headurl==null? "" : model.headurl);
	map.put("province", model.province==null? "" : model.province);
	map.put("city", model.city==null? "" : model.city);
	map.put("userid", model.userid==null? "" : model.userid);
	map.put("password", model.password==null? "" : model.password);
	map.put("mobileno", model.mobileno==null? "" : model.mobileno);
	map.put("addtime", String.valueOf(model.addtime));
	map.put("officestarttime", model.officestarttime==null? "" :model.officestarttime);
	map.put("officeendtime", model.officeendtime==null? "" :model.officeendtime);
	map.put("des", model.des==null? "" : model.des);
	map.put("desdetail", model.desdetail==null? "" : model.desdetail);
	map.put("doorpics", model.doorpics==null? "" : model.doorpics);
	map.put("shoppics", model.shoppics==null? "" : model.shoppics);
	
	map.put("aid", model.aid==null? "" : model.aid);
	map.put("latitude", String.valueOf(model.latitude));
	map.put("longitude", String.valueOf(model.longitude));
	map.put("isapprove", String.valueOf(model.isapprove));
	map.put("channel", model.channel==null? "" : model.channel);
	map.put("businesscode", model.businesscode==null? "" : model.businesscode);
	map.put("lbsid", model.lbsid==null? "" : model.lbsid);
	map.put("token", model.token==null? "" : model.token);
	map.put("approvepic", model.approvepic==null? "" : model.approvepic);
	map.put("busikeyword", model.busikeyword==null? "" : model.busikeyword);
	map.put("kfuseridlist", model.kfuseridlist==null? "" : model.kfuseridlist);
	if (model.businessservicelist != null) {
		String temp = JSON.toJSONString(model.businessservicelist);
		map.put("businessservicelist", temp);
		model.businessserviceliststr = temp;
	}
	if (model.businessactivitylist != null) {
		String temp = JSON.toJSONString(model.businessactivitylist);
		map.put("businessactivitylist", temp);
		model.businessactivityliststr = temp;
	}
	return map;
}

public static BusinessUserModel convertToModel(Map<String, String> map) {
	if (map == null) {
		return null;
	}

	BusinessUserModel model = new BusinessUserModel();

	if (map.containsKey("buid")) {
		if (!map.get("buid").isEmpty())
			model.buid = Long.parseLong(map.get("buid"));
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
	if (map.containsKey("usercount")) {
		if (!map.get("usercount").isEmpty())
			model.usercount = Integer.parseInt(map.get("usercount"));
	}
	if (map.containsKey("scorecount")) {
		if (!map.get("scorecount").isEmpty())
			model.scorecount = Integer.parseInt(map.get("scorecount"));
	}
	if (map.containsKey("state")) {
		if (!map.get("state").isEmpty())
			model.state = Integer.parseInt(map.get("state"));
	}
	
	if (map.containsKey("reason")) {
		if (!map.get("reason").isEmpty())
			model.reason = map.get("reason");
	}
	if (map.containsKey("headurl")) {
		if (!map.get("headurl").isEmpty())
			model.headurl = map.get("headurl");
	}
	if (map.containsKey("province")) {
		if (!map.get("province").isEmpty())
			model.province = map.get("province");
	}
	if (map.containsKey("city")) {
		if (!map.get("city").isEmpty())
			model.city = map.get("city");
	}
	if (map.containsKey("userid")) {
		if (!map.get("userid").isEmpty())
			model.userid = map.get("userid");
	}
	if (map.containsKey("password")) {
		if (!map.get("password").isEmpty())
			model.password = map.get("password");
	}
	if (map.containsKey("mobileno")) {
		if (!map.get("mobileno").isEmpty())
			model.mobileno = map.get("mobileno");
	}
	if (map.containsKey("addtime")) {
		if (!map.get("addtime").isEmpty())
			model.addtime = Long.valueOf(map.get("addtime"));
	}
	if (map.containsKey("officestarttime")) {
		if (!map.get("officestarttime").isEmpty())
			model.officestarttime = map.get("officestarttime");
	}
	if (map.containsKey("officeendtime")) {
		if (!map.get("officeendtime").isEmpty())
			model.officeendtime = map.get("officeendtime");
	}
	if (map.containsKey("des")) {
		if (!map.get("des").isEmpty())
			model.des = map.get("des");
	}
	if (map.containsKey("desdetail")) {
		if (!map.get("desdetail").isEmpty())
			model.desdetail = map.get("desdetail");
	}
	if (map.containsKey("doorpics")) {
		if (!map.get("doorpics").isEmpty())
			model.doorpics = map.get("doorpics");
	}
	if (map.containsKey("shoppics")) {
		if (!map.get("shoppics").isEmpty())
			model.shoppics = map.get("shoppics");
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
	if (map.containsKey("aid")) {
		if (!map.get("aid").isEmpty())
			model.aid = map.get("aid");
	}
	
	if (map.containsKey("isapprove")) {
		if (!map.get("isapprove").isEmpty())
			model.isapprove = Integer.valueOf(map.get("isapprove"));
	}
	if (map.containsKey("channel")) {
		if (!map.get("channel").isEmpty())
			model.channel = map.get("channel");
	}
	if (map.containsKey("businesscode")) {
		if (!map.get("businesscode").isEmpty())
			model.businesscode = map.get("businesscode");
	}
	if (map.containsKey("lbsid")) {
		if (!map.get("lbsid").isEmpty())
			model.lbsid = map.get("lbsid");
	}
	if (map.containsKey("token")) {
		if (!map.get("token").isEmpty())
			model.token = map.get("token");
	}
	if (map.containsKey("approvepic")) {
		if (!map.get("approvepic").isEmpty())
			model.approvepic = map.get("approvepic");
	}
	if (map.containsKey("businessactivitylist")) {
		if (!map.get("businessactivitylist").isEmpty()) {
			model.businessactivitylist = JSON.parseArray(map.get("businessactivitylist"), BusinessActivityModel.class);
		}
	}
	if (map.containsKey("busikeyword")) {
		if (!map.get("busikeyword").isEmpty()) {
			model.busikeyword = map.get("busikeyword");
		}
	}
	if (map.containsKey("kfuseridlist")) {
		if (!map.get("kfuseridlist").isEmpty()) {
			model.kfuseridlist = map.get("kfuseridlist");
		}
	}
	return model;
}

public String getBusikeyword() {
	return busikeyword;
}

public void setBusikeyword(String busikeyword) {
	this.busikeyword = busikeyword;
}

@Override
public String toString() {
	return "BusinessUserModel [buid=" + buid + ", name=" + name + ", contactname=" + contactname + ", contactphone=" + contactphone + ", type=" + type + ", mainbrand=" + mainbrand + ", address=" + address + ", businessarea=" + businessarea + ", staffcount=" + staffcount + ", age=" + age + ", recommendbrand=" + recommendbrand + ", license=" + license + ", other=" + other + ", usercount="
			+ usercount + ", scorecount=" + scorecount + ", state=" + state + ", reason=" + reason + ", headurl=" + headurl + ", province=" + province + ", city=" + city + ", userid=" + userid + ", password=" + password + ", mobileno=" + mobileno + ", addtime=" + addtime + ", officestarttime=" + officestarttime + ", officeendtime=" + officeendtime + ", des=" + des + ", desdetail=" + desdetail
			+ ", doorpics=" + doorpics + ", shoppics=" + shoppics + ", businessactivitylist=" + businessactivitylist + ", businessactivityliststr=" + businessactivityliststr + ", latitude=" + latitude + ", longitude=" + longitude + ", businessservicelist=" + businessservicelist + ", businessserviceliststr=" + businessserviceliststr + ", aid=" + aid + ", isapprove=" + isapprove + ", channel="
			+ channel + ", businesscode=" + businesscode + ", lbsid=" + lbsid + ", token=" + token + ", approvepic=" + approvepic + ", busikeyword=" + busikeyword + ", kfuseridlist=" + kfuseridlist + ", serviceids=" + serviceids + ", usecarmaingroupModels=" + usecarmaingroupModels + ", mainbrandNames=" + mainbrandNames + ", recommendbrandNames=" + recommendbrandNames + "]";
}





}
