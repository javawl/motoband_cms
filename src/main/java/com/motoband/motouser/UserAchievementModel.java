package com.motoband.motouser;

public class UserAchievementModel {
	private int id;
	private String userid;
	private int achid;
	private int achtype;
	private String source;
	private String motogp;
	private long gettime;

	private String ach_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getAch_name() {
		return ach_name;
	}

	public void setAch_name(String ach_name) {
		this.ach_name = ach_name;
	} 
	
}
