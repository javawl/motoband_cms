package com.motoband.credit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.motoband.util.MbUtil;
@Component
public class CreditService {
	@Autowired private CreditMapper creditMapper;
	
	
	public static final byte CreditActionType_Add = 0;// 增加
	public static final byte CreditActionType_Subtract = 1;// 减少
	
	public static final int CreditType_BreakNews = 200;
	public static final int CreditType_BreakStory = 201;
	public static final int CreditType_BreakComment = 202;
	public static final int CreditType_NewsRecommend = 15;
	public static final int CreditType_StoryRecommend = 16;
	public static final int CreditType_NewsRecommendMain = 17;
	public static final int CreditType_StoryRecommendMain = 18;
	public int updateUserCreditByCreditType(String userid, int creditType) throws Exception {
		switch (creditType) {
		case CreditType_NewsRecommend: {

			CreditLogModel model =new CreditLogModel();
			model.actiontype=CreditActionType_Add;
			model.credittype=creditType;
			model.score=10;
			model.userid=userid;
			
			this.updateUserCredit(model);
			break;
		}
		
		case CreditType_StoryRecommend: {

			CreditLogModel model =new CreditLogModel();
			model.actiontype=CreditActionType_Add;
			model.credittype=creditType;
			model.score=25;
			model.userid=userid;
			
			this.updateUserCredit(model);
			break;
		}
		
		case CreditType_NewsRecommendMain: {

			CreditLogModel model =new CreditLogModel();
			model.actiontype=CreditActionType_Add;
			model.credittype=creditType;
			model.score=20;
			model.userid=userid;
			
			this.updateUserCredit(model);
			break;
		}
		
		case CreditType_StoryRecommendMain: {

			CreditLogModel model =new CreditLogModel();
			model.actiontype=CreditActionType_Add;
			model.credittype=creditType;
			model.score=50;
			model.userid=userid;
			
			this.updateUserCredit(model);
			break;
		}
		case CreditType_BreakNews: {

			CreditLogModel model =new CreditLogModel();
			model.actiontype=CreditActionType_Subtract;
			model.credittype=creditType;
			model.score=20;
			model.userid=userid;
			
			this.updateUserCredit(model);
			break;
		}
		
		case CreditType_BreakStory: {

			CreditLogModel model =new CreditLogModel();
			model.actiontype=CreditActionType_Subtract;
			model.credittype=creditType;
			model.score=20;
			model.userid=userid;
			
			this.updateUserCredit(model);
			break;
		}
		case CreditType_BreakComment: {

			CreditLogModel model =new CreditLogModel();
			model.actiontype=CreditActionType_Subtract;
			model.credittype=creditType;
			model.score=20;
			model.userid=userid;
			
			this.updateUserCredit(model);
			break;
		}
		default:
			break;
		
		}
		return 1;
	}
	
	
	@Transactional
	public  Map<String ,Object> updateUserCredit(CreditLogModel model) throws Exception {
		long finalBalance =0;
		model.creditlogid=MbUtil.getUUID();
		model.logtime=System.currentTimeMillis();
		long tempBalance = creditMapper.selectUserCredit(model.userid);
		if (model.actiontype == 1) {
			if (tempBalance < model.score) {
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("balance", String.valueOf(tempBalance));
				map.put("result", "0");
				return map;
			}

			finalBalance=tempBalance-model.score;
		}else{
			finalBalance=tempBalance+model.score;
		}
		
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("userid", model.userid);
		temp.put("credit", finalBalance);
		creditMapper.updateUserCredit(temp);
		model.subffix = Math.abs(model.userid.hashCode()) % 10;
		creditMapper.insertCreditLog(model);
		 
		 
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("balance", String.valueOf(finalBalance));
		map.put("result", "1");
		return map;
	}
}
