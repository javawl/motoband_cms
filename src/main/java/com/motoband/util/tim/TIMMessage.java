/**
 * 
 */
package com.motoband.util.tim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Eason
 *
 */
public class TIMMessage {

	public static final String MessageType_TIMCustomElem = "TIMCustomElem";
	public List<String> To_Account;
	public long MsgRandom;
	public ArrayList<TIMMsgElement> MsgBody;
	public Map<String, Object> OfflinePushInfo;
	@Override
	public String toString() {
		return "TIMMessage [To_Account=" + To_Account + ", MsgRandom=" + MsgRandom + ", MsgBody=" + MsgBody + ", OfflinePushInfo=" + OfflinePushInfo + "]";
	}



}
