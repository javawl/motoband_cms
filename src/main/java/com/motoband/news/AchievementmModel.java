package com.motoband.news;

public class AchievementmModel {
	public long id;
	public String userid;
	public int achid;
	public int achtype;
	public String source;
	public String motogp;
	public long gettime;
	
	
	public String achname;
	
	
	public String getAchname() {
		return achname;
	}
	public void setAchname(String achname) {
		this.achname = achname;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getAchid() {
		return achid;
	}
	public void setAchid(int achid) {
		this.achid = achid;
	}
	public int getAchtype() {
		return achtype;
	}
	public void setAchtype(int achtype) {
		this.achtype = achtype;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getMotogp() {
		return motogp;
	}
	public void setMotogp(String motogp) {
		this.motogp = motogp;
	}
	public long getGettime() {
		return gettime;
	}
	public void setGettime(long gettime) {
		this.gettime = gettime;
	}
	
}
