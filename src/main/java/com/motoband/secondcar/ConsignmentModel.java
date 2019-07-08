package com.motoband.secondcar;

public class ConsignmentModel {

	public String consignmentid;
	public String carmodelinfo;//车型信息
	public String contactphone;//联系电话
	public long addtime;//添加时间
	public String userid;//用户id
	public String remark;//备注
	public String state;//状态  （0 待处理   1已处理）
	
	//扩展
	public String addtimeString;

	
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getConsignmentid() {
		return consignmentid;
	}

	public void setConsignmentid(String consignmentid) {
		this.consignmentid = consignmentid;
	}

	public String getCarmodelinfo() {
		return carmodelinfo;
	}

	public void setCarmodelinfo(String carmodelinfo) {
		this.carmodelinfo = carmodelinfo;
	}

	public String getContactphone() {
		return contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public long getAddtime() {
		return addtime;
	}

	public void setAddtime(long addtime) {
		this.addtime = addtime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAddtimeString() {
		return addtimeString;
	}

	public void setAddtimeString(String addtimeString) {
		this.addtimeString = addtimeString;
	}
	

	


}
