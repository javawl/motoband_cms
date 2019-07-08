package com.motoband.news;

public class newsLabelModel {
	public int labelid;
	public String labelkeyword;
	public String type;
	public int orderindex;
    public int state;
    public String imageurl;
    public String cimageurl;
    public int recommendindex;
	

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getCimageurl() {
		return cimageurl;
	}

	public void setCimageurl(String cimageurl) {
		this.cimageurl = cimageurl;
	}

	public int getRecommendindex() {
		return recommendindex;
	}

	public void setRecommendindex(int recommendindex) {
		this.recommendindex = recommendindex;
	}

	public int getLabelid() {
		return labelid;
	}

	public void setLabelid(int labelid) {
		this.labelid = labelid;
	}

	public String getLabelkeyword() {
		return labelkeyword;
	}

	public void setLabelkeyword(String labelkeyword) {
		this.labelkeyword = labelkeyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOrderindex() {
		return orderindex;
	}

	public void setOrderindex(int orderindex) {
		this.orderindex = orderindex;
	}

}
