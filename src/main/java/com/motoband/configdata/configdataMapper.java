package com.motoband.configdata;



import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface configdataMapper {

	ArrayList<configdata> getConfigList();

	void insertConfigdata(configdata configdata);

	configdata getConfigDataByID(@Param("configdata_id")int configdata_id);

	void saveConfigdataMessage(configdata configdata);

	void delConfigdataMessage(@Param("id")String id);

	String getConfigValueBykey(@Param("configkey")String configkey);

	void updateConfigValueByKey(@Param("configvalue")String configvalue,@Param("configkey") String configkey);


	

}
