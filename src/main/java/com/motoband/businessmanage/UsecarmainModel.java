package com.motoband.businessmanage;

import com.motoband.mallmanage.MallProductModel;
import com.motoband.secondcar.SecondCarModel;

public class UsecarmainModel {
 public long id;
 public int groupid;
 public String contentstr;
 public long orderindex;
 public int type;//0 商品 2 商家活动
 
 //扩展
 public MallProductModel mallProductModel;
 public String groupname;
 public int grouptype;
 public BusinessActivityModel businessActivityModel;
 public SecondCarModel secondCarModel;
 
 
public BusinessActivityModel getBusinessActivityModel() {
	return businessActivityModel;
}
public void setBusinessActivityModel(BusinessActivityModel businessActivityModel) {
	this.businessActivityModel = businessActivityModel;
}
public SecondCarModel getSecondCarModel() {
	return secondCarModel;
}
public void setSecondCarModel(SecondCarModel secondCarModel) {
	this.secondCarModel = secondCarModel;
}
public String getGroupname() {
	return groupname;
}
public void setGroupname(String groupname) {
	this.groupname = groupname;
}
public int getGrouptype() {
	return grouptype;
}
public void setGrouptype(int grouptype) {
	this.grouptype = grouptype;
}
public MallProductModel getMallProductModel() {
	return mallProductModel;
}
public void setMallProductModel(MallProductModel mallProductModel) {
	this.mallProductModel = mallProductModel;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public int getGroupid() {
	return groupid;
}
public void setGroupid(int groupid) {
	this.groupid = groupid;
}
public String getContentstr() {
	return contentstr;
}
public void setContentstr(String contentstr) {
	this.contentstr = contentstr;
}
public long getOrderindex() {
	return orderindex;
}
public void setOrderindex(long orderindex) {
	this.orderindex = orderindex;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
 
 
}
