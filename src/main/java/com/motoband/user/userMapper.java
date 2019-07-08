package com.motoband.user;



import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.rolemanage.rolelist;
import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface userMapper {

	Integer checkPrivilege(@Param("privilege_key")String privilege_key,@Param("userGuid")String userGuid);

	ArrayList<userlist> getUserlist();

	void insNewUser(userModel userModel);

	userModel getUserMessageByGuid(@Param("user_guid")String user_guid);

	ArrayList<rolelist> getRoleList();

	void updateUserMessageByGuid(userModel userModel);

	userModel checkLoginName(@Param("user_loginname")String user_loginname);

	void updateOwnMessage(@Param("user_guid")String user_guid, @Param("user_name")String user_name, @Param("user_sex")String user_sex,
			@Param("user_tel")String user_tel);

	userModel checkPasswordByGuid(@Param("user_guid")String user_guid,@Param("oldpassword")String oldpassword);

	void updatePasswordByGuid(@Param("user_guid")String user_guid,@Param("newpassword")String newpassword);

	void delUserMessage(@Param("user_guid")String user_guid);

	void resetUserPsw(@Param("user_guid")String user_guid, @Param("user_password")String user_password);

}
