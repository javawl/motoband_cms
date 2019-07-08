package com.motoband.news;

public class NewsVideoModel {
	public String fileid;
	public String videoUrl;
	public String thumbUrl;
	public long videoDurantion;
	public int videoWidth;
	public int videoHeight;
	public long videoLength;
	@Override
	public String toString() {
		return "NewsVideoModel [fileid=" + fileid + ", videoUrl=" + videoUrl + ", thumbUrl=" + thumbUrl + ", videoDurantion=" + videoDurantion + ", videoWidth=" + videoWidth + ", videoHeight=" + videoHeight + ", videoLength=" + videoLength + "]";
	}
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	public long getVideoDurantion() {
		return videoDurantion;
	}
	public void setVideoDurantion(long videoDurantion) {
		this.videoDurantion = videoDurantion;
	}
	public int getVideoWidth() {
		return videoWidth;
	}
	public void setVideoWidth(int videoWidth) {
		this.videoWidth = videoWidth;
	}
	public int getVideoHeight() {
		return videoHeight;
	}
	public void setVideoHeight(int videoHeight) {
		this.videoHeight = videoHeight;
	}
	public long getVideoLength() {
		return videoLength;
	}
	public void setVideoLength(long videoLength) {
		this.videoLength = videoLength;
	}
	
}
