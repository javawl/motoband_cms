package com.motoband.news;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class newsService {

	@Autowired
	private newsMapper newsMapper;



	public int getUserUpdateCheck(String userGuid) {

		return newsMapper.checkPrivilege("topic_upd", userGuid);
	}


	public int getUserInsCheck(String userGuid) {

		return newsMapper.checkPrivilege("topic_ins", userGuid);
	}

	public int getRecommendUpdateCheck(String userGuid) {
	
		return newsMapper.checkPrivilege("motorecommend_upd", userGuid);
	}

	public int getRecommendDelCheck(String userGuid) {
		
		return newsMapper.checkPrivilege("motorecommend_del", userGuid);
	}

	public int getBannerUpdateCheck(String userGuid) {
		
		return newsMapper.checkPrivilege("banner_upd", userGuid);
	}

	public int getBannerDelCheck(String userGuid) {
		
		return newsMapper.checkPrivilege("banner_del", userGuid);
	}

	public void addBanner(banner banner) {
		newsMapper.addBanner(banner);
		
	}

	public void delBanner(String bannerid) {
		newsMapper.delBanner(Long.parseLong(bannerid));
	}

	public void updatebanner(banner banner) {
		newsMapper.updatebanner(banner);
		
	}

	public void insertOrUpdateTopic(topic topic) {
		newsMapper.insertOrUpdateTopic(topic);
		
	}

	public List<topic> getTopics(int start, int limit, int order, String orderConditions,int topictype) {
		return newsMapper.getTopics(start, limit, order,orderConditions,topictype);
	}

	public List<newsLabelModel> getLabels() {
		return newsMapper.getLabels();
	}

	public int getStoryRecommendUpdCheck(String userGuid) {
		return newsMapper.checkPrivilege("storyrecommend_upd", userGuid);
		
	}
	public int getStoryRecommendDelCheck(String userGuid){
		return newsMapper.checkPrivilege("storyrecommend_del", userGuid);
	}

	public List<banner> getBannerlist() {
		
		return newsMapper.getBannerlist();
	}

	
	public List<newsModel> getnewsModelByuserid(String userid){
		return newsMapper.getnewsModelByuserid(userid);
	}
   
	public List<CommentModel> getcommentModelByuserid(String userid){
		return newsMapper.getcommentModelByuserid(userid);
	}

	public void deleteNewsCommentByCid(String cid) {
		newsMapper.deleteNewsCommentByCid(cid);
		
	}
	public void insertNews(newsModel newsModel){
		newsMapper.insertNews(newsModel);
	}

	public void updatenewsByNid(newsModel newsModel) {
		newsMapper.updatenewsByNid(newsModel);
		
	}

	public List<NewscategoryModel> getNewscategory() {
		return newsMapper.getNewscategory();
	}

	public int getBoxOrStoryTempListCount() {
		
		return newsMapper.getBoxOrStoryTempListCount();
	}

	public List<newsModel> getBoxOrStoryTempList(int start, int limit, int order) {
		
		return newsMapper.getBoxOrStoryTempList(start, limit,order);
	}

	public int getTopicsCount(int topictype) {
		
		return newsMapper.getTopicsCount(topictype) ;
	}


	public void addBannerall(banner banner) {
		newsMapper.addBannerall(banner);
		
	}


	public com.motoband.news.banner getbannerbyid(String bannerid) {
		
		return newsMapper.getbannerbyid(bannerid);
	}


	public List<BannerModel> getNewBannerlist() {
		
		return newsMapper.getNewBannerlist();
	}


	public BannerModel getnewbannerbyid(String bannerid) {
		
		return newsMapper.getnewbannerbyid(bannerid);
	}


	public void delnewBanner(String bannerid) {
		newsMapper.delnewBanner(bannerid);
		
	}


	public void addnewBannerall(BannerModel banner) {
		newsMapper.addnewBannerall(banner);
		
	}


	public void updateTopicIsMain(String topicid, int ismain) {
		newsMapper.updateTopicIsMain(topicid,ismain);
	}

}
