package com.motoband.motobandgpmanage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.motoband.admanage.MotobandGPModel;
import com.motoband.motouser.AchievementModel;

@Component
public class MotobandgpService {
@Autowired
private MotobandgpMapper motobandgpMapper;

public int getMotobandgpCount() {
	return motobandgpMapper.getMotobandgpCount();
}

public ArrayList<MotobandGPModel> getMotobandgplist(int start, int limit,
		int order, String orderConditions) {
	return motobandgpMapper.getMotobandgplist(start,limit,order,orderConditions);
}

public MotobandGPModel getMotobandgpByid(String gpid) {
	return  motobandgpMapper.getMotobandgpByid(gpid);
}

public void updatemotobandgpByid(MotobandGPModel motobandgp) {
	motobandgpMapper.updatemotobandgpByid(motobandgp);
}

public List<AchievementModel> getachievementlist() {
	
	return motobandgpMapper.getachievementlist();
}

public void addmotobandgp(MotobandGPModel motobandgp) {
	motobandgpMapper.addmotobandgp(motobandgp);
	
}

public int getMgpJoingpCount(String gpid) {
	
	return motobandgpMapper.getMgpJoingpCount(gpid);
}

public int getMgpCompletedgpCount(String gpid) {
	
	return motobandgpMapper.getMgpCompletedgpCount(gpid);
}
}
