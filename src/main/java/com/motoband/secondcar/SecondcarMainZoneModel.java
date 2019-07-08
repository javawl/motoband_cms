package com.motoband.secondcar;

public class SecondcarMainZoneModel {
public String scmzid;
public String picurl;//图片地址
public SearchSecondCarModel searchsecondcar;//二手车搜索model
public int state;//状态 0 下架 1 上架
public long orderindex;//排序
public String title;//标题

public String searchsecondcarstr;



@Override
public String toString() {
	return "SecondcarMainZoneModel [scmzid=" + scmzid + ", picurl=" + picurl + ", searchsecondcar=" + searchsecondcar + ", state=" + state + ", orderindex=" + orderindex + ", title=" + title + ", searchsecondcarstr=" + searchsecondcarstr + "]";
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public long getOrderindex() {
	return orderindex;
}

public void setOrderindex(long orderindex) {
	this.orderindex = orderindex;
}

public String getScmzid() {
	return scmzid;
}

public void setScmzid(String scmzid) {
	this.scmzid = scmzid;
}

public String getPicurl() {
	return picurl;
}

public void setPicurl(String picurl) {
	this.picurl = picurl;
}

public SearchSecondCarModel getSearchsecondcar() {
	return searchsecondcar;
}

public void setSearchsecondcar(SearchSecondCarModel searchsecondcar) {
	this.searchsecondcar = searchsecondcar;
}

public int getState() {
	return state;
}

public void setState(int state) {
	this.state = state;
}

public String getSearchsecondcarstr() {
	return searchsecondcarstr;
}

public void setSearchsecondcarstr(String searchsecondcarstr) {
	this.searchsecondcarstr = searchsecondcarstr;
}



}
