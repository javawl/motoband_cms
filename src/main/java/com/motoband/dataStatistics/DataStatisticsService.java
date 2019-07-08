package com.motoband.dataStatistics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataStatisticsService {
@Autowired
private DataStatisticsMapper dataStatisticsMapper;

public int getOpenAppCount(long startTime, long endTime) {
	return dataStatisticsMapper.getOpenAppCount(startTime,endTime);
}

public int getDistinctUser(long startTime, long endTime) {
	return dataStatisticsMapper.getDistinctUser(startTime, endTime);
}

public int getNewAddUser(long startTime, long endTime) {
	return dataStatisticsMapper.getNewAddUser( startTime,  endTime);
}

public int getRidedataCount(long startTime, long endTime) {
	return dataStatisticsMapper.getRidedataCount(startTime,endTime);
}

public int getNewsCount(long startTime, long endTime, String type,String recommend) {
	return dataStatisticsMapper.getNewsCount( startTime, endTime,  type,recommend);
}

public int getNewsLike(long startTime, long endTime) {
	return dataStatisticsMapper.getNewsLike(startTime,endTime);
}

public int getNewsCommentCount(long startTime, long endTime) {
	return dataStatisticsMapper.getNewsCommentCount(startTime,endTime);
}

public void insertDataStatistics(DataStatisticsModel dataStatisticsModel) {
	dataStatisticsMapper.insertDataStatistics(dataStatisticsModel);
}

public int getDataStatisticsCount(String startTime, String endTime,String type) {
	
	return dataStatisticsMapper.getDataStatisticsCount(startTime, endTime, type);
}

public ArrayList<DataStatisticsModel> getDataStatisticslist(String startTime,
		String endTime,String type) {
	
	return dataStatisticsMapper.getDataStatisticslist(startTime,
			endTime, type);
}

public int getGiftCount(long startTime, long endTime) {
	
	return dataStatisticsMapper.getGiftCount(startTime,endTime);
}

public int getGiftDistinctUserCount(long startTime, long endTime) {
	
	return dataStatisticsMapper.getGiftDistinctUserCount(startTime, endTime);
}

public int getGiftDistinctNidCount(long startTime, long endTime) {
	
	return dataStatisticsMapper.getGiftDistinctNidCount(startTime,endTime);
}

public List<BusinessDataStatisticsModel> getBusersDataStatisticsCount(long startTime, long endTime) {
	
	return dataStatisticsMapper.getBusersDataStatisticsCount(startTime,endTime);
}

public void insertBusinessDataStatistics(BusinessDataStatisticsModel businessDataStatisticsModel) {
	dataStatisticsMapper.insertBusinessDataStatistics(businessDataStatisticsModel);
	
}

public int getBusinessDataStatisticsCount(String startTime, String endTime, String buserid) {
	return dataStatisticsMapper.getBusinessDataStatisticsCount( startTime,  endTime,buserid);
}

public ArrayList<BusinessDataStatisticsModel> getBusinessDataStatisticslist(String startTime, String endTime, String buserid) {
	return dataStatisticsMapper.getBusinessDataStatisticslist(startTime,  endTime,  buserid);
}

public int getNewsUserCount(long startTime, long endTime, String type,String recommend) {
	
	return dataStatisticsMapper.getNewsUserCount(startTime,endTime,type, recommend) ;
}

public int getGiftexchangeUserCount(long startTime, long endTime) {
	
	return dataStatisticsMapper.getGiftexchangeUserCount(startTime,endTime);
}

public int getGiftexchangeCount(long startTime, long endTime) {
	
	return dataStatisticsMapper.getGiftexchangeCount(startTime,endTime);
}

public int getGiftexchangeCreditCount(long startTime, long endTime) {
	
	return dataStatisticsMapper.getGiftexchangeCreditCount(startTime,endTime);
}

public int getGiftgivenUserCount(long startTime, long endTime) {
	
	return dataStatisticsMapper.getGiftgivenUserCount(startTime,endTime) ;
}

public int getGiftgivenGiftCount(long startTime, long endTime) {
	
	return dataStatisticsMapper.getGiftgivenGiftCount(startTime,endTime);
}

public int getBuserSumCount(long startTime, long endTime) {
	return dataStatisticsMapper.getBuserSumCount(startTime, endTime);
}

public int getBuserAddCount(long startTime, long endTime) {
	return dataStatisticsMapper.getBuserAddCount(startTime,endTime);
}

public int getSecondcarSumCount(long startTime, long endTime) {
	return dataStatisticsMapper.getSecondcarSumCount(startTime, endTime);
}

public int getSecondcarAddCount(long startTime, long endTime) {
	return dataStatisticsMapper.getSecondcarAddCount(startTime,endTime);
}

public long getSummbuserCount() {
	return dataStatisticsMapper.getSummbuserCount();
}

public void updateDataStatistics(DataStatisticsModel dataStatisticsModel) {
	dataStatisticsMapper.updateDataStatistics( dataStatisticsModel);
	
}

public long getSummSecondCarCount() {
	return dataStatisticsMapper.getSummSecondCarCount();
}

public List<MonthActiveModel> getMonthActiveModels() {
	 return dataStatisticsMapper.getMonthActiveModels();
}

}
