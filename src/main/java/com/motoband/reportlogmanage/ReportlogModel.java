package com.motoband.reportlogmanage;

public class ReportlogModel {
 private Long id;
 private String userid;
 private String targetid;
 private String newsid;
 private Integer informtype;
 private Long informtime;
 
 private String informtimeString;
 
public String getInformtimeString() {
	return informtimeString;
}
public void setInformtimeString(String informtimeString) {
	this.informtimeString = informtimeString;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getTargetid() {
	return targetid;
}
public void setTargetid(String targetid) {
	this.targetid = targetid;
}
public String getNewsid() {
	return newsid;
}
public void setNewsid(String newsid) {
	this.newsid = newsid;
}
public Integer getInformtype() {
	return informtype;
}
public void setInformtype(Integer informtype) {
	this.informtype = informtype;
}
public Long getInformtime() {
	return informtime;
}
public void setInformtime(Long informtime) {
	this.informtime = informtime;
}
 
}
