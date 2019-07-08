package com.motoband.businessmanage;

public class LBSDataModel {
	public String _id;
	public String _name;

	
	public String _location;// 格式示例：104.394729,31.125698

	public String coordtype;//1: gps 2: autonavi 3: baidu

	public String _address;

	 public long buid;//商户ID
	 
	 public String province;
	 public String city;
	 public String userid;
	 public String businessservice;//保养，维修 等多个关键词逗号分隔
	 public String type;//维修店 品牌店 综合店 4S店
	 
	 public String isapprove;//0非认证 1认证
	 
	public String state;//0下线 1正常
	 
	 
	 public String _distance;
	 public String busikeyword;// 商家关键词逗号分隔
	@Override
	public String toString() {
		return "LBSDataModel [_id=" + _id + ", _name=" + _name + ", _location=" + _location + ", coordtype=" + coordtype + ", _address=" + _address + ", buid=" + buid + ", province=" + province + ", city=" + city + ", userid=" + userid + ", businessservice=" + businessservice + ", type=" + type + ", isapprove=" + isapprove + ", state=" + state + ", _distance=" + _distance + ", busikeyword="
				+ busikeyword + "]";
	}
	 
}
