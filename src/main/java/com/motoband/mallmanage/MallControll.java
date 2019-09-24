package com.motoband.mallmanage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.businessmanage.BusinessService;
import com.motoband.businessmanage.UsecarmainModel;
import com.motoband.businessmanage.UsecarmaingroupModel;
import com.motoband.carmanage.carModel;
import com.motoband.carmanage.carService;
import com.motoband.carmanage.motobrand;
import com.motoband.util.BeanUtils;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;
import com.motoband.util.RedisManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
@RequestMapping(value = "/mallmanage")
public class MallControll {
	@Autowired
	private MallService mallService;
	@Autowired
	private boxService boxService;
	@Autowired
	private carService carService;
	@Autowired
	private BusinessService businessService;
	
	public	static Logger logger = LoggerFactory.getLogger(MallControll.class);
	
	private static final String MALLKEY_NOTIFY = "mall_notify";
	private static final String MALLKEY_NOTIFYINFO = "mallnotify_";
	private static final String RUNKEY_MALLPRODUCTMAP = "_mallproductmap";
	
	public static final MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");

	private static OkHttpClient httpClient = new OkHttpClient();
	
	@RequestMapping(value = "/malllist", method = RequestMethod.GET)
	public void malllist(Model model, HttpSession session, HttpServletRequest request, String userGuid) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		ArrayList<MallParentTypeModel> mallParentTypeList = mallService.getMallParentTypeList();
		ArrayList<MallStyleModel> mallStyleList = mallService.getMallStyleList();
		ArrayList<MallBrandModel> mallBrandList = mallService.getMallBrandList("");
		
	
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
		
		
		model.addAttribute("mallParentTypeList", mallParentTypeList);
		model.addAttribute("mallStyleList", mallStyleList);
		model.addAttribute("mallBrandList", mallBrandList);

	}
	@RequestMapping(value = "/getTypeListByParenttypeid", method = RequestMethod.POST)
	public void getTypeListByParenttypeid(Model model, HttpSession session, HttpServletRequest request,String parentid,PrintWriter out) {

		String jsonStr="";
		if(parentid!=null&& !"".equals(parentid)){
			ArrayList<MallTypeModel> mallTypeList = mallService.getTypeListByParenttypeid(parentid);
			if(mallTypeList!=null && mallTypeList.size()>0){
				jsonStr=JSONObject.toJSONString(mallTypeList);
			}
		}
		
		out.write(jsonStr);
	}
	@RequestMapping(value = "/mallproductlist", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView mallproductlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int order,
			String search_parenttype,String search_type,
			@RequestParam(value = "search_probrand_arr[]",required = false)Integer[] search_probrand_arr,
			@RequestParam(value = "search_style_arr[]",required = false)Integer[] search_style_arr,
			String search_level,String search_price_start,String search_price_end,String search_input,
			@RequestParam(value = "search_state_arr[]",required = false)Integer[] search_state_arr) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		
		PageBean<MallProductModel> pageBean=new PageBean<MallProductModel>();
		if(page==0){
			page=1;
		}
      if(limit==0){
     	limit=20;
       }
   
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		
		String [] search_style_arr_str =null;
		 if(search_style_arr!=null && search_style_arr.length>0){
			 search_style_arr_str = new String [search_style_arr.length];
			 MallStyleModel mallStyleModel = new MallStyleModel();
			  for(int i=0;i<search_style_arr.length;i++){
	       	     mallStyleModel= mallService.getMallStyleModelByid(String.valueOf(search_style_arr[i]));
	         	 search_style_arr_str[i]=mallStyleModel.getName();
	          } 
		 }
		 
		int totalCount=mallService.getMallProductListCount(search_parenttype,search_type,search_probrand_arr,search_style_arr_str,search_level,search_price_start,search_price_end,search_input,search_state_arr);

		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;
		
		 
		  
		ArrayList<MallProductModel> mallproductlist =mallService.getMallProductList(userGuid,start,limit,order,
				search_parenttype,search_type,search_probrand_arr,search_style_arr_str,search_level,search_price_start,search_price_end,search_input,search_state_arr);
		
		ArrayList<MallParentTypeModel> mallParentTypeList = mallService.getMallParentTypeList();
		ArrayList<MallTypeModel> mallTypeList = mallService.getMallTypeList();
		ArrayList<MallBrandModel> mallBrandList = mallService.getMallBrandList("");
		Map<String,String> ptmap =new HashMap<String, String>();
		Map<String,String> tmap =new HashMap<String, String>();
		Map<String,String> bmap =new HashMap<String, String>();
		for(MallParentTypeModel mallParentTypeModel:mallParentTypeList){
			ptmap.put(mallParentTypeModel.getParentid().toString(), mallParentTypeModel.getName());
		}
		for(MallTypeModel mallTypeModel:mallTypeList){
			tmap.put(mallTypeModel.getTypeid().toString(), mallTypeModel.getName());
		}
		for(MallBrandModel mallBrandModel:mallBrandList){
			bmap.put(mallBrandModel.getBrandid().toString(),mallBrandModel.getName());
		}
       if(mallproductlist !=null && mallproductlist.size()>0){
    	   for(MallProductModel mallproduct : mallproductlist){
//    		   MallParentTypeModel  mallParentTypeModel =mallService.getMallParentTypeModelByPid(mallproduct.getParenttype()); 
//    		   MallTypeModel mallTypeModel=mallService.getMallTypeModelByid(mallproduct.getType());
//    		   MallBrandModel mallBrandModel = mallService.getMallBrandModelBybid(mallproduct.getProbrand());
//    		   mallproduct.setParenttypeName(mallParentTypeModel.getName());
//    		   mallproduct.setTypeName(mallTypeModel.getName());
//    		   mallproduct.setProbrandName(mallBrandModel.getName());
 //   		   System.out.println(mallproduct.getParenttype());
 //   		   System.out.println(ptmap.get(mallproduct.getParenttype().toString()));
    		   mallproduct.setParenttypeName(ptmap.get(mallproduct.getParenttype().toString()));
    		   mallproduct.setTypeName(tmap.get(mallproduct.getType().toString()));
    		   mallproduct.setProbrandName(bmap.get(mallproduct.getProbrand()==null?"":mallproduct.getProbrand().toString()));
    		   String oldprice_show = String.valueOf(mallproduct.getOldprice().doubleValue()/100);
    		   String newprice_show = String.valueOf(mallproduct.getNewprice().doubleValue()/100);
    		   mallproduct.setOldprice_show(oldprice_show);
    		   mallproduct.setNewprice_show(newprice_show);
    	   }
       }
		model.addAttribute("mallproductlist", mallproductlist);
		model.addAttribute("pageBean", pageBean);

		List<UsecarmaingroupModel> usecarmaingroupModels=businessService.getUsecarmaingroupList(4);
		model.addAttribute("usecarmaingroupModels", usecarmaingroupModels);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/mallmanage/mallproductlist");
		return modelAndView;
	}
	@RequestMapping(value = "/updateProductState", method = RequestMethod.POST)
	public void updateProductState(Model model, HttpSession session, HttpServletRequest request,String id,String state,PrintWriter out) throws IOException {

		 MallProductModel mallProductModel  = mallService.getMallProductById(id);
		     if("0".equals(state)){
				state="1";
			}else if("1".equals(state)){
				state="0";
			}
		    mallProductModel.setState(Integer.parseInt(state));
		   
		    //同步redis
		    sysnMallProductToRedis(mallProductModel);
		    
		    //为了搜索，将title拼接在keyword尾部
			 MallProductModel mallProductModelforsearch =mallProductModel;
			 mallProductModelforsearch.setKeyword(mallProductModel.getKeyword()+","+mallProductModel.getTitle());
			    
		    
			String data = JSONObject.toJSONString(mallProductModelforsearch);
	   		String urlString =Consts.MOTOBAND_SEARCH+"/search/mallproduct/add";
	   		
	   		logger.info(" updateProductState search request data:[url:"+urlString+"  param mallProductModel:"+data+"]");
	   		RequestBody body = RequestBody.create(MediaType_JSON, data);
	   		Request req = new Request.Builder().url(urlString).post(body).build();
	   		Response resp=httpClient.newCall(req).execute();
	   		String result =new String(resp.body().bytes(), "utf-8");
	   		logger.info("updateProductState search response data:[result:"+result+"]");
	   		JSONObject json = JSON.parseObject(result);
	   		String str =(String) json.get("code");
	   		if(Integer.parseInt(str)==0){
	   			try {
					
	   				mallService.updateProductState(id,state);	
				} catch (Exception e) {
					out.write("fail1");
				}
	   		    out.write("success");
	   		}else{
	   			out.write("fail2");
	   		}
	   		
	}
	@RequestMapping(value = "/deleteProductByid", method = RequestMethod.POST)
	public void deleteProductByid(Model model, HttpSession session, HttpServletRequest request,String id,PrintWriter out) {

		String jsonStr="";
		if(id!=null&& !"".equals(id)){
			mallService.deleteProductByid(id);
			jsonStr="success";
		}
		
		out.write(jsonStr);
	}
	
	@RequestMapping(value = "/getMallStyleList", method = RequestMethod.POST)
	public void getMallStyleList(Model model, HttpSession session, HttpServletRequest request,PrintWriter out) {

		String jsonStr="";
		ArrayList<MallStyleModel> mallStyleList = mallService.getMallStyleList();
			if(mallStyleList!=null && mallStyleList.size()>0){
				jsonStr=JSONObject.toJSONString(mallStyleList);
			}
		out.write(jsonStr);
	}
	@RequestMapping(value = "/getMallBrandList", method = RequestMethod.POST)
	public void getMallBrandList(Model model, HttpSession session,String name, HttpServletRequest request,PrintWriter out) {

		String jsonStr="";
		if(name==null){
			name="";
		}
		ArrayList<MallBrandModel> mallBrandList = mallService.getMallBrandList(name);
			if(mallBrandList!=null && mallBrandList.size()>0){
				jsonStr=JSONObject.toJSONString(mallBrandList);
			}
		out.write(jsonStr);
	}
	@RequestMapping(value = "/getMallLabelList", method = RequestMethod.POST)
	public void getMallLabelList(Model model, HttpSession session, HttpServletRequest request,PrintWriter out) {

		String jsonStr="";
		ArrayList<MallLabelModel> mallLabelList = mallService.getMallLabelList();
			if(mallLabelList!=null && mallLabelList.size()>0){
				jsonStr=JSONObject.toJSONString(mallLabelList);
			}
		out.write(jsonStr);
	}
	@RequestMapping(value = "/getMotoBrandList", method = RequestMethod.POST)
	public void getMotoBrandList(Model model, HttpSession session, HttpServletRequest request,PrintWriter out) {

		String jsonStr="";
		ArrayList<motobrand> motobrandlist = carService.getMotoBrandList();
			if(motobrandlist!=null && motobrandlist.size()>0){
				jsonStr=JSONObject.toJSONString(motobrandlist);
			}
			
		out.write(jsonStr);
	}
	@RequestMapping(value = "/getMotoModelList", method = RequestMethod.POST)
	@ResponseBody
	public void getMotoModelList(Model model, HttpSession session, HttpServletRequest request,@RequestParam(value = "brandids[]",required = false) Integer[] brandids,PrintWriter out) {
   // System.out.println(brandids);
		String jsonStr="";
		List<carModel> motoModellist = carService.getCarModelList(brandids);
			if(motoModellist!=null && motoModellist.size()>0){
				jsonStr=JSONObject.toJSONString(motoModellist);
			}
			
		out.write(jsonStr);
	}
	@RequestMapping(value = "/insertMallProduct", method = RequestMethod.POST)
	@ResponseBody
	public void insertMallProduct(Model model, HttpSession session, HttpServletRequest request,
			String title ,String des,String parenttype,String type,String probrand,String keyword,
//			@RequestParam(value = "brand_arr[]",required = false)Integer[] brand_arr ,
//			@RequestParam(value = "model_arr[]",required = false)Integer[] model_arr ,
//			@RequestParam(value = "style_arr[]",required = false)Integer[] style_arr ,
//			@RequestParam(value = "label_arr[]",required = false)Integer[] label_arr ,
			String brandids,String modelids,
			String brandtext,String modeltext,String styletext,String labeltext,
			String mallurl,String level,String source,String ispackage,String isrecommend ,String picurl,
			String oldprice,String newprice,String state ,String id,PrintWriter out) throws IOException {

           MallProductModel mallProductModel = new MallProductModel();
        //   mallProductModel.setId(MbUtil.getUUID());
           mallProductModel.setId(id);
           mallProductModel.setTitle(title);
           mallProductModel.setDes(des);
           mallProductModel.setParenttype(Integer.parseInt(parenttype));
           mallProductModel.setType(Integer.parseInt(type));
           mallProductModel.setProbrand(Integer.parseInt(probrand));
           mallProductModel.setKeyword(keyword);
//           StringBuffer brandsb = new StringBuffer();
//           StringBuffer modelsb = new StringBuffer();
//           StringBuffer stylesb = new StringBuffer();
//           StringBuffer labelsb = new StringBuffer();
//           motobrand brandmodel=new motobrand();
//           carModel  carmodel =new carModel();
//           MallStyleModel mallStyleModel = new MallStyleModel();
//           MallLabelModel mallLabelModel =new MallLabelModel();
//           for(int i=0;i<brand_arr.length;i++){
//        	   brandmodel= carService.getMotobrandByid(String.valueOf(brand_arr[i]));
//        	    if(i==brand_arr.length-1){
//        	    	brandsb.append(brandmodel.getName());
//        	    }else {
//        	    	brandsb.append(brandmodel.getName());
//        	    	brandsb.append(",");
//				}
//           }
//           for(int i=0;i<model_arr.length;i++){
//        	   carmodel= carService.getCarModelById(String.valueOf(model_arr[i]));
//        	    if(i==model_arr.length-1){
//        	    	modelsb.append(carmodel.getName());
//        	    }else {
//        	    	modelsb.append(carmodel.getName());
//        	    	modelsb.append(",");
//				}
//           }
//           for(int i=0;i<style_arr.length;i++){
//        	   mallStyleModel= mallService.getMallStyleModelByid(String.valueOf(style_arr[i]));
//        	    if(i==style_arr.length-1){
//        	    	stylesb.append(mallStyleModel.getName());
//        	    }else {
//        	    	stylesb.append(mallStyleModel.getName());
//        	    	stylesb.append(",");
//				}
//           }
//           for(int i=0;i<label_arr.length;i++){
//        	   mallLabelModel= mallService.getMallLabelModelByid(String.valueOf(label_arr[i]));
//        	    if(i==label_arr.length-1){
//        	    	labelsb.append(mallLabelModel.getName());
//        	    }else {
//        	    	labelsb.append(mallLabelModel.getName());
//        	    	labelsb.append(",");
//				}
//           }
     //      String brand=brandsb.toString();
      //     mallProductModel.setBrand(brand);
    //       String modelStr= modelsb.toString();
    //       mallProductModel.setModel(modelStr);
  //         String style=stylesb.toString();
   //        mallProductModel.setStyle(style);
//           String label=labelsb.toString();
 //          mallProductModel.setLabel(label);
           if(brandtext!=null && brandtext!=""){
        	   brandtext=brandtext.replace("×", ",");
        	   mallProductModel.setBrand(brandtext.substring(0,brandtext.length()-1));
           }
           if( modeltext!=null &&  modeltext!=""){
        	   modeltext=modeltext.replace("×", ",");
        	   mallProductModel.setModel(modeltext.substring(0,modeltext.length()-1));
           }
           if(styletext!=null && styletext!=""){
        	   styletext=styletext.replace("×", ","); 
        	   mallProductModel.setStyle(styletext.substring(0,styletext.length()-1));
           }
           if(labeltext!=null && labeltext!=""){
        	   labeltext=labeltext.replace("×", ",");
        	   mallProductModel.setLabel(labeltext.substring(0,labeltext.length()-1));
           }
//           brandtext=brandtext.replace("×", ",");
//           modeltext=modeltext.replace("×", ",");
//           styletext=styletext.replace("×", ",");
//           labeltext=labeltext.replace("×", ",");
//           mallProductModel.setBrand(brandtext.substring(0,brandtext.length()-1));
//           mallProductModel.setModel(modeltext.substring(0,modeltext.length()-1));
//           mallProductModel.setStyle(styletext.substring(0,styletext.length()-1));
//           mallProductModel.setLabel(labeltext.substring(0,labeltext.length()-1));
           if(brandids!=null && brandids!=""){
        	   mallProductModel.setBrandids(brandids.substring(0,brandids.length()-1));
           }
           if(modelids!=null && modelids!=""){
        	   mallProductModel.setModelids(modelids.substring(0,modelids.length()-1));
           }
//           mallProductModel.setBrandids(brandids.substring(0,brandids.length()-1));
//           mallProductModel.setModelids(modelids.substring(0,modelids.length()-1));
           mallProductModel.setMallurl(mallurl);
           mallProductModel.setLevel(level);
           mallProductModel.setSource(Integer.parseInt(source));
           mallProductModel.setIspackage(Integer.parseInt(ispackage));
           mallProductModel.setIsrecommend(Integer.parseInt(isrecommend));
           mallProductModel.setPicurl(picurl);
           
           Double oldprice_double= Double.valueOf(oldprice);
           double oldp = oldprice_double * 100;
           mallProductModel.setOldprice(new Double(oldp).intValue());
           
           Double newprice_double= Double.valueOf(newprice);
           double newp = newprice_double * 100;
           mallProductModel.setNewprice(new Double(newp).intValue());
           
           mallProductModel.setState(Integer.parseInt(state));
           mallProductModel.setPtime(System.currentTimeMillis());
           mallProductModel.setCount(0);
           
         //同步redis
		 sysnMallProductToRedis(mallProductModel);
		 
		 //为了搜索，将title拼接在keyword尾部
		 MallProductModel mallProductModelforsearch =mallProductModel;
		 mallProductModelforsearch.setKeyword(mallProductModel.getKeyword()+","+mallProductModel.getTitle());
		    
   		String data = JSONObject.toJSONString(mallProductModelforsearch);
   		String urlString = Consts.MOTOBAND_SEARCH+"/search/mallproduct/add";
   		
   		logger.info("insertMallProduct search request data:[url:"+urlString+" param mallProductModel:"+data+"]");
   		RequestBody body = RequestBody.create(MediaType_JSON, data);
   		Request req = new Request.Builder().url(urlString).post(body).build();
   		Response resp=httpClient.newCall(req).execute();
   		String result =new String(resp.body().bytes(), "utf-8");
   		logger.info("insertMallProduct search response data:[result:"+result+"]");
   		JSONObject json = JSON.parseObject(result);
   		String str =(String) json.get("code");
   		if(Integer.parseInt(str)==0){
   			try {
				
   				mallService.insertMallProduct(mallProductModel);	
			} catch (Exception e) {
				out.write("fail1");
			}
   		 out.write("success");
   		}else{
   			out.write("fail2");
   		}
   		
            
	}
	
	@RequestMapping(value = "/mallparenttypelist", method = RequestMethod.GET)
	public void mallparenttypelist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid, 
			int page,int limit,int order,String orderConditions,String name) {
		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<MallParentTypeModel> pageBean=new PageBean<MallParentTypeModel>();
		if(page==0){
			page=1;
		}
	    if(limit==0){
	    	limit=20;
	    }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		
		 if(name!=null && !"".equals(name)){
			  try {
				  name = MbUtil.sChangeBytes(name);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
       }
		int totalCount= mallService.getMallParenttypeListCount(name);
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
		ArrayList<MallParentTypeModel> mallParentTypeList = mallService.getMallParentTypeListPage(
				start,limit,order,orderConditions,name);
		ArrayList<MallBrandModel> mallBrandList = mallService.getMallBrandList("");
		Map<String,String> temp=new HashMap<String, String>();
		for(MallBrandModel mallBrandModel :mallBrandList){
			temp.put(mallBrandModel.getBrandid().toString(),mallBrandModel.getName());
		}
		for(MallParentTypeModel mallParentTypeModel:mallParentTypeList){
			String brandids = mallParentTypeModel.getBrandids();
			StringBuffer brandNamesBuffer = new StringBuffer();
			String brandNames="";
			if(brandids!=null && !"".equals(brandids)){
				String[] brandidArr= brandids.split(",");
				
				for(int i=0;i<brandidArr.length;i++){
					if(i==brandidArr.length-1){
						brandNamesBuffer.append(temp.get(brandidArr[i]));
					}else{
						brandNamesBuffer.append(temp.get(brandidArr[i]));
						brandNamesBuffer.append(",");
					}
				}
				brandNames=brandNamesBuffer.toString();
			}
			mallParentTypeModel.setBrandNames(brandNames);
		}
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("mallParentTypeList", mallParentTypeList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
		model.addAttribute("mallBrandList", mallBrandList);
		if(name==null|| "".equals(name)){
			model.addAttribute("name", "");	
		}else{
			model.addAttribute("name", name);	
		}
	}

	@RequestMapping(value = "/insMallParentType", method = RequestMethod.POST)
	@ResponseBody
	public void insMallParentType(Model model, HttpSession session, HttpServletRequest request,String name,
			@RequestParam(value = "brand_arr[]",required = false)Integer[] brand_arr,
			PrintWriter out) {

		String jsonStr="";
		StringBuffer brandidsBuffe=new StringBuffer();
		if(brand_arr==null ||brand_arr.length==0){
			brandidsBuffe.append("");
		}else{
			for(int i=0;i<brand_arr.length;i++){
				if(i==brand_arr.length-1){
					brandidsBuffe.append(brand_arr[i]);
				}else{
					brandidsBuffe.append(brand_arr[i]);
					brandidsBuffe.append(",");
				}
			}
		}
		String brandids=brandidsBuffe.toString();
		if(name!=null&& !"".equals(name)){
			mallService.insMallParentType(name,brandids);
			jsonStr="success";
		}
		
		out.write(jsonStr);
	}
	@RequestMapping(value = "/malltypelist", method = RequestMethod.GET)
	public void malltypelist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid, 
			int page,int limit,int order,String orderConditions,String name) {
		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<MallTypeModel> pageBean=new PageBean<MallTypeModel>();
		if(page==0){
			page=1;
		}
	    if(limit==0){
	    	limit=20;
	    }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		
		 if(name!=null && !"".equals(name)){
			  try {
				  name = MbUtil.sChangeBytes(name);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
       }
		int totalCount= mallService.getMalltypeListCount(name);
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
		ArrayList<MallTypeModel> mallTypeList = mallService.getMallTypeListPage(
				start,limit,order,orderConditions,name);

		if(mallTypeList!=null && mallTypeList.size()>0){
			for(MallTypeModel mallTypeModel : mallTypeList){
				 MallParentTypeModel  mallParentTypeModel =mallService.getMallParentTypeModelByPid(mallTypeModel.getParentid()); 
				 mallTypeModel.setParentName(mallParentTypeModel.getName());
			}
		}
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);ArrayList<MallParentTypeModel> mallParentTypeList = mallService.getMallParentTypeList();
		model.addAttribute("limitList", limitList);
		
		model.addAttribute("mallTypeList", mallTypeList);
		model.addAttribute("mallParentTypeList", mallParentTypeList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
		if(name==null|| "".equals(name)){
			model.addAttribute("name", "");	
		}else{
			model.addAttribute("name", name);	
		}
	}

	@RequestMapping(value = "/insMallType", method = RequestMethod.POST)
	public void insMallType(Model model, HttpSession session, HttpServletRequest request,String name,String parentid,PrintWriter out) {

		String jsonStr="";
		if(parentid!=null&& !"".equals(parentid)){
			mallService.insMallType(name,parentid);
			jsonStr="success";
		}
		
		out.write(jsonStr);
	}
	@RequestMapping(value = "/mallbrandlist", method = RequestMethod.GET)
	public void mallbrandlist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid, 
			int page,int limit,int order,String orderConditions,String name) {
		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<MallBrandModel> pageBean=new PageBean<MallBrandModel>();
		if(page==0){
			page=1;
		}
	    if(limit==0){
	    	limit=20;
	    }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		
		 if(name!=null && !"".equals(name)){
			  try {
				  name = MbUtil.sChangeBytes(name);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
       }
		int totalCount= mallService.getMallBrandListCount(name);
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
		ArrayList<MallBrandModel> mallBrandList = mallService.getMallBrandListPage(
				start,limit,order,orderConditions,name);
        for(MallBrandModel m :mallBrandList){
        	int productCount = mallService.getProductCountByBrandId(m.getBrandid());
        	m.setProductCount(productCount);
        }
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("mallBrandList", mallBrandList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
		if(name==null|| "".equals(name)){
			model.addAttribute("name", "");	
		}else{
			model.addAttribute("name", name);	
		}
	}

	@RequestMapping(value = "/insMallBrand", method = RequestMethod.POST)
	public void insMallBrand(Model model, HttpSession session, HttpServletRequest request,String name,PrintWriter out) {

		String jsonStr="";
		if(name!=null&& !"".equals(name)){
			mallService.insMallBrand(name);
			jsonStr="success";
		}
		
		out.write(jsonStr);
	}
	@RequestMapping(value = "/malllabellist", method = RequestMethod.GET)
	public void malllabellist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid, 
			int page,int limit,int order,String orderConditions,String name) {
		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<MallLabelModel> pageBean=new PageBean<MallLabelModel>();
		if(page==0){
			page=1;
		}
	    if(limit==0){
	    	limit=20;
	    }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		
		 if(name!=null && !"".equals(name)){
			  try {
				  name = MbUtil.sChangeBytes(name);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
       }
		int totalCount= mallService.getMallLabelListCount(name);
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
		ArrayList<MallLabelModel> mallLabelList = mallService.getMallLabelListPage(
				start,limit,order,orderConditions,name);

		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("mallLabelList", mallLabelList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
		if(name==null|| "".equals(name)){
			model.addAttribute("name", "");	
		}else{
			model.addAttribute("name", name);	
		}
	}
	@RequestMapping(value = "/mallproduct_ins", method = RequestMethod.GET)
	public void malllistins(Model model, HttpSession session, HttpServletRequest request, String userGuid) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		ArrayList<MallParentTypeModel> mallParentTypeList = mallService.getMallParentTypeList();
		ArrayList<MallStyleModel> mallStyleList = mallService.getMallStyleList();
		ArrayList<MallBrandModel> mallBrandList = mallService.getMallBrandList("");
		
	
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
		
		String uuid=MbUtil.getUUID();
		model.addAttribute("productuuid",uuid);
		model.addAttribute("mallParentTypeList", mallParentTypeList);
		model.addAttribute("mallStyleList", mallStyleList);
		model.addAttribute("mallBrandList", mallBrandList);

	}
	@RequestMapping(value = "/mallproduct_update", method = RequestMethod.GET)
	public void malllistupdate(Model model, HttpSession session, HttpServletRequest request, String userGuid,String id) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		ArrayList<MallParentTypeModel> mallParentTypeList = mallService.getMallParentTypeList();
		ArrayList<MallStyleModel> mallStyleList = mallService.getMallStyleList();
		ArrayList<MallBrandModel> mallBrandList = mallService.getMallBrandList("");
		
	
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
		
		
		model.addAttribute("mallParentTypeList", mallParentTypeList);
		model.addAttribute("mallStyleList", mallStyleList);
		model.addAttribute("mallBrandList", mallBrandList);
		model.addAttribute("mallproductid", id);

	}
	@RequestMapping(value = "/insMallLabel", method = RequestMethod.POST)
	public void insMallLabel(Model model, HttpSession session, HttpServletRequest request,String name,String color,PrintWriter out) {

		String jsonStr="";
		if(name!=null&& !"".equals(name) && color!=null&& !"".equals(color)){
			mallService.insMallLabel(name,color);
			jsonStr="success";
		}
		
		out.write(jsonStr);
	}
	@RequestMapping(value = "/mallstylelist", method = RequestMethod.GET)
	public void mallstylelist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid, 
			int page,int limit,int order,String orderConditions,String name) {
		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<MallStyleModel> pageBean=new PageBean<MallStyleModel>();
		if(page==0){
			page=1;
		}
	    if(limit==0){
	    	limit=20;
	    }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		
		 if(name!=null && !"".equals(name)){
			  try {
				  name = MbUtil.sChangeBytes(name);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
       }
		int totalCount= mallService.getMallStyleListCount(name);
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
		ArrayList<MallStyleModel> mallStyleList = mallService.getMallStyleListPage(
				start,limit,order,orderConditions,name);

		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("mallStyleList", mallStyleList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
		if(name==null|| "".equals(name)){
			model.addAttribute("name", "");	
		}else{
			model.addAttribute("name", name);	
		}
	}

	@RequestMapping(value = "/insMallStyle", method = RequestMethod.POST)
	public void insMallStyle(Model model, HttpSession session, HttpServletRequest request,String name,PrintWriter out) {

		String jsonStr="";
		if(name!=null&& !"".equals(name)){
			mallService.insMallStyle(name);
			jsonStr="success";
		}
		
		out.write(jsonStr);
	}
	@RequestMapping(value = "/mallnotifylist", method = RequestMethod.GET)
	public void mallnotifylist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid, 
			int page,int limit,int order,String orderConditions,String name) {
		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<MallNotifyModel> pageBean=new PageBean<MallNotifyModel>();
		if(page==0){
			page=1;
		}
	    if(limit==0){
	    	limit=20;
	    }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		
		 if(name!=null && !"".equals(name)){
			  try {
				  name = MbUtil.sChangeBytes(name);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
       }
		int totalCount= mallService.getMallNotifyListCount(name);
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<MallNotifyModel> mallNotifyList = mallService.getMallNotifyListPage(
				start,limit,order,orderConditions,name);
        if(mallNotifyList !=null && mallNotifyList.size()>0){
        	for(MallNotifyModel mallNotifyModel :mallNotifyList){
        		mallNotifyModel.setPtimeString(simpleDateFormat.format(new Date(mallNotifyModel.getPtime())));
        	}
        }
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
		
		model.addAttribute("mallNotifyList", mallNotifyList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
		if(name==null|| "".equals(name)){
			model.addAttribute("name", "");	
		}else{
			model.addAttribute("name", name);	
		}
	}

	@RequestMapping(value = "/insMallNotify", method = RequestMethod.POST)
	public void insMallNotify(Model model, HttpSession session, HttpServletRequest request,String name,String picurl,String mallurl,String source,PrintWriter out) {

		String jsonStr="";
		MallNotifyModel mallNotifyModel = new MallNotifyModel();
		mallNotifyModel.setName(name);
		mallNotifyModel.setPicurl(picurl);
		mallNotifyModel.setMallurl(mallurl);
		mallNotifyModel.setSource(Integer.parseInt(source));
		long time =System.currentTimeMillis();
		mallNotifyModel.setPtime(time);
		try {
			mallService.insMallNotify(mallNotifyModel);
		} catch (Exception e) {
			jsonStr="fail";
		}
		int id=mallNotifyModel.getNotifyid();
		RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, MALLKEY_NOTIFY, time, String.valueOf(id));
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("mallurl", mallurl);
		map.put("notifyid", String.valueOf(id));
		map.put("picurl",picurl);
		map.put("ptime", String.valueOf(time));
		map.put("source", source);
		RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, MALLKEY_NOTIFYINFO+String.valueOf(id), map);
		jsonStr="success";
	
		out.write(jsonStr);
	}
	@RequestMapping(value = "/getCarModelById", method = RequestMethod.POST)
	public void getCarModelById(Model model, HttpSession session, HttpServletRequest request,String motoid,PrintWriter out) {

		String jsonStr="";
		carModel carModel = carService.getCarModelById(motoid);
		if(carModel!=null){
			jsonStr=JSONObject.toJSONString(carModel);
		}

		out.write(jsonStr);
	}
	
	@RequestMapping(value = "/getMallProductById", method = RequestMethod.POST)
	public void getMallProductById(Model model, HttpSession session, HttpServletRequest request,String id,PrintWriter out) {

		String jsonStr="";
		StringBuffer labelids=new StringBuffer();
		StringBuffer styleids=new StringBuffer();
		MallProductModel mallProductModel  = mallService.getMallProductById(id);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		ArrayList<MallLabelModel> mallLabeList = mallService.getMallLabelList();
		ArrayList<MallStyleModel> mallStyleList = mallService.getMallStyleList();
		Map<String,String> lmap =new HashMap<String, String>();
		Map<String,String> smap =new HashMap<String, String>();
		for(MallLabelModel mallLabelModel:mallLabeList ){
			lmap.put(mallLabelModel.getName(),mallLabelModel.getLabelid().toString());
		}
		for(MallStyleModel mallStyleModel:mallStyleList){
			smap.put(mallStyleModel.getName(),mallStyleModel.getStyleid().toString());
		}
		
		if(mallProductModel!=null){
			String label = mallProductModel.getLabel();
			if(label!=null && label!=""){
				 String[] labelStrArray =label.split(",");
			        for (int i = 0; i < labelStrArray.length; i++) {
			        	labelids.append(lmap.get(labelStrArray[i].trim()));
						labelids.append(",");
//			        	ArrayList<MallLabelModel>  lists = mallService.getMallLabelByName(labelStrArray[i].trim());
//						if(lists!=null&& lists.size()>0){
//							labelids.append(String.valueOf(lists.get(0).getLabelid()));
//							labelids.append(",");
//						}
			        }
			        mallProductModel.setLabelids(labelids.substring(0, labelids.length()-1));
			}
			String style=mallProductModel.getStyle();
		     if(style!=null && style!=""){
		    	 String[] styleStrArray =style.split(",");
			        for (int i = 0; i < styleStrArray.length; i++) {
			        	styleids.append(smap.get(styleStrArray[i].trim()));
 					    styleids.append(",");
//			        	ArrayList<MallStyleModel>  lists1 = mallService.getMallStyleByName(styleStrArray[i].trim());
//						if(lists1!=null&& lists1.size()>0){
//							styleids.append(String.valueOf(lists1.get(0).getStyleid()));
//							styleids.append(",");
//						}
			        }
				
			        mallProductModel.setStyleids(styleids.substring(0,styleids.length()-1));
		     }
		     
		     String ptimeString = formatter.format(new Date(mallProductModel.getPtime()));
		     mallProductModel.setPtimeString(ptimeString);
			jsonStr=JSONObject.toJSONString(mallProductModel);
		}

		out.write(jsonStr);
	}
	@RequestMapping(value = "/updateMallProduct", method = RequestMethod.POST)
	@ResponseBody
	public void updateMallProduct(Model model, HttpSession session, HttpServletRequest request,
			String title ,String des,String parenttype,String type,String probrand,String keyword,
//			@RequestParam(value = "brand_arr[]",required = false)Integer[] brand_arr ,
//			@RequestParam(value = "model_arr[]",required = false)Integer[] model_arr ,
//			@RequestParam(value = "style_arr[]",required = false)Integer[] style_arr ,
//			@RequestParam(value = "label_arr[]",required = false)Integer[] label_arr ,
			String brandids,String modelids,
			String brandtext,String modeltext,String styletext,String labeltext,
			String mallurl,String level,String source,String ispackage,String isrecommend ,String picurl,
 String oldprice, String newprice, String state, String ptime, String id, PrintWriter out) throws IOException {

           MallProductModel mallProductModel = new MallProductModel();
           mallProductModel.setId(id);
           mallProductModel.setTitle(title);
           mallProductModel.setDes(des);
           mallProductModel.setParenttype(Integer.parseInt(parenttype));
           mallProductModel.setType(Integer.parseInt(type));
           mallProductModel.setProbrand(Integer.parseInt(probrand));
           mallProductModel.setKeyword(keyword);

//           brandtext=brandtext.replace("×", ",");
//           modeltext=modeltext.replace("×", ",");
//           styletext=styletext.replace("×", ",");
//           labeltext=labeltext.replace("×", ",");
//           mallProductModel.setBrand(brandtext.substring(0,brandtext.length()-1));
//           mallProductModel.setModel(modeltext.substring(0,modeltext.length()-1));
//           mallProductModel.setStyle(styletext.substring(0,styletext.length()-1));
//           mallProductModel.setLabel(labeltext.substring(0,labeltext.length()-1));
//           mallProductModel.setBrandids(brandids.substring(0,brandids.length()-1));
//           mallProductModel.setModelids(modelids.substring(0,modelids.length()-1));
           if(brandtext!=null && brandtext!=""){
        	   brandtext=brandtext.replace("×", ",");
        	   mallProductModel.setBrand(brandtext.substring(0,brandtext.length()-1));
           }else{
        	   mallProductModel.setBrand(""); 
           }
           if( modeltext!=null &&  modeltext!=""){
        	   modeltext=modeltext.replace("×", ",");
        	   mallProductModel.setModel(modeltext.substring(0,modeltext.length()-1));
           }else{
        	   mallProductModel.setModel(""); 
           }
           if(styletext!=null && styletext!=""){
        	   styletext=styletext.replace("×", ","); 
        	   mallProductModel.setStyle(styletext.substring(0,styletext.length()-1));
           }else{
        	   mallProductModel.setStyle("");  
           }
           if(labeltext!=null && labeltext!=""){
        	   labeltext=labeltext.replace("×", ",");
        	   mallProductModel.setLabel(labeltext.substring(0,labeltext.length()-1));
           }else{
        	   mallProductModel.setLabel(""); 
           }

           if(brandids!=null && brandids!=""){
        	   mallProductModel.setBrandids(brandids.substring(0,brandids.length()-1));
           }else{
        	   mallProductModel.setBrandids(""); 
           }
           if(modelids!=null && modelids!=""){
        	   mallProductModel.setModelids(modelids.substring(0,modelids.length()-1));
           }else{
        	   mallProductModel.setModelids(""); 
           }
           mallProductModel.setMallurl(mallurl);
           mallProductModel.setLevel(level);
           mallProductModel.setSource(Integer.parseInt(source));
           mallProductModel.setIspackage(Integer.parseInt(ispackage));
           mallProductModel.setIsrecommend(Integer.parseInt(isrecommend));
           mallProductModel.setPicurl(picurl);
           
           if(oldprice==null ||"".equals(oldprice)){
        	   mallProductModel.setOldprice(0);
           }else{
        	   Double oldprice_double= Double.valueOf(oldprice);
               double oldp = oldprice_double * 100;
               mallProductModel.setOldprice(new Double(oldp).intValue()); 
           }
           
           
           Double newprice_double= Double.valueOf(newprice);
           double newp = newprice_double * 100;
           mallProductModel.setNewprice(new Double(newp).intValue());
           
           mallProductModel.setState(Integer.parseInt(state));
           MallProductModel mp  = mallService.getMallProductById(id);
          // mallProductModel.setPtime(mp.getPtime());
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
           try {
			mallProductModel.setPtime(formatter.parse(ptime).getTime());
		   } catch (ParseException e1) {
			
			e1.printStackTrace();
		   }
         //  mallProductModel.setPtime(System.currentTimeMillis());
           mallProductModel.setCount(mp.getCount());
           
         //同步redis
  		 sysnMallProductToRedis(mallProductModel);   
       
  		 //为了搜索，将title拼接在keyword尾部
		 MallProductModel mallProductModelforsearch =mallProductModel;
		 mallProductModelforsearch.setKeyword(mallProductModel.getKeyword()+","+mallProductModel.getTitle());
		    
   		String data = JSONObject.toJSONString(mallProductModelforsearch);
   		String urlString =Consts.MOTOBAND_SEARCH+"/search/mallproduct/add";
   		
   		logger.info("updateMallProduct search request data:[url:"+urlString+" param mallProductModel:"+data+"]");
   		RequestBody body = RequestBody.create(MediaType_JSON, data);
   		Request req = new Request.Builder().url(urlString).post(body).build();
   		Response resp=httpClient.newCall(req).execute();
   		String result =new String(resp.body().bytes(), "utf-8");
   		logger.info("updateMallProduct search response data:[result:"+result+"]");
   		JSONObject json = JSON.parseObject(result);
   		String str =(String) json.get("code");
   		if(Integer.parseInt(str)==0){
   			try {
				
   				mallService.updateMallProduct(mallProductModel);	
			} catch (Exception e) {
				out.write("fail1");
			}
   		 out.write("success");
   		}else{
   			out.write("fail2");
   		}
   		
            
	}
	@RequestMapping(value = "/getMallLabelByName", method = RequestMethod.POST)
	public void getMallLabelByName(Model model, HttpSession session, HttpServletRequest request,String name,PrintWriter out) {

		String jsonStr="";
		ArrayList<MallLabelModel>  lists = mallService.getMallLabelByName(name);
		if(lists!=null&& lists.size()>0){
			jsonStr=String.valueOf(lists.get(0).getLabelid());
		}

		out.write(jsonStr);
	}
	@RequestMapping(value = "/getMallStyleByName", method = RequestMethod.POST)
	public void getMallStyleByName(Model model, HttpSession session, HttpServletRequest request,String name,PrintWriter out) {

		String jsonStr="";
		ArrayList<MallStyleModel>  lists = mallService.getMallStyleByName(name);
		if(lists!=null&& lists.size()>0){
			jsonStr=String.valueOf(lists.get(0).getStyleid());
		}

		out.write(jsonStr);
	}
	@RequestMapping(value = "/getMallBrandByName", method = RequestMethod.POST)
	public void getMallBrandByName(Model model, HttpSession session, HttpServletRequest request,String name,PrintWriter out) {

		String jsonStr="";
		ArrayList<MallBrandModel>  lists = mallService.getMallBrandByName(name);
		if(lists!=null&& lists.size()>0){
			jsonStr=String.valueOf(lists.get(0).getBrandid());
		}

		out.write(jsonStr);
	}
	@RequestMapping(value = "/getMallNotifyByid", method = RequestMethod.POST)
	public void getMallNotifyByid(Model model, HttpSession session, HttpServletRequest request,String notifyid,PrintWriter out) {

		String jsonStr="";
		MallNotifyModel mallNotifyModel = mallService.getMallNotifyByid(notifyid);
		if(mallNotifyModel!=null){
			jsonStr=JSONObject.toJSONString(mallNotifyModel);
		}
		out.write(jsonStr);
	}
	@RequestMapping(value = "/delMallNotifyByid", method = RequestMethod.POST)
	public void delMallNotifyByid(Model model, HttpSession session, HttpServletRequest request,String notifyid,PrintWriter out) {

		 String jsonStr="";
		 if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, MALLKEY_NOTIFY)){
			 long count = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_RUN, MALLKEY_NOTIFY);
			 Set<String> stringSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_RUN, MALLKEY_NOTIFY, 0,count);
			 for(String s: stringSet){
				 if(s.equals(notifyid)){
					 RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_RUN, MALLKEY_NOTIFY, s);
				 }
			 }
		 }
		 if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, MALLKEY_NOTIFYINFO+notifyid)){
			 RedisManager.getInstance().hdel(Consts.REDIS_SCHEME_RUN, MALLKEY_NOTIFYINFO+notifyid);
		 }
		 mallService.delMallNotifyByid(notifyid);
		out.write("success");
	}
	@RequestMapping(value = "/updateMallNotify", method = RequestMethod.POST)
	public void updateMallNotify(Model model, HttpSession session, HttpServletRequest request,String notifyid,
			String name,String picurl,String mallurl, String source ,PrintWriter out) {
		    String jsonStr="";
		    String ptime=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_RUN, MALLKEY_NOTIFYINFO+notifyid, "ptime");
			Map<String,String> map = new HashMap<String, String>();
			map.put("name", name);
			map.put("mallurl", mallurl);
			map.put("notifyid", notifyid);
			map.put("picurl",picurl);
			map.put("ptime", ptime);
			map.put("source", source);
			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, MALLKEY_NOTIFYINFO+notifyid, map);
		
		   mallService.updateMallNotify(map);
		  out.write("success");
	}
	@RequestMapping(value = "/mallproductrecommendlist", method = RequestMethod.GET)
	public void mallproductrecommendlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
			int page,int limit,int order,String orderConditions) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		PageBean<MallProductModel> pageBean=new PageBean<MallProductModel>();
		if(page==0){
			page=1;
		}
        if(limit==0){
        	limit=20;
        }
      
        if(orderConditions==null || orderConditions==""){
    		orderConditions="";
    	}
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=mallService.getMallProductRecommendCount();
		
		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;
		ArrayList<MallProductModel> mallproductlist = mallService.getMallProductRecommendList(userGuid, start,limit,order,orderConditions);
		
		ArrayList<MallParentTypeModel> mallParentTypeList = mallService.getMallParentTypeList();
		ArrayList<MallTypeModel> mallTypeList = mallService.getMallTypeList();
		ArrayList<MallBrandModel> mallBrandList = mallService.getMallBrandList("");
		Map<String,String> ptmap =new HashMap<String, String>();
		Map<String,String> tmap =new HashMap<String, String>();
		Map<String,String> bmap =new HashMap<String, String>();
		for(MallParentTypeModel mallParentTypeModel:mallParentTypeList){
			ptmap.put(mallParentTypeModel.getParentid().toString(), mallParentTypeModel.getName());
		}
		for(MallTypeModel mallTypeModel:mallTypeList){
			tmap.put(mallTypeModel.getTypeid().toString(), mallTypeModel.getName());
		}
		for(MallBrandModel mallBrandModel:mallBrandList){
			bmap.put(mallBrandModel.getBrandid().toString(),mallBrandModel.getName());
		}
		 if(mallproductlist !=null && mallproductlist.size()>0){
	    	   for(MallProductModel mallproduct : mallproductlist){
//	    		   MallParentTypeModel  mallParentTypeModel =mallService.getMallParentTypeModelByPid(mallproduct.getParenttype()); 
//	    		   MallTypeModel mallTypeModel=mallService.getMallTypeModelByid(mallproduct.getType());
//	    		   MallBrandModel mallBrandModel = mallService.getMallBrandModelBybid(mallproduct.getProbrand());
//	    		   mallproduct.setParenttypeName(mallParentTypeModel.getName());
//	    		   mallproduct.setTypeName(mallTypeModel.getName());
//	    		   mallproduct.setProbrandName(mallBrandModel.getName());
	    		   mallproduct.setParenttypeName(ptmap.get(mallproduct.getParenttype().toString()));
	    		   mallproduct.setTypeName(tmap.get(mallproduct.getType().toString()));
	    		   mallproduct.setProbrandName(bmap.get(mallproduct.getProbrand().toString()));
	    		   String oldprice_show = String.valueOf(mallproduct.getOldprice().doubleValue()/100);
	    		   String newprice_show = String.valueOf(mallproduct.getNewprice().doubleValue()/100);
	    		   mallproduct.setOldprice_show(oldprice_show);
	    		   mallproduct.setNewprice_show(newprice_show);
	    	   }
	       }
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("mallproductlist",mallproductlist);
		model.addAttribute("pageBean", pageBean);
	
		
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
	}
	
	@RequestMapping(value = "/getMallProductByProbrand", method = RequestMethod.POST)
	public void getMallProductByProbrand(Model model, HttpSession session, HttpServletRequest request,String probrand,PrintWriter out) {

		String jsonStr="";
		if(probrand!=null&& !"".equals(probrand)){
			ArrayList<MallProductModel> mallProductlist =mallService.getMallProductByProbrand(probrand);
			if(mallProductlist!=null && mallProductlist.size()>0){
				jsonStr=JSONObject.toJSONString(mallProductlist);
			}
			
		}
		
		out.write(jsonStr);
	}
	@RequestMapping(value = "/getparenttypeByparentid", method = RequestMethod.POST)
	public void getparenttypeByparentid(Model model, HttpSession session, HttpServletRequest request,String parentid,PrintWriter out) {

		String jsonStr="";
		if(parentid!=null&& !"".equals(parentid)){
			MallParentTypeModel mallParentTypeModel = mallService.getMallParentTypeModelByPid(Integer.parseInt(parentid));
			if(mallParentTypeModel!=null ){
				jsonStr=JSONObject.toJSONString(mallParentTypeModel);
			}
		}
		
		out.write(jsonStr);
	}
	
	@RequestMapping(value = "/updateMallParentType", method = RequestMethod.POST)
	@ResponseBody
	public void updateMallParentType(Model model, HttpSession session, HttpServletRequest request,String name,String parentid,
			@RequestParam(value = "brand_arr[]",required = false)Integer[] brand_arr,
			PrintWriter out) {

		String jsonStr="";
		StringBuffer brandidsBuffe=new StringBuffer();
		if(brand_arr==null ||brand_arr.length==0){
			brandidsBuffe.append("");
		}else{
			for(int i=0;i<brand_arr.length;i++){
				if(i==brand_arr.length-1){
					brandidsBuffe.append(brand_arr[i]);
				}else{
					brandidsBuffe.append(brand_arr[i]);
					brandidsBuffe.append(",");
				}
			}
		}
		String brandids=brandidsBuffe.toString();
		if(name!=null&& !"".equals(name)){
			mallService.updateMallParentType(parentid,name,brandids);
			jsonStr="success";
		}
		
		out.write(jsonStr);
	}
	
	@RequestMapping(value = "/productToUsecarmain", method = RequestMethod.POST)
	public void productToUsecarmain(Model model, HttpSession session, HttpServletRequest request, String id,
			String groupid,String orderindex, PrintWriter out) throws Exception {
		MallProductModel mallProductModel = mallService.getMallProductById(id);
		if(mallProductModel!=null){
			String contentstr=JSON.toJSONString(mallProductModel);
			UsecarmainModel usecarmainModel = new UsecarmainModel();
			usecarmainModel.setGroupid(Integer.parseInt(groupid));
			usecarmainModel.setContentstr(contentstr);
			usecarmainModel.setOrderindex(Long.valueOf(orderindex));
			usecarmainModel.setType(0);
			businessService.addtoUsecarmain(usecarmainModel);
			out.print("success");
		}else{
			out.print("fail");
		}
	
		
	}
	
	
	public void sysnMallProductToRedis(MallProductModel mallProductModel){
		try {
			Map<String, String> map = BeanUtils.objectToMap(mallProductModel);
			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, mallProductModel.getId()+RUNKEY_MALLPRODUCTMAP, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("sysnMallProductToRedis  error: id "+mallProductModel.getId());
		}
	}
	
	
	
	@RequestMapping(value = "/mallbaselist", method = RequestMethod.GET)
	public void mallbaselist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
			int page,int limit,int order,String orderConditions,int groupid,int grouptype) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		PageBean<MallBaseModel> pageBean=new PageBean<MallBaseModel>();
		if(page==0){
			page=1;
		}
	    if(limit==0){
	    	limit=20;
	    }
	  
	    if(orderConditions==null || orderConditions==""){
			orderConditions="";
		}
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=mallService.getMallbaseCount(groupid,grouptype);
		
		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<MallBaseModel> mallbaseModels=mallService.getMallbaselist(start,limit,order,orderConditions,groupid,grouptype);
		
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
		
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		ArrayList<Integer> typeList =new ArrayList<Integer>();
		typeList.add(0);
		typeList.add(1);

		model.addAttribute("pageBean", pageBean);
		
		List<EquippingGroupModel> equippinggroupModels=mallService.getEquippingGroupList(1,grouptype);
		model.addAttribute("equippinggroupModels", equippinggroupModels);
		
		model.addAttribute("mallbaseModels", mallbaseModels);
		model.addAttribute("limit", limit);
		model.addAttribute("groupid",groupid);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
		session.setAttribute("grouptype", grouptype);
	}
	
	@RequestMapping(value = "/getmallbasebymid", method = RequestMethod.POST)
	public void getmallbasebymid(Model model, HttpSession session, HttpServletRequest request,String mid,PrintWriter out) {

		String jsonStr="";
		if(mid!=null&& !"".equals(mid)){
			MallBaseModel mallBaseModel = mallService.getmallbasebymid(mid);
			if(mallBaseModel!=null ){
				jsonStr=JSONObject.toJSONString(mallBaseModel);
			}
		}
		
		out.write(jsonStr);
	}
	@RequestMapping(value = "/insertOrupdateMallbase", method = RequestMethod.POST)
	public void insertOrupdateMallbase(Model model, HttpSession session, HttpServletRequest request,
			String mid,String title,String picurl,String url,String price,String state,String orderindex,String groupid,String oldprice,String subtitle,
			PrintWriter out) {
		
		String jsonStr="";
		MallBaseModel mallBaseModel =new MallBaseModel();
		if(mid ==null|| "".equals(mid)){
			mid=MbUtil.getUUID();
		}
		mallBaseModel.setMid(mid);
		if(title!=null&& !"".equals(title)){
			mallBaseModel.setTitle(title);
		}
		if(subtitle!=null&& !"".equals(subtitle)){
			mallBaseModel.setSubtitle(subtitle);
		}
		if(picurl!=null&& !"".equals(picurl)){
			mallBaseModel.setPicurl(picurl);
		}
		if(url!=null&& !"".equals(url)){
			mallBaseModel.setUrl(url);
		}
		if(price!=null&& !"".equals(price)){
			mallBaseModel.setPrice(price);
		}
		if(oldprice!=null&& !"".equals(oldprice)){
			mallBaseModel.setOldprice(oldprice);
		}
		if(state!=null&& !"".equals(state)){
			mallBaseModel.setState(Integer.parseInt(state));
		}
		if(orderindex!=null&& !"".equals(orderindex)){
			mallBaseModel.setOrderindex(Long.valueOf(orderindex));
		}
		if(groupid!=null&& !"".equals(groupid)){
			mallBaseModel.setGroupid(Integer.parseInt(groupid));
		}
		try {
			mallService.insertOrupdateMallbase(mallBaseModel);
			jsonStr="success";
		} catch (Exception e) {
			jsonStr="fail";
		}
		
		
		out.write(jsonStr);
	}
	
	@RequestMapping(value = "/equippinggrouplist", method = RequestMethod.GET)
	public void equippinggrouplist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
			int page,int limit,int order,String orderConditions,int grouptype) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		PageBean<EquippingGroupModel> pageBean=new PageBean<EquippingGroupModel>();
		if(page==0){
			page=1;
		}
	    if(limit==0){
	    	limit=20;
	    }
	  
	    if(orderConditions==null || orderConditions==""){
			orderConditions="";
		}
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=mallService.getEquippingGroupCount(-1,grouptype);
		
		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;
		List<EquippingGroupModel> equippingGroupModels=mallService.getEquippingGrouplistWithLimit(-1,start,limit,order,orderConditions,grouptype);
		
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		ArrayList<Integer> typeList =new ArrayList<Integer>();
		typeList.add(0);
		typeList.add(1);

		model.addAttribute("pageBean", pageBean);
		
		
		model.addAttribute("equippingGroupModels", equippingGroupModels);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
		session.setAttribute("grouptype", grouptype);
	}
	
	@RequestMapping(value = "/getEquippinggroupByGroupid", method = RequestMethod.POST)
	public void getEquippinggroupByGroupid(Model model, HttpSession session, HttpServletRequest request,String groupid,PrintWriter out) {

		String jsonStr="";
		if(groupid!=null&& !"".equals(groupid)){
			EquippingGroupModel equippingGroupModel = mallService.getEquippinggroupByGroupid(groupid);
			if(equippingGroupModel!=null ){
				jsonStr=JSONObject.toJSONString(equippingGroupModel);
			}
		}
		
		out.write(jsonStr);
	}
	
	@RequestMapping(value = "/insertOrupdateEquippingGroup", method = RequestMethod.POST)
	public void insertOrupdateEquippingGroup(Model model, HttpSession session, HttpServletRequest request,
			String groupid,String title,String state,String orderindex,String type,String subtitle,String url,int grouptype,
			PrintWriter out) {
		
		String jsonStr="";
		EquippingGroupModel equippingGroupModel =new EquippingGroupModel();
		if(groupid !=null && !"".equals(groupid)){
			equippingGroupModel.setGroupid(Integer.parseInt(groupid));
		}
		
		if(subtitle!=null&& !"".equals(subtitle)){
			equippingGroupModel.setSubtitle(subtitle);
		}
		if(url!=null&& !"".equals(url)){
			equippingGroupModel.setUrl(url);
		}
		if(title!=null&& !"".equals(title)){
			equippingGroupModel.setTitle(title);
		}
		
		if(state!=null&& !"".equals(state)){
			equippingGroupModel.setState(Integer.parseInt(state));
		}
		if(orderindex!=null&& !"".equals(orderindex)){
			equippingGroupModel.setOrderindex(Long.valueOf(orderindex));
		}
		if(type!=null&& !"".equals(type)){
			equippingGroupModel.setType(Integer.parseInt(type));
		}
		equippingGroupModel.setGrouptype(grouptype);
		try {
			mallService.insertOrupdateEquippingGroup(equippingGroupModel);
			jsonStr="success";
		} catch (Exception e) {
			jsonStr="fail";
		}
		
		
		out.write(jsonStr);
	}
	
	@RequestMapping(value = "/addMallproductKeywords", method = RequestMethod.POST)
	public void addMallproductKeywords(Model model, HttpSession session, HttpServletRequest request,
			String id,String title,String keyword,
			PrintWriter out) throws Exception {
		
		String jsonStr="";
		String searchkeyword="";
		if(title!=null&& !"".equals(title)){
			searchkeyword=keyword+","+title;
		}else{
			searchkeyword=keyword;
		}
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("id", id);
		jsonobj.put("keyword", searchkeyword);
		    
  		String data = JSONObject.toJSONString(jsonobj);
  		String urlString = Consts.MOTOBAND_SEARCH+"/search/mallproduct/add";
  		
  		logger.info("addMallproductKeywords search request data:[url:"+urlString+" param :"+data+"]");
  		RequestBody body = RequestBody.create(MediaType_JSON, data);
  		Request req = new Request.Builder().url(urlString).post(body).build();
  		Response resp=httpClient.newCall(req).execute();
  		String result =new String(resp.body().bytes(), "utf-8");
  		logger.info("addMallproductKeywords search response data:[result:"+result+"]");
  		JSONObject json = JSON.parseObject(result);
  		String str =(String) json.get("code");
  		if(Integer.parseInt(str)==0){
  			try {
				
  				mallService.insertMallProductKeyword(id,keyword);	
			} catch (Exception e) {
				out.write("fail1");
			}
  		 out.write("success");
  		}else{
  			out.write("fail2");
  		}
		out.write(jsonStr);
	}
	
}
