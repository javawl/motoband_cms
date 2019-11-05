package com.motoband.motouser;

import javax.xml.bind.Marshaller.Listener;

import com.motoband.Events.TaskEvent;
import com.motoband.interfaces.TaskListener;
import com.motoband.news.LinkTypeEnum;

public class MessageTaskModel {
	public long id;
	public String name;
	public String taskid;
	public long createtime;
	public long updatetime;
	public int state;// 0 未完成 1 已完成

	// banner
	public String title;// 标题
	public String subtitle;// 副标题
	public String des;// 描述

	/**
	 * @see LinkTypeEnum
	 */
	public int linktype;
	public String imgurl;// 图片地址
	public String linkurl;// 跳转链接
	public int gpid;// gpid
	public String nid;// 动态id
	public String keyword;// 关键字
	public String secondcarid;// 二手车id
	public String miniprogramid;// 小程序id
	public String buserid;// 商家id
	public String groupid;// 群id

	public int sumcount;
	public int handlecount;
	public int successcount;
	public int failcount;
	public String createtimeString;
	public String updatetimeString;

	private TaskListener taskListener;
	
	public long starttime;//任务开始执行时间

	
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	/**
	 * 监听任务执行情况
	 */
    public void implementing() {
        if (taskListener != null) {
            TaskEvent event = new TaskEvent();
            event.setTask(this);
            taskListener.isImplementing(event);
        }
    }
	public TaskListener getTaskListener() {
		return taskListener;
	}

	public void setTaskListener(TaskListener taskListener) {
		this.taskListener = taskListener;
	}

	public String getCreatetimeString() {
		return createtimeString;
	}

	public void setCreatetimeString(String createtimeString) {
		this.createtimeString = createtimeString;
	}

	public String getUpdatetimeString() {
		return updatetimeString;
	}

	public void setUpdatetimeString(String updatetimeString) {
		this.updatetimeString = updatetimeString;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getLinktype() {
		return linktype;
	}

	public void setLinktype(int linktype) {
		this.linktype = linktype;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public int getGpid() {
		return gpid;
	}

	public void setGpid(int gpid) {
		this.gpid = gpid;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSecondcarid() {
		return secondcarid;
	}

	public void setSecondcarid(String secondcarid) {
		this.secondcarid = secondcarid;
	}

	public String getMiniprogramid() {
		return miniprogramid;
	}

	public void setMiniprogramid(String miniprogramid) {
		this.miniprogramid = miniprogramid;
	}

	public String getBuserid() {
		return buserid;
	}

	public void setBuserid(String buserid) {
		this.buserid = buserid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
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

	public int getSumcount() {
		return sumcount;
	}

	public void setSumcount(int sumcount) {
		this.sumcount = sumcount;
	}

	public int getHandlecount() {
		return handlecount;
	}

	public void setHandlecount(int handlecount) {
		this.handlecount = handlecount;
	}

	public int getSuccesscount() {
		return successcount;
	}

	public void setSuccesscount(int successcount) {
		this.successcount = successcount;
	}

	public int getFailcount() {
		return failcount;
	}

	public void setFailcount(int failcount) {
		this.failcount = failcount;
	}

	@Override
	public String toString() {
		return "MessageTaskModel [id=" + id + ", name=" + name + ", taskid=" + taskid + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", state=" + state + ", title=" + title + ", subtitle=" + subtitle
				+ ", des=" + des + ", linktype=" + linktype + ", imgurl=" + imgurl + ", linkurl=" + linkurl + ", gpid="
				+ gpid + ", nid=" + nid + ", keyword=" + keyword + ", secondcarid=" + secondcarid + ", miniprogramid="
				+ miniprogramid + ", buserid=" + buserid + ", sumcount=" + sumcount + ", handlecount=" + handlecount
				+ ", successcount=" + successcount + ", failcount=" + failcount + "]";
	}

}
