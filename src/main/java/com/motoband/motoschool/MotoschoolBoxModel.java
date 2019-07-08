package com.motoband.motoschool;

public class MotoschoolBoxModel {
private Integer bid;
private Integer boxid;
private Integer pid;
private Integer orderindex;
private Integer state;

private String boxtitle;
private String schoolpackagetitle;


public String getBoxtitle() {
	return boxtitle;
}
public void setBoxtitle(String boxtitle) {
	this.boxtitle = boxtitle;
}
public String getSchoolpackagetitle() {
	return schoolpackagetitle;
}
public void setSchoolpackagetitle(String schoolpackagetitle) {
	this.schoolpackagetitle = schoolpackagetitle;
}
public Integer getBid() {
	return bid;
}
public void setBid(Integer bid) {
	this.bid = bid;
}
public Integer getBoxid() {
	return boxid;
}
public void setBoxid(Integer boxid) {
	this.boxid = boxid;
}
public Integer getPid() {
	return pid;
}
public void setPid(Integer pid) {
	this.pid = pid;
}
public Integer getOrderindex() {
	return orderindex;
}
public void setOrderindex(Integer orderindex) {
	this.orderindex = orderindex;
}
public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
}


}
