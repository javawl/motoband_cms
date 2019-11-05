package com.motoband.news;

public enum LinkTypeEnum {
	//0 通知消息，不进行跳转操作、1 动态  2 话题   3问答    4 有赞  5 内部链接  6 外部链接  7小程序 8 motogp 9 二手车  10 本地商家   11 话题列表  12 问答列表  13 此刻,14群详情,15消息
	OTHER(0),NEWS(1),TOPIC(2),DISCUSS(3),YZMALL(4),INNERLINK(5),
   OUTTERLINK(6),MINIPROGRAM(7),MOTOGP(8),SECONDCAR(9),BUSINESSUSER(10),TOPICLIST(11),DISCUSSLIST(12),MOMENT(13),
   GROUP(14),MESSAGE(15);
  private int value;

  private LinkTypeEnum(int value){
	  this.value=value;
  }


public int getValue() {
	return value;
}

public void setValue(int value) {
	this.value = value;
}
  
  
}
