package com.motoband.credit;

import java.util.HashMap;
import java.util.Map;

public class CreditLogModel {

	public String creditlogid;
	public long logtime;
	public byte actiontype;// 0 增加 1减少
	public long score;
	public int credittype;
	public String datades;
	public String userid;
	public String title;

	public int subffix;


	public String getCreditlogid() {
		return creditlogid;
	}

	public void setCreditlogid(String creditlogid) {
		this.creditlogid = creditlogid;
	}

	public long getLogtime() {
		return logtime;
	}

	public void setLogtime(long logtime) {
		this.logtime = logtime;
	}

	public byte getActiontype() {
		return actiontype;
	}

	public void setActiontype(byte actiontype) {
		this.actiontype = actiontype;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public int getCredittype() {
		return credittype;
	}

	public void setCredittype(int credittype) {
		this.credittype = credittype;
	}

	public String getDatades() {
		return datades;
	}

	public void setDatades(String datades) {
		this.datades = datades;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSubffix() {
		return subffix;
	}

	public void setSubffix(int subffix) {
		this.subffix = subffix;
	}

	public static Map<String, String> convertToMap(CreditLogModel model) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("creditlogid", model.creditlogid == null ? "" : model.creditlogid);
		map.put("logtime", String.valueOf(model.logtime));
		map.put("actiontype", String.valueOf(model.actiontype));
		map.put("score", String.valueOf(model.score));
		map.put("credittype", String.valueOf(model.credittype));
		map.put("datades", model.datades == null ? "" : model.datades);
		map.put("userid", model.userid == null ? "" : model.userid);
		map.put("title", model.title == null ? "" : model.title);

		return map;
	}

	public static CreditLogModel convertToModel(Map<String, String> map) {
		if (map == null) {
			return null;
		}

		CreditLogModel model = new CreditLogModel();

		if (map.containsKey("creditlogid")) {
			if (!map.get("creditlogid").isEmpty())
				model.creditlogid = map.get("creditlogid");
		}

		if (map.containsKey("logtime")) {
			if (!map.get("logtime").isEmpty())
				model.logtime = Long.valueOf(map.get("logtime"));
		}

		if (map.containsKey("actiontype")) {
			if (!map.get("actiontype").isEmpty())
				model.actiontype = Byte.valueOf(map.get("actiontype"));
		}

		if (map.containsKey("score")) {
			if (!map.get("score").isEmpty())
				model.score = Long.valueOf(map.get("score"));
		}

		if (map.containsKey("credittype")) {
			if (!map.get("credittype").isEmpty())
				model.credittype = Integer.valueOf(map.get("credittype"));
		}

		if (map.containsKey("datades")) {
			if (!map.get("datades").isEmpty())
				model.datades = map.get("datades");
		}
		if (map.containsKey("userid")) {
			if (!map.get("userid").isEmpty())
				model.userid = map.get("userid");
		}
		if (map.containsKey("title")) {
			if (!map.get("title").isEmpty())
				model.title = map.get("title");
		}
		return model;
	}

}
