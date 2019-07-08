package com.motoband.activitymanage;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface ActivityMapper {

	int getActivityListCount();

	ArrayList<ActivityModel> getActivityList(@Param("userGuid")String userGuid, @Param("start")int start, @Param("limit")int limit,
			@Param("order")int order);

	ActivityModel getActivityByaid(@Param("aid")String aid);

	void updateActivity(ActivityModel activityModel);

	void insActivity(ActivityModel activityModel);

}
