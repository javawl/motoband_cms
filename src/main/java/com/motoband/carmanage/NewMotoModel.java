package com.motoband.carmanage;

import java.util.List;

public class NewMotoModel {
	public String mid;//uuid 
	public int modelid;//车型id
	public int brandid;//小品牌 id
	public int bpid;//大品牌id
	public String name;//车型名称
	public long price;//价格 元
	public String style;//车型风格
	public float maxpower;//最大功率
	public float maxtorque;//最大扭矩
	public float cc;//发动机排量
	public String gearbox;//变速箱
	public int sitheight;//坐高
	public String lwh;//长宽高  
	public float tankcapacity;//油箱容积 l
	public String oilway;//供油方式
	public String transmissionway;//传动方式
	public String frontbrake;//前制动器
	public String rearbrake;//后制动器
	public String frontwheelsize;//前轮胎规格
	public String rearwheelsize;//后轮胎规格
	public int haveabs;//是否有abs（0 无 1 有）
	public int caryear;//年份 预留
	public String firstpic;//首图
	public List<String> allpic;//全部图片
	public String otherelectronic;//其他电子设备
	public int haveonboard;//是否有车载巡航（0 无 1 有）
	
	
	//扩展
	public String brandname1;
	public String brandparentname;
	
	

	public String getOtherelectronic() {
		return otherelectronic;
	}
	public void setOtherelectronic(String otherelectronic) {
		this.otherelectronic = otherelectronic;
	}
	public int getHaveonboard() {
		return haveonboard;
	}
	public void setHaveonboard(int haveonboard) {
		this.haveonboard = haveonboard;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getModelid() {
		return modelid;
	}
	public void setModelid(int modelid) {
		this.modelid = modelid;
	}
	public int getBrandid() {
		return brandid;
	}
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}
	public int getBpid() {
		return bpid;
	}
	public void setBpid(int bpid) {
		this.bpid = bpid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public float getMaxpower() {
		return maxpower;
	}
	public void setMaxpower(float maxpower) {
		this.maxpower = maxpower;
	}
	public float getMaxtorque() {
		return maxtorque;
	}
	public void setMaxtorque(float maxtorque) {
		this.maxtorque = maxtorque;
	}
	public float getCc() {
		return cc;
	}
	public void setCc(float cc) {
		this.cc = cc;
	}
	public String getGearbox() {
		return gearbox;
	}
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}
	public int getSitheight() {
		return sitheight;
	}
	public void setSitheight(int sitheight) {
		this.sitheight = sitheight;
	}
	public String getLwh() {
		return lwh;
	}
	public void setLwh(String lwh) {
		this.lwh = lwh;
	}
	public float getTankcapacity() {
		return tankcapacity;
	}
	public void setTankcapacity(float tankcapacity) {
		this.tankcapacity = tankcapacity;
	}
	public String getOilway() {
		return oilway;
	}
	public void setOilway(String oilway) {
		this.oilway = oilway;
	}
	public String getTransmissionway() {
		return transmissionway;
	}
	public void setTransmissionway(String transmissionway) {
		this.transmissionway = transmissionway;
	}
	public String getFrontbrake() {
		return frontbrake;
	}
	public void setFrontbrake(String frontbrake) {
		this.frontbrake = frontbrake;
	}
	public String getRearbrake() {
		return rearbrake;
	}
	public void setRearbrake(String rearbrake) {
		this.rearbrake = rearbrake;
	}
	public String getFrontwheelsize() {
		return frontwheelsize;
	}
	public void setFrontwheelsize(String frontwheelsize) {
		this.frontwheelsize = frontwheelsize;
	}
	public String getRearwheelsize() {
		return rearwheelsize;
	}
	public void setRearwheelsize(String rearwheelsize) {
		this.rearwheelsize = rearwheelsize;
	}
	public int getHaveabs() {
		return haveabs;
	}
	public void setHaveabs(int haveabs) {
		this.haveabs = haveabs;
	}
	public int getCaryear() {
		return caryear;
	}
	public void setCaryear(int caryear) {
		this.caryear = caryear;
	}
	public String getFirstpic() {
		return firstpic;
	}
	public void setFirstpic(String firstpic) {
		this.firstpic = firstpic;
	}
	public List<String> getAllpic() {
		return allpic;
	}
	public void setAllpic(List<String> allpic) {
		this.allpic = allpic;
	}

	public String getBrandparentname() {
		return brandparentname;
	}
	public void setBrandparentname(String brandparentname) {
		this.brandparentname = brandparentname;
	}
	public String getBrandname1() {
		return brandname1;
	}
	public void setBrandname1(String brandname1) {
		this.brandname1 = brandname1;
	}
	@Override
	public String toString() {
		return "NewMotoModel [mid=" + mid + ", modelid=" + modelid + ", brandid=" + brandid + ", bpid=" + bpid + ", name=" + name + ", price=" + price + ", style=" + style + ", maxpower=" + maxpower + ", maxtorque=" + maxtorque + ", cc=" + cc + ", gearbox=" + gearbox + ", sitheight=" + sitheight + ", lwh=" + lwh + ", tankcapacity=" + tankcapacity + ", oilway=" + oilway + ", transmissionway="
				+ transmissionway + ", frontbrake=" + frontbrake + ", rearbrake=" + rearbrake + ", frontwheelsize=" + frontwheelsize + ", rearwheelsize=" + rearwheelsize + ", haveabs=" + haveabs + ", caryear=" + caryear + ", firstpic=" + firstpic + ", allpic=" + allpic + ", otherelectronic=" + otherelectronic + ", haveonboard=" + haveonboard + ", brandname1=" + brandname1 + ", brandparentname="
				+ brandparentname + "]";
	}
	
	
	
	
	

}
