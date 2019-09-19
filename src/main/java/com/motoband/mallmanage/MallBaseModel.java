package com.motoband.mallmanage;

public class MallBaseModel {
	public String mid;//id 
	public String title;//标题
	public String subtitle;//标题
	public String picurl;//图片
	public String url;//链接地址
	public String price;//价格
	public String oldprice;//价格
	public int state;//状态（0 下架   1上架）
	public long orderindex;//排序 倒序
	public int groupid;//组id
	
	//扩展
	public String groupname;//组标题
	public String groupsubname;//组副标题
	public int grouptype;//分组所属首页类型
	
	


	public String getGroupsubname() {
		return groupsubname;
	}


	public void setGroupsubname(String groupsubname) {
		this.groupsubname = groupsubname;
	}

	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	public String getOldprice() {
		return oldprice;
	}


	public void setOldprice(String oldprice) {
		this.oldprice = oldprice;
	}


	@Override
	public String toString() {
		return "MallBaseModel [mid=" + mid + ", title=" + title + ", subtitle=" + subtitle + ", picurl=" + picurl + ", url=" + url + ", price=" + price + ", oldprice=" + oldprice + ", state=" + state + ", orderindex=" + orderindex + ", groupid=" + groupid + ", groupname=" + groupname + ", grouptype=" + grouptype + "]";
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPicurl() {
		return picurl;
	}


	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public long getOrderindex() {
		return orderindex;
	}


	public void setOrderindex(long orderindex) {
		this.orderindex = orderindex;
	}


	public int getGroupid() {
		return groupid;
	}


	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}


	public String getGroupname() {
		return groupname;
	}


	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}


	public int getGrouptype() {
		return grouptype;
	}


	public void setGrouptype(int grouptype) {
		this.grouptype = grouptype;
	}
	
	
	
	
		
}
