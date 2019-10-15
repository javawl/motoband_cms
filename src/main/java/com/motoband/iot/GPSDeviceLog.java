package com.motoband.iot;

import java.sql.Timestamp;

/**
 * 上行下行日志
 * Created by junfei.Yang on 2019年7月16日.
 */
public class GPSDeviceLog {
	public long lid;
	public String MsgType;
	public String Event;
	public String Topic;
	public long Seq;
	public  long  PayloadLen;
	public String ProductId;
	public String DeviceName;
	public String Payload;
	public Timestamp Time;
}
