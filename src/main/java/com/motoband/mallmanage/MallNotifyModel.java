package com.motoband.mallmanage;

public class MallNotifyModel {
private Integer notifyid ;
private String name;
private Long ptime;
private String picurl;
private String mallurl;
private Integer source;

//扩展
private String ptimeString;



public String getPtimeString() {
	return ptimeString;
}
public void setPtimeString(String ptimeString) {
	this.ptimeString = ptimeString;
}
public Integer getNotifyid() {
	return notifyid;
}
public void setNotifyid(Integer notifyid) {
	this.notifyid = notifyid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Long getPtime() {
	return ptime;
}
public void setPtime(Long ptime) {
	this.ptime = ptime;
}
public String getPicurl() {
	return picurl;
}
public void setPicurl(String picurl) {
	this.picurl = picurl;
}
public String getMallurl() {
	return mallurl;
}
public void setMallurl(String mallurl) {
	this.mallurl = mallurl;
}
public Integer getSource() {
	return source;
}
public void setSource(Integer source) {
	this.source = source;
}


}
