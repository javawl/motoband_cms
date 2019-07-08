package com.motoband.mallmanage;

public class MallBrandModel {
private Integer brandid;
private String name;

//扩展
private Integer productCount;

public Integer getProductCount() {
	return productCount;
}
public void setProductCount(Integer productCount) {
	this.productCount = productCount;
}
public Integer getBrandid() {
	return brandid;
}
public void setBrandid(Integer brandid) {
	this.brandid = brandid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
