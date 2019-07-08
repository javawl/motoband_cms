package com.motoband.admanage;

public class AdvertisingImgModel {
	public String adimgid;//广告图id
	public String adid;//广告id
	public int linktype;//1 动态  2 话题   3问答    4 有赞  5 内部链接  6 外部链接  7小程序 8 motogp 9 二手车  10 本地商家
	public String imgurl;//图片地址
	public String linkurl;//跳转链接
	public int gpid;//gpid
	public String nid;//动态id
	public String keyword;//关键字
	public String secondcarid;//二手车id
	public String miniprogramid;//小程序id
	public String buserid;//商家id
	public long orderindex;//排序
	
	
	
	public String getAdimgid() {
		return adimgid;
	}



	public void setAdimgid(String adimgid) {
		this.adimgid = adimgid;
	}



	public String getAdid() {
		return adid;
	}



	public void setAdid(String adid) {
		this.adid = adid;
	}



	public int getLinktype() {
		return linktype;
	}



	public void setLinktype(int linktype) {
		this.linktype = linktype;
	}



	public String getImgurl() {
		return imgurl;
	}



	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}



	public String getLinkurl() {
		return linkurl;
	}



	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}



	public int getGpid() {
		return gpid;
	}



	public void setGpid(int gpid) {
		this.gpid = gpid;
	}



	public String getNid() {
		return nid;
	}



	public void setNid(String nid) {
		this.nid = nid;
	}



	public String getKeyword() {
		return keyword;
	}



	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	public String getSecondcarid() {
		return secondcarid;
	}



	public void setSecondcarid(String secondcarid) {
		this.secondcarid = secondcarid;
	}



	public String getMiniprogramid() {
		return miniprogramid;
	}



	public void setMiniprogramid(String miniprogramid) {
		this.miniprogramid = miniprogramid;
	}



	public String getBuserid() {
		return buserid;
	}



	public void setBuserid(String buserid) {
		this.buserid = buserid;
	}



	public long getOrderindex() {
		return orderindex;
	}



	public void setOrderindex(long orderindex) {
		this.orderindex = orderindex;
	}



	@Override
	public String toString() {
		return "AdvertisingImgModel [adimgid=" + adimgid + ", adid=" + adid + ", linktype=" + linktype + ", imgurl=" + imgurl + ", linkurl=" + linkurl + ", gpid=" + gpid + ", nid=" + nid + ", keyword=" + keyword + ", secondcarid=" + secondcarid + ", miniprogramid=" + miniprogramid + ", buserid=" + buserid + ", orderindex=" + orderindex + "]";
	}
	
	
}
