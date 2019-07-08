package com.motoband.botuser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface BotLogMapper {

	ArrayList<BotLogModel> getList(@Param("start")int start, @Param("limit")int limit);

	BotLogModel getModelByParam(Map<String,Object> map);

	Integer insertOrUpdate(BotLogModel model);

	int getCount();




}
