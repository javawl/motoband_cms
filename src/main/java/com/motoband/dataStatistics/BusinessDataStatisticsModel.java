package com.motoband.dataStatistics;

public class BusinessDataStatisticsModel {
private int id;	
private int usecarmainBusersCount;
private int allBusersListCount;
private int usecarmainActivityBusersCount;
private int onlineBusersCount;
private int onphoneBusersCount;
private int commentBusersCount;
private String buserid;
private long timestamp;
private String timeformat;
//扩展
private int type;
private int count;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public long getTimestamp() {
	return timestamp;
}
public void setTimestamp(long timestamp) {
	this.timestamp = timestamp;
}
public String getTimeformat() {
	return timeformat;
}
public void setTimeformat(String timeformat) {
	this.timeformat = timeformat;
}
public String getBuserid() {
	return buserid;
}
public void setBuserid(String buserid) {
	this.buserid = buserid;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public int getUsecarmainBusersCount() {
	return usecarmainBusersCount;
}
public void setUsecarmainBusersCount(int usecarmainBusersCount) {
	this.usecarmainBusersCount = usecarmainBusersCount;
}
public int getAllBusersListCount() {
	return allBusersListCount;
}
public void setAllBusersListCount(int allBusersListCount) {
	this.allBusersListCount = allBusersListCount;
}
public int getUsecarmainActivityBusersCount() {
	return usecarmainActivityBusersCount;
}
public void setUsecarmainActivityBusersCount(int usecarmainActivityBusersCount) {
	this.usecarmainActivityBusersCount = usecarmainActivityBusersCount;
}
public int getOnlineBusersCount() {
	return onlineBusersCount;
}
public void setOnlineBusersCount(int onlineBusersCount) {
	this.onlineBusersCount = onlineBusersCount;
}
public int getOnphoneBusersCount() {
	return onphoneBusersCount;
}
public void setOnphoneBusersCount(int onphoneBusersCount) {
	this.onphoneBusersCount = onphoneBusersCount;
}
public int getCommentBusersCount() {
	return commentBusersCount;
}
public void setCommentBusersCount(int commentBusersCount) {
	this.commentBusersCount = commentBusersCount;
}

}
