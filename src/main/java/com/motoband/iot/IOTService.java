package com.motoband.iot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IOTService {

	@Autowired
	private IOTMapper iotMapper;

	public int getDeviceLogCount() {
		return iotMapper.getDeviceLogCount();
	}

}
