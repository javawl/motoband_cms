package com.motoband.carmanage;

public class carlist {
	private int modelid;
	private String name;
	private int cc;
	private int typeid;
	private int state;
	private String typename;
	private int brandid;
	private String brandname;
	private int bpid;
	private String brandparentname;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getBpid() {
		return bpid;
	}

	public void setBpid(int bpid) {
		this.bpid = bpid;
	}

	public String getBrandparentname() {
		return brandparentname;
	}

	public void setBrandparentname(String brandparentname) {
		this.brandparentname = brandparentname;
	}

	public int getModelid() {
		return modelid;
	}

	public void setModelid(int modelid) {
		this.modelid = modelid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getBrandid() {
		return brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

}
