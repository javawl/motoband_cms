package com.motoband.admanage;

import java.util.HashMap;
import java.util.Map;

public class AdPoolImgModel {
	public String adpoolimgid;
	public int adtype;//0 首页广告  1 关注广告   2 首页搜索广告  3 用车搜索广告   4 工具广告
	public String adtitle;//广告标题
	public String addes;//广告简介描述
	public String price;//广告价格字符串
	public int linktype;//0普通外链 1活动（MotobandGP）2话题页面 3商品详细页 4保险页面 5贷款页面 6文章详细页面 7 二手车详情页
	public String imgurl;
	public String linkurl;
	public int gpid;
	public String keyword;
	public String mallurl;
	public String boxid;
	public String secondcarid;//客户端目前不用支持
	public int orderindex;//顺序，客户端不需要
	public int state;//0 下线   1 上线，客户端不需要
	
	
	@Override
	public String toString() {
		return "AdPoolImgModel [adpoolimgid=" + adpoolimgid + ", adtype=" + adtype + ", adtitle=" + adtitle + ", addes=" + addes + ", price=" + price + ", linktype=" + linktype + ", imgurl=" + imgurl + ", linkurl=" + linkurl + ", gpid=" + gpid + ", keyword=" + keyword + ", mallurl=" + mallurl + ", boxid=" + boxid + ", secondcarid=" + secondcarid + ", orderindex=" + orderindex + ", state=" + state
				+ "]";
	}



	public String getAddes() {
		return addes;
	}



	public void setAddes(String addes) {
		this.addes = addes;
	}



	public String getAdpoolimgid() {
		return adpoolimgid;
	}



	public void setAdpoolimgid(String adpoolimgid) {
		this.adpoolimgid = adpoolimgid;
	}



	public int getAdtype() {
		return adtype;
	}



	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}



	public String getAdtitle() {
		return adtitle;
	}



	public void setAdtitle(String adtitle) {
		this.adtitle = adtitle;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
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



	public String getKeyword() {
		return keyword;
	}



	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	public String getMallurl() {
		return mallurl;
	}



	public void setMallurl(String mallurl) {
		this.mallurl = mallurl;
	}



	public String getBoxid() {
		return boxid;
	}



	public void setBoxid(String boxid) {
		this.boxid = boxid;
	}



	public String getSecondcarid() {
		return secondcarid;
	}



	public void setSecondcarid(String secondcarid) {
		this.secondcarid = secondcarid;
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



	public static Map<String, String> convertToMap(AdPoolImgModel model) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("adpoolimgid", model.adpoolimgid==null? "" : model.adpoolimgid);
		map.put("adtitle", model.adtitle==null? "" : model.adtitle);
		map.put("addes", model.addes==null? "" : model.addes);
		map.put("price", model.price==null? "" : model.price);
		map.put("adtype", String.valueOf(model.adtype));
		map.put("linktype", String.valueOf(model.linktype));
		map.put("imgurl", model.imgurl==null? "" : model.imgurl);
		map.put("linkurl", model.linkurl==null? "" : model.linkurl);
		map.put("gpid", String.valueOf(model.gpid));
		map.put("keyword", model.keyword==null? "" : model.keyword);
		map.put("mallurl", model.mallurl==null? "" : model.mallurl);
		map.put("boxid", model.boxid==null? "" : model.boxid);
		map.put("secondcarid", model.secondcarid==null? "" : model.secondcarid);
		map.put("orderindex", String.valueOf(model.orderindex));
		map.put("state", String.valueOf(model.state));
		return map;
	}

	public static AdPoolImgModel convertToModel(Map<String, String> map) {
		if (map == null) {
			return null;
		}

		AdPoolImgModel model = new AdPoolImgModel();

		if (map.containsKey("adpoolimgid")) {
			if (!map.get("adpoolimgid").isEmpty())
				model.adpoolimgid = map.get("adpoolimgid");
		}
		if (map.containsKey("adtitle")) {
			if (!map.get("adtitle").isEmpty())
				model.adtitle = map.get("adtitle");
		}
		if (map.containsKey("addes")) {
			if (!map.get("addes").isEmpty())
				model.addes = map.get("addes");
		}
		if (map.containsKey("price")) {
			if (!map.get("price").isEmpty())
				model.price = map.get("price");
		}
		if (map.containsKey("adtype")) {
			if (!map.get("adtype").isEmpty())
				model.adtype = Integer.parseInt(map.get("adtype"));
		}
		if (map.containsKey("linktype")) {
			if (!map.get("linktype").isEmpty())
				model.linktype = Integer.parseInt(map.get("linktype"));
		}
		
		if (map.containsKey("imgurl")) {
			if (!map.get("imgurl").isEmpty())
				model.imgurl = map.get("imgurl");
		}
		if (map.containsKey("linkurl")) {
			if (!map.get("linkurl").isEmpty())
				model.linkurl = map.get("linkurl");
		}
		if (map.containsKey("gpid")) {
			if (!map.get("gpid").isEmpty())
				model.gpid = Integer.parseInt(map.get("gpid"));
		}
		if (map.containsKey("keyword")) {
			if (!map.get("keyword").isEmpty())
				model.keyword = map.get("keyword");
		}
		if (map.containsKey("mallurl")) {
			if (!map.get("mallurl").isEmpty())
				model.mallurl = map.get("mallurl");
		}
		if (map.containsKey("boxid")) {
			if (!map.get("boxid").isEmpty())
				model.boxid = map.get("boxid");
		}
		
		if (map.containsKey("secondcarid")) {
			if (!map.get("secondcarid").isEmpty())
				model.secondcarid = map.get("secondcarid");
		}
	
		if (map.containsKey("orderindex")) {
			if (!map.get("orderindex").isEmpty())
				model.orderindex = Integer.valueOf(map.get("orderindex"));
		}
		if (map.containsKey("state")) {
			if (!map.get("state").isEmpty())
				model.state = Integer.valueOf(map.get("state"));
		}
	
		return model;
	}

}
