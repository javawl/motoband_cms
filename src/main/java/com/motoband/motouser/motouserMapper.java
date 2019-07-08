package com.motoband.motouser;




import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface motouserMapper {

	List<ridelineModel> searchUserRideLine(@Param("userid")String userid);


	List<ridedatelog> searchUserRideDateLog(@Param("userid")String userid);


	List<garageModel> searchUserGarage(@Param("userid")String userid);


	List<MBUserModel> searchByusernickname(@Param("usernickname")String usernickname);


	MBUserModel getUserByID(@Param("uid")String uid);


	int checkPrivilege(@Param("privilege_key")String privilege_key, @Param("userGuid")String userGuid);


	void addUserBoxApprove(@Param("uid")String uid,@Param("media")String media);


	long searchUserAddtime(@Param("targetid")String targetid);


	AchievementModel getachievement(@Param("achid")String achid);


	List<UserAchievementModel> getuserachievement(@Param("userid")String userid);


	List<MBUserModel> getUserByUserType(@Param("usertype")int usertype);


	void updateUsertypeAndapprovedes(@Param("usertype")Integer usertype, @Param("approvedes")String approvedes,@Param("userid")String userid);


	MBUserModel searchByusermobileno(@Param("mobileno")String mobileno);


	void updateUserPSWByUserid(@Param("userid")String userid, @Param("password")String password);


	List<MBUserlevelModel> getUserLevels();


	MBUserlevelModel getUserLevelByLevel(@Param("level")int level);


	List<String> getAutomatedOperationUseridList();


	int getOpenScreenListCount();


	List<OpenScreenModel> getOpenScreenList(@Param("start")int start, @Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions);


	void insertOrUpdateOpenscreen(OpenScreenModel openScreenModel);


	void delopenscreen(@Param("osid")String osid);


	void updatePrivilegelongForUser(@Param("userid")String userid, @Param("userprivilegelong")long privilegelong);


	int checkvipmember(@Param("userid")String userid, @Param("viptype")int viptype);


	void insertVipCardMember(VipCardMemberModel vipCardMemberModel);


	List<VipCardMemberModel> getVipCardMemberByuserid(@Param("userid")String userid);


	List<MessageTaskModel> getMessageTasks();


	int getUserTaskCount(@Param("taskid")String taskid, @Param("state")int state);


	void addMessageTask(MessageTaskModel messageTaskModel);


	void addMessageTaskUserAll(@Param("taskid")String taskid);


	void batchaddMessageTaskUser(Map<String,Object> map);


	List<String> getMessageTaskUserByTaskid(Map<String, Object> dataMap);


	MessageTaskModel getTaskMsgByTaskid(@Param("taskid")String taskid);


	void updateUsertaskmsg(Map<String, Object> dataMap);


	void updatetaskmsgliststate(Map<String, Object> dataMap);


	List<MessageTaskModel> getUnFinishTask();
	
	int getMessageTaskAllUserByTaskid(@Param("taskid")String taskid);
	int getMessageTaskSucessUserByTaskid(@Param("taskid")String taskid);
	int getMessageTaskFailUserByTaskid(@Param("taskid")String taskid);


	List<String> getUnFinishTaskUsers(String taskid);


	void deleteVip(@Param("userid")String userid, @Param("viptype")int viptype);





}
