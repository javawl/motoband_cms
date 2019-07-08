package com.motoband.clientupdate;



import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface clientupdateMapper {

	ArrayList<updateconf> getClientUpdateList(@Param("ctype")String ctype);

	void insertclientupdate(updateconf updateconf);

	updateconf getUpdateConf(@Param("id")int id);

	void updateUpdateConf(updateconf updateconf);

	void deleteclientupdate(@Param("id")int id);

//	void insertConfigdata(configdata configdata);
//
//	configdata getConfigDataByID(@Param("configdata_id")int configdata_id);
//
//	void saveConfigdataMessage(configdata configdata);
//
//	void delConfigdataMessage(@Param("id")String id);


	

}
