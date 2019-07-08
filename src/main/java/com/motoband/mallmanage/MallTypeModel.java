package com.motoband.mallmanage;

public class MallTypeModel {
private Integer typeid;
private String name;
private Integer parentid;

//扩展
private String parentName;


public String getParentName() {
	return parentName;
}
public void setParentName(String parentName) {
	this.parentName = parentName;
}
public Integer getTypeid() {
	return typeid;
}
public void setTypeid(Integer typeid) {
	this.typeid = typeid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getParentid() {
	return parentid;
}
public void setParentid(Integer parentid) {
	this.parentid = parentid;
}

}
