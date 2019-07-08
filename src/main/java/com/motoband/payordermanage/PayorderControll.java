package com.motoband.payordermanage;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.motoband.admin.admin.Admin;
import com.motoband.configdata.configdataService;
import com.motoband.dataversion.dataversionService;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.PageBean;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


@Controller
@RequestMapping(value = "/payordermanage")
public class PayorderControll {
 @Autowired
 private PayorderService payorderService;
 @Autowired
 private configdataService configdataService;
 @Autowired
 private dataversionService dataversionService;
 
//日志记录
	public	static Logger logger = LoggerFactory.getLogger(PayorderControll.class);
	
	public static final MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");	
	private static OkHttpClient httpClient = new OkHttpClient();
	
	@RequestMapping(value = "/payorderlist", method = RequestMethod.GET)
	public void payorderlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int order,String userid,String order_no,
			String state,String starttime,String endtime) {

		if(userid==null || "".equals(userid)){
			userid="";
		}
		if(order_no==null || "".equals(order_no)){
			order_no="";
		}
		if(state==null || "".equals(state)|| "-1".equals(state)){
			state="";
		}
		long starttimelong = 0;
		long endtimelong = 0;
		if(starttime==null || "".equals(starttime)){
			starttime="";
			starttimelong = 0;
		}else{
			starttimelong=Long.parseLong(starttime);
		}
		if(endtime==null || "".equals(endtime)){
			endtime="";
			endtimelong=0;
		}else{
			endtimelong=Long.parseLong(endtime);
		}
		PageBean<PayorderModel> pageBean=new PageBean<PayorderModel>();
		if(page==0){
			page=1;
		}
      if(limit==0){
     	limit=20;
       }
   
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=payorderService.getPayorderListCount(userid,order_no,state,starttimelong,endtimelong);

		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;

		ArrayList<PayorderModel> payorderlist =payorderService.getPayorderList(userGuid,start,limit,order,userid,order_no,state,starttimelong,endtimelong);
		
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		if(payorderlist !=null && payorderlist.size()>0){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(PayorderModel payorderModel:payorderlist){
				String ordertimeString = formatter.format(payorderModel.getOrdertime());
				payorderModel.setOrdertimeString(ordertimeString);
				JSONObject jsonObj= JSON.parseObject(payorderModel.getCarinfo());
				payorderModel.setPlateNumber(jsonObj.getString("plateNumber"));
				jsonObj= JSON.parseObject(payorderModel.getUserinfo());
				payorderModel.setUserName(jsonObj.getString("userName"));
				payorderModel.setUserIDInfo(jsonObj.getString("userIDInfo"));
				
			}
		}
		model.addAttribute("payorderlist", payorderlist);
		model.addAttribute("pageBean", pageBean);

		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		if(state!=null && state !=""){
			model.addAttribute("state",state);	
		}else{
			model.addAttribute("state",-1);	
		}
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(starttime !=null && starttime !=""){
			model.addAttribute("starttime",formatter1.format(Long.parseLong(starttime)));
		}else{
			model.addAttribute("starttime",null);
		}
		if(endtime !=null && endtime !=""){
			model.addAttribute("endtime",formatter1.format(Long.parseLong(endtime)));
		}else{
			model.addAttribute("endtime",null);
		}
		
		int updateCheck = payorderService.getPayorderUpdateCheck(userGuid);
		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		
	}
	
	@RequestMapping(value = "/lookCar", method = RequestMethod.POST)
	public void lookCar(Model model, HttpSession session, HttpServletRequest request, String payid,PrintWriter out) {
		String carinfo = null;
	    if(payid !=null && !"".equals(payid)){
	    	 carinfo =payorderService.lookCar(payid);
	    }
		out.print(carinfo);
		
	}
	@RequestMapping(value = "/lookUser", method = RequestMethod.POST)
	public void lookUser(Model model, HttpSession session, HttpServletRequest request, String payid,PrintWriter out) {
		String userinfo = null;
	    if(payid !=null && !"".equals(payid)){
	    	 userinfo =payorderService.lookUser(payid);
	    }
		out.print(userinfo);
		
	}
	@RequestMapping(value = "/lookInsurance", method = RequestMethod.POST)
	public void lookInsurance(Model model, HttpSession session, HttpServletRequest request, String order_no,PrintWriter out) {
		InsuranceModel insuranceModel = null;
		String jsonObjectStr =null;
	    if(order_no !=null && !"".equals(order_no)){
	    	insuranceModel =payorderService.lookInsurance(order_no);
	    	
	    }
	    if(insuranceModel !=null){
	    	jsonObjectStr = JSONObject.toJSONString(insuranceModel);
	    }
		out.print(jsonObjectStr);
		
	}
	@RequestMapping(value = "/payorderloglist", method = RequestMethod.GET)
	public void payorderloglist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int order,String inputorder_id) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<PayorderlogModel> pageBean=new PageBean<PayorderlogModel>();
		if(page==0){
			page=1;
		}
      if(limit==0){
     	limit=20;
       }
   
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=payorderService.getPayorderlogListCount(inputorder_id);

		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;
		ArrayList<PayorderlogModel> payorderloglist =payorderService.getPayorderlogList(userGuid,start,limit,order,inputorder_id);
		
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		if(payorderloglist!=null && payorderloglist.size()>0){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(PayorderlogModel payorderlogModel:payorderloglist){
				String timeString = formatter.format(payorderlogModel.getTime());
				payorderlogModel.setTimeString(timeString);
			}
			
		}
		model.addAttribute("payorderloglist", payorderloglist);
		model.addAttribute("pageBean", pageBean);

		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
	}
	@RequestMapping(value = "/insuranceupdatelist", method = RequestMethod.GET)
	public void getInsuranceupdatelist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int order) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<InsuranceupdateModel> pageBean=new PageBean<InsuranceupdateModel>();
		if(page==0){
			page=1;
		}
      if(limit==0){
     	limit=20;
       }
   
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=payorderService.getInsuranceupdatelistCount();

		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;
		ArrayList<InsuranceupdateModel> insuranceudatelist =payorderService.getInsuranceupdatelist(userGuid,start,limit,order);
		String carCheckCode = configdataService.getConfigValueBykey("INSURANCE_CARCHECKCODE");
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		if(insuranceudatelist!=null && insuranceudatelist.size()>0){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(InsuranceupdateModel insuranceupdateModel:insuranceudatelist){
				String timeString = formatter.format(insuranceupdateModel.getAddtime());
				insuranceupdateModel.setAddtimeString(timeString);
			}
			
		}
		model.addAttribute("insuranceudatelist", insuranceudatelist);
		model.addAttribute("carCheckCode", carCheckCode);
		model.addAttribute("pageBean", pageBean);

		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
	}
	@RequestMapping(value = "/getInsuranceupdateByid", method = RequestMethod.POST)
	public void getInsuranceupdateByid(Model model, HttpSession session, HttpServletRequest request, String iuid,PrintWriter out) {
		InsuranceupdateModel insuranceupdateModel = null;
		String jsonObjectStr =null;
	    if(iuid !=null && !"".equals(iuid)){
	    	insuranceupdateModel =payorderService.getInsuranceupdateByid(iuid);
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    	insuranceupdateModel.setAddtimeString(formatter.format(insuranceupdateModel.getAddtime()));
	    	
	    }
	    if(insuranceupdateModel !=null){
	    	jsonObjectStr = JSONObject.toJSONString(insuranceupdateModel);
	    }
		out.print(jsonObjectStr);
		
	}
	@RequestMapping(value = "/updateInsuranceupdate", method = RequestMethod.POST)
	public void updateInsuranceupdate(Model model, HttpSession session, HttpServletRequest request, String iuid,String desc,String addtime, PrintWriter out) throws ParseException {
		InsuranceupdateModel insuranceupdateModel = new InsuranceupdateModel();
	    if(iuid !=null && !"".equals(iuid)){
	    	insuranceupdateModel.setIuid(Integer.parseInt(iuid));	
	    }
	    if(desc !=null && !"".equals(desc)){
    		insuranceupdateModel.setDesc(desc);
	    }
	    if(addtime !=null && !"".equals(addtime)){
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    	insuranceupdateModel.setAddtime(formatter.parse(addtime).getTime());
	    }
	    payorderService.updateInsuranceupdate(insuranceupdateModel);
	   
		out.print("success");
		
	}
	@RequestMapping(value = "/addInsuranceupdate", method = RequestMethod.POST)
	public void addInsuranceupdate(Model model, HttpSession session, HttpServletRequest request,String desc,String addtime,PrintWriter out) throws ParseException {
		InsuranceupdateModel insuranceupdateModel = new InsuranceupdateModel();
	    
	      if(desc !=null && !"".equals(desc)){
	     		insuranceupdateModel.setDesc(desc);
	 	    }
	 	    if(addtime !=null && !"".equals(addtime)){
	 	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	 	    	insuranceupdateModel.setAddtime(formatter.parse(addtime).getTime());
	 	    }
	 	  payorderService.addInsuranceupdate(insuranceupdateModel);
	 	   
	 		out.print("success");
		
	}
	@RequestMapping(value = "/deleteInsuranceupdateByid", method = RequestMethod.POST)
	public void deleteInsuranceupdateByid(Model model, HttpSession session, HttpServletRequest request,String iuid,PrintWriter out) throws ParseException {
	      if(iuid !=null && !"".equals(iuid)){
	    	  payorderService.deleteInsuranceupdateByid(iuid);
	    	  out.print("success");
	 	    }
	 	   	   
	}
	
	@RequestMapping(value = "/updateCarCheckCode", method = RequestMethod.POST)
	public void updateCarCheckCode(Model model, HttpSession session, HttpServletRequest request,String carcheckcode,PrintWriter out) throws ParseException {
	    String configkey="INSURANCE_CARCHECKCODE";
		if(carcheckcode !=null && !"".equals(carcheckcode)){
	    	  configdataService.updateConfigValueByKey(carcheckcode,configkey);
	    	  dataversionService.updateConfigversion();
	    	  out.print("success");
	 	    }
	 	   	   
	}
	@RequestMapping(value = "/changePolicyStatus", method = RequestMethod.POST)
	public void changePolicyStatus(Model model, HttpSession session, HttpServletRequest request,String channelOrderNo,String policyStatus,PrintWriter out) throws ParseException {
	    if(policyStatus!=null && !"".equals(policyStatus) && channelOrderNo!=null && !"".equals(channelOrderNo)){
	    	 payorderService.changePolicyStatus(channelOrderNo,policyStatus);
	    	 out.print("success"); 
	    }else{
	    	out.print("fail");
	    }
		
	}
	@RequestMapping(value = "/refreshpolicystatus", method = RequestMethod.POST)
	public void refreshpolicystatus(Model model, HttpSession session, HttpServletRequest request,String channelOrderNo,String userid,PrintWriter out) throws ParseException, Exception {
		if(userid!=null && !"".equals(userid) && channelOrderNo!=null && !"".equals(channelOrderNo)){
			JSONObject obj = new JSONObject();
			obj.put("userid",userid);
			obj.put("channelOrderNo", channelOrderNo);
			String data = JSON.toJSONString(obj);
			String urlString = Consts.ServiceUrl + "insurance/refreshpolicystatus";
			
			logger.info("refreshpolicystatus request data:[url:"+urlString+"  param:"+data+"]");
			RequestBody body = RequestBody.create(MediaType_JSON, data);
			Request req = new Request.Builder().url(urlString).post(body).build();
			Response resp=httpClient.newCall(req).execute();
			String result =new String(resp.body().bytes(), "utf-8");
			logger.info("refreshpolicystatus response data:[result:"+result+"]");
			JSONObject json = JSON.parseObject(result);
			String  policystatusStr= json.getString("data");
			if(policystatusStr!=null){
				out.write(policystatusStr);
			}else{
				out.write("fail");
			}
		}else{
			out.print("fail");
		}
		
	}
}
