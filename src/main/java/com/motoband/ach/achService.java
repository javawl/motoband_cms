package com.motoband.ach;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class achService {

	@Autowired
	private achMapper achMapper;

	public ArrayList<achievement> getAchievements(String userGuid) {

		int sel = achMapper.checkPrivilege("motoach_sel", userGuid);
		if (sel != 0) {
			return achMapper.getAchievementList();
		} else {
			return null;
		}
	}

	public int getUserUpdateCheck(String userGuid) {

		return achMapper.checkPrivilege("motoach_upd", userGuid);
	}

	public int getUserDelCheck(String userGuid) {

		return achMapper.checkPrivilege("motoach_del", userGuid);
	}

	public int getUserInsCheck(String userGuid) {

		return achMapper.checkPrivilege("motoach_ins", userGuid);
	}

	public void addAchievement(achievement achievement) {
		achMapper.addAchievement(achievement);
		
	}

	public void updateAchievement(achievement achievement) {
		achMapper.updateAchievement(achievement);
		
	}



	
	

}
