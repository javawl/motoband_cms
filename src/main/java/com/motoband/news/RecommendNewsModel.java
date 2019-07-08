package com.motoband.news;

import java.util.Map;

public class RecommendNewsModel {
	public long score;
	public String content;
	public String nid;
	public String picurl;
	public String userid;
	public long ccount;
	public long lcount;
	public int type;
	public long ptime;
	public String keywords;
	public String ptimeString;
	public String updatetimeString;
	public String labels;
	public long updatetime;
	public  int boxid;
	public int categoryid;
	public int recommend;//0普通 1精选
	
	
	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getBoxid() {
		return boxid;
	}

	public void setBoxid(int boxid) {
		this.boxid = boxid;
	}

	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdatetimeString() {
		return updatetimeString;
	}

	public void setUpdatetimeString(String updatetimeString) {
		this.updatetimeString = updatetimeString;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getPtimeString() {
		return ptimeString;
	}

	public void setPtimeString(String ptimeString) {
		this.ptimeString = ptimeString;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public long getCcount() {
		return ccount;
	}

	public void setCcount(long ccount) {
		this.ccount = ccount;
	}

	public long getLcount() {
		return lcount;
	}

	public void setLcount(long lcount) {
		this.lcount = lcount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getPtime() {
		return ptime;
	}

	public void setPtime(long ptime) {
		this.ptime = ptime;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public static RecommendNewsModel convertToCommentModel(Map<String, String> map) {

		if (map == null) {
			return null;
		}

		RecommendNewsModel model = new RecommendNewsModel();
		
		if (map.containsKey("score")) {
			model.score=Long.parseLong(map.get("score"));
		}
		if (map.containsKey("content")) {
			model.content=map.get("content");
		}
		if (map.containsKey("nid")) {
			model.nid=map.get("nid");
		}
		if (map.containsKey("picurl")) {
			model.picurl=map.get("picurl");
		}
		if (map.containsKey("userid")) {
			model.userid=map.get("userid");
		}
		if (map.containsKey("ccount")) {
			model.ccount=Long.parseLong(map.get("ccount"));
		}
		if (map.containsKey("lcount")) {
			model.lcount=Long.parseLong(map.get("lcount"));
		}
		
		if (map.containsKey("type")) {
			model.type=Integer.parseInt(map.get("type"));
		}
		if (map.containsKey("ptime")) {
			model.ptime=Long.parseLong(map.get("ptime"));
		}
		if (map.containsKey("keywords")) {
			model.keywords=map.get("keywords");
		}
		if (map.containsKey("ptimeString")) {
			model.ptimeString=map.get("ptimeString");
		}
		if (map.containsKey("updatetimeString")) {
			model.updatetimeString=map.get("updatetimeString");
		}
		if (map.containsKey("labels")) {
			model.labels=map.get("labels");
		}
		if (map.containsKey("updatetime")) {
			model.updatetime=Long.parseLong(map.get("updatetime"));
		}
		if (map.containsKey("boxid")) {
			model.boxid=Integer.parseInt(map.get("boxid"));
		}
		if (map.containsKey("categoryid")) {

			if (!map.get("categoryid").isEmpty())
				model.categoryid = Integer.parseInt(map.get("categoryid"));
		}
		if (map.containsKey("recommend")) {
			model.recommend=Integer.valueOf(map.get("recommend"));
		}
		return model;
	 }
}
