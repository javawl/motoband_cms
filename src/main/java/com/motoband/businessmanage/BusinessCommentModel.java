package com.motoband.businessmanage;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class BusinessCommentModel {
 public String cid;
 public String userid;
 public String tuserid;
 public String tcid;
 public long ctime;
 public String content;
 public int score;
 public int modelid;
 public int cost;
 public String pics;
 public BusinessCommentModel businesscommentmodel;//商家回复model
 public String businesscommentmodelstr;
 
//扩展
 public String userName;
 public String tuserName;
 public String modelName;
 
 
public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getTuserName() {
	return tuserName;
}

public void setTuserName(String tuserName) {
	this.tuserName = tuserName;
}

public String getModelName() {
	return modelName;
}

public void setModelName(String modelName) {
	this.modelName = modelName;
}

public String getCid() {
	return cid;
}

public void setCid(String cid) {
	this.cid = cid;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public String getTuserid() {
	return tuserid;
}

public void setTuserid(String tuserid) {
	this.tuserid = tuserid;
}

public String getTcid() {
	return tcid;
}

public void setTcid(String tcid) {
	this.tcid = tcid;
}

public long getCtime() {
	return ctime;
}

public void setCtime(long ctime) {
	this.ctime = ctime;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

public int getModelid() {
	return modelid;
}

public void setModelid(int modelid) {
	this.modelid = modelid;
}

public int getCost() {
	return cost;
}

public void setCost(int cost) {
	this.cost = cost;
}

public String getPics() {
	return pics;
}

public void setPics(String pics) {
	this.pics = pics;
}

public BusinessCommentModel getBusinesscommentmodel() {
	return businesscommentmodel;
}

public void setBusinesscommentmodel(BusinessCommentModel businesscommentmodel) {
	this.businesscommentmodel = businesscommentmodel;
}

public String getBusinesscommentmodelstr() {
	return businesscommentmodelstr;
}

public void setBusinesscommentmodelstr(String businesscommentmodelstr) {
	this.businesscommentmodelstr = businesscommentmodelstr;
}

public static Map<String, String> convertToMap(BusinessCommentModel model) {
	Map<String, String> map = new HashMap<String, String>();

	
	map.put("cid", model.cid==null? "" : model.cid);
	map.put("userid", model.userid==null? "" : model.userid);
	map.put("tuserid", model.tuserid==null? "" : model.tuserid);
	map.put("tcid", model.tcid==null? "" : model.tcid);
	map.put("ctime", String.valueOf(model.ctime));
	map.put("content", model.content==null? "" : model.content);
	map.put("score", String.valueOf(model.score));
	map.put("modelid", String.valueOf(model.modelid));
	map.put("cost", String.valueOf(model.cost));
	map.put("pics", model.pics==null? "" : model.pics);
	if (model.businesscommentmodel != null) {
		String temp = JSON.toJSONString(model.businesscommentmodel);
		map.put("businesscommentmodel", temp);
		model.businesscommentmodelstr = temp;
	}
	return map;
}

public static BusinessCommentModel convertToModel(Map<String, String> map) {
	if (map == null) {
		return null;
	}

	BusinessCommentModel model = new BusinessCommentModel();

	if (map.containsKey("cid")) {
		if (!map.get("cid").isEmpty())
			model.cid = map.get("cid");
	}
	if (map.containsKey("userid")) {
		if (!map.get("userid").isEmpty())
			model.userid = map.get("userid");
	}
	if (map.containsKey("tuserid")) {
		if (!map.get("tuserid").isEmpty())
			model.tuserid = map.get("tuserid");
	}

	if (map.containsKey("tcid")) {
		if (!map.get("tcid").isEmpty())
			model.tcid = map.get("tcid");
	}
	
	if (map.containsKey("ctime")) {
		if (!map.get("ctime").isEmpty())
			model.ctime = Long.parseLong(map.get("ctime"));
	}
	if (map.containsKey("content")) {
		if (!map.get("content").isEmpty())
			model.content = map.get("content");
	}
	if (map.containsKey("score")) {
		if (!map.get("score").isEmpty())
			model.score = Integer.parseInt(map.get("score"));
	}
	if (map.containsKey("modelid")) {
		if (!map.get("modelid").isEmpty())
			model.modelid = Integer.parseInt(map.get("modelid"));
	}
	if (map.containsKey("cost")) {
		if (!map.get("cost").isEmpty())
			model.cost = Integer.parseInt(map.get("cost"));
	}

	if (map.containsKey("pics")) {
		if (!map.get("pics").isEmpty())
			model.pics = map.get("pics");
	}
	if (map.containsKey("businesscommentmodel")) {

		if (!map.get("businesscommentmodel").isEmpty())
			model.businesscommentmodel = JSON.parseObject(map.get("businesscommentmodel"), BusinessCommentModel.class);
	}
	return model;
}

}
