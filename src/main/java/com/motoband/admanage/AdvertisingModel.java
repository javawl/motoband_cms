package com.motoband.admanage;

import java.util.List;

public class AdvertisingModel {
	public String adid;
	public String title;//广告标题
	public String subtitle;//广告副标题
	public String des;// 广告简介描述
	public String adheadurl;// 广告主头像地址
	public int adtype;//0 开屏   1轮播    2首页推荐信息流     3首页分类信息流     4二手车信息流     5关注信息流     6发现信息流
	                  //7此刻信息流   8动态图文详情    9动态视频详情   10故事详情
	public int state;// 0 下线 1 上线
	public List<AdvertisingImgModel> adlist;
	
	
	public int adheadtype;//广告主头像类型  (0 摩托邦商城    1疯狂摩托车   2贝纳利)
	
	
	
	public int getAdheadtype() {
		return adheadtype;
	}


	public void setAdheadtype(int adheadtype) {
		this.adheadtype = adheadtype;
	}


	public String getAdid() {
		return adid;
	}


	public void setAdid(String adid) {
		this.adid = adid;
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


	public int getAdtype() {
		return adtype;
	}


	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public List<AdvertisingImgModel> getAdlist() {
		return adlist;
	}


	public void setAdlist(List<AdvertisingImgModel> adlist) {
		this.adlist = adlist;
	}


	public String getAdheadurl() {
		return adheadurl;
	}


	public void setAdheadurl(String adheadurl) {
		this.adheadurl = adheadurl;
	}


	@Override
	public String toString() {
		return "AdvertisingModel [adid=" + adid + ", title=" + title + ", subtitle=" + subtitle + ", des=" + des + ", adheadurl=" + adheadurl + ", adtype=" + adtype + ", state=" + state + ", adlist=" + adlist + ", adheadtype=" + adheadtype + "]";
	}


	
	
	
	
}
