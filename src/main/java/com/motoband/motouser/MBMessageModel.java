package com.motoband.motouser;

import com.motoband.news.BannerModel;
import com.motoband.secondcar.SecondCarModel;

public class MBMessageModel {

	public static final int MBMsgType_FollowNotify = 3;
	public static final int MBMsgType_NewsLikeNotify = 4;
	public static final int MBMsgType_NewsCommentNotify = 5;
	public static final int MBMsgType_NewsCommentReplyNotify = 6;
	public static final int MBMsgType_NewsRecommendNotify=18;//动态加精
	public static final int MBMsgType_UserRecommendNotify=19;//用户加推荐
	public static final int MBMsgType_GiftNotify=21;//礼物通知
	public static final int MBMsgType_DiscussInviteNotify=22;//讨论邀请通知
	public static final int MBMsgType_BusinessCommentNotify=23;//评论商家通知
	public static final int MBMsgType_BusinessReplyCommentNotify=24;//商家回复通知
	public static final int MBMsgType_JoinDiscussNotify=27;//用户参与讨论
	
	public static final int MBMsgType_CommentSecondCarNotify=28;//用户留言二手车
	public static final int MBMsgType_CommentReplySecondCarNotify=29;//回复二手车留言
	
	//2.5.3
	public static final int MBMsgType_MentionNotify=31;//@通知
	
	public static final int Typefor_Comment=0;//在什么类型中@    评论
	public static final int Typefor_Publish=1;//在什么类型中@    发布
	public static final int MentionType_News=0;//@类型    动态
	public static final int MentionType_Secondcar=3;//@类型    二手车
	public static final int MentionType_Businessuser=4;//@类型    本地商家
	
	//2.8.0
	public static final int MBMsgType_JoinVoiceRoom=35;//加入语音房间
	public static final int MBMsgType_ExitVoiceRoom=36;//退出语音房间
	public static final int MBMsgType_DissolveVoiceRoom=37;//解散语音房间
	public static final int MBMsgType_KickoutVoiceRoom=38;//被踢出语音房间
	public static final int MBMsgType_UpdateMemeberid=39;//更新Memeberid
	
	
	public static final int MBMsgType_JoinLBSTeam=42;//加入组队
	public static final int MBMsgType_ExitLBSTeam=43;//退出组队
	public static final int MBMsgType_DissolveLBSTeam=44;//解散组队
	public static final int MBMsgType_KickoutLBSTeam=45;//被踢出组队
//	public static final int MBMsgType_ContinueVoiceRoom=46;//续语音房间
	public static final int MBMsgType_ReplaceVoiceRoomAdmin=47;//更换语音房主
	public static final int MBMsgType_ReplaceLBSTeamAdmin=48;//更换组队房主
	
	
	public static final int MBMsgType_Banner=49;//banner类型消息
	public static final int MBMsgType_YZCustomServiceMessage=50;//商城客服消息
	
	
	public int msgtype;
	public long msgtime;
	public String msgid;

	// 动态ID
	public String nid;
	
	public int ntype;

	// 评论ID
	public String cid;

	// 评论|回复 内容
	public String content;
	
	//动态图片
	public String picurl;
	
	
	//礼物ID
	public int giftid;

	//keyword 话题和讨论使用
	public String keyword;
		
	//二手车id
	public String secondcarid;
	
	public SecondCarModel secondcarModel;
	
	//2.5.3
	public int typefor;//在什么类型中@ （0评论中  1 发布中）
	public int mentiontype;//@类型   （0动态   3二手车 4 本地商家 ）
	public String buserid;//本地商家ID
	
	//2.8.0
	public String roomid;//房间id
	public String kickouteduserid;//被踢人id
	public String kickoutednickname;//被踢人昵称
	
	public String gmeroomid;//gme房间号
	
	public SimpleUserModel simpleusermodel;//消息发起者简单model
	
	public BannerModel bannermodel;//banner类型消息的返回
	
	//兼容老版本
	public String fromuserid;
	public String fromnickname;
	
	public String groupid;
	
	
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public int getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(int msgtype) {
		this.msgtype = msgtype;
	}
	public long getMsgtime() {
		return msgtime;
	}
	public void setMsgtime(long msgtime) {
		this.msgtime = msgtime;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public int getNtype() {
		return ntype;
	}
	public void setNtype(int ntype) {
		this.ntype = ntype;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public int getGiftid() {
		return giftid;
	}
	public void setGiftid(int giftid) {
		this.giftid = giftid;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSecondcarid() {
		return secondcarid;
	}
	public void setSecondcarid(String secondcarid) {
		this.secondcarid = secondcarid;
	}
	public SecondCarModel getSecondcarModel() {
		return secondcarModel;
	}
	public void setSecondcarModel(SecondCarModel secondcarModel) {
		this.secondcarModel = secondcarModel;
	}
	public int getTypefor() {
		return typefor;
	}
	public void setTypefor(int typefor) {
		this.typefor = typefor;
	}
	public int getMentiontype() {
		return mentiontype;
	}
	public void setMentiontype(int mentiontype) {
		this.mentiontype = mentiontype;
	}
	public String getBuserid() {
		return buserid;
	}
	public void setBuserid(String buserid) {
		this.buserid = buserid;
	}
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	public String getKickouteduserid() {
		return kickouteduserid;
	}
	public void setKickouteduserid(String kickouteduserid) {
		this.kickouteduserid = kickouteduserid;
	}
	public String getKickoutednickname() {
		return kickoutednickname;
	}
	public void setKickoutednickname(String kickoutednickname) {
		this.kickoutednickname = kickoutednickname;
	}
	public String getGmeroomid() {
		return gmeroomid;
	}
	public void setGmeroomid(String gmeroomid) {
		this.gmeroomid = gmeroomid;
	}
	public SimpleUserModel getSimpleusermodel() {
		return simpleusermodel;
	}
	public void setSimpleusermodel(SimpleUserModel simpleusermodel) {
		this.simpleusermodel = simpleusermodel;
	}
	public BannerModel getBannermodel() {
		return bannermodel;
	}
	public void setBannermodel(BannerModel bannermodel) {
		this.bannermodel = bannermodel;
	}
	public String getFromuserid() {
		return fromuserid;
	}
	public void setFromuserid(String fromuserid) {
		this.fromuserid = fromuserid;
	}
	public String getFromnickname() {
		return fromnickname;
	}
	public void setFromnickname(String fromnickname) {
		this.fromnickname = fromnickname;
	}
	
	
	
	

}
