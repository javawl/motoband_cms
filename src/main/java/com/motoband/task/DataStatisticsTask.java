package com.motoband.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.motoband.dataStatistics.BusinessDataStatisticsModel;
import com.motoband.dataStatistics.DataStatisticsModel;
import com.motoband.dataStatistics.DataStatisticsService;
import com.motoband.util.Consts;
import com.motoband.util.DateFormat;

public class DataStatisticsTask {
	
	@Autowired
	private DataStatisticsService dataStatisticsService;

	public void execute() {
		// System.out.println("定时任务.......1111111111111111111111111");
		
		if(Consts.ISOPENTASK){
			
			long time = System.currentTimeMillis() / 1000 - 24 * 60 * 60;
			long startTime = DateFormat.getDayStart(time) * 1000;
			long endTime = DateFormat.getDayEnd(time) * 1000 + 999;

			String timeformat = DateFormat.formatDate(new Date(startTime), "yyyyMMdd");

			// APP打开次数
			int openAppCount = dataStatisticsService.getOpenAppCount(startTime, endTime);
			// 独立用户数
			int distinctUserCount = dataStatisticsService.getDistinctUser(startTime, endTime);
			// 新增用户数
			int newAddUserCount = dataStatisticsService.getNewAddUser(startTime, endTime);
			// 骑行次数
			int ridedataCount = dataStatisticsService.getRidedataCount(startTime, endTime);
			// 动态数
			int newsCount = dataStatisticsService.getNewsCount(startTime, endTime, null, null);
			// 故事数
			int newsStoryCount = dataStatisticsService.getNewsCount(startTime, endTime, "4", null);
			// 参与讨论人数
			int newsDiscussCount = dataStatisticsService.getNewsUserCount(startTime, endTime, "9", null);
			// 发布讨论人数
			int newsPublishDiscussUserCount = dataStatisticsService.getNewsUserCount(startTime, endTime, "10", null);
			// 发布讨论次数
			int newsPublishDiscussCount = dataStatisticsService.getNewsCount(startTime, endTime, "10", null);
			// 文章转载人数
			int newsBoxUserCount = dataStatisticsService.getNewsUserCount(startTime, endTime, "8", null);
			// 文章转载次数
			int newsBoxCount = dataStatisticsService.getNewsCount(startTime, endTime, "8", null);
			// 兑换礼物人数
		//	int giftexchangeUserCount = dataStatisticsService.getGiftexchangeUserCount(startTime, endTime);
			// 兑换礼物次数
		//	int giftexchangeCount = dataStatisticsService.getGiftexchangeCount(startTime, endTime);
			// 兑换总积分
	//		int giftexchangeCreditCount = dataStatisticsService.getGiftexchangeCreditCount(startTime, endTime);
			// 转赠礼物人数
	//		int giftgivenUserCount = dataStatisticsService.getGiftgivenUserCount(startTime, endTime);
			// 转赠礼物总数
	//		int giftgivenGiftCount = dataStatisticsService.getGiftgivenGiftCount(startTime, endTime);
			// 图文数
			int newsGraphicCount = dataStatisticsService.getNewsCount(startTime, endTime, "0", null);
			// 视频动态数
			int newsVideoCount = dataStatisticsService.getNewsCount(startTime, endTime, "1", null);
			// 线路动态数
			int newsRidelineCount = dataStatisticsService.getNewsCount(startTime, endTime, "2", null);
			// 精选数
			int newsRecommmentCount = dataStatisticsService.getNewsCount(startTime, endTime, null, "1");
			// 点赞数
			int newsLikeCount = dataStatisticsService.getNewsLike(startTime, endTime);
			// 评论数
			int newsCommentCount = dataStatisticsService.getNewsCommentCount(startTime, endTime);
			// 礼物数
			int giftCount = dataStatisticsService.getGiftCount(startTime, endTime);
			// 送礼独立用户数
			int giftDistinctUserCount = dataStatisticsService.getGiftDistinctUserCount(startTime, endTime);
			// 礼物分布动态数
			int giftDistinctNidCount = dataStatisticsService.getGiftDistinctNidCount(startTime, endTime);
			// 本地商家合计数
		//	int buserSumCount = dataStatisticsService.getBuserSumCount(startTime, endTime);
			// 本地商家日新增数
	//		int buserAddCount = dataStatisticsService.getBuserAddCount(startTime, endTime);
			// 二手车合计数
	//		int secondcarSumCount = dataStatisticsService.getSecondcarSumCount(startTime, endTime);
			// 二手车日增数
			int secondcarAddCount = dataStatisticsService.getSecondcarAddCount(startTime, endTime);
			
			//随机数
		  float ratio1=(float) (4+Math.random()); 
		  float ratio2=(float)(1+Math.random()); 
		  float ratio11=(float) (Math.floor(ratio1*10)/10);
		  float ratio22=(float) (Math.floor(ratio2*10)/10);

			DataStatisticsModel dataStatisticsModel = new DataStatisticsModel();
			dataStatisticsModel.setOpenAppCount(openAppCount);
			dataStatisticsModel.setDistinctUserCount(distinctUserCount);
			dataStatisticsModel.setNewAddUserCount(newAddUserCount);
			dataStatisticsModel.setRidedataCount(ridedataCount);
			dataStatisticsModel.setNewsCount(newsCount);
			dataStatisticsModel.setNewsStoryCount(newsStoryCount);
			dataStatisticsModel.setNewsVideoCount(newsVideoCount);
			dataStatisticsModel.setNewsRidelineCount(newsRidelineCount);
			dataStatisticsModel.setNewsRecommmentCount(newsRecommmentCount);
			dataStatisticsModel.setNewsLikeCount(newsLikeCount);
			dataStatisticsModel.setNewsCommentCount(newsCommentCount);
			dataStatisticsModel.setTimestamp(startTime);
			dataStatisticsModel.setTimeformat(timeformat);
			dataStatisticsModel.setGiftCount(giftCount);
			dataStatisticsModel.setGiftDistinctUserCount(giftDistinctUserCount);
			dataStatisticsModel.setGiftDistinctNidCount(giftDistinctNidCount);
			dataStatisticsModel.setNewsDiscussCount(newsDiscussCount);
			dataStatisticsModel.setNewsGraphicCount(newsGraphicCount);
			dataStatisticsModel.setNewsPublishDiscussUserCount(newsPublishDiscussUserCount);
			dataStatisticsModel.setNewsPublishDiscussCount(newsPublishDiscussCount);
//			dataStatisticsModel.setGiftexchangeUserCount(giftexchangeUserCount);
//			dataStatisticsModel.setGiftexchangeCount(giftexchangeCount);
//			dataStatisticsModel.setGiftexchangeCreditCount(giftexchangeCreditCount);
//			dataStatisticsModel.setGiftgivenUserCount(giftgivenUserCount);
//			dataStatisticsModel.setGiftgivenGiftCount(giftgivenGiftCount);
			dataStatisticsModel.setNewsBoxCount(newsBoxCount);
			dataStatisticsModel.setNewsBoxUserCount(newsBoxUserCount);
//			dataStatisticsModel.setBuserSumCount(buserSumCount);
//			dataStatisticsModel.setBuserAddCount(buserAddCount);
			dataStatisticsModel.setSecondcarAddCount(secondcarAddCount);
	//		dataStatisticsModel.setSecondcarSumCount(secondcarSumCount);
			
			dataStatisticsModel.setRatio1(ratio11);
			dataStatisticsModel.setRatio2(ratio22);

			dataStatisticsService.insertDataStatistics(dataStatisticsModel);

	/*		// 商家统计
			// 查到所有
			List<BusinessDataStatisticsModel> businessDataStatisticsModels = dataStatisticsService.getBusersDataStatisticsCount(startTime, endTime);
			Map<String, HashMap<String, String>> outtermap = new HashMap<String, HashMap<String, String>>();

			for (BusinessDataStatisticsModel bds : businessDataStatisticsModels) {
				if (outtermap.containsKey(bds.getBuserid())) {
					HashMap<String, String> innerMap = outtermap.get(bds.getBuserid());
					innerMap.put(String.valueOf(bds.getType()), String.valueOf(bds.getCount()));
					outtermap.put(bds.getBuserid(), innerMap);
				} else {
					HashMap<String, String> innerMap = new HashMap<String, String>();
					innerMap.put(String.valueOf(bds.getType()), String.valueOf(bds.getCount()));
					outtermap.put(bds.getBuserid(), innerMap);
				}
			}
			for (Map.Entry<String, HashMap<String, String>> entry : outtermap.entrySet()) {
				BusinessDataStatisticsModel businessDataStatisticsModel = new BusinessDataStatisticsModel();
				businessDataStatisticsModel.setBuserid(entry.getKey());
				HashMap<String, String> tempMap = entry.getValue();
				if (tempMap.containsKey("1")) {
					businessDataStatisticsModel.setUsecarmainBusersCount(Integer.parseInt(tempMap.get("1")));
				}
				if (tempMap.containsKey("2")) {
					businessDataStatisticsModel.setAllBusersListCount(Integer.parseInt(tempMap.get("2")));
				}
				if (tempMap.containsKey("3")) {
					businessDataStatisticsModel.setUsecarmainActivityBusersCount(Integer.parseInt(tempMap.get("3")));
				}
				if (tempMap.containsKey("4")) {
					businessDataStatisticsModel.setOnphoneBusersCount(Integer.parseInt(tempMap.get("4")));
				}
				if (tempMap.containsKey("5")) {
					businessDataStatisticsModel.setOnlineBusersCount(Integer.parseInt(tempMap.get("5")));
				}
				if (tempMap.containsKey("6")) {
					businessDataStatisticsModel.setCommentBusersCount(Integer.parseInt(tempMap.get("6")));
				}

				businessDataStatisticsModel.setTimestamp(startTime);
				businessDataStatisticsModel.setTimeformat(timeformat);
				dataStatisticsService.insertBusinessDataStatistics(businessDataStatisticsModel);
			}*/
			
		}
		

	}
}
