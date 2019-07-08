package com.motoband.boxmanage;

public class noteModel {
	private String note_guid;
	private String boxid;
	private String note_writer;
	private String note_content;
	

	public String getNote_guid() {
		return note_guid;
	}

	public void setNote_guid(String note_guid) {
		this.note_guid = note_guid;
	}

	public String getBoxid() {
		return boxid;
	}

	public void setBoxid(String  boxid) {
		this.boxid = boxid;
	}

	public String getNote_writer() {
		return note_writer;
	}

	public void setNote_writer(String note_writer) {
		this.note_writer = note_writer;
	}

	public String getNote_content() {
		return note_content;
	}

	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}

}
