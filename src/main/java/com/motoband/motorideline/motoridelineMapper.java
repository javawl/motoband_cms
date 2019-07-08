package com.motoband.motorideline;





import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface motoridelineMapper {

	rideline queryRidelineMessageById(@Param("ridelineid")String ridelineid);

	String getBrandNameById(@Param("brandid")int brandid);

	String getModelName(@Param("modelid")int modelid);

	
	

}
