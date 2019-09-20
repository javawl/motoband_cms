package com.motoband.dataversion;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class dataversionService {

	@Autowired
	private dataversionMapper dataversionMapper;

	public com.motoband.dataversion.dataversion getMessage() {
		
		return dataversionMapper.getMessage();
	}

	public void updateBrandversion() {
		
		dataversionMapper.updateBrandversion();
	}

	public void updateModelversion() {
		
		dataversionMapper.updateModelversion();
	}

	public void updateTypeversion() {
		
	dataversionMapper.updateTypeversion();
	}

	public void updateConfigversion() {
		
		dataversionMapper.updataConfigversion();
	}

	public void updateAchieve() {
		
		dataversionMapper.updateAchieve();
	}

	public void updateUserLevel() {
		
		dataversionMapper.updateUserLevel();
	}

	public void updateClientUpdate() {
		
		dataversionMapper.updateClientUpdate();
	}

	public void updateBanner() {
		
		dataversionMapper.updateBanner();
	}

	public void updateHotCity() {
		dataversionMapper.updateHotCity();
		
	}

	public void updateWeather() {
		dataversionMapper.updateWeather();
		
	}

	public void updateTopic() {
		dataversionMapper.updateTopic();
	}

	public void updataRuntype() {
		dataversionMapper.updateRuntype();
		
	}

	public void updateNewslabel() {
		dataversionMapper. updateNewslabel();
		
	}

	public void updateMotobandgp() {
		dataversionMapper.updateMotobandgp();
		
	}

	public void updateServiceconfig() {
		dataversionMapper.updateServiceconfig();
		
	}

	public void updateBoxtype() {
		dataversionMapper.updateBoxtype();
		
	}

	public void updateInsurancemotodata() {
		dataversionMapper.updateInsurancemotodata();
		
	}

	public void updateInsurancepackage() {
		dataversionMapper.updateInsurancepackage();
		
	}

	public void updateInsuranceproduct() {
		dataversionMapper.updateInsuranceproduct();
		
	}

	public void updateAd() {
		dataversionMapper.updateAd();
		
	}

	public void updateMotobrandparent() {
		dataversionMapper.updateMotobrandparent();
		
	}

	public void updateInsuranceupdate() {
		dataversionMapper.updateInsuranceupdate();
	}

	public void updateActivity() {
		dataversionMapper.updateActivity();
		
	}

	public void updateSchoolpackage() {
		dataversionMapper.updateSchoolpackage();
		
	}

	public void updateSchoolvideo() {
		dataversionMapper.updateSchoolvideo();
		
	}

	public void updateSchoolbox() {
		dataversionMapper.updateSchoolbox();
		
	}

	public void updateNewscategory() {
		dataversionMapper.updateNewscategory();
		
	}

	public void updateMall() {
		dataversionMapper.updateMall();
		
	}

	public void updateMallnewcar() {
		dataversionMapper.updateMallnewcar();
		
	}

	public void updateMallsecondcar() {
		dataversionMapper.updateMallsecondcar();
		
	}

	public void updateMallbrand() {
		dataversionMapper.updateMallbrand();
		
	}

	public void updateMallparenttype() {
		dataversionMapper.updateMallparenttype();
	}

	public void updateMallstyle() {
		dataversionMapper.updateMallstyle();
		
	}

	public void updateMalllabel() {
		dataversionMapper.updateMalllabel();
		
	}

	public void updateMallnotify() {
		dataversionMapper.updateMallnotify();
		
	}

	public void updateMalltype() {
		dataversionMapper.updateMalltype();
		
	}

	public void updateGift() {
		dataversionMapper.updateGift();
		
	}

	public void updateDiscuss() {
		dataversionMapper.updateDiscuss();
	}

	public void updateBusinessservice() {
		dataversionMapper.updateBusinessservice();
	}

	public void updateBusinesstype() {
		dataversionMapper.updateBusinesstype();
	}

	public void updateUsecarmain() {
		dataversionMapper.updateUsecarmain();
	}

	public void updateGiftexchange() {
		
		dataversionMapper.updateGiftexchange();
	}

	public void updateAdpool() {
		dataversionMapper.updateAdpool();
		
	}

	public void updateTribal() {
		dataversionMapper.updateTribal();
		
	}

	public void updateEquipping() {
		
		dataversionMapper.updateEquipping();
	}

	public void updateTribalType() {
		dataversionMapper.updateTribalType();
		
	}

	public void updateVipcardinfo() {
		dataversionMapper.updateVipcardinfo();
		
	}

	public void updateQiji() {
		dataversionMapper.updateQiji();

	}

	public void updateMallActivityversion() {
		dataversionMapper.updateMallActivityversion();
	}

	public void updateBusinessservicev3_8_0version() {
		dataversionMapper.updateBusinessservicev3_8_0version();
	}
	


}
