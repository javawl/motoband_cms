package com.motoband.tribal;

public class TribalTypeModel {
	public int tribaltypeid;// 部落类型id
	public String name;// 部落类型名称
	public int state;// 状态（0 下架 1 上架）
	public long orderindex;//排序
	
	public String rediskeyname;//服务器专用


	public int getTribaltypeid() {
		return tribaltypeid;
	}


	public void setTribaltypeid(int tribaltypeid) {
		this.tribaltypeid = tribaltypeid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public String getRediskeyname() {
		return rediskeyname;
	}


	public void setRediskeyname(String rediskeyname) {
		this.rediskeyname = rediskeyname;
	}


	@Override
	public String toString() {
		return "TribalTypeModel [tribaltypeid=" + tribaltypeid + ", name=" + name + ", state=" + state + ", orderindex=" + orderindex + ", rediskeyname=" + rediskeyname + "]";
	}


	

	
}
