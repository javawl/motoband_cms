package com.motoband.util.tim;

import java.util.ArrayList;

public class TIMMessageBatchResp {

	public String ActionStatus;
	public String ErrorInfo;
	public ArrayList<TIMMessageError> ErrorList;
	@Override
	public String toString() {
		return "TIMMessageBatchResp [ActionStatus=" + ActionStatus + ", ErrorInfo=" + ErrorInfo + ", ErrorList=" + ErrorList + "]";
	}
	
	
}
