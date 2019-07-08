package com.motoband.rolemanage;



import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.admin.admin.Privilege;
import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface roleMapper {

	Integer checkPrivilege(@Param("privilege_key")String privilege_key,@Param("userGuid")String userGuid);

	ArrayList<rolelist> getRolelist();

	ArrayList<parentPrivilegelist> getParentPrivilegelist(@Param("role_guid")String role_guid);

	ArrayList<Privilege> getParentPriviegesNoHaveChild();

	ArrayList<Privilegelist> getChildPrivilegelist();

	ArrayList<RolePrivilegeListByroleguid> getRolePrivilegeListByroleguid(
			@Param("role_guid")String role_guid);

	String getRolenameByRoleguid(@Param("role_guid")String role_guid);

	String getParentGuidByChildGuid(@Param("rolestring")String rolestring);

	void delRoleConf(@Param("role_guid")String role_guid);

	void insRoleConf(@Param("PRguid")String PRguid,@Param("role_guid")String role_guid, @Param("stri")String stri);

	void insNewRole(@Param("role_guid")String role_guid, @Param("role_name")String role_name,@Param("role_des")String role_des);

	void delRoleMessage(@Param("role_guid")String role_guid);

	void delRolePrivilege(@Param("role_guid")String role_guid);

	void updateUserRole(@Param("role_guid")String role_guid);

	int checkNewRoleName(@Param("newrolename")String newrolename);

	void checkNewRoleMessage(@Param("role_guid")String role_guid,@Param("newrole_name")String newrole_name,
			@Param("newrole_des")String newrole_des);

	int  checkRoleName(@Param("role_guid")String role_guid,@Param("newrole_name")String newrole_name);

}
