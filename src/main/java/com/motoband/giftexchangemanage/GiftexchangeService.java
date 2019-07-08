package com.motoband.giftexchangemanage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GiftexchangeService {
@Autowired
private GiftexchangeMapper giftexchangeMapper;

public int getGiftexchangeCount() {
	return giftexchangeMapper.getGiftexchangeCount();
}

public List<GiftexchangeModel> getGiftexchangelist(int start, int limit, int order, String orderConditions) {
	return giftexchangeMapper.getGiftexchangelist(start, limit,order, orderConditions);
}

public List<GiftModel> getGiftList() {
	return giftexchangeMapper.getGiftList();
}

public void addGiftexchange(GiftexchangeModel giftexchangeModel) {
	giftexchangeMapper.addGiftexchange(giftexchangeModel) ;
	
}
@Transactional
public void insertBusinessGiftexchange(String buserids, String exchangeid) {
	String[] buseridsarr= buserids.split(",");
	for(int i=0 ; i<buseridsarr.length ; i++){
		giftexchangeMapper.insertBusinessGiftexchange(buseridsarr[i],exchangeid);	
	}
	
	
}

public GiftexchangeModel getGiftexchangeByid(String exchangeid) {
	
	return giftexchangeMapper.getGiftexchangeByid(exchangeid);
}

public List<BusinessGiftexchangeModel> getBusergiftexchangeByid(String exchangeid) {
	
	return giftexchangeMapper.getBusergiftexchangeByid(exchangeid);
}

public void updateGiftexchange(GiftexchangeModel giftexchangeModel) {
	giftexchangeMapper.updateGiftexchange(giftexchangeModel);
}

public void deleteBusinessGiftexchange(String exchangeid) {
	giftexchangeMapper.deleteBusinessGiftexchange(exchangeid);
}
}
