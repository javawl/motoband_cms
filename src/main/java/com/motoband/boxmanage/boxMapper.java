package com.motoband.boxmanage;




import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface boxMapper {

	ArrayList<Boxlist> getBoxList(@Param("start")int start,@Param("limit")int limit,@Param("order")int order,@Param("title") String title,@Param("orderConditions")String orderConditions);
	int checkPrivilege(@Param("privilege_key")String privilege_key, @Param("userGuid")String userGuid);
	ArrayList<Boxtype> getBoxTypeList();
	int insMotoBox(motobox motobox);
	void insMotoNote(noteModel noteModel);
	motobox getMotoBoxMessageByBoxid(@Param("box_id")String box_id);
	noteModel getNoteModelMessageByBoxid(@Param("box_id")String box_id);
	ArrayList<motoimg> getMotoImgList();
	void insMotoImg(motoimg motoimg);
	void delMotoImg(@Param("img_guid")String img_guid);
	void updateBoxMessage(motobox motobox);
	void updateNoteMessage(noteModel noteModel);
	void updatePath(@Param("boxid")String boxid,@Param("localpath")String localpath,@Param("showurl")String showurl);
	String getPageLocalPath();
	String getBoxUrlIP();
	ArrayList<Boxlist> getBoxListByType(@Param("nowtypeid")String nowtypeid,@Param("start")int start,@Param("limit")int limit,@Param("order")int order, @Param("title") String title,@Param("orderConditions")String orderConditions);
	void delBoxMessage(@Param("box_id")String box_id);
	void delNoteMessage(@Param("box_id")String box_id);
	ArrayList<imggroup> getImgGroupList();
	ArrayList<motoimg> getMotoImgListByGroupGuid(@Param("group_guid")String group_guid);
	void updateImgName(@Param("img_guid")String img_guid, @Param("newname")String newname);
	String selectBoxIdByGuid(@Param("img_guid")String img_guid);
	void saveimgNewGroup(@Param("img_guid")String img_guid,@Param("imgnewgroup") String imgnewgroup);
	String checkGroupName(@Param("groupname")String groupname);
	void addImgGroup(@Param("groupid")String groupid,@Param("groupname") String groupname);
	void addImgBox(motoimgbox motoimgbox);
	void delImgBox(@Param("box_id")String box_id);
	void addTitleImg(@Param("box_id")String box_id, @Param("titleimg")String titleimg);
	String getTitleImage(@Param("box_id")String box_id);
	long getBoxLook(@Param("boxid")String boxid);
	List<BoxRecommendModel> getBoxRecommendList();
	int checkUserApprove(@Param("userid")String userid);
	BoxRecommendModel getRecommend(@Param("recommendBoxID")Long recommendBoxID);
	void updateBoxRecommdState(@Param("recommendBoxID")String recommendBoxID);
	void addHotBox(@Param("boxid")String boxid);
	void delHotBox(@Param("boxid")String boxid);
	List<BoxBannerModel> getBoxBannerList();
	void delBanner(@Param("bannerid")String bannerid);
	void updatebanner(BoxBannerModel banner);
	motobox getBoxByBoxid(@Param("boxid")String boxid);
	void addBoxBanner(BoxBannerModel banner);
	List<BoxKeywordModel> getBoxKeywordList();
	void delboxkeyword(@Param("bannerid")String bannerid);
	void updateboxkeyword(BoxKeywordModel boxKeywordModel);
	void addBoxKeyword(@Param("keyword")String keyword);
	int getBoxListCount(@Param("title")String title);
	int getBoxListBytypidCount(@Param("nowtypeid")String nowtypeid,@Param("title")String title);
	Boxtype getBoxTypeByTypeid(@Param("typeid")String typeid);
	ArrayList<Boxlist> getBoxTitleList();
	void updateNewsStatus(@Param("news")int news,@Param("boxid")String boxid);
	
	

}
