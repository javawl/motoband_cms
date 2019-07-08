package com.motoband.motorideline;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class motoridelineService {

	@Autowired
	private motoridelineMapper motoridelineMapper;

	public com.motoband.motorideline.rideline queryRidelineMessageById(
			String ridelineid) {
		// TODO Auto-generated method stub
		return motoridelineMapper.queryRidelineMessageById(ridelineid);
	}

	public String getBrandNameById(int brandid) {
		// TODO Auto-generated method stub
		return motoridelineMapper.getBrandNameById(brandid);
	}

	public String getModelName(int modelid) {
		// TODO Auto-generated method stub
		return motoridelineMapper.getModelName(modelid);
	}


}
