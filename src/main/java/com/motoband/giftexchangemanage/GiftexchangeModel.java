package com.motoband.giftexchangemanage;

public class GiftexchangeModel {
  public String exchangeid;
  public String name;
  public String des;
  public int condition;
  public String conditiondes;// 兑换条件(用户等级名称)
  public String scope;
  public int sumcount;
  public long validitystarttime;
  public long validityendtime;
  public int giftid;
  public int giftcount;
  public String remind;
  public String pic;
  public int state;
  
  
  //扩展
  public String validitystarttimeString;
  public String validityendtimeString;
  public String buserids;
  public String busernames;
  public String giftName;
  public String conditionName;
  

  
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public String getConditiondes() {
	return conditiondes;
}
public void setConditiondes(String conditiondes) {
	this.conditiondes = conditiondes;
}
public String getGiftName() {
	return giftName;
}
public void setGiftName(String giftName) {
	this.giftName = giftName;
}
public String getConditionName() {
	return conditionName;
}
public void setConditionName(String conditionName) {
	this.conditionName = conditionName;
}
public String getBuserids() {
	return buserids;
}
public void setBuserids(String buserids) {
	this.buserids = buserids;
}
public String getBusernames() {
	return busernames;
}
public void setBusernames(String busernames) {
	this.busernames = busernames;
}
public String getValiditystarttimeString() {
	return validitystarttimeString;
}
public void setValiditystarttimeString(String validitystarttimeString) {
	this.validitystarttimeString = validitystarttimeString;
}
public String getValidityendtimeString() {
	return validityendtimeString;
}
public void setValidityendtimeString(String validityendtimeString) {
	this.validityendtimeString = validityendtimeString;
}

public String getExchangeid() {
	return exchangeid;
}
public void setExchangeid(String exchangeid) {
	this.exchangeid = exchangeid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDes() {
	return des;
}
public void setDes(String des) {
	this.des = des;
}
public int getCondition() {
	return condition;
}
public void setCondition(int condition) {
	this.condition = condition;
}
public String getScope() {
	return scope;
}
public void setScope(String scope) {
	this.scope = scope;
}
public int getSumcount() {
	return sumcount;
}
public void setSumcount(int sumcount) {
	this.sumcount = sumcount;
}
public long getValiditystarttime() {
	return validitystarttime;
}
public void setValiditystarttime(long validitystarttime) {
	this.validitystarttime = validitystarttime;
}
public long getValidityendtime() {
	return validityendtime;
}
public void setValidityendtime(long validityendtime) {
	this.validityendtime = validityendtime;
}
//public String getBuserids() {
//	return buserids;
//}
//public void setBuserids(String buserids) {
//	this.buserids = buserids;
//}
public int getGiftid() {
	return giftid;
}
public void setGiftid(int giftid) {
	this.giftid = giftid;
}
public int getGiftcount() {
	return giftcount;
}
public void setGiftcount(int giftcount) {
	this.giftcount = giftcount;
}
public String getRemind() {
	return remind;
}
public void setRemind(String remind) {
	this.remind = remind;
}
public String getPic() {
	return pic;
}
public void setPic(String pic) {
	this.pic = pic;
}
  
  
  
}
