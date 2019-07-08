package com.motoband.tribal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class TribalModel {
	public String tribalid;// 部落ID uuid
	public String name;// 部落名称
	public String logopic;// 部落logo图片地址
	public String bannerpic;// 部落banner图片地址
	public List<String> mainadmins;// 部落主管理员列表
	public List<String> viceadmins;// 部落副管理员列表
	public String des;// 部落介绍
	public int type;// 部落类型（0 地域 1品牌 2主题）
	public long followcount;// 粉丝数
	public long todayupdatecount;// 今日更新数
	public long addtime;// 添加时间
	public long lastupdatetime;// 最后修改时间

	public String mainadminsstr;
	public String viceadminsstr;

	//扩展
	public long orderindex;//客户端不需要，仅用于排序

	

	

	public static Map<String, String> convertToMap(TribalModel tribalModel) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tribalid", tribalModel.tribalid == null ? "" : tribalModel.tribalid);
		map.put("name", tribalModel.name == null ? "" : tribalModel.name);
		map.put("logopic", tribalModel.logopic == null ? "" : tribalModel.logopic);
		map.put("bannerpic", tribalModel.bannerpic == null ? "" : tribalModel.bannerpic);
		map.put("des", tribalModel.des == null ? "" : tribalModel.des);
		map.put("type", String.valueOf(tribalModel.type));
		map.put("followcount", String.valueOf(tribalModel.followcount));
		map.put("todayupdatecount", String.valueOf(tribalModel.todayupdatecount));
		map.put("addtime", String.valueOf(tribalModel.addtime));
		map.put("lastupdatetime", String.valueOf(tribalModel.lastupdatetime));
		if (tribalModel.mainadmins != null && tribalModel.mainadmins.size() > 0) {
			String temp = JSON.toJSONString(tribalModel.mainadmins);
			map.put("mainadmins", temp);
			tribalModel.mainadminsstr = temp;
		}
		if (tribalModel.viceadmins != null && tribalModel.viceadmins.size() > 0) {
			String temp = JSON.toJSONString(tribalModel.viceadmins);
			map.put("viceadmins", temp);
			tribalModel.viceadminsstr = temp;
		}

		return map;
	}

	public static TribalModel convertToModel(Map<String, String> map) {

		if (map == null) {
			return null;
		}

		TribalModel model = new TribalModel();

		if (map.containsKey("tribalid")) {
			if (!map.get("tribalid").isEmpty()) {
				model.tribalid = map.get("tribalid");
			}
		}
		if (map.containsKey("name")) {
			if (!map.get("name").isEmpty()) {
				model.name = map.get("name");
			}
		}
		if (map.containsKey("logopic")) {
			if (!map.get("logopic").isEmpty()) {
				model.logopic = map.get("logopic");
			}
		}
		if (map.containsKey("bannerpic")) {
			if (!map.get("bannerpic").isEmpty()) {
				model.bannerpic = map.get("bannerpic");
			}
		}
		if (map.containsKey("des")) {
			if (!map.get("des").isEmpty()) {
				model.des = map.get("des");
			}
		}
		if (map.containsKey("type")) {
			if (!map.get("type").isEmpty()) {
				model.type = Integer.parseInt(map.get("type"));
			}
		}
		if (map.containsKey("followcount")) {
			if (!map.get("followcount").isEmpty()) {
				model.followcount = Long.valueOf(map.get("followcount"));
			}
		}
		if (map.containsKey("todayupdatecount")) {
			if (!map.get("todayupdatecount").isEmpty()) {
				model.todayupdatecount = Long.valueOf(map.get("todayupdatecount"));
			}
		}
		if (map.containsKey("addtime")) {
			if (!map.get("addtime").isEmpty()) {
				model.addtime = Long.valueOf(map.get("addtime"));
			}
		}
		if (map.containsKey("lastupdatetime")) {
			if (!map.get("lastupdatetime").isEmpty()) {
				model.lastupdatetime = Long.valueOf(map.get("lastupdatetime"));
			}
		}
		if (map.containsKey("mainadmins")) {
			if (!map.get("mainadmins").isEmpty()) {
				model.mainadmins = JSON.parseArray(map.get("mainadmins"), String.class);
			}
		}
		if (map.containsKey("viceadmins")) {
			if (!map.get("viceadmins").isEmpty()) {
				model.viceadmins = JSON.parseArray(map.get("viceadmins"), String.class);
			}
		}
		return model;
	}

	public long getTodayupdatecount() {
		return todayupdatecount;
	}

	public void setTodayupdatecount(long todayupdatecount) {
		this.todayupdatecount = todayupdatecount;
	}

	public String getTribalid() {
		return tribalid;
	}

	public void setTribalid(String tribalid) {
		this.tribalid = tribalid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogopic() {
		return logopic;
	}

	public void setLogopic(String logopic) {
		this.logopic = logopic;
	}

	public String getBannerpic() {
		return bannerpic;
	}

	public void setBannerpic(String bannerpic) {
		this.bannerpic = bannerpic;
	}

	public List<String> getMainadmins() {
		return mainadmins;
	}

	public void setMainadmins(List<String> mainadmins) {
		this.mainadmins = mainadmins;
	}

	public List<String> getViceadmins() {
		return viceadmins;
	}

	public void setViceadmins(List<String> viceadmins) {
		this.viceadmins = viceadmins;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getFollowcount() {
		return followcount;
	}

	public void setFollowcount(long followcount) {
		this.followcount = followcount;
	}

	public long getAddtime() {
		return addtime;
	}

	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}

	public long getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(long lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public String getMainadminsstr() {
		return mainadminsstr;
	}

	public void setMainadminsstr(String mainadminsstr) {
		this.mainadminsstr = mainadminsstr;
	}

	public String getViceadminsstr() {
		return viceadminsstr;
	}

	public void setViceadminsstr(String viceadminsstr) {
		this.viceadminsstr = viceadminsstr;
	}

	public long getOrderindex() {
		return orderindex;
	}

	public void setOrderindex(long orderindex) {
		this.orderindex = orderindex;
	}
	
	
}
