package com.motoband.boxmanage;

import java.util.HashMap;
import java.util.Map;

public class BoxYunSouModel {
	public int NA;
	public int boxid;
	public String title;
	public String keyword;
	public int getNA() {
		return NA;
	}
	public void setNA(int nA) {
		NA = nA;
	}
	public int getBoxid() {
		return boxid;
	}
	public void setBoxid(int boxid) {
		this.boxid = boxid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public static Map<String, Object> convertToMap(BoxYunSouModel boxYunSouModel) {
		Map<String, Object> addMap=new HashMap<String, Object>();
		addMap.put("contents.0.NA", boxYunSouModel.getNA());
		addMap.put("contents.0.boxid", boxYunSouModel.getBoxid());
		addMap.put("contents.0.title", boxYunSouModel.getTitle()==null?"":boxYunSouModel.getTitle());
		addMap.put("contents.0.keyword", boxYunSouModel.getKeyword()==null?"":boxYunSouModel.getKeyword());
		return addMap;
	}
}
