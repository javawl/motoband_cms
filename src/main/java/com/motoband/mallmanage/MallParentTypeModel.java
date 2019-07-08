package com.motoband.mallmanage;

import java.util.List;

public class MallParentTypeModel {
	private String name;
	private Integer parentid;
	private String brandids;
	//扩展
	private List<MallBrandModel> mallBrandmodels;
	private String brandNames;
	
	
	public String getBrandNames() {
		return brandNames;
	}
	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
	}
	public String getBrandids() {
		return brandids;
	}
	public void setBrandids(String brandids) {
		this.brandids = brandids;
	}
	public List<MallBrandModel> getMallBrandmodels() {
		return mallBrandmodels;
	}
	public void setMallBrandmodels(List<MallBrandModel> mallBrandmodels) {
		this.mallBrandmodels = mallBrandmodels;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	
}
