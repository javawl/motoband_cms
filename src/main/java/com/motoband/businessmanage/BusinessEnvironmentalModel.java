package com.motoband.businessmanage;

public class BusinessEnvironmentalModel {
	public String doorpics;
	public String shoppics;
	public String getDoorpics() {
		return doorpics;
	}
	public void setDoorpics(String doorpics) {
		this.doorpics = doorpics;
	}
	public String getShoppics() {
		return shoppics;
	}
	public void setShoppics(String shoppics) {
		this.shoppics = shoppics;
	}
	

//	public static Map<String, String> convertToMap(BusinessEnvironmentalModel model) {
//		Map<String, String> map = new HashMap<String, String>();
//
//		map.put("doorpics", model.doorpics==null? "" : model.doorpics);
//		map.put("shoppics", model.shoppics==null? "" : model.shoppics);
//		
//		return map;
//	}
}
