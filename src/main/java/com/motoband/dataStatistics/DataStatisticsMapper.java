package com.motoband.dataStatistics;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface DataStatisticsMapper {

	int getOpenAppCount(@Param("startTime")long startTime, @Param("endTime")long endTime);

	int getDistinctUser(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getNewAddUser(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getRidedataCount(@Param("startTime")long startTime, @Param("endTime")long endTime);

	int getNewsCount(@Param("startTime")long startTime,@Param("endTime") long endTime, @Param("type")String type, @Param("recommend")String recommend);

	int getNewsLike(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getNewsCommentCount(@Param("startTime")long startTime, @Param("endTime")long endTime);

	void insertDataStatistics(DataStatisticsModel dataStatisticsModel);

	int getDataStatisticsCount(@Param("startTime")String startTime,@Param("endTime") String endTime,@Param("type")String type);

	ArrayList<DataStatisticsModel> getDataStatisticslist(@Param("startTime")String startTime,
			@Param("endTime")String endTime,@Param("type")String type);

	int getGiftCount(@Param("startTime")long startTime, @Param("endTime")long endTime);

	int getGiftDistinctUserCount(@Param("startTime")long startTime, @Param("endTime")long endTime);

	int getGiftDistinctNidCount(@Param("startTime")long startTime, @Param("endTime")long endTime);

	List<BusinessDataStatisticsModel> getBusersDataStatisticsCount(@Param("startTime")long startTime, @Param("endTime")long endTime);

	void insertBusinessDataStatistics(BusinessDataStatisticsModel businessDataStatisticsModel);

	int getBusinessDataStatisticsCount(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("buserid")String buserid);

	ArrayList<BusinessDataStatisticsModel> getBusinessDataStatisticslist(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("buserid")String buserid);

	int getNewsUserCount(@Param("startTime")long startTime,@Param("endTime") long endTime, @Param("type")String type, @Param("recommend")String recommend);

	int getGiftexchangeUserCount(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getGiftexchangeCount(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getGiftexchangeCreditCount(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getGiftgivenUserCount(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getGiftgivenGiftCount(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getBuserSumCount(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getBuserAddCount(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getSecondcarAddCount(@Param("startTime")long startTime,@Param("endTime") long endTime);

	int getSecondcarSumCount(@Param("startTime")long startTime,@Param("endTime") long endTime);

	long getSummbuserCount();

	void updateDataStatistics(DataStatisticsModel dataStatisticsModel);

	long getSummSecondCarCount();

	List<MonthActiveModel> getMonthActiveModels();
}
