package com.motoband.secondcar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecondCarService {
@Autowired 
private SecondCarMapper secondCarMapper;

public int getSecondCarListCount(String createuser) {
	return secondCarMapper.getSecondCarListCount(createuser);
}

public List<SecondCarModel> getSecondCarList(int start, int limit, int order, String orderConditions, String createuser) {
	return secondCarMapper.getSecondCarList(start,  limit,  order,  orderConditions,  createuser);
}

public void addkeyword(String secondcarid, String keyword) {
	secondCarMapper.addkeyword(secondcarid, keyword);
	
}

public int getSecondcarMainZoneListCount() {
	return secondCarMapper.getSecondcarMainZoneListCount();
}

public List<SecondcarMainZoneModel> getSecondcarMainZoneList(int start, int limit, int order, String orderConditions) {
	return secondCarMapper.getSecondcarMainZoneList( start,  limit,  order,  orderConditions);
}

public SecondcarMainZoneModel getSecondcarMainZoneById(String scmzid) {
	return secondCarMapper.getSecondcarMainZoneById( scmzid) ;
}

public void insertOrUpdateSecondcarMainZone(SecondcarMainZoneModel scmz) {
	secondCarMapper.insertOrUpdateSecondcarMainZone(scmz);
	
}

public int getConsignmentListCount(long starttimelong, long endtimelong) {
	
	return secondCarMapper.getConsignmentListCount(starttimelong,endtimelong);
}

public List<ConsignmentModel> getConsignmentList(int start, int limit, int order, String orderConditions, long starttimelong, long endtimelong) {
	
	return secondCarMapper.getConsignmentList( start,  limit,  order,  orderConditions,  starttimelong,  endtimelong);
}

public List<SecondcarMainZoneModel> getSecondcarMainZoneNameList() {
	
	return secondCarMapper.getSecondcarMainZoneNameList();
}

public void changeState(String secondcarid, String state) {
	 secondCarMapper.changeState(secondcarid, state) ;
	
}

public void addRemarkandState(String consignmentid, String state, String remark) {
	secondCarMapper.addRemarkandState(consignmentid, state,remark) ;
	
}

}
