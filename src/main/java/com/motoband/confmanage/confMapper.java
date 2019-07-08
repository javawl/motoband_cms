package com.motoband.confmanage;



import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.admin.admin.Privilege;
import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface confMapper {

	Integer checkPrivilege(@Param("privilege_key")String privilege_key,@Param("userGuid")String userGuid);

	ArrayList<conflist> getConflist();

	void insertPrivilege(conflist conflistmain);

	Privilege selPrivilegeByGuid(@Param("conf_guid")String conf_guid);

	ArrayList<Privilege> selchildPrivilegesByGuid(@Param("conf_guid")String conf_guid);

	void saveConfByGuid(@Param("conf_guid")String conf_guid, @Param("conf_name")String conf_name, @Param("conf_key")String conf_key,
			@Param("conf_des")String conf_des);

	void saveChildConfByPartentGuid(@Param("conf_guid")String conf_guid, @Param("conf_key")String conf_key);

	void saveChildConfByChild(@Param("conf_guid")String conf_guid, @Param("conf_name")String conf_name, @Param("conf_key")String conf_key,
			@Param("conf_des")String conf_des);

	void insertChildConf(@Param("conf_guid")String conf_guid, @Param("conf_name")String conf_name, @Param("conf_key")String conf_key,
			@Param("conf_parentguid")String conf_parentguid, @Param("conf_des")String conf_des);

	void delChildConf(@Param("conf_guid")String conf_guid);

	void delChildConf_Role(@Param("conf_guid")String conf_guid);

	int checkNewConfKey(@Param("conf_key")String conf_key);

	int checkNewConfName(@Param("conf_name")String conf_name);

	int checkNewParentConfKey(@Param("parentconf_key")String parentconf_key,@Param("parentconf_guid")String parentconf_guid);

	int checkNewParentConfName(@Param("parentconf_name")String parentconf_name, @Param("parentconf_guid")String parentconf_guid);

	int checkNewChildConfKey(@Param("childchonf_guid")String childconf_guid, @Param("childconf_key")String childconf_key);

	int checkNewChildConfName(@Param("childconf_name")String childconf_name, @Param("childconf_guid")String childconf_guid);

	int checkchildname(@Param("parent_guid")String parent_guid, @Param("childname")String childname);

	int checkchildkey(@Param("parent_guid")String parent_guid,@Param("childconf_key") String childconf_key);


}
