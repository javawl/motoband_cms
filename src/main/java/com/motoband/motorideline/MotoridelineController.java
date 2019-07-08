package com.motoband.motorideline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qcloud.cosapi.common.Sign;

@Controller
@RequestMapping(value = "/motorideline")
public class MotoridelineController {

	@Autowired
	private motoridelineService motoservice;

	@RequestMapping(value = "/getridelinelbasamap", method = RequestMethod.GET)
	public void login(Model model, HttpSession session,
			HttpServletRequest request, String ridelineid) {
		rideline rideline = motoservice.queryRidelineMessageById(ridelineid);

		int APP_ID_V2 = 10013836; // 项目ID
		String SECRET_ID_V2 = "AKID8TCGA2cH6hkJQ3NriIAkzN0bvIHd9r1w"; // 项目SecretID
		String SECRET_KEY_V2 = "NZoF8svEjxv9d1ThKdalZsmCoAuOBjX5"; // 项目SecretKey
		String BUCKET = "ridelinedata"; // 空间名称bucket
		long expired = System.currentTimeMillis() / 1000 + 3600 * 24 * 30 * 2;
		String sign = Sign.appSignature(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2,
				expired, BUCKET);
		String filepathString = rideline.getDataurl() + "?sign=" + sign;
		String line = "";
		StringBuilder str = new StringBuilder("");
		BufferedReader reader = null;
		try {
			URL url = new URL(filepathString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((line = reader.readLine()) != null) {
				str.append(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
		JSONArray jsonArray = JSON.parseArray(str.toString());
		
		ArrayList<positiondata> positiondatas = new ArrayList<positiondata>();
		for (int i = 0; i < jsonArray.size(); i++) {
			if(i%5==0){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				positiondata p = new positiondata();
				p.setSpeed(jsonObject.getString("speed"));
				p.setLongitude(jsonObject.getString("longitude"));
				p.setLatitude(jsonObject.getString("latitude"));
				positiondatas.add(p);
			}

		}
		model.addAttribute("str", str);
		String mileage=rideline.getMileage()/1000+"."+rideline.getMileage()%1000;
		float dipangle= (float)(Math.round(rideline.getDipangle()*10))/10;
		rideline.setDipangle(dipangle);
		float mi=Float.parseFloat(mileage);
	    float mi2=(float)(Math.round(mi*100))/100;

		model.addAttribute("mi", mi2);
		model.addAttribute("rideline", rideline);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		// 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		Date dt = new Date(rideline.getStarttime());
		String sDateTime = sdf.format(dt); // 得到精确到秒的表示：01/30/2016 21:00:00
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
		Date dt2 = new Date(rideline.getEndtime());
		String sDateTime2 = sdf2.format(dt2);

		String titletime = sDateTime + "-" + sDateTime2;
		model.addAttribute("titletime", titletime);
		String brandname = motoservice.getBrandNameById(rideline.getBrandid());
		model.addAttribute("brandname", brandname);
		String modelname = motoservice.getModelName(rideline.getModelid());
		model.addAttribute("modelname", modelname);
		long time=rideline.getRidetime();
		long hour = time / (60 * 60 * 1000);
		long minute = (time - hour * 60 * 60 * 1000) / (60 * 1000);
		long second = (time - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
	
		DecimalFormat df = new DecimalFormat("#00");
		String ridetime=df.format(hour)+":"+df.format(minute)+":"+df.format(second);
		model.addAttribute("ridetime", ridetime);
		String picString=rideline.getPics();
		 String[] pics=null;
		if (picString!=null) {
			 pics=picString.split(",");
		} else {
            pics=null;
		}
	    model.addAttribute("pics", pics);

	}
	
}
