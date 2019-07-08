package com.motoband.rolemanage;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.motoband.admin.admin.Privilege;

@Component
public class roleService {

	@Autowired
	private roleMapper roleMapper;

	public ArrayList<rolelist> getRolelistByuserGuid(String userGuid) {
		// TODO Auto-generated method stub
		// 权限判断
		int sel = roleMapper.checkPrivilege("role_sel", userGuid);
		if (sel != 0) {
		//	System.out.println("有查询权限");
			return roleMapper.getRolelist();
		} else {
		//	System.out.println("没有查询权限");
			return null;
		}
		
		
	}

	public int getRoleUpdateCheck(String userGuid) {
		// TODO Auto-generated method stub
		return	roleMapper.checkPrivilege("role_upd", userGuid);
	}

	public int getRoleDelCheck(String userGuid) {
		// TODO Auto-generated method stub
		return roleMapper.checkPrivilege("role_del", userGuid);
	}

	public int getRoleInsCheck(String userGuid) {
		// TODO Auto-generated method stub
		return roleMapper.checkPrivilege("role_ins", userGuid);
	}

	public ArrayList<parentPrivilegelist> getParentPrivilegelist(String role_guid) {
		// TODO Auto-generated method stub
		return roleMapper.getParentPrivilegelist(role_guid);
	}

	public ArrayList<Privilege> getParentPriviegesNoHaveChild() {
		// TODO Auto-generated method stub
		return roleMapper.getParentPriviegesNoHaveChild();
	}

	public ArrayList<Privilegelist> getChildPrivilegelist() {
		// TODO Auto-generated method stub
		return roleMapper.getChildPrivilegelist();
	}

	public ArrayList<RolePrivilegeListByroleguid> getRolePrivilegeListByroleguid(
			String role_guid) {
		// TODO Auto-generated method stub
		return roleMapper.getRolePrivilegeListByroleguid(role_guid);
	}

	public String getRolenameByRoleguid(String role_guid) {
		// TODO Auto-generated method stub
		return roleMapper.getRolenameByRoleguid(role_guid);
	}

	public String getParentGuidByChildGuid(String rolestring) {
		// TODO Auto-generated method stub
		return roleMapper.getParentGuidByChildGuid(rolestring);
	}

	public void delRoleConf(String role_guid) {
		// TODO Auto-generated method stub
		roleMapper.delRoleConf(role_guid);
		
	}

	public void insRoleConf(String role_guid, String stri) {
		// TODO Auto-generated method stub
		String PRguid=UUID.randomUUID().toString();
		roleMapper.insRoleConf(PRguid,role_guid,stri);
		
	}

	public void insNewRole(String role_guid, String role_name, String role_des) {
		// TODO Auto-generated method stub
		roleMapper.insNewRole(role_guid,role_name,role_des);
	}

	@Transactional
	public void delRoleMessage(String role_guid) {
		// TODO Auto-generated method stub
		roleMapper.delRoleMessage(role_guid);
		roleMapper.delRolePrivilege(role_guid);
		roleMapper.updateUserRole(role_guid);
		
	}

	public int checkNewRoleName(String newrolename) {
		// TODO Auto-generated method stub
		return roleMapper.checkNewRoleName(newrolename);
	}

	public void saveNewRoleMessage(String role_guid, String newrole_name,
			String newrole_des) {
		// TODO Auto-generated method stub
		roleMapper.checkNewRoleMessage(role_guid,newrole_name,newrole_des);
	}

	public int checkRoleName(String role_guid, String newrole_name) {
		// TODO Auto-generated method stub
		return roleMapper.checkRoleName(role_guid,newrole_name);
	}

	/*public void delRolePrivilege(String role_guid) {
		// TODO Auto-generated method stub
	
	}

	public void updateUserRole(String role_guid) {
		// TODO Auto-generated method stub
		
	}*/
	

}
