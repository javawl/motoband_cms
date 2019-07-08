package com.motoband.admin.admin;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface AdminMapper {

	Admin getAdminMessageByLoginnameAndPassword(@Param("username")String username,@Param("pwd")String pwd);

	int insertAdmin(Admin admin);

	Admin getAdminMessageByLoginname(@Param("username")String username);

	int updateAdmin(Admin admin);
	
	ArrayList<Privilege> getPrivilegesByloginname(@Param("loginname")String loginname);

}
