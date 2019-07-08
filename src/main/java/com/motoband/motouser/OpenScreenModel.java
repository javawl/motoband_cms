package com.motoband.motouser;

import java.util.HashMap;
import java.util.Map;

public class OpenScreenModel {
	public String osid;//uuID
	public String userid;//用户ID
	public String nickname;//用户昵称
	public String pic;//图片
	public long addtime;//添加时间 只排序用
	

	public static Map<String, String> convertToMap(OpenScreenModel model) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("osid", model.osid == null ? "" : model.osid);
		map.put("userid", model.userid==null?"":model.userid);
		map.put("nickname", model.nickname == null ? "" : model.nickname);
		map.put("pic", model.pic == null ? "" : model.pic);

		return map;
	}

	


	@Override
	public String toString() {
		return "OpenScreenModel [osid=" + osid + ", userid=" + userid + ", nickname=" + nickname + ", pic=" + pic + ", addtime=" + addtime + "]";
	}




	public static OpenScreenModel convertToModel(Map<String, String> map) {

		if (map == null) {
			return null;
		}

		OpenScreenModel model = new OpenScreenModel();

		if (map.containsKey("osid")){

			if (!map.get("osid").isEmpty())
				model.osid = map.get("osid");
		}

		if (map.containsKey("userid")){

			if (!map.get("userid").isEmpty())
				model.userid = map.get("userid");
		}

		if (map.containsKey("nickname")){

			if (!map.get("nickname").isEmpty())
				model.nickname = map.get("nickname");
		}
		if (map.containsKey("pic")) {

			if (!map.get("pic").isEmpty())
				model.pic = map.get("pic");
		}
		

		return model;
	}


	public String getOsid() {
		return osid;
	}


	public void setOsid(String osid) {
		this.osid = osid;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getPic() {
		return pic;
	}


	public void setPic(String pic) {
		this.pic = pic;
	}




	public long getAddtime() {
		return addtime;
	}




	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}
	
	
}
