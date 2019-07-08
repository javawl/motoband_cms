package com.motoband.carmanage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.brandparentmanage.BrandparentModel;
import com.motoband.util.BeanUtils;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.ESManager;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;
import com.motoband.util.RedisManager;


@Controller
@RequestMapping(value = "/carmanage")
public class carController {

	@Autowired
	private carService carService;
	@Autowired
	private boxService boxService;
	
	public static final String RUNKEY_NEWMOTOMODELMAP = "_newmotomodelmap";
	public static final String RUNKEY_NEWMOTOMODELPICLIST = "_newmotomodelpiclist";

	@RequestMapping(value = "/carMessage", method = RequestMethod.GET)
	public void carMessage(Model model, HttpSession session,
			HttpServletRequest request,String motoid) {
		ArrayList<motobrand> motobrandlist = carService.getMotoBrandList();
		ArrayList<mototype> mototypelist = carService.getMotoTypeList();
		carModel carModel = carService.getCarModelById(motoid);
		model.addAttribute("mototypelist", mototypelist);
		model.addAttribute("motobrandlist", motobrandlist);
		model.addAttribute("carModel", carModel);
		
	}
	@RequestMapping(value = "/carlist", method = RequestMethod.GET)
	public void boxlist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid, String nowtypeid,
			String nowbrandid,int page,int limit,int order,String orderConditions) {
		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		if (nowtypeid != null && nowtypeid.equals("-1")) {
			nowtypeid = null;
		}
		if (nowbrandid != null && nowbrandid.equals("-1")) {
			nowbrandid = null;
		}
		
		PageBean<carlist> pageBean=new PageBean<carlist>();
		if(page==0){
			page=1;
		}
        if(limit==0){
        	limit=20;
        }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount= carService.getCarListCount(nowtypeid,nowbrandid);
		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;

		if(orderConditions==null || orderConditions==""){
			orderConditions="";
		}
		ArrayList<carlist> carlists = carService.getCarList(userGuid,
				nowtypeid, nowbrandid,start,limit,order,orderConditions);
		ArrayList<motobrand> motobrandlist = carService.getMotoBrandList();
		ArrayList<mototype> mototypelist = carService.getMotoTypeList();

		int updateCheck = carService.getCarCheck("car_upd", userGuid);
		int delCheck = carService.getCarCheck("car_del", userGuid);
		int insCheck = carService.getCarCheck("car_ins", userGuid);
		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		if (delCheck > 0) {
			model.addAttribute("delCheck", "lock");
		} else {
			model.addAttribute("delCheck", "unlock");
		}
		if (insCheck > 0) {
			model.addAttribute("insCheck", "lock");
		} else {
			model.addAttribute("insCheck", "unlock");
		}
		model.addAttribute("carlists", carlists);
		model.addAttribute("mototypelist", mototypelist);
		model.addAttribute("motobrandlist", motobrandlist);
		if (nowtypeid == null) {
			model.addAttribute("nowtypeid", "-1");
		} else {
			model.addAttribute("nowtypeid", nowtypeid);
		}
		if (nowbrandid == null) {
			model.addAttribute("nowbrandid", "-1");
		} else {
			model.addAttribute("nowbrandid", nowbrandid);
		}
		
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
	}

	@RequestMapping(value = "/checkCarName", method = RequestMethod.POST)
	public void checkCarName(Model model, HttpSession session,
			HttpServletRequest request, String newcarname, PrintWriter out) {
		carModel carModel = carService.checkCarName(newcarname);

		if (carModel == null) {
			out.print("ok");
		} else {
			out.print("no");
		}
	}

	@RequestMapping(value = "/saveNewCar", method = RequestMethod.POST)
	public void saveNewCar(Model model, HttpSession session,
			HttpServletRequest request, String newcarname, String newcarcc,
			String newCar_Type, String newCar_Brand, PrintWriter out) {
		carModel carModel = new carModel();
		carModel.setName(newcarname);
		carModel.setCc(Integer.parseInt(newcarcc));
		carModel.setBrandid(Integer.parseInt(newCar_Brand));
		carModel.setTypeid(Integer.parseInt(newCar_Type));
		carService.saveNewCar(carModel);
		out.print("success");
	}

	@RequestMapping(value = "/checkCarType", method = RequestMethod.POST)
	public void checkCarType(Model model, HttpSession session,
			HttpServletRequest request, String newCarTypeName, PrintWriter out) {
		String typename = carService.checkCarType(newCarTypeName);
		if (typename == null) {
			out.print("ok");
		} else {
			out.print("no");
		}
	}

	@RequestMapping(value = "/insertNewCarType", method = RequestMethod.POST)
	public void insertNewCarType(Model model, HttpSession session,
			HttpServletRequest request, String newcartype, PrintWriter out) {
		carService.insertNewCarType(newcartype);
		out.print("success");
	}

	@RequestMapping(value = "/checkCarBrand", method = RequestMethod.POST)
	public void checkCarBrand(Model model, HttpSession session,
			HttpServletRequest request, String newCarBrand, PrintWriter out) {
		String brandname = carService.checkCarBrand(newCarBrand);
		if (brandname == null) {
			out.print("ok");
		} else {
			out.print("no");
		}
	}

	@RequestMapping(value = "/insertNewCarBrand", method = RequestMethod.POST)
	public void insertNewCarBrand(Model model, HttpSession session,
			HttpServletRequest request, String newcarbrand,String bpid, PrintWriter out) {
		carService.insertNewCarBrand(newcarbrand,bpid);
		out.print("success");
	}
	@RequestMapping(value = "/saveCarMessage", method = RequestMethod.POST)
	public void saveCarMessage(Model model, HttpSession session,
			HttpServletRequest request, String car_id, String car_name,String car_cc,String car_type,String car_brand,PrintWriter out) {
		carModel carModel=new carModel();
		carModel.setModelid(Integer.parseInt(car_id));
		carModel.setName(car_name);
		carModel.setCc(Integer.parseInt(car_cc));
		carModel.setTypeid(Integer.parseInt(car_type));
		carModel.setBrandid(Integer.parseInt(car_brand));
		carService.saveCarMessage(carModel);
		out.print("success");
	}
	
	@RequestMapping(value = "/checkCarNewName", method = RequestMethod.POST)
	public void checkCarNewName(Model model, HttpSession session,
			HttpServletRequest request, String caroldname, String newcarname,String car_id,PrintWriter out) {
		 carModel carModel=carService.checkCarNewName(car_id,newcarname);
		 if (carModel==null) {
				out.print("ok");
			} else {
				out.print("no");
			}
	}
	
	@RequestMapping(value = "/delCarMessage", method = RequestMethod.POST)
	public void delCarMessage(Model model, HttpSession session,
			HttpServletRequest request,String car_id,PrintWriter out) {
		carService.delCarMessage(car_id);
		out.print("success");
	}
	@RequestMapping(value = "/selectCarbrandParentname", method = RequestMethod.POST)
	public void selectCarbrandParentname(Model model, HttpSession session,
			HttpServletRequest request, PrintWriter out) {
		List<BrandparentModel> brandparentModels =carService.selectCarbrandParentname();
		String jsonStr="";
		if(brandparentModels !=null && brandparentModels.size()>0){
			jsonStr=JSONObject.toJSONString(brandparentModels);
		}
		out.print(jsonStr);
	}
	
	
	@RequestMapping(value = "/newmotomodellist", method = RequestMethod.GET)
	public void newmotomodellist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid,
			int page,int limit,int order,String orderConditions) {
		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<NewMotoModel> pageBean=new PageBean<NewMotoModel>();
		if(page==0){
			page=1;
		}
        if(limit==0){
        	limit=20;
        }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount= carService.getNewmotomodelListCount();
		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;

		if(orderConditions==null || orderConditions==""){
			orderConditions="";
		}
		ArrayList<NewMotoModel> newmotomodels = carService.getNewmotomodelList(
				start,limit,order,orderConditions);


		model.addAttribute("newmotomodels", newmotomodels);

		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
	}
	@RequestMapping(value = "/newmotomodel_update", method = RequestMethod.GET)
	public void newmotomodel_update(Model model, HttpSession session,
			HttpServletRequest request,String mid) {
	
		NewMotoModel newmotomodel = carService.getNewmotomodelByid(mid);
		List<String> piclist = carService.getNewmotomodelpicListBymid(mid);
		
		model.addAttribute("piclist", piclist);
		model.addAttribute("newmotomodel", newmotomodel);
		
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);

	}
	
	@RequestMapping(value = "/getBrandListByBpid", method = RequestMethod.POST)
	public void getBrandListByBpid(Model model, HttpSession session,
			HttpServletRequest request,String bpid, PrintWriter out) {
		List<motobrand> brandModels =carService.getBrandListByBpid(bpid);
		String jsonStr="";
		if(brandModels !=null && brandModels.size()>0){
			jsonStr=JSONObject.toJSONString(brandModels);
		}
		out.print(jsonStr);
	}
	@RequestMapping(value = "/getMotomodelByBrandid", method = RequestMethod.POST)
	public void getMotomodelByBrandid(Model model, HttpSession session,
			HttpServletRequest request,String brandid, PrintWriter out) {
		List<carModel> motoModels =carService.getMotomodelByBrandid(brandid);
		String jsonStr="";
		if(motoModels !=null && motoModels.size()>0){
			jsonStr=JSONObject.toJSONString(motoModels);
		}
		out.print(jsonStr);
	}
	@RequestMapping(value = "/insertOrupdateNewMotomodel", method = RequestMethod.POST)
	public void insertOrupdateNewMotomodel(Model model, HttpSession session,
			HttpServletRequest request,String mid,
			String modelid,String brandid,String bpid,String name,String price,
			String style,String maxpower,String maxtorque,String cc,String gearbox,
			String sitheight,String lwh,String tankcapacity,String oilway,String transmissionway,
			String frontbrake,String rearbrake,String frontwheelsize,String rearwheelsize,String haveabs,
			String caryear,String otherelectronic,String haveonboard,
			String pic1,String pic2,String pic3,String pic4,String pic5,
			String pic6,String pic7,String pic8,String pic9,
			PrintWriter out) throws Exception {
		
		String jsonStr="";
		NewMotoModel newMotoModel = new NewMotoModel();
		if(modelid!=null && !"".equals(modelid)){
			newMotoModel.setModelid(Integer.parseInt(modelid));
		}
		if(brandid!=null && !"".equals(brandid)){
			newMotoModel.setBrandid(Integer.parseInt(brandid));
		}
		if(bpid!=null && !"".equals(bpid)){
			newMotoModel.setBpid(Integer.parseInt(bpid));
		}
		newMotoModel.setName(name);
		if(price!=null && !"".equals(price)){
			newMotoModel.setPrice(Long.parseLong(price));;
		}
		newMotoModel.setStyle(style);
		if(maxpower!=null && !"".equals(maxpower)){
			newMotoModel.setMaxpower(Float.parseFloat(maxpower));
		}
		if(maxtorque!=null && !"".equals(maxtorque)){
			newMotoModel.setMaxtorque(Float.parseFloat(maxtorque));
		}
		if(cc!=null && !"".equals(cc)){
			newMotoModel.setCc(Float.parseFloat(cc));
		}
		newMotoModel.setGearbox(gearbox);
		if(sitheight!=null && !"".equals(sitheight)){
			newMotoModel.setSitheight(Integer.parseInt(sitheight));
		}
		newMotoModel.setLwh(lwh);
		if(tankcapacity!=null && !"".equals(tankcapacity)){
			newMotoModel.setTankcapacity(Float.parseFloat(tankcapacity));
		}
		newMotoModel.setOilway(oilway);
		newMotoModel.setTransmissionway(transmissionway);
		newMotoModel.setFrontbrake(frontbrake);
		newMotoModel.setRearbrake(rearbrake);
		newMotoModel.setFrontwheelsize(frontwheelsize);
		newMotoModel.setRearwheelsize(rearwheelsize);
		if(haveabs!=null && !"".equals(haveabs)){
			newMotoModel.setHaveabs(Integer.parseInt(haveabs));
		}
		if(caryear!=null && !"".equals(caryear)){
			newMotoModel.setCaryear(Integer.parseInt(caryear));
		}
		newMotoModel.setOtherelectronic(otherelectronic);
		if(haveonboard!=null && !"".equals(haveonboard)){
			newMotoModel.setHaveonboard(Integer.parseInt(haveonboard));
		}
		
		if(mid!=null && mid!=""){
			newMotoModel.mid=mid;
		 }else{
			 newMotoModel.mid=MbUtil.getUUID();
		 }
		long currenttime=System.currentTimeMillis();
		
		//对图片的处理
		List<NewMotoModelPicModel>  piclist= new ArrayList<NewMotoModelPicModel>();
		if(pic1!=null&&!"".equals(pic1)){
			newMotoModel.setFirstpic(pic1);
			NewMotoModelPicModel nmpm= new NewMotoModelPicModel();
			nmpm.setMid(newMotoModel.mid);
			nmpm.setPicurl(pic1);
			nmpm.setOrderindex(currenttime+1);
			piclist.add(nmpm);
		}
		if(pic2!=null&&!"".equals(pic2)){
			NewMotoModelPicModel nmpm= new NewMotoModelPicModel();
			nmpm.setMid(newMotoModel.mid);
			nmpm.setPicurl(pic2);
			nmpm.setOrderindex(currenttime+2);
			piclist.add(nmpm);
		}
		if(pic3!=null&&!"".equals(pic3)){
			NewMotoModelPicModel nmpm= new NewMotoModelPicModel();
			nmpm.setMid(newMotoModel.mid);
			nmpm.setPicurl(pic3);
			nmpm.setOrderindex(currenttime+3);
			piclist.add(nmpm);
		}
		if(pic4!=null&&!"".equals(pic4)){
			NewMotoModelPicModel nmpm= new NewMotoModelPicModel();
			nmpm.setMid(newMotoModel.mid);
			nmpm.setPicurl(pic4);
			nmpm.setOrderindex(currenttime+4);
			piclist.add(nmpm);
		}
		if(pic6!=null&&!"".equals(pic6)){
			NewMotoModelPicModel nmpm= new NewMotoModelPicModel();
			nmpm.setMid(newMotoModel.mid);
			nmpm.setPicurl(pic6);
			nmpm.setOrderindex(currenttime+6);
			piclist.add(nmpm);
		}
		if(pic5!=null&&!"".equals(pic5)){
			NewMotoModelPicModel nmpm= new NewMotoModelPicModel();
			nmpm.setMid(newMotoModel.mid);
			nmpm.setPicurl(pic5);
			nmpm.setOrderindex(currenttime+5);
			piclist.add(nmpm);
		}
		if(pic7!=null&&!"".equals(pic7)){
			NewMotoModelPicModel nmpm= new NewMotoModelPicModel();
			nmpm.setMid(newMotoModel.mid);
			nmpm.setPicurl(pic7);
			nmpm.setOrderindex(currenttime+7);
			piclist.add(nmpm);
		}
		if(pic8!=null&&!"".equals(pic8)){
			NewMotoModelPicModel nmpm= new NewMotoModelPicModel();
			nmpm.setMid(newMotoModel.mid);
			nmpm.setPicurl(pic8);
			nmpm.setOrderindex(currenttime+8);
			piclist.add(nmpm);
		}
		if(pic9!=null&&!"".equals(pic9)){
			NewMotoModelPicModel nmpm= new NewMotoModelPicModel();
			nmpm.setMid(newMotoModel.mid);
			nmpm.setPicurl(pic9);
			nmpm.setOrderindex(currenttime+9);
			piclist.add(nmpm);
		}
		if(piclist!=null && piclist.size()>0){
			carService.dowithNewMotomodelPic(newMotoModel.mid,piclist);
		}
		
		boolean flag=false;
		try {
			//处理newmotomodel
			 carService.insertOrUpdateNewMotomodel(newMotoModel);
			 flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr="fail";
		}
		
		if(flag){
			//处理redis
			Map<String, String> nmmmap=BeanUtils.objectToMap(newMotoModel);
			nmmmap.remove("brandname");
			nmmmap.remove("brandparentname");
	    	RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, newMotoModel.mid + RUNKEY_NEWMOTOMODELMAP, nmmmap);
	    	RedisManager.getInstance().delbykey(Consts.REDIS_SCHEME_RUN, newMotoModel.mid + RUNKEY_NEWMOTOMODELPICLIST);
        	if(piclist!=null && piclist.size()>0){
        		for(NewMotoModelPicModel nmp: piclist){
        			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, newMotoModel.mid + RUNKEY_NEWMOTOMODELPICLIST,nmp.getOrderindex(), nmp.getPicurl());
        		}
        	}
        	
           //处理ES
		   ESManager.syncNewMotomodelEs(newMotoModel);

			jsonStr="success";
		}else{
			jsonStr="fail";
		} 
		
		out.print(jsonStr);
	}
	
	@RequestMapping(value = "/newmotomodel_ins", method = RequestMethod.GET)
	public void newmotomodel_ins(Model model, HttpSession session,
			HttpServletRequest request) {
	
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);

	}
	
	
	@RequestMapping(value = "/deleteNewMotomodel", method = RequestMethod.POST)
	public void deleteNewMotomodel(Model model, HttpSession session, HttpServletRequest request, String mid,
			PrintWriter out) throws Exception {
		try {
			carService.deleteNewMotomodelbymid(mid);
			RedisManager.getInstance().hdel(Consts.REDIS_SCHEME_RUN,mid + RUNKEY_NEWMOTOMODELMAP);
			RedisManager.getInstance().delbykey(Consts.REDIS_SCHEME_RUN,mid + RUNKEY_NEWMOTOMODELPICLIST);
			
			ESManager.delNewMotoModelEs(mid);
			
			out.print("success");
		} catch (Exception e) {
			out.print("fail");
		}
		
	}

}
