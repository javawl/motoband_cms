package com.motoband.iot;

import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface IOTMapper{

	int getDeviceLogCount();
}
