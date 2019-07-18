package com.motoband.motouser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.motoband.Listeners.TaskImplementListener;

@Component
public class motouserService {
	@Autowired
	private motouserMapper motouserMapper;

	public List<ridelineModel> searchUserRideLine(String userid) {
		List<ridelineModel> ridelines = motouserMapper.searchUserRideLine(userid);
		return ridelines;
	}

	public List<ridedatelog> searchUserRideDateLog(String userid) {
		
		List<ridedatelog> ridedatelogs=motouserMapper.searchUserRideDateLog(userid);
		return ridedatelogs;
	}

	public List<garageModel> searchUserGarage(String userid) {
		List<garageModel> garageModels=motouserMapper.searchUserGarage(userid);
		return garageModels;
	}

	public List<MBUserModel> searchByusernickname(String usernickname) {
		List<MBUserModel> userlistList=motouserMapper.searchByusernickname(usernickname);
		return userlistList;
	}

	public MBUserModel getUserByID(String uid) {
		MBUserModel userModel=motouserMapper.getUserByID(uid);
		return userModel;
	}

	public int getUserUpdateCheck(String userGuid) {
		return motouserMapper.checkPrivilege("userrecommend_upd", userGuid);
	}

	public int getUserInsCheck(String userGuid) {
		return motouserMapper.checkPrivilege("userrecommend_del", userGuid);
	}

	public void addUserBoxApprove(String uid, String media) {
		motouserMapper.addUserBoxApprove(uid,media);
	}

	public long searchUserAddtime(String targetid) {
		
		return motouserMapper.searchUserAddtime(targetid);
	}

	public AchievementModel getachievement(String achid) {
		
		return motouserMapper.getachievement(achid);
	}

	public List<UserAchievementModel> getuserachievement(String userid) {
		return motouserMapper.getuserachievement(userid);
	}

	public List<MBUserModel> getUserByUserType(int usertype) {
		
		return motouserMapper.getUserByUserType(usertype) ;
	}

	
	public void updateUsertypeAndapprovedes(Integer usertype, String approvedes,String userid) {
		
		motouserMapper.updateUsertypeAndapprovedes(usertype,approvedes,userid);
	}

	public MBUserModel searchByusermobileno(String mobileno) {
		
		return motouserMapper.searchByusermobileno(mobileno);
	}

	public int getMotoUserUpdateCheck(String userGuid) {
		return motouserMapper.checkPrivilege("motouser_upd", userGuid);
	}

	public void updateUserPSWByUserid(String userid, String password) {
		motouserMapper.updateUserPSWByUserid(userid, password);
		
	}

  public List<MBUserlevelModel> getUserLevels() {
		
		return motouserMapper.getUserLevels();
	}
  public MBUserlevelModel getUserLevelByLevel( int level) {
	  
	  return motouserMapper.getUserLevelByLevel(level);
  }

public List<String> getAutomatedOperationUseridList() {
	
	return motouserMapper.getAutomatedOperationUseridList();
}

public int getOpenScreenListCount() {
	
	return motouserMapper.getOpenScreenListCount();
}

public List<OpenScreenModel> getOpenScreenList(int start, int limit, int order, String orderConditions) {
	return motouserMapper.getOpenScreenList(start,  limit, order,  orderConditions);
}

public void insertOrUpdateOpenscreen(OpenScreenModel openScreenModel) {
	
	motouserMapper.insertOrUpdateOpenscreen( openScreenModel);
}

public void delopenscreen(String osid) {
	motouserMapper.delopenscreen(osid) ;
	
}

public void updatePrivilegelongForUser(String userid, long privilegelong) {
	
	motouserMapper.updatePrivilegelongForUser( userid,  privilegelong);
}

public int checkvipmember(String userid, int viptype) {
	
	return motouserMapper.checkvipmember(userid, viptype);
}

public void insertVipCardMember(VipCardMemberModel vipCardMemberModel) {
	
	motouserMapper.insertVipCardMember( vipCardMemberModel);
}

public List<VipCardMemberModel> getVipCardMemberByuserid(String userid) {
	
	return motouserMapper.getVipCardMemberByuserid( userid);
}

public List<MessageTaskModel> getMessageTasks() {
	
	return motouserMapper.getMessageTasks();
}

public int getUserTaskCount(String taskid, int state) {
	return motouserMapper.getUserTaskCount( taskid,  state);
}

public void addMessageTask(MessageTaskModel messageTaskModel) {
	motouserMapper.addMessageTask(messageTaskModel);
	TaskImplementListener l=new TaskImplementListener();
	messageTaskModel.setTaskListener(l);
	messageTaskModel.implementing();
}

public void addMessageTaskUserAll(String taskid) {
	
	motouserMapper.addMessageTaskUserAll(taskid);
}

public void batchaddMessageTaskUser(Map<String,Object> map) {
	motouserMapper.batchaddMessageTaskUser(map);
	
}

public List<String> getMessageTaskUserByTaskid(Map<String,Object> dataMap) {
	
	return motouserMapper.getMessageTaskUserByTaskid(dataMap);
	
	
}

public MessageTaskModel getTaskMsgByTaskid(String taskid) {
	return motouserMapper.getTaskMsgByTaskid(taskid);
}

public void updateUsertaskmsg(Map<String, Object> dataMap) {
	motouserMapper.updateUsertaskmsg(dataMap);
}

public void updatetaskmsgliststate(Map<String, Object> dataMap) {
	motouserMapper.updatetaskmsgliststate(dataMap);
}

public List<MessageTaskModel> getUnFinishTask() {
	// TODO Auto-generated method stub
	return motouserMapper.getUnFinishTask();
}

public boolean checkTask(String taskid) {
	return motouserMapper.getMessageTaskAllUserByTaskid(taskid)==(motouserMapper.getMessageTaskSucessUserByTaskid(taskid)+motouserMapper.getMessageTaskFailUserByTaskid(taskid));
}

public List<String> getUnFinishTaskUsers(String taskid) {
	// TODO Auto-generated method stub
	return motouserMapper.getUnFinishTaskUsers(taskid);
}

public List<String> getTaskUsers(String taskid) {
	return motouserMapper.getTaskUsers(taskid);
}

public int getMessageTaskAllUserByTaskid(String taskid) {
	// TODO Auto-generated method stub
	return motouserMapper.getMessageTaskAllUserByTaskid(taskid);
}

public void deleteVip(String userid, int viptype) {
	motouserMapper.deleteVip(userid,viptype);
}

}
