package com.motoband.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.motoband.dataStatistics.DataStatisticsModel;
import com.motoband.dataStatistics.DataStatisticsService;
import com.motoband.util.DateFormat;

public class DataStatisticsTaskInitdata {
	@Autowired
	private DataStatisticsService dataStatisticsService;
    public void execute_initdata() {  

      System.out.println("定时任务.......2222222222222222222222222222222");  
  /*  	 long startTime =1514736000000l;//20180101 00:00:00  
    	 long endTime=1519833599999l;//20180228 23:59:59
     //    long endTime=1545148799999l;//20181218 23:59:59
         for(long i=startTime; i<=endTime;i=i+86400000){
        	// initmethod(i,i+86399999);
        	 initmethodbyday(i,i+86399999);
         } */
      
      
      
//      long startTime =1514736000000l;// 
// 	 long endTime=1517414400000l-1;//
//      for(long i=startTime; i<=endTime;i=i+86400000){
//     	 initmethodbyday(i,i+86399999);
//      } 
//      System.out.println("===========================1月完了======================");
         
         Runnable a1=  new Runnable() {
			@Override
			public void run() {
				 long startTime =1514736000000l;// 
		    	 long endTime=1517414400000l-1;//
		         for(long i=startTime; i<=endTime;i=i+86400000){
		        	 initmethodbyday(i,i+86399999);
		         } 
		         
		         System.out.println("===========================1月完了======================");
				
			}
		};
		Runnable a2=  new Runnable() {
			@Override
			public void run() {
				long startTime =1517414400000l;//
				long endTime=1519833600000l-1;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================2月完了======================");
			}
		};
		Runnable a3=  new Runnable() {
			@Override
			public void run() {
				long startTime =1519833600000l;//  
				long endTime=1522512000000l-1;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================3月完了======================");
			}
		};
		Runnable a4=  new Runnable() {
			@Override
			public void run() {
				long startTime =1522512000000l;//  
				long endTime=1525104000000l-1 ;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================4月完了======================");
			}
		};
		Runnable a5=  new Runnable() {
			@Override
			public void run() {
				long startTime =1525104000000l;//  
				long endTime=1527782400000l-1;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================5月完了======================");
			}
		};
		Runnable a6=  new Runnable() {
			@Override
			public void run() {
				long startTime =1527782400000l;//
				long endTime=1530374400000l-1;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================6月完了======================");
			}
		};
		Runnable a7=  new Runnable() {
			@Override
			public void run() {
				long startTime =1530374400000l;//
				long endTime=1533052800000l-1;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================7月完了======================");
			}
		};
		Runnable a8=  new Runnable() {
			@Override
			public void run() {
				long startTime =1533052800000l;//
				long endTime=1535731200000l-1;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================8月完了======================");
			}
		};
		Runnable a9=  new Runnable() {
			@Override
			public void run() {
				long startTime =1535731200000l;//
				long endTime=1538323200000l-1;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================9月完了======================");
			}
		};
		Runnable a10=  new Runnable() {
			@Override
			public void run() {
				long startTime =1538323200000l;// 
				long endTime=1541001600000l-1;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================10月完了======================");
			}
		};
		Runnable a11=  new Runnable() {
			@Override
			public void run() {
				long startTime =1541001600000l;// 
				long endTime=1543593600000l-1;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================11月完了======================");
			}
		};
		Runnable a12=  new Runnable() {
			@Override
			public void run() {
				long startTime =1543593600000l;// 
				long endTime=1546272000000l-1;//
				for(long i=startTime; i<=endTime;i=i+86400000){
					initmethodbyday(i,i+86399999);
				} 
				System.out.println("===========================12月完了======================");
			}
		};
		
//	Thread t1 = new Thread(a1);
//	Thread t2 = new Thread(a2);
//	Thread t3 = new Thread(a3);
//	Thread t4 = new Thread(a4);
//	Thread t5= new Thread(a5);
//	Thread t6 = new Thread(a6);
//	Thread t7 = new Thread(a7);
//	Thread t8 = new Thread(a8);
//	Thread t9 = new Thread(a9);
//	Thread t10 = new Thread(a10);
//	Thread t11 = new Thread(a11);
//	Thread t12 = new Thread(a12);
	
//	t1.start();
//	t2.start();
//	t3.start();
//	t4.start();
//	t5.start();
//	t6.start();
//	t7.start();
//	t8.start();
//	t9.start();
//	t10.start();
//	t11.start();
//	t12.start();
	
         
         
         
 
      
     //补单天
//      initmethod(1538064000000l,1538150399999l);
//      initmethod(1544976000000l,1545062399999l);
//      initmethod(1545062400000l,1545148799999l);
//		initmethod(1558454400000l,1558540799999l);
         
//         //更新每月1号数据
//         for(int i=0;i<10;i++){
//        	 Date date =DateFormat.getFirstDayOfMonth(2017,i);
//      	     long datestart=DateFormat.getDayStart(date.getTime()/1000);
//      	     initmethodbyday(datestart*1000,datestart*1000+86399999);
//         }
//       
      
      
     
    }  
    public void initmethod(long startTime,long endTime){
    	 
          String timeformat=DateFormat.formatDate(new Date(startTime), "yyyyMMdd");
          
          
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
  	
  		dataStatisticsModel.setNewsBoxCount(newsBoxCount);
  		dataStatisticsModel.setNewsBoxUserCount(newsBoxUserCount);
  	
  		dataStatisticsModel.setSecondcarAddCount(secondcarAddCount);
  		
  		dataStatisticsModel.setRatio1(ratio11);
  		dataStatisticsModel.setRatio2(ratio22);

  		dataStatisticsService.insertDataStatistics(dataStatisticsModel);
  		
	SimpleDateFormat  dataformat = new SimpleDateFormat("yyyy-MM-dd");
  		
  	System.out.println(dataformat.format(new Date(startTime))+"----------------完成");
    }
    
    
    public void initmethodbyday(long startTime,long endTime){
   	 
        String timeformat=DateFormat.formatDate(new Date(startTime), "yyyyMMdd");
        
        
        // APP打开次数
		int openAppCount = dataStatisticsService.getOpenAppCount(startTime, endTime);
		// 独立用户数
		int distinctUserCount = dataStatisticsService.getDistinctUser(startTime, endTime);
		// 新增用户数
		int newAddUserCount = dataStatisticsService.getNewAddUser(startTime, endTime);
	

		DataStatisticsModel dataStatisticsModel = new DataStatisticsModel();
		dataStatisticsModel.setOpenAppCount(openAppCount);
		dataStatisticsModel.setDistinctUserCount(distinctUserCount);
		dataStatisticsModel.setNewAddUserCount(newAddUserCount);
		dataStatisticsModel.setTimestamp(startTime);
		dataStatisticsModel.setTimeformat(timeformat);
	
		dataStatisticsService.updateDataStatistics(dataStatisticsModel);
		
	   SimpleDateFormat  dataformat = new SimpleDateFormat("yyyy-MM-dd");
		
	  System.out.println(dataformat.format(new Date(startTime))+"----------------完成");
  }
 
}
