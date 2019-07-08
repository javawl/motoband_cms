package com.motoband.ach;




import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface achMapper {
	int checkPrivilege(@Param("privilege_key")String privilege_key, @Param("userGuid")String userGuid);

	ArrayList<achievement> getAchievementList();

	void addAchievement(achievement achievement);

	void updateAchievement(achievement achievement);
	
	

}
