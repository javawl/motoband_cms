package com.motoband.admanage;

public class AdModel {
private int adid;
private String adtitle;
private int adtype;
private long starttime;
private long endtime;
private String starttimeString;
private String endtimeString;
private int state;
public int getAdid() {
	return adid;
}
public void setAdid(int adid) {
	this.adid = adid;
}
public String getAdtitle() {
	return adtitle;
}
public void setAdtitle(String adtitle) {
	this.adtitle = adtitle;
}
public int getAdtype() {
	return adtype;
}
public void setAdtype(int adtype) {
	this.adtype = adtype;
}
public long getStarttime() {
	return starttime;
}
public void setStarttime(long starttime) {
	this.starttime = starttime;
}
public long getEndtime() {
	return endtime;
}
public void setEndtime(long endtime) {
	this.endtime = endtime;
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
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}

}
