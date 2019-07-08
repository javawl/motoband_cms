package com.motoband.motobandgpmanage;

public class GpuserRankingModel {
  private String userid;
  private String nickname;
  private String ridetimeScore;
  private double  mileageScore;
  private long redisCount;
  
public long getRedisCount() {
	return redisCount;
}
public void setRedisCount(long redisCount) {
	this.redisCount = redisCount;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getRidetimeScore() {
	return ridetimeScore;
}
public void setRidetimeScore(String ridetimeScore) {
	this.ridetimeScore = ridetimeScore;
}

public double getMileageScore() {
	return mileageScore;
}
public void setMileageScore(double mileageScore) {
	this.mileageScore = mileageScore;
}
  
}
