package com.motoband.payordermanage;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface PayorderMapper {

	int getPayorderListCount(@Param("userid")String userid,@Param("order_no")String order_no,@Param("state")String state,@Param("starttime")long starttime,@Param("endtime")long endtime);

	ArrayList<PayorderModel> getPayorderList(@Param("userGuid")String userGuid, @Param("start")int start, @Param("limit")int limit,
			@Param("order")int order,@Param("userid")String userid,@Param("order_no")String order_no,@Param("state")String state,@Param("starttime")long starttime,@Param("endtime")long endtime);

	String lookCar(@Param("payid")String payid);

	String lookUser(@Param("payid")String payid);

	InsuranceModel lookInsurance(@Param("order_no")String order_no);

	int getPayorderlogListCount(@Param("inputorder_id")String inputorder_id);

	ArrayList<PayorderlogModel> getPayorderlogList(@Param("userGuid")String userGuid, @Param("start")int start,
			@Param("limit")int limit, @Param("order")int order,@Param("inputorder_id")String inputorder_id);

	int getInsuranceupdatelistCount();

	ArrayList<InsuranceupdateModel> getInsuranceupdatelist(@Param("userGuid")String userGuid, @Param("start")int start,
			@Param("limit")int limit, @Param("order")int order);

	InsuranceupdateModel getInsuranceupdateByid(@Param("iuid")String iuid);

	void updateInsuranceupdate(InsuranceupdateModel insuranceupdateModel);

	void addInsuranceupdate(InsuranceupdateModel insuranceupdateModel);

	void deleteInsuranceupdateByid(@Param("iuid")String iuid);

	void changePolicyStatus(@Param("channelOrderNo")String channelOrderNo, @Param("policyStatus")String policyStatus);

	int checkPrivilege(@Param("privilege_key")String privilege_key, @Param("userGuid")String userGuid);

}
