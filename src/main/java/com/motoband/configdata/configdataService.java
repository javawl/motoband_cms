package com.motoband.configdata;





import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.motoband.user.userMapper;




@Component
public class configdataService {

	@Autowired
	private configdataMapper configdataMapper;
	@Autowired
	private userMapper userMapper;

	public ArrayList<configdata> getConfigList() {
		// TODO Auto-generated method stub
		return configdataMapper.getConfigList();
	}

	public int getUserUpdateCheck(String userGuid) {
		// TODO Auto-generated method stub
		return userMapper.checkPrivilege("configdata_upd", userGuid);
	}

	public int getUserDelCheck(String userGuid) {
		// TODO Auto-generated method stub
		return userMapper.checkPrivilege("configdata_del", userGuid);
	}

	public int getUserInsCheck(String userGuid) {
		// TODO Auto-generated method stub
		return userMapper.checkPrivilege("configdata_ins", userGuid);
	}

	public void insertConfigdata(configdata configdata) {
		// TODO Auto-generated method stub
		configdataMapper.insertConfigdata(configdata);
	}

	public com.motoband.configdata.configdata getConfigDataByID(
			int configdata_id) {
		// TODO Auto-generated method stub
		return configdataMapper.getConfigDataByID(configdata_id);
	}

	public void saveConfigdataMessage(configdata configdata) {
		// TODO Auto-generated method stub
		configdataMapper.saveConfigdataMessage(configdata);
		
	}

	public void delConfigdataMessage(String id) {
		// TODO Auto-generated method stub
		configdataMapper.delConfigdataMessage(id);
	}

	public String getConfigValueBykey(String configkey) {
		// TODO Auto-generated method stub
		return configdataMapper.getConfigValueBykey(configkey);
	}

	public void updateConfigValueByKey(String configvalue, String configkey) {
		configdataMapper.updateConfigValueByKey(configvalue,configkey);
		
	}


	

}
