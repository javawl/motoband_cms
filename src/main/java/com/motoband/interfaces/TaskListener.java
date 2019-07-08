package com.motoband.interfaces;

import com.motoband.Events.TaskEvent;

public interface TaskListener {
	/**
	 * 任务准备执行
	 * @param event
	 */
    public void isImplementing(TaskEvent event);
}