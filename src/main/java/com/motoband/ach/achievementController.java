package com.motoband.ach;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.util.Constants;

@Controller
@RequestMapping(value = "/ach")
public class achievementController {
	@Autowired
	private achService achService;
	@Autowired
	private boxService boxService;

	@RequestMapping(value = "/achlist", method = RequestMethod.GET)
	public void achlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, String nowtypeid) {
		ArrayList<achievement> achievements = achService.getAchievements(userGuid);
		int updateCheck = achService.getUserUpdateCheck(userGuid);
		int delCheck = achService.getUserDelCheck(userGuid);
		int insCheck = achService.getUserInsCheck(userGuid);
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
		model.addAttribute("achievements", achievements);
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
	}
	@RequestMapping(value = "/addachievement", method = RequestMethod.POST)
	public void addachievement(Model model, HttpSession session, HttpServletRequest request,String updateORins,String achid, String achname, String achdes,String achimg,String achdisableimg,String achcode,
			String groupname,String mallurl,String score,String achtype,PrintWriter out) {
		achievement achievement=new achievement();
		achievement.setAch_name(achname);
		achievement.setAch_des(achdes);
		achievement.setAch_img(achimg);
		achievement.setAch_disableimg(achdisableimg);
		achievement.setAch_code(Integer.parseInt(achcode));
		achievement.setAchid(Integer.parseInt(achid));
		achievement.setGroupname(groupname);
		achievement.setScore(score);
		String result="";
		if(mallurl==null || "".equals(mallurl)){
			achievement.setMallurl(null);
		}else{
			achievement.setMallurl(mallurl);
		}
		
		achievement.setAchtype(Integer.parseInt(achtype));
		if (updateORins.equals("0")) {
			achService.updateAchievement(achievement);
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			result=admin.getUser_guid();
			
		}else if(updateORins.equals("1")){
			achService.addAchievement(achievement);
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			result=admin.getUser_guid();
		}
		
		out.print(result);
	}
}
