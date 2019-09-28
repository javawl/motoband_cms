package com.motoband.businessmanage;

import java.util.List;

import com.motoband.motouser.SimpleUserModel;


/**
 * 3.8.0重构商家功能
 * Created by junfei.Yang on 2019年9月16日.
 */
public class BusinessUserV3_8_0Model {

	public int buid;
	public String buserid;
	public String name;
	public String province;
	public String city;
	public List<String> contactphone;
	public List<String> pics;
	public String address;
	public String businesshours;//营业时间
	public String des;//简介
	public double latitude;//纬度
	public double longitude;//经度
	public List<String> kfuseridlist;//客服
	
	/**
	 * {@link TIMGroupModel}
	 */
	public String groupid;//群ID
	public List<String> lables;//商家标签
	
	public long createtime;
	public long updatetime;
	public int state;//0在线,1下线

	public List<SimpleUserModel> kfsimpleuserlist;
	public int usercount;//评论人数
	public int scorecount;//评分总数
	public float bscore;  //评分
	public int orderindex;
	
	public List<BusinessServiceV3_8_0Model> businessservice;
	
	

	public int getOrderindex() {
		return orderindex;
	}

	public void setOrderindex(int orderindex) {
		this.orderindex = orderindex;
	}

	public int getBuid() {
		return buid;
	}

	public String getBuserid() {
		return buserid;
	}

	public void setBuserid(String buserid) {
		this.buserid = buserid;
	}

	public void setBuid(int buid) {
		this.buid = buid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getContactphone() {
		return contactphone;
	}

	public void setContactphone(List<String> contactphone) {
		this.contactphone = contactphone;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinesshours() {
		return businesshours;
	}

	public void setBusinesshours(String businesshours) {
		this.businesshours = businesshours;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public List<String> getKfuseridlist() {
		return kfuseridlist;
	}

	public void setKfuseridlist(List<String> kfuseridlist) {
		this.kfuseridlist = kfuseridlist;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public List<String> getLables() {
		return lables;
	}

	public void setLables(List<String> lables) {
		this.lables = lables;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<SimpleUserModel> getKfsimpleuserlist() {
		return kfsimpleuserlist;
	}

	public void setKfsimpleuserlist(List<SimpleUserModel> kfsimpleuserlist) {
		this.kfsimpleuserlist = kfsimpleuserlist;
	}

	public int getUsercount() {
		return usercount;
	}

	public void setUsercount(int usercount) {
		this.usercount = usercount;
	}

	public int getScorecount() {
		return scorecount;
	}

	public void setScorecount(int scorecount) {
		this.scorecount = scorecount;
	}

	public float getBscore() {
		return bscore;
	}

	public void setBscore(float bscore) {
		this.bscore = bscore;
	}

	public List<BusinessServiceV3_8_0Model> getBusinessservice() {
		return businessservice;
	}

	public void setBusinessservice(List<BusinessServiceV3_8_0Model> businessservice) {
		this.businessservice = businessservice;
	}
	
	
}
