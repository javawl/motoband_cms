package com.motoband.boxmanage;

import java.util.Map;

public class motobox {
	private String boxid;
	private int typeid;
	private String title;
	private int boxkind;
	private String titleimage;
	private String subtitle;
	private long boxtime;
	private String localpath;
	private String boxurl;
	private String submitter;
	private String source;
	private String keyword;
	private int approve;
	private int ismotoband;
	private int ishot;
	private int status;
	private int news;

	private long ptime;
	private long updatetime;
	
	public int categoryid;
	public String nid;//反向查找
	
	
	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public long getPtime() {
		return ptime;
	}

	public void setPtime(long ptime) {
		this.ptime = ptime;
	}

	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

	public int getNews() {
		return news;
	}

	public void setNews(int news) {
		this.news = news;
	}

	public int getIshot() {
		return ishot;
	}

	public void setIshot(int ishot) {
		this.ishot = ishot;
	}

	public int getApprove() {
		return approve;
	}

	public void setApprove(int approve) {
		this.approve = approve;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	

	public int getIsmotoband() {
		return ismotoband;
	}

	public void setIsmotoband(int ismotoband) {
		this.ismotoband = ismotoband;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getBoxkind() {
		return boxkind;
	}

	public void setBoxkind(int boxkind) {
		this.boxkind = boxkind;
	}

	public String getTitleimage() {
		return titleimage;
	}

	public void setTitleimage(String titleimage) {
		this.titleimage = titleimage;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getBoxid() {
		return boxid;
	}

	public void setBoxid(String boxid) {
		this.boxid = boxid;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getBoxtime() {
		return boxtime;
	}

	public void setBoxtime(long boxtime) {
		this.boxtime = boxtime;
	}

	public String getLocalpath() {
		return localpath;
	}

	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}

	public String getBoxurl() {
		return boxurl;
	}

	public void setBoxurl(String boxurl) {
		this.boxurl = boxurl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public static motobox convertToMotobox(Map<String, String> map) {

		if (map == null) {
			return null;
		}

		motobox model = new motobox();
		
		if (map.containsKey("boxid")) {
			model.boxid=map.get("boxid");
		}
		if (map.containsKey("typeid")) {
			model.typeid=Integer.parseInt(map.get("typeid"));
		}
		if (map.containsKey("title")) {
			model.title=map.get("title");
		}
		if (map.containsKey("boxkind")) {
			model.boxkind=Integer.parseInt(map.get("boxkind"));
		}
		if (map.containsKey("titleimage")) {
			model.titleimage=map.get("titleimage");
		}
		if (map.containsKey("subtitle")) {
			model.subtitle=map.get("subtitle");
		}
		if (map.containsKey("boxtime")) {
			model.boxtime=Long.parseLong(map.get("boxtime"));
		}
		if (map.containsKey("localpath")) {
			model.localpath=map.get("localpath");
		}
		if (map.containsKey("boxurl")) {
			model.boxurl=map.get("boxurl");
		}
		if (map.containsKey("submitter")) {
			model.submitter=map.get("submitter");
		}
		if (map.containsKey("source")) {
			model.source=map.get("source");
		}
		if (map.containsKey("keyword")) {
			model.keyword=map.get("keyword");
		}
		if (map.containsKey("approve")) {
			model.approve=Integer.parseInt(map.get("approve"));
		}
		if (map.containsKey("ismotoband")) {
			model.ismotoband=Integer.parseInt(map.get("ismotoband"));
		}
		if (map.containsKey("ishot")) {
			model.ishot=Integer.parseInt(map.get("ishot"));
		}
		if (map.containsKey("news")) {
			model.news=Integer.parseInt(map.get("news"));
		}
		if (map.containsKey("status")) {
			model.status=Integer.parseInt(map.get("status"));
		}
		if (map.containsKey("updatetime")) {
			model.updatetime=Long.parseLong(map.get("updatetime"));
		}
		if (map.containsKey("ptime")) {
			model.ptime=Long.parseLong(map.get("ptime"));
		}
		if (map.containsKey("categoryid")) {

			if (!map.get("categoryid").isEmpty())
				model.categoryid = Integer.parseInt(map.get("categoryid"));
		}
		if (map.containsKey("nid")) {

			if (!map.get("nid").isEmpty())
				model.nid = map.get("nid");
		}
		return model;
	 }
}
