package com.motoband.boxmanage;

public class Boxtype {
	private int typeid;
	private String description;
	private String imgurl;
	private String userid;
	private int orderindex;
	private int state;

	
	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getOrderindex() {
		return orderindex;
	}

	public void setOrderindex(int orderindex) {
		this.orderindex = orderindex;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
