package com.motoband.secondcar;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.motoband.news.NewsVideoModel;



public class SecondCarModel {
	public String secondcarid;
	public int brandid;// 品牌id
	public String brand;// 品牌
	public int modelid;// 车型id
	public String model;// 车型
	public String title;// 搜索用
	public double kilometer;// 表显里程
	public int cc;
	public long price;// 转让价格
	public String caraddress;// 车所在地
	public String licenseplate; // 牌照
	public int transfercount;// 过户次数
	public long firstonthecardtime;// 初次上牌时间
	public String des;// 详细介绍
	public String videourl;// 视频url
	public String exteriorpicurl;// 外观图片url，多个以逗号分隔
	public String detailspicurl;// 细节图片url，多个以逗号分隔
	public String flawpicurl;// 瑕疵图片url，多个以逗号分隔
	public String contacts;// 联系人
	public String contactsphone;// 联系人电话
	public String createuser;// 创建人
	public long createtime;// 创建时间
	public long lastupdatetime;// 最后编辑时间
	public int state;// 状态 0上线，1下线，2已卖出，
	public int createusertype;// 0个人车源，1商家车源
	public int viewcount;// 浏览量
	public int commentcount;// 留言数
	public int favoritescount;// 收藏数
	
	public NewsVideoModel videodatamodel;
	public String videodatastr;
	
	public String style;// 车型
	public int hasolddrivercomment;// 0 无 1 有
	public int procedure;// 手续情况 (0正规 1其他)
	public int lapsestate;// 是否失效 (0未失效 1已失效)
	public long lastrefreshtime;// 最后刷新时间
	public int hasofficial;// 是否摩托邦官方（0否，1是）
	public String keyword;// 关键字（cms录入）
	
	
   //扩展
	public String firstonthecardtimeString;	
	public String createtimeString;	
	public String lastrefreshtimeString;	
	

	public String getCreatetimeString() {
		return createtimeString;
	}

	public void setCreatetimeString(String createtimeString) {
		this.createtimeString = createtimeString;
	}

	public String getLastrefreshtimeString() {
		return lastrefreshtimeString;
	}

	public void setLastrefreshtimeString(String lastrefreshtimeString) {
		this.lastrefreshtimeString = lastrefreshtimeString;
	}

	public String getSecondcarid() {
		return secondcarid;
	}

	public void setSecondcarid(String secondcarid) {
		this.secondcarid = secondcarid;
	}

	public int getBrandid() {
		return brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getModelid() {
		return modelid;
	}

	public void setModelid(int modelid) {
		this.modelid = modelid;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getKilometer() {
		return kilometer;
	}

	public void setKilometer(double kilometer) {
		this.kilometer = kilometer;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getCaraddress() {
		return caraddress;
	}

	public void setCaraddress(String caraddress) {
		this.caraddress = caraddress;
	}

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public int getTransfercount() {
		return transfercount;
	}

	public void setTransfercount(int transfercount) {
		this.transfercount = transfercount;
	}

	public long getFirstonthecardtime() {
		return firstonthecardtime;
	}

	public void setFirstonthecardtime(long firstonthecardtime) {
		this.firstonthecardtime = firstonthecardtime;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public String getExteriorpicurl() {
		return exteriorpicurl;
	}

	public void setExteriorpicurl(String exteriorpicurl) {
		this.exteriorpicurl = exteriorpicurl;
	}

	public String getDetailspicurl() {
		return detailspicurl;
	}

	public void setDetailspicurl(String detailspicurl) {
		this.detailspicurl = detailspicurl;
	}

	public String getFlawpicurl() {
		return flawpicurl;
	}

	public void setFlawpicurl(String flawpicurl) {
		this.flawpicurl = flawpicurl;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsphone() {
		return contactsphone;
	}

	public void setContactsphone(String contactsphone) {
		this.contactsphone = contactsphone;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public long getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(long lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCreateusertype() {
		return createusertype;
	}

	public void setCreateusertype(int createusertype) {
		this.createusertype = createusertype;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public int getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}

	public int getFavoritescount() {
		return favoritescount;
	}

	public void setFavoritescount(int favoritescount) {
		this.favoritescount = favoritescount;
	}

	public String getFirstonthecardtimeString() {
		return firstonthecardtimeString;
	}

	public void setFirstonthecardtimeString(String firstonthecardtimeString) {
		this.firstonthecardtimeString = firstonthecardtimeString;
	}

	

	public NewsVideoModel getVideodatamodel() {
		return videodatamodel;
	}

	public void setVideodatamodel(NewsVideoModel videodatamodel) {
		this.videodatamodel = videodatamodel;
	}

	public String getVideodatastr() {
		return videodatastr;
	}

	public void setVideodatastr(String videodatastr) {
		this.videodatastr = videodatastr;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int getHasolddrivercomment() {
		return hasolddrivercomment;
	}

	public void setHasolddrivercomment(int hasolddrivercomment) {
		this.hasolddrivercomment = hasolddrivercomment;
	}

	public int getProcedure() {
		return procedure;
	}

	public void setProcedure(int procedure) {
		this.procedure = procedure;
	}

	public int getLapsestate() {
		return lapsestate;
	}

	public void setLapsestate(int lapsestate) {
		this.lapsestate = lapsestate;
	}

	public long getLastrefreshtime() {
		return lastrefreshtime;
	}

	public void setLastrefreshtime(long lastrefreshtime) {
		this.lastrefreshtime = lastrefreshtime;
	}

	public int getHasofficial() {
		return hasofficial;
	}

	public void setHasofficial(int hasofficial) {
		this.hasofficial = hasofficial;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SecondCarModel [secondcarid=" + secondcarid + ", brandid=" + brandid + ", brand=" + brand + ", modelid=" + modelid + ", model=" + model + ", title=" + title + ", kilometer=" + kilometer + ", cc=" + cc + ", price=" + price + ", caraddress=" + caraddress + ", licenseplate=" + licenseplate + ", transfercount=" + transfercount + ", firstonthecardtime=" + firstonthecardtime + ", des="
				+ des + ", videourl=" + videourl + ", exteriorpicurl=" + exteriorpicurl + ", detailspicurl=" + detailspicurl + ", flawpicurl=" + flawpicurl + ", contacts=" + contacts + ", contactsphone=" + contactsphone + ", createuser=" + createuser + ", createtime=" + createtime + ", lastupdatetime=" + lastupdatetime + ", state=" + state + ", createusertype=" + createusertype + ", viewcount="
				+ viewcount + ", commentcount=" + commentcount + ", favoritescount=" + favoritescount + ", videodatamodel=" + videodatamodel + ", videodatastr=" + videodatastr + ", style=" + style + ", hasolddrivercomment=" + hasolddrivercomment + ", procedure=" + procedure + ", lapsestate=" + lapsestate + ", lastrefreshtime=" + lastrefreshtime + ", hasofficial=" + hasofficial + ", keyword="
				+ keyword + ", firstonthecardtimeString=" + firstonthecardtimeString + "]";
	}

	public static Map<String, String> convertToMap(SecondCarModel model) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("secondcarid", model.secondcarid==null? "" : model.secondcarid);
		map.put("brandid", String.valueOf(model.brandid));
		map.put("brand", model.brand==null? "" : model.brand);
		map.put("modelid", String.valueOf(model.modelid));
		map.put("model", model.model==null? "" : model.model);
		map.put("title", model.title==null? "" : model.title);
		map.put("kilometer", String.valueOf(model.kilometer));
		map.put("price", String.valueOf(model.price));
		map.put("cc", String.valueOf(model.cc));

		map.put("caraddress", model.caraddress==null? "" : model.caraddress);
		map.put("licenseplate", model.licenseplate==null? "" : model.licenseplate);
		map.put("transfercount", String.valueOf(model.transfercount));
		map.put("firstonthecardtime", String.valueOf(model.firstonthecardtime));
		map.put("des", model.des==null? "" : model.des);
		map.put("videourl", model.videourl==null? "" : model.videourl);
		map.put("exteriorpicurl", model.exteriorpicurl==null? "" : model.exteriorpicurl);
		map.put("detailspicurl", model.detailspicurl==null? "" : model.detailspicurl);
		map.put("flawpicurl", model.flawpicurl==null? "" : model.flawpicurl);
		map.put("contacts", model.contacts==null? "" : model.contacts);
		map.put("contactsphone", model.contactsphone==null? "" : model.contactsphone);
		map.put("createuser", model.createuser==null? "" :model.createuser);
		map.put("createtime", String.valueOf(model.createtime));
		map.put("lastupdatetime", String.valueOf(model.lastupdatetime));
		map.put("state", String.valueOf(model.state));
		map.put("createusertype", String.valueOf(model.createusertype));
		map.put("viewcount", String.valueOf(model.viewcount));
		map.put("commentcount", String.valueOf(model.commentcount));
		map.put("favoritescount", String.valueOf(model.favoritescount));
		if (model.videodatamodel != null) {
			String tempStr = JSON.toJSONString(model.videodatamodel);
			map.put("videodatamodel", tempStr);
			model.videodatastr = tempStr;
		}
		map.put("style", model.style==null? "" : model.style);
		map.put("hasolddrivercomment", String.valueOf(model.hasolddrivercomment));
		map.put("procedure", String.valueOf(model.procedure));
		map.put("lapsestate", String.valueOf(model.lapsestate));
		map.put("lastrefreshtime", String.valueOf(model.lastrefreshtime));
		map.put("hasofficial", String.valueOf(model.hasofficial));
		map.put("keyword", model.keyword==null? "" : model.keyword);
		return map;
	}

	public static SecondCarModel convertToModel(Map<String, String> map) {
		if (map == null) {
			return null;
		}

		SecondCarModel model = new SecondCarModel();

		if (map.containsKey("secondcarid")) {
			if (!map.get("secondcarid").isEmpty())
				model.secondcarid = map.get("secondcarid");
		}
		if (map.containsKey("brandid")) {
			if (!map.get("brandid").isEmpty())
				model.brandid = Integer.parseInt(map.get("brandid"));
		}
		if (map.containsKey("cc")) {
			if (!map.get("cc").isEmpty())
				model.cc = Integer.parseInt(map.get("cc"));
		}
		if (map.containsKey("brand")) {
			if (!map.get("brand").isEmpty())
				model.brand = map.get("brand");
		}
		if (map.containsKey("model")) {
			if (!map.get("model").isEmpty())
				model.model = map.get("model");
		}
		if (map.containsKey("modelid")) {
			if (!map.get("modelid").isEmpty())
				model.modelid = Integer.parseInt(map.get("modelid"));
		}
		if (map.containsKey("title")) {
			if (!map.get("title").isEmpty())
				model.title = map.get("title");
		}
		
		if (map.containsKey("kilometer")) {
			if (!map.get("kilometer").isEmpty())
				model.kilometer = Double.valueOf(map.get("kilometer"));
		}
		if (map.containsKey("price")) {
			if (!map.get("price").isEmpty())
				model.price = Long.valueOf(map.get("price"));
		}

		if (map.containsKey("caraddress")) {
			if (!map.get("caraddress").isEmpty())
				model.caraddress = map.get("caraddress");
		}
		if (map.containsKey("licenseplate")) {
			if (!map.get("licenseplate").isEmpty())
				model.licenseplate = map.get("licenseplate");
		}
		if (map.containsKey("transfercount")) {
			if (!map.get("transfercount").isEmpty())
				model.transfercount = Integer.parseInt(map.get("transfercount"));
		}
		if (map.containsKey("firstonthecardtime")) {
			if (!map.get("firstonthecardtime").isEmpty())
				model.firstonthecardtime = Long.valueOf(map.get("firstonthecardtime"));
		}
		
		if (map.containsKey("des")) {
			if (!map.get("des").isEmpty())
				model.des = map.get("des");
		}
		if (map.containsKey("videourl")) {
			if (!map.get("videourl").isEmpty())
				model.videourl = map.get("videourl");
		}
		if (map.containsKey("exteriorpicurl")) {
			if (!map.get("exteriorpicurl").isEmpty())
				model.exteriorpicurl = map.get("exteriorpicurl");
		}
		if (map.containsKey("detailspicurl")) {
			if (!map.get("detailspicurl").isEmpty())
				model.detailspicurl = map.get("detailspicurl");
		}
		if (map.containsKey("flawpicurl")) {
			if (!map.get("flawpicurl").isEmpty())
				model.flawpicurl = map.get("flawpicurl");
		}
		if (map.containsKey("contacts")) {
			if (!map.get("contacts").isEmpty())
				model.contacts = map.get("contacts");
		}
		if (map.containsKey("contactsphone")) {
			if (!map.get("contactsphone").isEmpty())
				model.contactsphone = map.get("contactsphone");
		}
		if (map.containsKey("createuser")) {
			if (!map.get("createuser").isEmpty())
				model.createuser = map.get("createuser");
		}
		
		if (map.containsKey("createtime")) {
			if (!map.get("createtime").isEmpty())
				model.createtime = Long.valueOf(map.get("createtime"));
		}
		if (map.containsKey("lastupdatetime")) {
			if (!map.get("lastupdatetime").isEmpty())
				model.lastupdatetime = Long.valueOf(map.get("lastupdatetime"));
		}
		
		if (map.containsKey("state")) {
			if (!map.get("state").isEmpty())
				model.state = Integer.valueOf(map.get("state"));
		}
		if (map.containsKey("createusertype")) {
			if (!map.get("createusertype").isEmpty())
				model.createusertype = Integer.valueOf(map.get("createusertype"));
		}
		if (map.containsKey("viewcount")) {
			if (!map.get("viewcount").isEmpty())
				model.viewcount = Integer.valueOf(map.get("viewcount"));
		}
		if (map.containsKey("commentcount")) {
			if (!map.get("commentcount").isEmpty())
				model.commentcount = Integer.valueOf(map.get("commentcount"));
		}
		if (map.containsKey("favoritescount")) {
			if (!map.get("favoritescount").isEmpty())
				model.favoritescount = Integer.valueOf(map.get("favoritescount"));
		}
		if (map.containsKey("videodatamodel")) {
			if (!map.get("videodatamodel").isEmpty()) {
				model.videodatamodel = JSON.parseObject(map.get("videodatamodel"), NewsVideoModel.class);
			}
		}
		if (map.containsKey("style")) {
			if (!map.get("style").isEmpty())
				model.style = map.get("style");
		}
		if (map.containsKey("hasolddrivercomment")) {
			if (!map.get("hasolddrivercomment").isEmpty())
				model.hasolddrivercomment = Integer.valueOf(map.get("hasolddrivercomment"));
		}
		if (map.containsKey("procedure")) {
			if (!map.get("procedure").isEmpty())
				model.procedure = Integer.valueOf(map.get("procedure"));
		}
		if (map.containsKey("lapsestate")) {
			if (!map.get("lapsestate").isEmpty())
				model.lapsestate = Integer.valueOf(map.get("lapsestate"));
		}
		if (map.containsKey("lastrefreshtime")) {
			if (!map.get("lastrefreshtime").isEmpty())
				model.lastrefreshtime = Long.valueOf(map.get("lastrefreshtime"));
		}
		if (map.containsKey("hasofficial")) {
			if (!map.get("hasofficial").isEmpty())
				model.hasofficial = Integer.valueOf(map.get("hasofficial"));
		}
		if (map.containsKey("keyword")) {
			if (!map.get("keyword").isEmpty())
				model.keyword = map.get("keyword");
		}
		return model;
	}


}
