package com.motoband.businessmanage;

import java.util.HashMap;
import java.util.Map;


public class BusinessActivityModel {
 public String baid;
 public String title;
 public String content;
 public String pics;
 public int orderindex;
 public int state;
 public String buserid;
 
public String getBuserid() {
	return buserid;
}
public void setBuserid(String buserid) {
	this.buserid = buserid;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public String getBaid() {
	return baid;
}
public void setBaid(String baid) {
	this.baid = baid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getPics() {
	return pics;
}
public void setPics(String pics) {
	this.pics = pics;
}
public int getOrderindex() {
	return orderindex;
}
public void setOrderindex(int orderindex) {
	this.orderindex = orderindex;
}
public static Map<String, String> convertToMap(BusinessActivityModel model) {
	Map<String, String> map = new HashMap<String, String>();

	map.put("baid", model.baid==null?"":model.baid);
	map.put("title", model.title==null? "" : model.title);
	map.put("content", model.content==null? "" : model.content);
	map.put("pics", model.pics==null? "" : model.pics);
	map.put("buserid", model.buserid==null? "" : model.buserid);
	map.put("orderindex", String.valueOf(model.orderindex));
	return map;
}

public static BusinessActivityModel convertToModel(Map<String, String> map) {
	if (map == null) {
		return null;
	}

	BusinessActivityModel model = new BusinessActivityModel();

	if (map.containsKey("baid")) {
		if (!map.get("baid").isEmpty())
			model.baid = map.get("baid");
	}
	if (map.containsKey("title")) {
		if (!map.get("title").isEmpty())
			model.title = map.get("title");
	}
	if (map.containsKey("content")) {
		if (!map.get("content").isEmpty())
			model.content = map.get("content");
	}
	if (map.containsKey("pics")) {
		if (!map.get("pics").isEmpty())
			model.pics = map.get("pics");
	}
	if (map.containsKey("buserid")) {
		if (!map.get("buserid").isEmpty())
			model.buserid = map.get("buserid");
	}
	if (map.containsKey("orderindex")) {
		if (!map.get("orderindex").isEmpty())
			model.orderindex = Integer.parseInt(map.get("orderindex"));
	}
	return model;
}

 
}
