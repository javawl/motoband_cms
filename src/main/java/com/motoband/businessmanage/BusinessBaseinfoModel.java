package com.motoband.businessmanage;

public class BusinessBaseinfoModel {
	public String name;
	public String contactphone;
	public String address;
	public String officestarttime;
	public String officeendtime;
	public String headurl;
	public String province;
	public String city;
	public String des;
	public String desdetail;
	public double latitude;//纬度
	public double longitude;//经度
	public String businesscode;
	
	public String getBusinesscode() {
		return businesscode;
	}
	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactphone() {
		return contactphone;
	}
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	
	


//	public static Map<String, String> convertToMap(BusinessBaseinfoModel model) {
//		Map<String, String> map = new HashMap<String, String>();
//
//		map.put("name", model.name==null? "" : model.name);
//		map.put("contactphone", model.contactphone==null? "" : model.contactphone);
//		map.put("address", model.address==null? "" : model.address);
//		map.put("headurl", model.headurl==null? "" : model.headurl);
//		map.put("province", model.province==null? "" : model.province);
//		map.put("city", model.city==null? "" : model.city);
//		map.put("officestarttime", model.officestarttime==null? "" :model.officestarttime);
//		map.put("officeendtime", model.officeendtime==null? "" :model.officeendtime);
//		map.put("des", model.des==null? "" : model.des);
//		map.put("desdetail", model.desdetail==null? "" : model.desdetail);
//		map.put("latitude", String.valueOf(model.latitude));
//		map.put("longitude", String.valueOf(model.longitude));
//		return map;
//	}
	
}
