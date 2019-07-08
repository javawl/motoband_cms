package com.motoband.motouser;

import java.util.HashMap;
import java.util.Map;


public class MBUserModel {

	public long mbid;
	public String userid;
	public String mobileno;
	public int gender;
	public String birth;
	public String nickname;
	public String province;
	public String city;
	public String area;
	public String headurl;
	public String signature;
	public long addtime;
	public String channel;
	public int level;
	public int usertype;
	public String approvedes;
	public int commentswitch;
	public int likeswitch;
	public int systemswitch;
	public int followswitch;
	public int rideshareswitch;
	public int nearuserswitch;
	public long credit;
	
	public long followcount;
	public long followedcount;
	public long newscount;
	public long friendcount;
	public long updatetime;
	public long createruncount;
	public long createverifycount;
	public long signruncount;
	public long verifycount;
	public long recommendcount;
	public long score;
	
	public int nationcode;// 地区码
	public int pkswitch;// 0开启，1关闭
	public String weixinid;
	public String weiboid;
	public String qqid;

	public long userprivilegelong;
	
	public String userprivilegelongstr;
	
	


	public String getUserprivilegelongstr() {
		return userprivilegelongstr;
	}

	public void setUserprivilegelongstr(String userprivilegelongstr) {
		this.userprivilegelongstr = userprivilegelongstr;
	}

	public long getUserprivilegelong() {
		return userprivilegelong;
	}

	public void setUserprivilegelong(long userprivilegelong) {
		this.userprivilegelong = userprivilegelong;
	}

	public int getNationcode() {
		return nationcode;
	}

	public void setNationcode(int nationcode) {
		this.nationcode = nationcode;
	}

	public int getPkswitch() {
		return pkswitch;
	}

	public void setPkswitch(int pkswitch) {
		this.pkswitch = pkswitch;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getWeiboid() {
		return weiboid;
	}

	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}

	public String getQqid() {
		return qqid;
	}

	public void setQqid(String qqid) {
		this.qqid = qqid;
	}

	public long getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}

	public long getRecommendcount() {
		return recommendcount;
	}

	public void setRecommendcount(long recommendcount) {
		this.recommendcount = recommendcount;
	}

	public int getNearuserswitch() {
		return nearuserswitch;
	}

	public void setNearuserswitch(int nearuserswitch) {
		this.nearuserswitch = nearuserswitch;
	}

	public int getRideshareswitch() {
		return rideshareswitch;
	}

	public void setRideshareswitch(int rideshareswitch) {
		this.rideshareswitch = rideshareswitch;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getApprovedes() {
		return approvedes;
	}

	public void setApprovedes(String approvedes) {
		this.approvedes = approvedes;
	}

	public int getCommentswitch() {
		return commentswitch;
	}

	public void setCommentswitch(int commentswitch) {
		this.commentswitch = commentswitch;
	}

	public int getLikeswitch() {
		return likeswitch;
	}

	public void setLikeswitch(int likeswitch) {
		this.likeswitch = likeswitch;
	}

	public int getSystemswitch() {
		return systemswitch;
	}

	public void setSystemswitch(int systemswitch) {
		this.systemswitch = systemswitch;
	}

	public int getFollowswitch() {
		return followswitch;
	}

	public void setFollowswitch(int followswitch) {
		this.followswitch = followswitch;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public long getMbid() {
		return mbid;
	}

	public void setMbid(long mbid) {
		this.mbid = mbid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public long getAddtime() {
		return addtime;
	}

	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getFollowcount() {
		return followcount;
	}

	public void setFollowcount(long followcount) {
		this.followcount = followcount;
	}

	public long getFollowedcount() {
		return followedcount;
	}

	public void setFollowedcount(long followedcount) {
		this.followedcount = followedcount;
	}

	public long getNewscount() {
		return newscount;
	}

	public void setNewscount(long newscount) {
		this.newscount = newscount;
	}

	public long getFriendcount() {
		return friendcount;
	}

	public void setFriendcount(long friendcount) {
		this.friendcount = friendcount;
	}

	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

	public long getCreateruncount() {
		return createruncount;
	}

	public void setCreateruncount(long createruncount) {
		this.createruncount = createruncount;
	}

	public long getCreateverifycount() {
		return createverifycount;
	}

	public void setCreateverifycount(long createverifycount) {
		this.createverifycount = createverifycount;
	}

	public long getSignruncount() {
		return signruncount;
	}

	public void setSignruncount(long signruncount) {
		this.signruncount = signruncount;
	}

	public long getVerifycount() {
		return verifycount;
	}

	public void setVerifycount(long verifycount) {
		this.verifycount = verifycount;
	}

	public static Map<String, String> convertToMap(MBUserModel mbUserModel) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("mbid", String.valueOf(mbUserModel.mbid));
		map.put("userid", mbUserModel.userid == null ? "" : mbUserModel.userid);
		map.put("mobileno", mbUserModel.mobileno == null ? "" : mbUserModel.mobileno);
		map.put("gender", String.valueOf(mbUserModel.gender));
		map.put("birth", mbUserModel.birth == null ? "" : mbUserModel.birth);
		map.put("nickname", mbUserModel.nickname == null ? "" : mbUserModel.nickname);
		map.put("area", mbUserModel.area == null ? "" : mbUserModel.area);
		map.put("headurl", mbUserModel.headurl == null ? "" : mbUserModel.headurl);
		map.put("signature", mbUserModel.signature == null ? "" : mbUserModel.signature);
		map.put("addtime", String.valueOf(mbUserModel.addtime));
		map.put("channel", mbUserModel.channel == null ? "" : mbUserModel.channel);
		map.put("level", String.valueOf(mbUserModel.level));
		map.put("newscount", String.valueOf(mbUserModel.newscount));
		map.put("friendcount", String.valueOf(mbUserModel.friendcount));
		map.put("updatetime", String.valueOf(mbUserModel.updatetime));
		map.put("followcount", String.valueOf(mbUserModel.followcount));
		map.put("followedcount", String.valueOf(mbUserModel.followedcount));
		map.put("createruncount", String.valueOf(mbUserModel.createruncount));
		map.put("createverifycount", String.valueOf(mbUserModel.signruncount));
		map.put("signruncount", String.valueOf(mbUserModel.signruncount));
		map.put("verifycount", String.valueOf(mbUserModel.verifycount));
		map.put("recommendcount", String.valueOf(mbUserModel.recommendcount));
		map.put("credit", String.valueOf(mbUserModel.credit));
		map.put("nationcode", String.valueOf(mbUserModel.nationcode));
		map.put("pkswitch", String.valueOf(mbUserModel.pkswitch));
		map.put("weixinid", mbUserModel.weixinid == null ? "" : mbUserModel.weixinid);
		map.put("weiboid", mbUserModel.weiboid == null ? "" : mbUserModel.weiboid);
		map.put("qqid", mbUserModel.qqid == null ? "" : mbUserModel.qqid);
		map.put("userprivilegelong",String.valueOf(mbUserModel.userprivilegelong));
		
		return map;
	}
	public static MBUserModel convertToModel(Map<String, String> map) {

		if (map == null) {
			return null;
		}

		MBUserModel model = new MBUserModel();

		if (map.containsKey("mbid")){

			if (!map.get("mbid").isEmpty())
				model.mbid = Long.valueOf(map.get("mbid"));
		}

		if (map.containsKey("userid")){
				model.userid =map.get("userid");
		}

		if (map.containsKey("mobileno")){

			if (!map.get("mobileno").isEmpty())
				model.mobileno = map.get("mobileno");
		}
		if (map.containsKey("gender")) {

			if (!map.get("gender").isEmpty())
				model.gender = Integer.parseInt(map.get("gender"));
		}
		
		if (map.containsKey("birth")){

			if (!map.get("birth").isEmpty())
				model.birth = map.get("birth");
		}


		if (map.containsKey("nickname"))
			model.nickname =map.get("nickname");
		
		if (map.containsKey("province")){

			if (!map.get("province").isEmpty())
				model.province = map.get("province");
		}
		if (map.containsKey("city")){
			
			if (!map.get("city").isEmpty())
				model.city = map.get("city");
		}
		if (map.containsKey("area")){
			
			if (!map.get("area").isEmpty())
				model.area = map.get("area");
		}
		if (map.containsKey("headurl")){
			
			if (!map.get("headurl").isEmpty())
				model.headurl = map.get("headurl");
		}
		if (map.containsKey("signature")){
			
			if (!map.get("signature").isEmpty())
				model.signature = map.get("signature");
		}
		if (map.containsKey("addtime")){
			
			if (!map.get("addtime").isEmpty())
				model.addtime = Long.valueOf(map.get("addtime"));
		}
		if (map.containsKey("channel")){
			
			if (!map.get("channel").isEmpty())
				model.channel = map.get("channel");
		}
		if (map.containsKey("level")){
			
			if (!map.get("level").isEmpty())
				model.level = Integer.parseInt(map.get("level"));
		}
		if (map.containsKey("usertype")){
			
			if (!map.get("usertype").isEmpty())
				model.usertype = Integer.parseInt(map.get("usertype"));
		}
		if (map.containsKey("approvedes")){
			
			if (!map.get("approvedes").isEmpty())
				model.approvedes = map.get("approvedes");
		}
		if (map.containsKey("commentswitch")){
			
			if (!map.get("commentswitch").isEmpty())
				model.commentswitch = Integer.parseInt(map.get("commentswitch"));
		}
		if (map.containsKey("likeswitch")){
			
			if (!map.get("likeswitch").isEmpty())
				model.likeswitch = Integer.parseInt(map.get("likeswitch"));
		}
		if (map.containsKey("systemswitch")){
			
			if (!map.get("systemswitch").isEmpty())
				model.systemswitch = Integer.parseInt(map.get("systemswitch"));
		}
		if (map.containsKey("followswitch")){
			
			if (!map.get("followswitch").isEmpty())
				model.followswitch = Integer.parseInt(map.get("followswitch"));
		}
		if (map.containsKey("rideshareswitch")){
			
			if (!map.get("rideshareswitch").isEmpty())
				model.rideshareswitch = Integer.parseInt(map.get("rideshareswitch"));
		}
		if (map.containsKey("nearuserswitch")){
			
			if (!map.get("nearuserswitch").isEmpty())
				model.nearuserswitch = Integer.parseInt(map.get("nearuserswitch"));
		}
		
		if (map.containsKey("credit")){
			
			if (!map.get("credit").isEmpty())
				model.credit = Long.valueOf(map.get("credit"));
		}
		
		if (map.containsKey("followcount")){
			
			if (!map.get("followcount").isEmpty())
				model.followcount = Long.valueOf(map.get("followcount"));
		}
		
		if (map.containsKey("followedcount")){
			
			if (!map.get("followedcount").isEmpty())
				model.followedcount = Long.valueOf(map.get("followedcount"));
		}
		
		if (map.containsKey("newscount")){
			
			if (!map.get("newscount").isEmpty())
				model.newscount = Long.valueOf(map.get("newscount"));
		}
		
		if (map.containsKey("friendcount")){
			
			if (!map.get("friendcount").isEmpty())
				model.friendcount = Long.valueOf(map.get("friendcount"));
		}
		
		if (map.containsKey("updatetime")){
			
			if (!map.get("updatetime").isEmpty())
				model.updatetime = Long.valueOf(map.get("updatetime"));
		}
		
		if (map.containsKey("createruncount")){
			
			if (!map.get("createruncount").isEmpty())
				model.createruncount = Long.valueOf(map.get("createruncount"));
		}
		
		if (map.containsKey("createverifycount")){
			
			if (!map.get("createverifycount").isEmpty())
				model.createverifycount = Long.valueOf(map.get("createverifycount"));
		}
		
		if (map.containsKey("signruncount")){
			
			if (!map.get("signruncount").isEmpty())
				model.signruncount = Long.valueOf(map.get("signruncount"));
		}
		
		if (map.containsKey("verifycount")){
			
			if (!map.get("verifycount").isEmpty())
				model.verifycount = Long.valueOf(map.get("verifycount"));
		}
		
		if (map.containsKey("recommendcount")){
			
			if (!map.get("recommendcount").isEmpty())
				model.recommendcount = Long.valueOf(map.get("recommendcount"));
		}
		
		if (map.containsKey("score")){
			
			if (!map.get("score").isEmpty())
				model.score = Long.valueOf(map.get("score"));
		}
		if (map.containsKey("nationcode")) {

			if (!map.get("nationcode").isEmpty())
				model.nationcode = Integer.parseInt(map.get("nationcode"));
		}
		if (map.containsKey("pkswitch")) {

			if (!map.get("pkswitch").isEmpty())
				model.pkswitch = Integer.parseInt(map.get("pkswitch"));
		}
		if (map.containsKey("weixinid")){
					
					if (!map.get("weixinid").isEmpty())
						model.weixinid = map.get("weixinid");
				}
		if (map.containsKey("weiboid")){
			
			if (!map.get("weiboid").isEmpty())
				model.weiboid = map.get("weiboid");
		}
		if (map.containsKey("qqid")){
			
			if (!map.get("qqid").isEmpty())
				model.qqid = map.get("qqid");
		}
		if (map.containsKey("userprivilegelong")){
			
			if (!map.get("userprivilegelong").isEmpty())
				model.userprivilegelong = Long.parseLong(map.get("userprivilegelong"));
		}
		return model;
	}
}
