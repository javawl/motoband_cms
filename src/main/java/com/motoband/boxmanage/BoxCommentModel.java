package com.motoband.boxmanage;

import java.util.Map;

public class BoxCommentModel {
private String cid;
private String tcid;
private String tuserid;
private String boxid;
private String content;
private String userid;
private String ctime;
private String ctimeString;
private String usernickname;
private String tusernickname;
private String userHeadurl;
private String tuserHeadurl;

public String getUserHeadurl() {
	return userHeadurl;
}
public void setUserHeadurl(String userHeadurl) {
	this.userHeadurl = userHeadurl;
}
public String getTuserHeadurl() {
	return tuserHeadurl;
}
public void setTuserHeadurl(String tuserHeadurl) {
	this.tuserHeadurl = tuserHeadurl;
}
public String getCid() {
	return cid;
}
public void setCid(String cid) {
	this.cid = cid;
}
public String getTcid() {
	return tcid;
}
public void setTcid(String tcid) {
	this.tcid = tcid;
}
public String getTuserid() {
	return tuserid;
}
public void setTuserid(String tuserid) {
	this.tuserid = tuserid;
}
public String getBoxid() {
	return boxid;
}
public void setBoxid(String boxid) {
	this.boxid = boxid;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getCtime() {
	return ctime;
}
public void setCtime(String ctime) {
	this.ctime = ctime;
}
public String getCtimeString() {
	return ctimeString;
}
public void setCtimeString(String ctimeString) {
	this.ctimeString = ctimeString;
}
public String getUsernickname() {
	return usernickname;
}
public void setUsernickname(String usernickname) {
	this.usernickname = usernickname;
}
public String getTusernickname() {
	return tusernickname;
}
public void setTusernickname(String tusernickname) {
	this.tusernickname = tusernickname;
}
public static BoxCommentModel convertToCommentModel(Map<String, String> map) {

	if (map == null) {
		return null;
	}

	BoxCommentModel model = new BoxCommentModel();
	
	if (map.containsKey("tuserid")) {
		model.tuserid=map.get("tuserid");
	}
	if (map.containsKey("cid")) {
		model.cid=map.get("cid");
	}
	if (map.containsKey("ctime")) {
		model.ctime=map.get("ctime");
	}
	if (map.containsKey("boxid")) {
		model.boxid=map.get("boxid");
	}
	if (map.containsKey("tcid")) {
		model.tcid=map.get("tcid");
	}
	if (map.containsKey("content")) {
		model.content=map.get("content");
	}
	if (map.containsKey("userid")) {
		model.userid=map.get("userid");
	}
	
	return model;
 }
}
