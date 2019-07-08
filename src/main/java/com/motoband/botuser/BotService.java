package com.motoband.botuser;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BotService {

	@Autowired
	private BotLogMapper qijimapper;

	public ArrayList<BotLogModel> getList(int start, int limit) {
		return qijimapper.getList(start, limit);

	}

	public BotLogModel getModelById(Map<String, Object> param) {
		return qijimapper.getModelByParam(param);
	}

	public boolean insertOrUpdateQijiModel(BotLogModel model) {
		boolean flag = qijimapper.insertOrUpdate(model) > 0;
		return flag;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return qijimapper.getCount();
	}
}
