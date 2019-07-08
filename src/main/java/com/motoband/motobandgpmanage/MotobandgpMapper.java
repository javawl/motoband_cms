package com.motoband.motobandgpmanage;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motoband.admanage.MotobandGPModel;
import com.motoband.motouser.AchievementModel;
import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface MotobandgpMapper {

	int getMotobandgpCount();

	ArrayList<MotobandGPModel> getMotobandgplist(@Param("start")int start, @Param("limit")int limit,@Param("order") int order,@Param("orderConditions") String orderConditions);

	void updatemotobandgpByid(MotobandGPModel motobandgp);

	MotobandGPModel getMotobandgpByid(@Param("gpid")String gpid);

	List<AchievementModel> getachievementlist();

	void addmotobandgp(MotobandGPModel motobandgp);

	int getMgpJoingpCount(@Param("gpid")String gpid);

	int getMgpCompletedgpCount(@Param("gpid")String gpid);

}
