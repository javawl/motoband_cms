package com.motoband.admin.admin;

public class Privilege {
	private String privilege_guid;
	private String privilege_name;
	private String privilege_parentguid;
	private String privilege_key;
	private String privilege_url;
	private String privilege_des;

	public String getPrivilege_key() {
		return privilege_key;
	}

	public void setPrivilege_key(String privilege_key) {
		this.privilege_key = privilege_key;
	}

	public String getPrivilege_guid() {
		return privilege_guid;
	}

	public void setPrivilege_guid(String privilege_guid) {
		this.privilege_guid = privilege_guid;
	}

	public String getPrivilege_name() {
		return privilege_name;
	}

	public void setPrivilege_name(String privilege_name) {
		this.privilege_name = privilege_name;
	}

	public String getPrivilege_parentguid() {
		return privilege_parentguid;
	}

	public void setPrivilege_parentguid(String privilege_parentguid) {
		this.privilege_parentguid = privilege_parentguid;
	}

	public String getPrivilege_url() {
		return privilege_url;
	}

	public void setPrivilege_url(String privilege_url) {
		this.privilege_url = privilege_url;
	}

	public String getPrivilege_des() {
		return privilege_des;
	}

	public void setPrivilege_des(String privilege_des) {
		this.privilege_des = privilege_des;
	}

}
