package com.motoband.news;

import java.util.List;

public class BannerModel {
	public long bannerid;
	public int bannertype;// 0 首页banner区域  1 商城banner区域    2选车首页banner区域 3  二手车首页banner区域    4 首页活动区域  5首页滚动区域    6发现页区域（服务器拼接，cms无需配置）,7商城活动提醒区域(这个类型的版本号单独由mallversion控制)
	public String title;//标题
	public String subtitle;//副标题
	public String des;//描述
	public int state;// 0 下线 1 上线

	/**
	 * @LinkTypeEnum
	 */
	public int linktype;
	public String imgurl;//图片地址
	public String linkurl;//跳转链接
	public int gpid;//gpid
	public String nid;//动态id
	public String keyword;//关键字
	public String secondcarid;//二手车id
	public String miniprogramid;//小程序id
	public String buserid;//商家id
	public String groupid;//群id
	public long orderindex;//排序
	
	public int ntype;//动态的类型   当为跳转类型为动态时  自动赋值
	public String icon;
	public List<String> labels;
	
	
	
	
	public long getBannerid() {
		return bannerid;
	}




	public void setBannerid(long bannerid) {
		this.bannerid = bannerid;
	}




	public int getBannertype() {
		return bannertype;
	}




	public void setBannertype(int bannertype) {
		this.bannertype = bannertype;
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




	public String getDes() {
		return des;
	}




	public void setDes(String des) {
		this.des = des;
	}




	public int getState() {
		return state;
	}




	public void setState(int state) {
		this.state = state;
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




	public String getGroupid() {
		return groupid;
	}




	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}




	public long getOrderindex() {
		return orderindex;
	}




	public void setOrderindex(long orderindex) {
		this.orderindex = orderindex;
	}




	public int getNtype() {
		return ntype;
	}




	public void setNtype(int ntype) {
		this.ntype = ntype;
	}




	public String getIcon() {
		return icon;
	}




	public void setIcon(String icon) {
		this.icon = icon;
	}




	public List<String> getLabels() {
		return labels;
	}




	public void setLabels(List<String> labels) {
		this.labels = labels;
	}




	@Override
	public String toString() {
		return "BannerModel [bannerid=" + bannerid + ", bannertype=" + bannertype + ", title=" + title + ", subtitle=" + subtitle + ", des=" + des + ", state=" + state + ", linktype=" + linktype + ", imgurl=" + imgurl + ", linkurl=" + linkurl + ", gpid=" + gpid + ", nid=" + nid + ", keyword=" + keyword + ", secondcarid=" + secondcarid + ", miniprogramid=" + miniprogramid + ", buserid=" + buserid
				+ ", groupid=" + groupid + ", orderindex=" + orderindex + ", ntype=" + ntype + ", icon=" + icon + ", labels=" + labels + "]";
	}


}
