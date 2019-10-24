package com.motoband.news;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.motoband.boxmanage.motobox;


public class newsModel {
	public Long id;
	public String nid;
	public String userid;
	public String title;
	public String content;
	public String keywords;
	public String labels;
	public String picurl;
	public String citycode;
	public Integer type;//0:图文 1：视频 2：线路 3：路书 4：故事
	public Long ptime;
	public Long updatetime;
	public String location;
	public Long lcount;
	public Long ccount;
	public Integer recommend;//0普通 1精选
	
	public Long score;
	public String ptimeString;
	public String updatetimeString;
	
	public String headurl;
	public String nickname;

	public String videourl;
	public String newsupdatetime;
	
	public String ridedatamodelstr;

	public String routedatamodelstr;

	public String videodatastr;
	
	public List<newsModel> storylist; 
	public String storyliststr;
	public motobox motoboxmodel;
	
	public Long recommendtime;
	public String recommenduserid;
	public Integer gpid;
	public Integer boxid;
	public String useridliststr;
	public String achievementmodelstr;
	public String recommendtimeString;
	public List<String> useridlist; 
	public AchievementmModel achievementmodel; 
	
	public int categoryid;
	public String pid;
	public String businessuserid;
	
	public float picwidth;
	public float picheight;
	
	public topic topicmodel;
	public String topicmodelstr;
	
	
	public String linkurl;//跳转链接   只存redis 供boxurl等跳转
	public String discusskeyword;
	
	
	
	public String getDiscusskeyword() {
		return discusskeyword;
	}



	public void setDiscusskeyword(String discusskeyword) {
		this.discusskeyword = discusskeyword;
	}



	public String getLinkurl() {
		return linkurl;
	}



	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}



	public float getPicwidth() {
		return picwidth;
	}



	public void setPicwidth(float picwidth) {
		this.picwidth = picwidth;
	}



	public float getPicheight() {
		return picheight;
	}



	public void setPicheight(float picheight) {
		this.picheight = picheight;
	}



	public String getBusinessuserid() {
		return businessuserid;
	}



	public void setBusinessuserid(String businessuserid) {
		this.businessuserid = businessuserid;
	}



	public topic getTopicmodel() {
		return topicmodel;
	}



	public void setTopicmodel(topic topicmodel) {
		this.topicmodel = topicmodel;
	}



	public String getTopicmodelstr() {
		return topicmodelstr;
	}



	public void setTopicmodelstr(String topicmodelstr) {
		this.topicmodelstr = topicmodelstr;
	}



	public String getPid() {
		return pid;
	}



	public void setPid(String pid) {
		this.pid = pid;
	}



	public int getCategoryid() {
		return categoryid;
	}



	public motobox getMotoboxmodel() {
		return motoboxmodel;
	}



	public void setMotoboxmodel(motobox motoboxmodel) {
		this.motoboxmodel = motoboxmodel;
	}



	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNid() {
		return nid;
	}



	public void setNid(String nid) {
		this.nid = nid;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getKeywords() {
		return keywords;
	}



	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}



	public String getLabels() {
		return labels;
	}



	public void setLabels(String labels) {
		this.labels = labels;
	}



	public String getPicurl() {
		return picurl;
	}



	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}



	public String getCitycode() {
		return citycode;
	}



	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public Long getPtime() {
		return ptime;
	}



	public void setPtime(Long ptime) {
		this.ptime = ptime;
	}



	public Long getUpdatetime() {
		return updatetime;
	}



	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public Long getLcount() {
		return lcount;
	}



	public void setLcount(Long lcount) {
		this.lcount = lcount;
	}



	public Long getCcount() {
		return ccount;
	}



	public void setCcount(Long ccount) {
		this.ccount = ccount;
	}



	public Integer getRecommend() {
		return recommend;
	}



	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}



	public Long getScore() {
		return score;
	}



	public void setScore(Long score) {
		this.score = score;
	}



	public String getPtimeString() {
		return ptimeString;
	}



	public void setPtimeString(String ptimeString) {
		this.ptimeString = ptimeString;
	}



	public String getUpdatetimeString() {
		return updatetimeString;
	}



	public void setUpdatetimeString(String updatetimeString) {
		this.updatetimeString = updatetimeString;
	}



	public String getHeadurl() {
		return headurl;
	}



	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getVideourl() {
		return videourl;
	}



	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}



	public String getNewsupdatetime() {
		return newsupdatetime;
	}



	public void setNewsupdatetime(String newsupdatetime) {
		this.newsupdatetime = newsupdatetime;
	}



	public String getRidedatamodelstr() {
		return ridedatamodelstr;
	}



	public void setRidedatamodelstr(String ridedatamodelstr) {
		this.ridedatamodelstr = ridedatamodelstr;
	}



	public String getRoutedatamodelstr() {
		return routedatamodelstr;
	}



	public void setRoutedatamodelstr(String routedatamodelstr) {
		this.routedatamodelstr = routedatamodelstr;
	}



	public String getVideodatastr() {
		return videodatastr;
	}



	public void setVideodatastr(String videodatastr) {
		this.videodatastr = videodatastr;
	}



	public List<newsModel> getStorylist() {
		return storylist;
	}



	public void setStorylist(List<newsModel> storylist) {
		this.storylist = storylist;
	}



	public String getStoryliststr() {
		return storyliststr;
	}



	public void setStoryliststr(String storyliststr) {
		this.storyliststr = storyliststr;
	}



	public Long getRecommendtime() {
		return recommendtime;
	}



	public void setRecommendtime(Long recommendtime) {
		this.recommendtime = recommendtime;
	}



	public String getRecommenduserid() {
		return recommenduserid;
	}



	public void setRecommenduserid(String recommenduserid) {
		this.recommenduserid = recommenduserid;
	}



	public Integer getGpid() {
		return gpid;
	}



	public void setGpid(Integer gpid) {
		this.gpid = gpid;
	}



	public Integer getBoxid() {
		return boxid;
	}



	public void setBoxid(Integer boxid) {
		this.boxid = boxid;
	}



	public String getUseridliststr() {
		return useridliststr;
	}



	public void setUseridliststr(String useridliststr) {
		this.useridliststr = useridliststr;
	}



	public String getAchievementmodelstr() {
		return achievementmodelstr;
	}



	public void setAchievementmodelstr(String achievementmodelstr) {
		this.achievementmodelstr = achievementmodelstr;
	}



	public String getRecommendtimeString() {
		return recommendtimeString;
	}



	public void setRecommendtimeString(String recommendtimeString) {
		this.recommendtimeString = recommendtimeString;
	}



	public List<String> getUseridlist() {
		return useridlist;
	}



	public void setUseridlist(List<String> useridlist) {
		this.useridlist = useridlist;
	}



	public AchievementmModel getAchievementmodel() {
		return achievementmodel;
	}



	public void setAchievementmodel(AchievementmModel achievementmodel) {
		this.achievementmodel = achievementmodel;
	}



	public static newsModel convertToModel(Map<String, String> map) {

		if (map == null) {
			return null;
		}

		newsModel model = new newsModel();
		
		if (map.containsKey("id")) {
			model.id=Long.valueOf(map.get("id"));
		}
		if (map.containsKey("nid")) {

			if (!map.get("nid").isEmpty())
				model.nid = map.get("nid");
		}

		if (map.containsKey("userid")) {

			if (!map.get("userid").isEmpty())
				model.userid = map.get("userid");
		}
		if (map.containsKey("title")) {
			if (!map.get("title").isEmpty()) {
				model.title=map.get("title");
			}
		}
		if (map.containsKey("content")) {

			if (!map.get("content").isEmpty())
				model.content = map.get("content");
		}

		if (map.containsKey("keywords")) {

			if (!map.get("keywords").isEmpty())
				model.keywords = map.get("keywords");
		}

		if (map.containsKey("picurl")) {

			if (!map.get("picurl").isEmpty())
				model.picurl = map.get("picurl");
		}
		if (map.containsKey("labels")) {
			if (!map.get("labels").isEmpty()) {
				model.labels=map.get("labels");
			}
		}

		if (map.containsKey("type"))
			model.type = Integer.valueOf(map.get("type"));
		if (map.containsKey("updatetime"))
			model.updatetime=Long.valueOf(map.get("updatetime"));
			
			
		if (map.containsKey("ptime"))
			model.ptime = Long.valueOf(map.get("ptime"));

		if (map.containsKey("location")) {

			if (!map.get("location").isEmpty())
				model.location = map.get("location");
		}

		if (map.containsKey("lcount"))
			model.lcount = Long.valueOf(map.get("lcount"));

		if (map.containsKey("ccount"))
			model.ccount = Long.valueOf(map.get("ccount"));
		
		if (map.containsKey("citycode")) {

			if (!map.get("citycode").isEmpty())
				model.citycode = map.get("citycode");
		}
		if (map.containsKey("recommend")) {
			model.recommend=Integer.valueOf(map.get("recommend"));
		}
		if (map.containsKey("score")) {
			model.score=Long.valueOf(map.get("score"));
		}
	
		if (map.containsKey("recommendtime")) {

			if (!map.get("recommendtime").isEmpty())
				model.recommendtime = Long.valueOf(map.get("recommendtime"));
		}
		if (map.containsKey("recommenduserid")) {

			if (!map.get("recommenduserid").isEmpty())
				model.recommenduserid = map.get("recommenduserid");
		}
		if (map.containsKey("gpid")) {

			if (!map.get("gpid").isEmpty())
				model.gpid = Integer.parseInt(map.get("gpid"));
		}
		if (map.containsKey("boxid")) {

			if (!map.get("boxid").isEmpty())
				model.boxid = Integer.parseInt(map.get("boxid"));
		}
		if (map.containsKey("useridliststr")) {

			if (!map.get("useridliststr").isEmpty())
				model.useridliststr = map.get("useridliststr");
		}
		if (map.containsKey("achievementmodelstr")) {

			if (!map.get("achievementmodelstr").isEmpty())
				model.achievementmodelstr = map.get("achievementmodelstr");
		}
		if (map.containsKey("categoryid")) {

			if (!map.get("categoryid").isEmpty())
				model.categoryid = Integer.parseInt(map.get("categoryid"));
		}
		if (map.containsKey("picheight")) {
			
			if (!map.get("picheight").isEmpty())
				model.picheight = Float.parseFloat(map.get("picheight"));
		}
		if (map.containsKey("picwidth")) {
			
			if (!map.get("picwidth").isEmpty())
				model.picwidth = Float.parseFloat(map.get("picwidth"));
		}
		if (map.containsKey("pid")) {

			if (!map.get("pid").isEmpty())
				model.pid = map.get("pid");
		}
		if (map.containsKey("linkurl")) {
			
			if (!map.get("linkurl").isEmpty())
				model.linkurl = map.get("linkurl");
		}
		if (map.containsKey("businessuserid")) {
			
			if (!map.get("businessuserid").isEmpty())
				model.businessuserid = map.get("businessuserid");
		}
		if (map.containsKey("motoboxmodel")) {
			if (!map.get("motoboxmodel").isEmpty()) {
				model.motoboxmodel = JSON.parseObject(map.get("motoboxmodel"), motobox.class);
			}
		}
		
		if (map.containsKey("topicmodel")) {
			if (!map.get("topicmodel").isEmpty()) {
				model.topicmodel = JSON.parseObject(map.get("topicmodel"), topic.class);
			}
		}
		
		return model;
	}
	
	
}
