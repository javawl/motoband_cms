package com.motoband.qiji;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface QiJiMapper {


	ArrayList<QiJiInfoModel> getList(@Param("start")int start, @Param("limit")int limit,@Param("order") int order,
			@Param("orderConditions")String orderConditions, @Param("province") String province, @Param("city") String city);
	
//	Integer getCount();
	Integer getCount(@Param("province")String province, @Param("city")String city);

	QiJiInfoModel getQijiModelById(@Param("sid")String sid);

	Integer insertOrUpdateQijiModel(QiJiInfoModel model);

	int delopenqiji(String sid);

	List<String> getProvinceList();
	
	List<String> getCityBypProvince(String province);




}
