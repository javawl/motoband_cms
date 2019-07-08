package com.motoband.util.tim;

import java.util.ArrayList;
import java.util.HashMap;

public class UserProfile {

	public String To_Account;
	public ArrayList<HashMap<String, String>> ProfileItem;
	public String ResultCode;
	public String ResultInfo;

	@Override
	public String toString() {
		return "UserProfile [To_Account=" + To_Account + ", ProfileItem=" + ProfileItem + ", ResultCode=" + ResultCode
				+ ", ResultInfo=" + ResultInfo + "]";
	}
	

}
