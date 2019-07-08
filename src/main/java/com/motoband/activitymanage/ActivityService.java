package com.motoband.activitymanage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityService {
@Autowired
private ActivityMapper activityMapper;

public int getActivityListCount() {
	return activityMapper.getActivityListCount();
}

public ArrayList<ActivityModel> getActivityList(String userGuid, int start,
		int limit, int order) {
	return activityMapper.getActivityList( userGuid, start,
			limit, order);
}

public ActivityModel getActivityByaid(String aid) {
	
	return activityMapper.getActivityByaid(aid);
}

public void updateActivity(ActivityModel activityModel) {
	activityMapper.updateActivity(activityModel);
}

public void insActivity(ActivityModel activityModel) {
	activityMapper.insActivity(activityModel);
	
}
}
