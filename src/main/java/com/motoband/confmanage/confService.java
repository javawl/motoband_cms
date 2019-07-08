package com.motoband.confmanage;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.motoband.admin.admin.Privilege;

@Component
public class confService {

	@Autowired
	private confMapper confMapper;

	public ArrayList<com.motoband.confmanage.conflist> getConflistByuserGuid(
			String userGuid) {
		// TODO Auto-generated method stub
		int sel = confMapper.checkPrivilege("conf_sel", userGuid);
		if (sel != 0) {
		//	System.out.println("有查询权限");
			return confMapper.getConflist();
		} else {
		//	System.out.println("没有查询权限");
			return null;
		}
	}

	public int getConfUpdateCheck(String userGuid) {
		// TODO Auto-generated method stub
		return confMapper.checkPrivilege("conf_upd", userGuid);
	}

	public int getConfDelCheck(String userGuid) {
		// TODO Auto-generated method stub
		return confMapper.checkPrivilege("conf_del", userGuid);
	}

	public int getConfInsCheck(String userGuid) {
		// TODO Auto-generated method stub
		return confMapper.checkPrivilege("conf_ins", userGuid);
	}
@Transactional
	public void insertPrivilege(conflist conflistmain1, conflist conflistmain2, conflist conflistmain3, conflist conflistmain4, conflist conflistmain5) {
		// TODO Auto-generated method stub
		confMapper.insertPrivilege(conflistmain1);
		confMapper.insertPrivilege(conflistmain2);
		confMapper.insertPrivilege(conflistmain3);
		confMapper.insertPrivilege(conflistmain4);
		confMapper.insertPrivilege(conflistmain5);
	}

	public Privilege selPrivilegeByGuid(String conf_guid) {
		// TODO Auto-generated method stub
		return confMapper.selPrivilegeByGuid(conf_guid);
	}

	public ArrayList<Privilege> selchildPrivilegesByGuid(String conf_guid) {
		// TODO Auto-generated method stub
		return confMapper.selchildPrivilegesByGuid(conf_guid);
	}

	public void saveConfByGuid(String conf_guid, String conf_name,
			String conf_key, String conf_des) {
		// TODO Auto-generated method stub
		confMapper.saveConfByGuid(conf_guid,conf_name,conf_key,conf_des);
		
		
	}
	public void saveChildConfByPartentGuid(String conf_guid,String conf_key) {
		confMapper.saveChildConfByPartentGuid(conf_guid, conf_key);
	}

	public void saveChildConfByChild(String conf_guid, String conf_name,
			String conf_key, String conf_des) {
		// TODO Auto-generated method stub
		confMapper.saveChildConfByChild(conf_guid,conf_name,conf_key,conf_des);
		
	}

	public void insertChildConf(String conf_guid, String conf_name,
			String conf_key, String conf_parentguid, String conf_des) {
		// TODO Auto-generated method stub
		confMapper.insertChildConf(conf_guid,conf_name,conf_key,conf_parentguid,conf_des);
		
	}
@Transactional
	public void delChildConf(String conf_guid) {
		// TODO Auto-generated method stub
		confMapper.delChildConf(conf_guid);
		confMapper.delChildConf_Role(conf_guid);
		
	}

	public int checkNewConfKey(String conf_key) {
		// TODO Auto-generated method stub
		return confMapper.checkNewConfKey(conf_key);
	}

	public int checkNewConfName(String conf_name) {
		// TODO Auto-generated method stub
		return confMapper.checkNewConfName(conf_name);
	}

	public int checkNewParentConfKey(String parentconf_key,String parentconf_guid) {
		// TODO Auto-generated method stub
		return confMapper.checkNewParentConfKey(parentconf_key,parentconf_guid);
	}

	public int checkNewParentConfName(String parentconf_name,
			String parentconf_guid) {
		// TODO Auto-generated method stub
		return confMapper.checkNewParentConfName(parentconf_name,parentconf_guid);
	}

	public int checkNewChildConfKey(String childconf_key, String childconf_guid) {
		// TODO Auto-generated method stub
		if ("manage".equals(childconf_key)) {
			return 1;
		} else {
			return confMapper.checkNewChildConfKey(childconf_guid,childconf_key);
		}
		
	}

	public int checkNewChildConfName(String childconf_name,
			String childconf_guid) {
		// TODO Auto-generated method stub
		return confMapper.checkNewChildConfName(childconf_name,childconf_guid);
	}

	public int checkchildname(String parent_guid, String childname) {
		// TODO Auto-generated method stub
		return confMapper.checkchildname(parent_guid,childname);
	}

	public int checkchildkey(String parent_guid, String childconf_key) {
		// TODO Auto-generated method stub
		return confMapper.checkchildkey(parent_guid, childconf_key);
	}

	
	

}
