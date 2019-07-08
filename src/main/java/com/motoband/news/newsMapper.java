package com.motoband.news;




import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface newsMapper {

	
	int checkPrivilege(@Param("privilege_key")String privilege_key, @Param("userGuid")String userGuid);

	void addBanner(banner banner);

	void delBanner(@Param("bannerid")long bannerid);

	void updatebanner(banner banner);

	void insertOrUpdateTopic(topic topic);

	List<topic> getTopics(@Param("start")int start, @Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions,@Param("topictype")int topictype);

	List<newsLabelModel> getLabels();

	List<banner> getBannerlist();


	List<newsModel> getnewsModelByuserid(@Param("userid")String userid);

	List<CommentModel> getcommentModelByuserid(@Param("userid")String userid);

	void deleteNewsCommentByCid(@Param("cid")String cid);

	void insertNews(newsModel newsModel);

	void updatenewsByNid(newsModel newsModel);

	List<NewscategoryModel> getNewscategory();

	int getBoxOrStoryTempListCount();

	List<newsModel> getBoxOrStoryTempList(@Param("start")int start, @Param("limit")int limit, @Param("order")int order);

	int getTopicsCount(@Param("topictype")int topictype);

	void addBannerall(banner banner);

	banner getbannerbyid(@Param("bannerid")String bannerid);

	List<BannerModel> getNewBannerlist();

	BannerModel getnewbannerbyid(@Param("bannerid")String bannerid);

	void delnewBanner(@Param("bannerid")String bannerid);

	void addnewBannerall(BannerModel banner);

	void updateTopicIsMain(@Param("topicid")String topicid, @Param("ismain")int ismain);



}
