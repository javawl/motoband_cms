package com.motoband.motouser;

public class VipCardMemberModel {
 public long vcmid;//
 public String userid;//用户id
 public long starttime;//开始时间
 public long endtime;//结束时间
 public int viptype;//类型  唯一 从1开始
 
 //服务器用
 public int level;//用于排序   越大越贵  级别越高  最大为主卡
 public int vipcardid;//VIP卡id
 
@Override
public String toString() {
	return "VipCardMemberModel [vcmid=" + vcmid + ", userid=" + userid + ", starttime=" + starttime + ", endtime=" + endtime + ", viptype=" + viptype + ", level=" + level + ", vipcardid=" + vipcardid + "]";
}

public long getVcmid() {
	return vcmid;
}

public void setVcmid(long vcmid) {
	this.vcmid = vcmid;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public long getStarttime() {
	return starttime;
}

public void setStarttime(long starttime) {
	this.starttime = starttime;
}

public long getEndtime() {
	return endtime;
}

public void setEndtime(long endtime) {
	this.endtime = endtime;
}

public int getViptype() {
	return viptype;
}

public void setViptype(int viptype) {
	this.viptype = viptype;
}

public int getLevel() {
	return level;
}

public void setLevel(int level) {
	this.level = level;
}

public int getVipcardid() {
	return vipcardid;
}

public void setVipcardid(int vipcardid) {
	this.vipcardid = vipcardid;
}
 




 

}
