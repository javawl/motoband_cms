package com.motoband.admanage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.motoband.boxmanage.Boxlist;
import com.motoband.news.topic;

@Component
public class AdService {
@Autowired
private AdMapper adMapper;

public int getAdListCount() {
	
	return adMapper.getAdListCount() ;
}

public ArrayList<AdModel> getAdList(String userGuid, int start, int limit,
		int order) {
	
	return adMapper.getAdList(userGuid,start,limit,order);
}

public AdModel editAd(int adid) {
	
	return adMapper.selectAdById(adid);
}
@Transactional
public void deleteAdById(int adid) {
	 adMapper.deleteAdById(adid);
	 adMapper.deleteAdimgByAdId(adid);
	
}

public void updateAd(AdModel adModel) {
	adMapper.updateAd(adModel);
	
}

public void insertAdImg(AdimgModel adimgModel) {
	adMapper.insertAdImg(adimgModel);
	
}

public void addAd(AdModel adModel) {
	adMapper.addAd(adModel);
	
}

public List<AdimgModel> getAdimgByadid(int adid) {
	
	return adMapper.getAdimgByadid(adid) ;
}

public void deleteAdimgById(int adimgid) {
	adMapper.deleteAdimgById(adimgid);
	
}

public AdimgModel getAdimgByadImgid(int adimgid) {
	
	return adMapper.getAdimgByadImgid(adimgid);
}

public void updateAdImg(AdimgModel adimgModel) {
	adMapper.updateAdImg(adimgModel);
	
}

public List<MotobandGPModel> getmotoGp() {
	
	return adMapper.getmotoGp();
}

public List<topic> getmotoKeywords() {
	
	return adMapper.getmotoKeywords();
}

public List<Boxlist> getmotoBoxid() {
	
	return adMapper.getmotoBoxid();
}



}
