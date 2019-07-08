package com.motoband.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class userService {

	@Autowired
	private userMapper userMapper;

	public ArrayList<userlist> getUserlistByuserGuid(String userGuid) {
		// TODO Auto-generated method stub
		// 权限判断
		int sel = userMapper.checkPrivilege("user_sel", userGuid);
		if (sel != 0) {
		//	System.out.println("有查询权限");
			return userMapper.getUserlist();
		} else {
		//	System.out.println("没有查询权限");
			return null;
		}
		
	}

	public int getUserUpdateCheck(String userGuid) {
		// TODO Auto-generated method stub
		return	userMapper.checkPrivilege("user_upd", userGuid);
	}

	public int getUserDelCheck(String userGuid) {
		// TODO Auto-generated method stub
		return userMapper.checkPrivilege("user_del", userGuid);
	}

	public int getUserInsCheck(String userGuid) {
		// TODO Auto-generated method stub
		return userMapper.checkPrivilege("user_ins", userGuid);
	}

	public void insNewUser(userModel userModel) {
		// TODO Auto-generated method stub
		userMapper.insNewUser(userModel);
	}

	public com.motoband.user.userModel getUserMessageByGuid(String user_guid) {
		// TODO Auto-generated method stub
		return userMapper.getUserMessageByGuid(user_guid);
	}

	public ArrayList<com.motoband.rolemanage.rolelist> getRoleList() {
		// TODO Auto-generated method stub
		return userMapper.getRoleList();
	}

	public void updateUserMessageByGuid(userModel userModel) {
		// TODO Auto-generated method stub
		userMapper.updateUserMessageByGuid(userModel);
		
		
	}

	public com.motoband.user.userModel checkLoginName(String user_loginname) {
		// TODO Auto-generated method stub
		return userMapper.checkLoginName(user_loginname);
	}

	public void updateOwnMessage(String user_guid, String user_name,
			String user_sex, String user_tel) {
		// TODO Auto-generated method stub
		userMapper.updateOwnMessage(user_guid,user_name,user_sex,user_tel);
	}

	public com.motoband.user.userModel checkPasswordByGuid(String user_guid,String oldpassword) {
		// TODO Auto-generated method stub
		return userMapper.checkPasswordByGuid(user_guid,oldpassword);
	}

	public void updatePasswordByGuid(String user_guid, String newpassword) {
		// TODO Auto-generated method stub
		userMapper.updatePasswordByGuid(user_guid,newpassword);
	}

	public void delUserMessage(String user_guid) {
		// TODO Auto-generated method stub
		userMapper.delUserMessage(user_guid);
	}

	public int getUserPushCheck(String user_guid) {
		// TODO Auto-generated method stub
		return userMapper.checkPrivilege("box_push", user_guid);
	}

	public void resetUserPsw(String user_guid, String user_password) {
		// TODO Auto-generated method stub
		userMapper.resetUserPsw(user_guid,user_password);
	}
	

}
