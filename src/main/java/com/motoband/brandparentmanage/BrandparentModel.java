package com.motoband.brandparentmanage;

public class BrandparentModel {
  private int bpid;
  private String name;
  private String imgurl;
  private int ishot;
  private int orderindex;
  private int state;
  
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public int getBpid() {
	return bpid;
}
public void setBpid(int bpid) {
	this.bpid = bpid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getImgurl() {
	return imgurl;
}
public void setImgurl(String imgurl) {
	this.imgurl = imgurl;
}
public int getIshot() {
	return ishot;
}
public void setIshot(int ishot) {
	this.ishot = ishot;
}
public int getOrderindex() {
	return orderindex;
}
public void setOrderindex(int orderindex) {
	this.orderindex = orderindex;
}
  
}
