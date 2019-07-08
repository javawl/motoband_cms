package com.motoband.payordermanage;

public class PayorderlogModel {
private int id;
private String orderid;
private String infotype;
private String description;
private long time;
private String timeString;

public String getTimeString() {
	return timeString;
}
public void setTimeString(String timeString) {
	this.timeString = timeString;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public String getInfotype() {
	return infotype;
}
public void setInfotype(String infotype) {
	this.infotype = infotype;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public long getTime() {
	return time;
}
public void setTime(long time) {
	this.time = time;
}

}
