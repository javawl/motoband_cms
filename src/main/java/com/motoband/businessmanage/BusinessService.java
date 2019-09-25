package com.motoband.businessmanage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessService {
@Autowired private BusinessMapper businessMapper;

public int getApplyCount(int state) {
	
	return businessMapper.getApplyCount(state);
}

public List<BusinessApplyModel> getApplys(int start, int limit, int order,
		String orderConditions, int state) {
	
	return businessMapper.getApplys(start, limit,  order,
		 orderConditions, state);
}

public void changeApplyState(String state, String reason,String aid) {
	businessMapper.changeApplyState(state, reason,aid);
	
}

public BusinessApplyModel getApplyByAid(String aid) {
	
	return businessMapper.getApplyByAid( aid);
}

public void insertBusinessUser(BusinessUserModel businessUserModel) {
	
	businessMapper.insertBusinessUser( businessUserModel);
}

public void insertUserinfoApply(
		BusinessUserinfoApplyModel businessUserinfoApplyModel) {
	
	businessMapper.insertUserinfoApply(businessUserinfoApplyModel);
}

public void updateBusinessUser(Map<String, String> updatemap) {
	businessMapper.updateBusinessUser(updatemap);
	
}

public void insertOrupdateBusinessUserV_3_8_0(BusinessUserV3_8_0Model user) {
	businessMapper.insertOrupdateBusinessUserV_3_8_0(user);
}

public BusinessUserinfoApplyModel getBuserinfoApplyBybuserid(String buserid) {
	
	return businessMapper.getBuserinfoApplyBybuserid(buserid);
}

public void changeUserinfoApplyState(
		BusinessUserinfoApplyModel businessUserinfoApplyModel) {
	businessMapper.changeUserinfoApplyState( businessUserinfoApplyModel);
	
}

public int getUserinfoApplyCount() {
	
	return businessMapper.getUserinfoApplyCount();
}

public List<BusinessUserinfoApplyModel> getUserinfoApplys(int start, int limit,
		int order, String orderConditions) {
	
	return businessMapper.getUserinfoApplys(start, limit,order,orderConditions);
}

public void updateUsesrinfoApplyBigState(String buserid, int state, long updatetime) {
	
	businessMapper.updateUsesrinfoApplyBigState(buserid, state, updatetime);
}

public List<BusinessTypeModel> getBusinessTypeList() {
	
	return businessMapper.getBusinessTypeList();
}

public List<BusinessServiceModel> getBusinessServiceList() {
	
	return businessMapper.getBusinessServiceList();
}

public void updateApply(BusinessApplyModel businessApplyModel) {
	businessMapper.updateApply(businessApplyModel);
	
}

public int getBuserCount() {
	
	return businessMapper.getBuserCount();
}

public List<BusinessUserModel> getBusers(int start, int limit, int order,
		String orderConditions) {
	
	return businessMapper.getBusers(start,  limit,order,orderConditions);
}
public List<BusinessUserModel> getBusersBystate(int state) {
	
	return businessMapper.getBusersBystate(state);
}
public void resetBuserPassword(String userid, String password) {
	
	businessMapper.resetBuserPassword(userid, password);
}

public void changeToApprove(String userid, String lbsid) {
	businessMapper.changeToApprove(userid ,lbsid);
	
}

public BusinessUserModel getBuserByUsesrid(String userid) {
	
	return businessMapper.getBuserByUsesrid(userid);
}

public BusinessTypeModel getBusinessTypeModelByid(int btid) {
	
	return businessMapper.getBusinessTypeModelByid(btid);
}

public List<UsecarmaingroupModel> getUsecarmaingroupList(int type) {
	
	return businessMapper.getUsecarmaingroupList(type);
}

public void addtoUsecarmain(UsecarmainModel usecarmainModel) {
	businessMapper.addtoUsecarmain(usecarmainModel); 
	
}

public int getUsecarmainCount(int groupid) {
	
	return businessMapper.getUsecarmainCount(groupid);
}

public List<UsecarmainModel> getUsecarmainlist(int start, int limit, int order,
		String orderConditions, int groupid) {
	
	return businessMapper.getUsecarmainlist(start, limit, order,
			 orderConditions, groupid);
}

public void deleteUsecarmainByid(String id) {
	businessMapper.deleteUsecarmainByid( id);
}

public void updateUsecarmainOrderindex(String id, String orderindex) {
	businessMapper.updateUsecarmainOrderindex(id,orderindex);
}

public void uploadapprovepic(String userid, String approvepic) {
	businessMapper.uploadapprovepic(userid, approvepic);
	
}

public void deletbusercomment(String cid) {
	businessMapper.deletbusercomment(cid);
	
}

public UsecarmaingroupModel getUsecarmaingroupByid(String groupid) {
	return businessMapper.getUsecarmaingroupByid(groupid);
}

public void updateBuserCommentScore(String buserid, String score) {
	businessMapper.updateBuserCommentScore(buserid, score);
	
}

public boolean deleteAllBusinessActivity(String userid) {
	try {
		businessMapper.deleteAllBusinessActivity(userid);
		return true;
	} catch (Exception e) {
		return false;
	}
}

public boolean insertOrUpdateBusinessActivity(BusinessActivityModel businessActivityModel) {
	try {
		businessMapper.insertOrUpdateBusinessActivity(businessActivityModel);
		return true;
	} catch (Exception e) {
		return false;
	}
	
}

public int getRedirctShopCount() {
	return businessMapper.getRedirctShopCount();
}

public List<BusinessUserV3_8_0Model> getRedirctShopList(int start, int limit, int order, String orderConditions) {
	// TODO Auto-generated method stub
	return businessMapper.getRedirctShopList(start,limit,order,orderConditions);
}
public BusinessUserV3_8_0Model getgetBUserV3_8_0ByUserid(int buid) {
	// TODO Auto-generated method stub
	return businessMapper.getBUserV3_8_0ByUserid(buid);
}

}
