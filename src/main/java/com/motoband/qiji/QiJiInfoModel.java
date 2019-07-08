package com.motoband.qiji;

import com.motoband.motouser.SimpleUserModel;
import com.motoband.news.newsModel;

/**
 * 骑迹景点model
 * Created by junfei.Yang on 2019年1月8日.
 */
public class QiJiInfoModel {
	public String _id;
	public String _name;
	public String _location;// 格式示例：104.394729,31.125698
	public String _address;
	public double _distance;
	public int lid;//自增id
	public String sid;//唯一标识
	public double longitude;
	public double latitude ;
	public String province;
	public String city;
	public String type;//
	public int state;// 0正常 1下线
	public String name;

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//展示用
	public SimpleUserModel user;//点赞数第一的动态的用户
	public String backgroudurl;
	public long signcount;//该景点所有用户签到总次数
	public newsModel news;//点赞最多的动态
	
	public boolean issign;
	public long signrank;//签到排名
	public long signtime;//签到时间
	@Override
	public String toString() {
		return "QiJiInfoModel [_id=" + _id + ", _name=" + _name + ", _location=" + _location + ", _address=" + _address
				+ ", _distance=" + _distance + ", lid=" + lid + ", sid=" + sid + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", province=" + province + ", city=" + city + ", type=" + type + ", state="
				+ state + ", user=" + user + ", backgroudurl=" + backgroudurl + ", signcount=" + signcount + ", news="
				+ news + ", issign=" + issign + ", signrank=" + signrank + ", signtime=" + signtime + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_address == null) ? 0 : _address.hashCode());
		long temp;
		temp = Double.doubleToLongBits(_distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((_location == null) ? 0 : _location.hashCode());
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
		result = prime * result + ((backgroudurl == null) ? 0 : backgroudurl.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + (issign ? 1231 : 1237);
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + lid;
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((news == null) ? 0 : news.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result + (int) (signcount ^ (signcount >>> 32));
		result = prime * result + (int) (signrank ^ (signrank >>> 32));
		result = prime * result + (int) (signtime ^ (signtime >>> 32));
		result = prime * result + state;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QiJiInfoModel other = (QiJiInfoModel) obj;
		if (_address == null) {
			if (other._address != null)
				return false;
		} else if (!_address.equals(other._address))
			return false;
		if (Double.doubleToLongBits(_distance) != Double.doubleToLongBits(other._distance))
			return false;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (_location == null) {
			if (other._location != null)
				return false;
		} else if (!_location.equals(other._location))
			return false;
		if (_name == null) {
			if (other._name != null)
				return false;
		} else if (!_name.equals(other._name))
			return false;
		if (backgroudurl == null) {
			if (other.backgroudurl != null)
				return false;
		} else if (!backgroudurl.equals(other.backgroudurl))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (issign != other.issign)
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (lid != other.lid)
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (news == null) {
			if (other.news != null)
				return false;
		} else if (!news.equals(other.news))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		if (signcount != other.signcount)
			return false;
		if (signrank != other.signrank)
			return false;
		if (signtime != other.signtime)
			return false;
		if (state != other.state)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_location() {
		return _location;
	}
	public void set_location(String _location) {
		this._location = _location;
	}
	public String get_address() {
		return _address;
	}
	public void set_address(String _address) {
		this._address = _address;
	}
	public double get_distance() {
		return _distance;
	}
	public void set_distance(double _distance) {
		this._distance = _distance;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public SimpleUserModel getUser() {
		return user;
	}
	public void setUser(SimpleUserModel user) {
		this.user = user;
	}
	public String getBackgroudurl() {
		return backgroudurl;
	}
	public void setBackgroudurl(String backgroudurl) {
		this.backgroudurl = backgroudurl;
	}
	public long getSigncount() {
		return signcount;
	}
	public void setSigncount(long signcount) {
		this.signcount = signcount;
	}
	public newsModel getNews() {
		return news;
	}
	public void setNews(newsModel news) {
		this.news = news;
	}
	public boolean isIssign() {
		return issign;
	}
	public void setIssign(boolean issign) {
		this.issign = issign;
	}
	public long getSignrank() {
		return signrank;
	}
	public void setSignrank(long signrank) {
		this.signrank = signrank;
	}
	public long getSigntime() {
		return signtime;
	}
	public void setSigntime(long signtime) {
		this.signtime = signtime;
	}
	
	
}
