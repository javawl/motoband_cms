package com.motoband.news;

public class topic {
	private String content;
	private String keyword;
	private long orderindex;
	private String pic;
	private String title;
	private int status;
	private int topicid;
	private int usercount;
	
	private int topictype;
	private String userid;
	private long pubtime;
	private String pubtimeString;
	public int picwidth;//图片宽度
	public int picheight;//图片高度
	public String pid; //商品id
	public String businessuserid;
	public int ismain;
	
	
	public int getIsmain() {
		return ismain;
	}

	public void setIsmain(int ismain) {
		this.ismain = ismain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((businessuserid == null) ? 0 : businessuserid.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ismain;
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + (int) (orderindex ^ (orderindex >>> 32));
		result = prime * result + ((pic == null) ? 0 : pic.hashCode());
		result = prime * result + picheight;
		result = prime * result + picwidth;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + (int) (pubtime ^ (pubtime >>> 32));
		result = prime * result + ((pubtimeString == null) ? 0 : pubtimeString.hashCode());
		result = prime * result + status;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + topicid;
		result = prime * result + topictype;
		result = prime * result + usercount;
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		topic other = (topic) obj;
		if (businessuserid == null) {
			if (other.businessuserid != null)
				return false;
		} else if (!businessuserid.equals(other.businessuserid))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (ismain != other.ismain)
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (orderindex != other.orderindex)
			return false;
		if (pic == null) {
			if (other.pic != null)
				return false;
		} else if (!pic.equals(other.pic))
			return false;
		if (picheight != other.picheight)
			return false;
		if (picwidth != other.picwidth)
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (pubtime != other.pubtime)
			return false;
		if (pubtimeString == null) {
			if (other.pubtimeString != null)
				return false;
		} else if (!pubtimeString.equals(other.pubtimeString))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (topicid != other.topicid)
			return false;
		if (topictype != other.topictype)
			return false;
		if (usercount != other.usercount)
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

	public String getBusinessuserid() {
		return businessuserid;
	}

	public void setBusinessuserid(String businessuserid) {
		this.businessuserid = businessuserid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getPicwidth() {
		return picwidth;
	}

	public void setPicwidth(int picwidth) {
		this.picwidth = picwidth;
	}

	public int getPicheight() {
		return picheight;
	}

	public void setPicheight(int picheight) {
		this.picheight = picheight;
	}

	public String getPubtimeString() {
		return pubtimeString;
	}

	public void setPubtimeString(String pubtimeString) {
		this.pubtimeString = pubtimeString;
	}

	public int getTopictype() {
		return topictype;
	}

	public void setTopictype(int topictype) {
		this.topictype = topictype;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public long getPubtime() {
		return pubtime;
	}

	public void setPubtime(long pubtime) {
		this.pubtime = pubtime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public long getOrderindex() {
		return orderindex;
	}

	public void setOrderindex(long orderindex) {
		this.orderindex = orderindex;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTopicid() {
		return topicid;
	}

	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}

	public int getUsercount() {
		return usercount;
	}

	public void setUsercount(int usercount) {
		this.usercount = usercount;
	}

}
