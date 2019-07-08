package com.motoband.motouser;

public class SimpleUserModel {
	public String userid;
	public int gender;
	public String nickname;
	public String headurl;
	public int level;
	public String signature;
	public String city;
	public String province;
	public String approvedes;
	public long userprivilegelong;//用户所有权限long值
	public int  isfollow;// 是否已关注   0没有关系   1我关注了他（这个model用户）   2他（这个model用户）是我的粉丝   3 互相关注
	public String  remarks;// 备注
	public String mobileno;
	
	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getApprovedes() {
		return approvedes;
	}

	public void setApprovedes(String approvedes) {
		this.approvedes = approvedes;
	}

	public long getUserprivilegelong() {
		return userprivilegelong;
	}

	public void setUserprivilegelong(long userprivilegelong) {
		this.userprivilegelong = userprivilegelong;
	}

	public int getIsfollow() {
		return isfollow;
	}

	public void setIsfollow(int isfollow) {
		this.isfollow = isfollow;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	@Override
	public String toString() {
		return "SimpleUserModel [userid=" + userid + ", gender=" + gender + ", nickname=" + nickname + ", headurl=" + headurl + ", level=" + level + ", signature=" + signature + ", city=" + city + ", province=" + province + ", approvedes=" + approvedes + ", userprivilegelong=" + userprivilegelong + ", isfollow=" + isfollow + ", remarks=" + remarks + ", mobileno=" + mobileno + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvedes == null) ? 0 : approvedes.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + gender;
		result = prime * result + ((headurl == null) ? 0 : headurl.hashCode());
		result = prime * result + isfollow;
		result = prime * result + level;
		result = prime * result + ((mobileno == null) ? 0 : mobileno.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		result = prime * result + (int) (userprivilegelong ^ (userprivilegelong >>> 32));
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
		SimpleUserModel other = (SimpleUserModel) obj;
		if (approvedes == null) {
			if (other.approvedes != null)
				return false;
		} else if (!approvedes.equals(other.approvedes))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (gender != other.gender)
			return false;
		if (headurl == null) {
			if (other.headurl != null)
				return false;
		} else if (!headurl.equals(other.headurl))
			return false;
		if (isfollow != other.isfollow)
			return false;
		if (level != other.level)
			return false;
		if (mobileno == null) {
			if (other.mobileno != null)
				return false;
		} else if (!mobileno.equals(other.mobileno))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		if (userprivilegelong != other.userprivilegelong)
			return false;
		return true;
	}
	
	
	
	
	
}
