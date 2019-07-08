package com.motoband.dataversion;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.motoband.admin.admin.Admin;
import com.motoband.util.Constants;

@Controller
@RequestMapping(value = "/dataversion")
public class dataversionController {

	@Autowired
	private dataversionService dataversionService;

	@RequestMapping(value = "/dataversionlist", method = RequestMethod.GET)
	public void carMessage(Model model, HttpSession session, HttpServletRequest request, String userGuid) {
		dataversion dataversion = dataversionService.getMessage();
		model.addAttribute("dataversion", dataversion);
	}

	@RequestMapping(value = "/updateversion", method = RequestMethod.POST)
	public void updateversion(Model model, HttpSession session, HttpServletRequest request, String sign, PrintWriter out) {

		if (sign.equals("motobrand")) {
			dataversionService.updateBrandversion();
		} else if (sign.equals("motomodel")) {
			dataversionService.updateModelversion();
		} else if (sign.equals("mototype")) {
			dataversionService.updateTypeversion();
		} else if (sign.equals("config")) {
			dataversionService.updateConfigversion();
		} else if (sign.equals("achievementversion")) {
			dataversionService.updateAchieve();
		} else if (sign.equals("userlevelversion")) {
			dataversionService.updateUserLevel();
		} else if (sign.equals("clientupdateversion")) {
			dataversionService.updateClientUpdate();
		} else if (sign.equals("bannerversion")) {
			dataversionService.updateBanner();
		}else  if (sign.equals("hotcityversion")) {
			dataversionService.updateHotCity();
		}else if (sign.equals("weatherversion")) {
			dataversionService.updateWeather();
		}else if (sign.equals("topicversion")) {
			dataversionService.updateTopic();
		}else if(sign.equals("runtypeversion")){
			dataversionService.updataRuntype();
		}else if (sign.equals("newslabelversion")) {
			dataversionService.updateNewslabel();
		}else if (sign.equals("motobandgpversion")) {
			dataversionService.updateMotobandgp();
		}else if (sign.equals("serviceconfigversion")) {
			dataversionService.updateServiceconfig();
		}else if (sign.equals("boxtypeversion")) {
			dataversionService.updateBoxtype();
		}else if (sign.equals("insurancemotodataversion")) {
			dataversionService.updateInsurancemotodata();
		}else if (sign.equals("insurancepackageversion")) {
			dataversionService.updateInsurancepackage();
		}else if (sign.equals("insuranceproductversion")) {
			dataversionService.updateInsuranceproduct();
		}else if (sign.equals("adversion")) {
			dataversionService.updateAd();
		}else if (sign.equals("motobrandparentversion")) {
			dataversionService.updateMotobrandparent();
		}else if (sign.equals("insuranceupdateversion")) {
			dataversionService.updateInsuranceupdate();
		}else if (sign.equals("activityversion")) {
			dataversionService.updateActivity();
		}else if (sign.equals("schoolpackageversion")) {
			dataversionService.updateSchoolpackage();
		}else if (sign.equals("schoolvideoversion")) {
			dataversionService.updateSchoolvideo();
		}else if (sign.equals("schoolboxversion")) {
			dataversionService.updateSchoolbox();
		}else if (sign.equals("newscategoryversion")) {
			dataversionService.updateNewscategory();
		}else if (sign.equals("mallversion")) {
			dataversionService.updateMall();
//			dataversionService.updateMallActivityversion();
		}else if (sign.equals("mallnewcarversion")) {
			dataversionService.updateMallnewcar();
		}else if (sign.equals("mallsecondcarversion")) {
			dataversionService.updateMallsecondcar();
		}else if (sign.equals("mallbrandversion")) {
			dataversionService.updateMallbrand();
		}else if (sign.equals("mallparenttypeversion")) {
			dataversionService.updateMallparenttype();
		}else if (sign.equals("mallstyleversion")) {
			dataversionService.updateMallstyle();
		}else if (sign.equals("malltypeversion")) {
			dataversionService.updateMalltype();
		}else if (sign.equals("malllabelversion")) {
			dataversionService.updateMalllabel();
		}else if (sign.equals("mallnotifyversion")) {
			dataversionService.updateMallnotify();
		}else if (sign.equals("giftversion")) {
			dataversionService.updateGift();
		}else if (sign.equals("discussversion")) {
			dataversionService.updateDiscuss();
		}else if (sign.equals("businessserviceversion")) {
			dataversionService.updateBusinessservice();
		}else if (sign.equals("businesstypeversion")) {
			dataversionService.updateBusinesstype();
		}else if (sign.equals("usecarmainversion")) {
			dataversionService.updateUsecarmain();
		}else if (sign.equals("giftexchangeversion")) {
		dataversionService.updateGiftexchange();
	    }else if (sign.equals("adpoolversion")) {
		dataversionService.updateAdpool();
	}else if (sign.equals("tribalversion")) {
		dataversionService.updateTribal();
	}else if (sign.equals("equippingversion")) {
		dataversionService.updateEquipping();
	}else if (sign.equals("tribaltypeversion")) {
		dataversionService.updateTribalType();
	}else if (sign.equals("vipcardinfoversion")) {
		dataversionService.updateVipcardinfo();
	}if (sign.equals("qijiversion")) {
		dataversionService.updateQiji();
	}
			
		

		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);

		out.print(admin.getUser_guid());
	}
 
}
