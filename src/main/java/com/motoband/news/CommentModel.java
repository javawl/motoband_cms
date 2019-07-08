package com.motoband.news;

import java.util.Map;

public class CommentModel {
 private String cid;
 private String tuserid;
 private String ctime;
 private String ctimeString;
 private String runid;
 private String tcid;
 private String content;
 private String userid;
 private String usernickname;
 private String tusernickname;
 private String userHeadurl;
 private String tuserHeadurl;
 private String nid;
 
 
public String getNid() {
	return nid;
}
public void setNid(String nid) {
	this.nid = nid;
}
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
public String getCid() {
	return cid;
}
public void setCid(String cid) {
	this.cid = cid;
}
public String getTuserid() {
	return tuserid;
}
public void setTuserid(String tuserid) {
	this.tuserid = tuserid;
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
public String getRunid() {
	return runid;
}
public void setRunid(String runid) {
	this.runid = runid;
}
public String getTcid() {
	return tcid;
}
public void setTcid(String tcid) {
	this.tcid = tcid;
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
 
public static CommentModel convertToCommentModel(Map<String, String> map) {

	if (map == null) {
		return null;
	}

	CommentModel model = new CommentModel();
	
	if (map.containsKey("tuserid")) {
		model.tuserid=map.get("tuserid");
	}
	if (map.containsKey("nid")) {
		model.nid=map.get("nid");
	}
	if (map.containsKey("cid")) {
		model.cid=map.get("cid");
	}
	if (map.containsKey("ctime")) {
		model.ctime=map.get("ctime");
	}
	if (map.containsKey("runid")) {
		model.runid=map.get("runid");
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
