package com.motoband.businessmanage;

import java.util.List;
import java.util.Map;

import com.github.ltsopensource.core.json.JSON;
import com.google.common.collect.Maps;

/**
 * 商家提供的服务
 * Created by junfei.Yang on 2019年9月16日.
 */
public class BusinessServiceV3_8_0Model {

	public int bsid;
	public String uuid;
	/**
	 * 商家id
	 * @see com.motoband.model.business.v_3_8_0.BusinessUserV3_8_0Model
	 */
	public int buid;
	public String icon;//图标
	public String name;
	public String value;
	public List<String> lables;//服务标签
	public int linktype;//1 动态  2 话题   3问答    4 有赞  5 内部链接  6 外部链接  7小程序 8 motogp 9 二手车  10 本地商家  11 话题列表  12 问答列表  13 此刻
	public int orderindex;//排序 越大越优先展示
	public int state;//0上线，1下线
	
	public String modelJsonStr;
	
	
	public String getModelJsonStr() {
		Map<String,Object> map=Maps.newHashMap();
		map.put("bsid", bsid);
		map.put("uuid", uuid);
		map.put("buid", buid);
		map.put("icon", icon);
		map.put("name", name);
		map.put("value", value);
		map.put("lables", lables);
		map.put("linktype", linktype);
		map.put("orderindex", orderindex);
		map.put("state", state);
		return JSON.toJSONString(map);
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getLinktype() {
		return linktype;
	}
	public int getBsid() {
		return bsid;
	}
	public void setBsid(int bsid) {
		this.bsid = bsid;
	}
	public int getBuid() {
		return buid;
	}
	public void setBuid(int buid) {
		this.buid = buid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<String> getLables() {
		return lables;
	}
	public void setLables(List<String> lables) {
		this.lables = lables;
	}
	public void setLinktype(int linktype) {
		this.linktype = linktype;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getOrderindex() {
		return orderindex;
	}
	public void setOrderindex(int orderindex) {
		this.orderindex = orderindex;
	}
	
	
	
}
