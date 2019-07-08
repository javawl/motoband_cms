package com.motoband.clientupdate;

public class updateconf {
	private int id;
	private String cversion;
	private String tcversion;
	private int ctype;
	private String content;
	private String downloadurl;
	private int state;
	private int ishighversion;
	/*private String old_cversion;
	private String old_tcversion;
	private int old_ctype;*/

	
	public int getIshighversion() {
		return ishighversion;
	}

	public void setIshighversion(int ishighversion) {
		this.ishighversion = ishighversion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getOld_cversion() {
//		return old_cversion;
//	}
//
//	public void setOld_cversion(String old_cversion) {
//		this.old_cversion = old_cversion;
//	}
//
//	public String getOld_tcversion() {
//		return old_tcversion;
//	}
//
//	public void setOld_tcversion(String old_tcversion) {
//		this.old_tcversion = old_tcversion;
//	}
//
//	public int getOld_ctype() {
//		return old_ctype;
//	}
//
//	public void setOld_ctype(int old_ctype) {
//		this.old_ctype = old_ctype;
//	}

	public String getCversion() {
		return cversion;
	}

	public void setCversion(String cversion) {
		this.cversion = cversion;
	}

	public String getTcversion() {
		return tcversion;
	}

	public void setTcversion(String tcversion) {
		this.tcversion = tcversion;
	}

	public int getCtype() {
		return ctype;
	}

	public void setCtype(int ctype) {
		this.ctype = ctype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDownloadurl() {
		return downloadurl;
	}

	public void setDownloadurl(String downloadurl) {
		this.downloadurl = downloadurl;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
