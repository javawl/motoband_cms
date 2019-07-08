package com.motoband.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.motoband.Events.TaskEvent;
import com.motoband.interfaces.TaskListener;
import com.motoband.motouser.motouserController;

/**
 * 推送任务执行监听器
 * @author Administrator
 *
 */
public class TaskImplementListener implements TaskListener {

	public	static Logger logger = LoggerFactory.getLogger(motouserController.class);

	@Override
	public void isImplementing(TaskEvent event) {
		if(logger.isInfoEnabled()) {
			logger.info("正在执行任务："+JSON.toJSONString(event.getTask()));;
		}
	}

}
