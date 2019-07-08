package com.motoband.clientupdate;





import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.motoband.user.userMapper;




@Component
public class clientupdateService {

	@Autowired
	private clientupdateMapper clientupdateMapper;
	@Autowired
	private userMapper userMapper;

	public ArrayList<updateconf> getClientUpdateList(String ctype) {
		
		return clientupdateMapper.getClientUpdateList(ctype);
	}

	public int getUserUpdateCheck(String userGuid) {
		
		return userMapper.checkPrivilege("clientupdate_upd", userGuid);
	}

	public int getUserDelCheck(String userGuid) {
		
		return userMapper.checkPrivilege("clientupdate_del", userGuid);
	}

	public int getUserInsCheck(String userGuid) {
		
		return userMapper.checkPrivilege("clientupdate_ins", userGuid);
	}

	public void insertclientupdate(updateconf updateconf) {
		clientupdateMapper.insertclientupdate(updateconf);
		
	}

	public updateconf getUpdateConf(int id) {
		return clientupdateMapper.getUpdateConf(id);
	}

	public void updateUpdateConf(updateconf updateconf) {
		clientupdateMapper.updateUpdateConf(updateconf);
		
	}

	public void deleteclientupdate(int id) {
		clientupdateMapper.deleteclientupdate(id);
		
	}

	

//	public void insertConfigdata(configdata configdata) {
//		// TODO Auto-generated method stub
//		clientupdateMapper.insertConfigdata(configdata);
//	}
//
//	public com.motoband.configdata.configdata getConfigDataByID(
//			int configdata_id) {
//		// TODO Auto-generated method stub
//		return clientupdateMapper.getConfigDataByID(configdata_id);
//	}
//
//	public void saveConfigdataMessage(configdata configdata) {
//		// TODO Auto-generated method stub
//		clientupdateMapper.saveConfigdataMessage(configdata);
//		
//	}
//
//	public void delConfigdataMessage(String id) {
//		// TODO Auto-generated method stub
//		clientupdateMapper.delConfigdataMessage(id);
//	}


	

}
