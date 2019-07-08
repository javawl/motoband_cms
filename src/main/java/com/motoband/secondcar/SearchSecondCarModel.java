package com.motoband.secondcar;

/**
 * 二手车搜索条件实体 Created by junfei.Yang on 2017年6月5日.
 */
public class SearchSecondCarModel {

	public String secondcarid;
	public String title;
	public String city;
	/**
	 * 老版: 0商家排序，1最新上架，2价格最低，3价格最高,4车龄最短,5里程最短，6个人车源,7老司机 2.5.3:
	 * 1最新发布,2价格最低,3价格最高,4车龄最短,5里程最少
	 */
	public int store;//默认1
	public String brand;
	public String model;// 车型
	public int bpid;// 大品牌
	public int brandid;// 小品牌
	public int modelid;// 车型
	public int minprice;
	public int maxprice;
	public int pageNo;
	public int pageSize;
	public String style;// 车型
	public int minage;// 最小车龄
	public int maxage;// 最大车龄 最大0；
	public double minkilometer;// 最小里程
	public double maxkilometer;// 最大里程
	public int procedure;// 手续情况 (0不限，1正规，2其他)
	public int hasolddrivercomment;// 是否老司机车评（0不限，1否，2是）
	public int hasofficial;// 是否摩托邦官方（0不限，1否，2是）
	public int createusertype;//个人车源或者商家车源（0不限，1个人，2商家）
	public String keyword;
	
	
	@Override
	public String toString() {
		return "SearchSecondCarModel [secondcarid=" + secondcarid + ", title=" + title + ", city=" + city + ", store=" + store + ", brand=" + brand + ", model=" + model + ", bpid=" + bpid + ", brandid=" + brandid + ", modelid=" + modelid + ", minprice=" + minprice + ", maxprice=" + maxprice + ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", style=" + style + ", minage=" + minage + ", maxage="
				+ maxage + ", minkilometer=" + minkilometer + ", maxkilometer=" + maxkilometer + ", procedure=" + procedure + ", hasolddrivercomment=" + hasolddrivercomment + ", hasofficial=" + hasofficial + ", createusertype=" + createusertype + ", keyword=" + keyword + "]";
	}


	public String getSecondcarid() {
		return secondcarid;
	}


	public void setSecondcarid(String secondcarid) {
		this.secondcarid = secondcarid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getStore() {
		return store;
	}


	public void setStore(int store) {
		this.store = store;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public int getBpid() {
		return bpid;
	}


	public void setBpid(int bpid) {
		this.bpid = bpid;
	}


	public int getBrandid() {
		return brandid;
	}


	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}


	public int getModelid() {
		return modelid;
	}


	public void setModelid(int modelid) {
		this.modelid = modelid;
	}


	public int getMinprice() {
		return minprice;
	}


	public void setMinprice(int minprice) {
		this.minprice = minprice;
	}


	public int getMaxprice() {
		return maxprice;
	}


	public void setMaxprice(int maxprice) {
		this.maxprice = maxprice;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}


	public int getMinage() {
		return minage;
	}


	public void setMinage(int minage) {
		this.minage = minage;
	}


	public int getMaxage() {
		return maxage;
	}


	public void setMaxage(int maxage) {
		this.maxage = maxage;
	}


	public double getMinkilometer() {
		return minkilometer;
	}


	public void setMinkilometer(double minkilometer) {
		this.minkilometer = minkilometer;
	}


	public double getMaxkilometer() {
		return maxkilometer;
	}


	public void setMaxkilometer(double maxkilometer) {
		this.maxkilometer = maxkilometer;
	}


	public int getProcedure() {
		return procedure;
	}


	public void setProcedure(int procedure) {
		this.procedure = procedure;
	}


	public int getHasolddrivercomment() {
		return hasolddrivercomment;
	}


	public void setHasolddrivercomment(int hasolddrivercomment) {
		this.hasolddrivercomment = hasolddrivercomment;
	}


	public int getHasofficial() {
		return hasofficial;
	}


	public void setHasofficial(int hasofficial) {
		this.hasofficial = hasofficial;
	}


	public int getCreateusertype() {
		return createusertype;
	}


	public void setCreateusertype(int createusertype) {
		this.createusertype = createusertype;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	

	

}
