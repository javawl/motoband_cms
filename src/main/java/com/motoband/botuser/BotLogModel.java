package com.motoband.botuser;

/**
 * BOT日志
 */
public class BotLogModel {
	public long bid;
	public String botuserid;
	public String nid;
	public int nidtasklcount;//动态应点赞数
	public int nidtaskgcount;//动态应送礼物数
	public byte logtype;//1 点赞，2送礼物，3关注
	public long logtime;//执行时间
	public int giftid;//花费积分
	
	public String nickname;
	public String headurl;
	
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
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public String getBotuserid() {
		return botuserid;
	}
	public void setBotuserid(String botuserid) {
		this.botuserid = botuserid;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public int getNidtasklcount() {
		return nidtasklcount;
	}
	public void setNidtasklcount(int nidtasklcount) {
		this.nidtasklcount = nidtasklcount;
	}
	public int getNidtaskgcount() {
		return nidtaskgcount;
	}
	public void setNidtaskgcount(int nidtaskgcount) {
		this.nidtaskgcount = nidtaskgcount;
	}
	public byte getLogtype() {
		return logtype;
	}
	public void setLogtype(byte logtype) {
		this.logtype = logtype;
	}
	public long getLogtime() {
		return logtime;
	}
	public void setLogtime(long logtime) {
		this.logtime = logtime;
	}
	public int getGiftid() {
		return giftid;
	}
	public void setGiftid(int giftid) {
		this.giftid = giftid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bid ^ (bid >>> 32));
		result = prime * result + ((botuserid == null) ? 0 : botuserid.hashCode());
		result = prime * result + giftid;
		result = prime * result + (int) (logtime ^ (logtime >>> 32));
		result = prime * result + logtype;
		result = prime * result + ((nid == null) ? 0 : nid.hashCode());
		result = prime * result + nidtaskgcount;
		result = prime * result + nidtasklcount;
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
		BotLogModel other = (BotLogModel) obj;
		if (bid != other.bid)
			return false;
		if (botuserid == null) {
			if (other.botuserid != null)
				return false;
		} else if (!botuserid.equals(other.botuserid))
			return false;
		if (giftid != other.giftid)
			return false;
		if (logtime != other.logtime)
			return false;
		if (logtype != other.logtype)
			return false;
		if (nid == null) {
			if (other.nid != null)
				return false;
		} else if (!nid.equals(other.nid))
			return false;
		if (nidtaskgcount != other.nidtaskgcount)
			return false;
		if (nidtasklcount != other.nidtasklcount)
			return false;
		return true;
	}
	
	
}
