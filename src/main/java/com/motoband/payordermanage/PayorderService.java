package com.motoband.payordermanage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayorderService {
 @Autowired
 private PayorderMapper payorderMapper;
	public int getPayorderListCount(String userid,String order_no,String state,long starttime,long endtime) {	
		return payorderMapper.getPayorderListCount( userid,order_no,state,starttime,endtime);
	}
	public ArrayList<PayorderModel> getPayorderList(String userGuid, int start,
			int limit, int order,String userid,String order_no,String state,long starttime,long endtime) {
		
		return payorderMapper.getPayorderList(userGuid,start,limit,order,userid,order_no, state, starttime, endtime);
	}
	public String lookCar(String payid) {
		
		return payorderMapper.lookCar(payid);
	}
	public String lookUser(String payid) {
		
		return payorderMapper.lookUser(payid);
	}
	public InsuranceModel lookInsurance(String order_no) {
		
		return payorderMapper.lookInsurance(order_no) ;
	}
	public int getPayorderlogListCount(String inputorder_id) {
		
		return payorderMapper.getPayorderlogListCount(inputorder_id);
	}
	public ArrayList<PayorderlogModel> getPayorderlogList(String userGuid,
			int start, int limit, int order,String inputorder_id) {
		
		return payorderMapper.getPayorderlogList(userGuid,start,limit,order,inputorder_id);
	}
	public int getInsuranceupdatelistCount() {
		
		return payorderMapper.getInsuranceupdatelistCount();
	}
	public ArrayList<InsuranceupdateModel> getInsuranceupdatelist(
			String userGuid, int start, int limit, int order) {
		
		return payorderMapper.getInsuranceupdatelist(
				userGuid, start,limit, order);
	}
	public InsuranceupdateModel getInsuranceupdateByid(String iuid) {
		return payorderMapper.getInsuranceupdateByid(iuid);
	}
	public void updateInsuranceupdate(InsuranceupdateModel insuranceupdateModel) {
		payorderMapper.updateInsuranceupdate(insuranceupdateModel);
	}
	public void addInsuranceupdate(InsuranceupdateModel insuranceupdateModel) {
		payorderMapper.addInsuranceupdate( insuranceupdateModel);
	}
	public void deleteInsuranceupdateByid(String iuid) {
		payorderMapper.deleteInsuranceupdateByid(iuid);
		
	}
	public void changePolicyStatus(String channelOrderNo, String policyStatus) {
		payorderMapper.changePolicyStatus(channelOrderNo,policyStatus);
	}
	public int getPayorderUpdateCheck(String userGuid) {
		return payorderMapper.checkPrivilege("payorder_upd", userGuid);
	}

}
