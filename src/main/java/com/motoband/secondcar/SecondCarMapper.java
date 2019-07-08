package com.motoband.secondcar;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface SecondCarMapper {

	int getSecondCarListCount(@Param("createuser")String createuser);

	List<SecondCarModel> getSecondCarList(@Param("start")int start, @Param("limit")int limit,@Param("order") int order, @Param("orderConditions")String orderConditions,@Param("createuser") String createuser);

	void addkeyword(@Param("secondcarid")String secondcarid, @Param("keyword")String keyword);

	int getSecondcarMainZoneListCount();

	List<SecondcarMainZoneModel> getSecondcarMainZoneList(@Param("start")int start, @Param("limit")int limit,@Param("order") int order, @Param("orderConditions")String orderConditions);

	SecondcarMainZoneModel getSecondcarMainZoneById(@Param("scmzid")String scmzid);

	void insertOrUpdateSecondcarMainZone(SecondcarMainZoneModel scmz);

	int getConsignmentListCount(@Param("starttime")long starttimelong, @Param("endtime")long endtimelong);

	List<ConsignmentModel> getConsignmentList(@Param("start")int start, @Param("limit")int limit,@Param("order") int order, @Param("orderConditions")String orderConditions, @Param("starttime")long starttimelong, @Param("endtime")long endtimelong);

	List<SecondcarMainZoneModel> getSecondcarMainZoneNameList();


	void changeState(@Param("secondcarid")String secondcarid, @Param("state")String state);

	void addRemarkandState(@Param("consignmentid")String consignmentid, @Param("state")String state, @Param("remark")String remark);

}
