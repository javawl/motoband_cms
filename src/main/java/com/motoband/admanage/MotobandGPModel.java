package com.motoband.admanage;

public class MotobandGPModel {
private int gpid;
private String title;
private String subtitle;
private String content;
private String picurl;
private String starttimeString;
private String endtimeString;
private long endtime;
private long starttime;
private long mileage;
private int lap;
private int achid;
private int type;
private int status;

public int getAchid() {
	return achid;
}
public void setAchid(int achid) {
	this.achid = achid;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public int getGpid() {
	return gpid;
}
public void setGpid(int gpid) {
	this.gpid = gpid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getSubtitle() {
	return subtitle;
}
public void setSubtitle(String subtitle) {
	this.subtitle = subtitle;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getPicurl() {
	return picurl;
}
public void setPicurl(String picurl) {
	this.picurl = picurl;
}
public String getStarttimeString() {
	return starttimeString;
}
public void setStarttimeString(String starttimeString) {
	this.starttimeString = starttimeString;
}
public String getEndtimeString() {
	return endtimeString;
}
public void setEndtimeString(String endtimeString) {
	this.endtimeString = endtimeString;
}
public long getEndtime() {
	return endtime;
}
public void setEndtime(long endtime) {
	this.endtime = endtime;
}
public long getStarttime() {
	return starttime;
}
public void setStarttime(long starttime) {
	this.starttime = starttime;
}
public long getMileage() {
	return mileage;
}
public void setMileage(long mileage) {
	this.mileage = mileage;
}
public int getLap() {
	return lap;
}
public void setLap(int lap) {
	this.lap = lap;
}

}
