package com.motoband.mallmanage;

public class EquippingGroupModel {

	public int groupid;//组id
	public String title;//组名
	public String subtitle;//组名
	public String url;//链接地址 加载更多
	public int type;//0 分类组   1 广告组  2 分组商品组 3 推荐商品组
	public long orderindex;//排序 倒序
	public int state;//状态（0 下架   1上架）
	
	@Override
	public String toString() {
		return "EquippingGroupModel [groupid=" + groupid + ", title=" + title + ", type=" + type + ", orderindex=" + orderindex + ", state=" + state + "]";
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getOrderindex() {
		return orderindex;
	}
	public void setOrderindex(long orderindex) {
		this.orderindex = orderindex;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
