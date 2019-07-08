package com.motoband.dataStatistics;

public class MonthActiveModel {
   private String datetime;
   private int num;
public String getDatetime() {
	return datetime;
}
public void setDatetime(String datetime) {
	this.datetime = datetime;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
@Override
public String toString() {
	return "MonthActiveModel [datetime=" + datetime + ", num=" + num + "]";
}
   
}
