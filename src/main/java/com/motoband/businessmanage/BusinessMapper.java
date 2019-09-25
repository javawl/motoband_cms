package com.motoband.businessmanage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface BusinessMapper {

	int getApplyCount(@Param("state")int state);

	List<BusinessApplyModel> getApplys(@Param("start")int start, @Param("limit")int limit, @Param("order")int order,
			@Param("orderConditions")String orderConditions, @Param("state")int state);

	void changeApplyState(@Param("state")String state, @Param("reason")String reason,@Param("aid")String aid);

	BusinessApplyModel getApplyByAid(@Param("aid")String aid);

	void insertBusinessUser(BusinessUserModel businessUserModel);

	void insertUserinfoApply(
			BusinessUserinfoApplyModel businessUserinfoApplyModel);

	void updateBusinessUser(Map<String, String> updatemap);

	BusinessUserinfoApplyModel getBuserinfoApplyBybuserid(@Param("userid")String buserid);

	void changeUserinfoApplyState(BusinessUserinfoApplyModel businessUserinfoApplyModel);

	int getUserinfoApplyCount();

	List<BusinessUserinfoApplyModel> getUserinfoApplys(@Param("start")int start, @Param("limit")int limit, @Param("order")int order,
			@Param("orderConditions")String orderConditions);

	void updateUsesrinfoApplyBigState(@Param("userid")String buserid, @Param("state")int state,
			@Param("updatetime")long updatetime);

	List<BusinessTypeModel> getBusinessTypeList();

	List<BusinessServiceModel> getBusinessServiceList();

	void updateApply(BusinessApplyModel businessApplyModel);

	int getBuserCount();

	List<BusinessUserModel> getBusers(@Param("start")int start, @Param("limit")int limit, @Param("order")int order,
			@Param("orderConditions")String orderConditions);

	void resetBuserPassword(@Param("userid")String userid, @Param("password")String password);

	void changeToApprove(@Param("userid")String userid, @Param("lbsid")String lbsid);

	BusinessUserModel getBuserByUsesrid(@Param("userid")String userid);

	BusinessTypeModel getBusinessTypeModelByid(@Param("btid")int btid);

	List<UsecarmaingroupModel> getUsecarmaingroupList(@Param("type")int type);

	void addtoUsecarmain(UsecarmainModel usecarmainModel);

	int getUsecarmainCount(@Param("groupid")int groupid);

	List<UsecarmainModel> getUsecarmainlist(@Param("start")int start, @Param("limit")int limit, @Param("order")int order,
			@Param("orderConditions")String orderConditions, @Param("groupid")int groupid);

	void deleteUsecarmainByid(@Param("id")String id);

	void updateUsecarmainOrderindex(@Param("id")String id, @Param("orderindex")String orderindex);

	void uploadapprovepic(@Param("userid")String userid, @Param("approvepic")String approvepic);

	List<BusinessUserModel> getBusersBystate(@Param("state")int state);

	void deletbusercomment(@Param("cid")String cid);

	UsecarmaingroupModel getUsecarmaingroupByid(@Param("groupid")String groupid);

	void updateBuserCommentScore(@Param("buserid")String buserid, @Param("score")String score);

	void deleteAllBusinessActivity(@Param("buserid")String userid);

	void insertOrUpdateBusinessActivity(BusinessActivityModel businessActivityModel);

	int getRedirctShopCount();

	List<BusinessUserV3_8_0Model> getRedirctShopList(@Param("start")int start, @Param("limit")int limit, @Param("order")int order,
			@Param("orderConditions")String orderConditions);

	BusinessUserV3_8_0Model getBUserV3_8_0ByUserid(@Param("buid")int buid);

	void insertOrupdateBusinessUserV_3_8_0(BusinessUserV3_8_0Model user);
    
}
