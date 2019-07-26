package com.motoband.botuser;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.motoband.qiji.QiJiInfoModel;
import com.motoband.util.Base64Utils;
import com.motoband.util.Consts;
import com.motoband.util.MD5;
import com.motoband.util.OkHttpClientUtil;
import com.motoband.util.PageBean;
import com.motoband.util.RSAUtil;
import com.motoband.util.RedisManager;

import okhttp3.Headers;

@Controller
@RequestMapping(value = "/botuser")
public class BotUserController {
	private static Logger logger = Logger.getLogger(BotUserController.class);

	private static final String app_privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAo2VMTMp1v/bddhDSRiZsnTnMjN8paDlHKUI6yf+ZZWEg2zzsGQRG+vDSsYC9+FtE06XNEno3SGelhmYhQ6savwIDAQABAkEAhqqwbgHXdnWCJRLMG2ED17mRavFqcSn0Cn85cM6moLRPu/HBKNHrD9Iu+tXgkqXbDu3lrCwCieRUrN1CwRNrQQIhAOJ6CJvTOmBKJ4nEPw1ZGn31fCF6yzVVnb2w9DyUxgsPAiEAuLIhE00Br41lKeM7s7WyhFv5LDj4gS0jL5Moi+HQtVECIQCcjAgpVi/y4S9FXn7LBj12tdqQ9eVDP6QivA+HVLs0ZwIgKRkCASB2ipDE/QAiTcfVlFw+4tc+fMgFd1WghRfXcDECIAtbg9pD4x5JeNvftHntBXlEkyBMGGAl8p0SxCGFW5j6";
	private static final String secretId = "AKIDKpo6me25b14nzcNefQeoqR95syh2ayx97s0g";
	private static final String secretKey = "5a38htm67thU4xyrLvtektm1OP53FjjfMtw3trf8";
	private static final String HMAC_ALGORITHM = "HmacSHA1";
	private static final String CONTENT_CHARSET = "UTF-8";
	private static final String like_url = "http://10.0.0.11:8081/news/likenews";
	private static final String gift_url = "http://10.0.0.11:8081/gift/sendgift";
	private static final String follow_url = "http://10.0.0.11:8081/user/addfollowuser";
//	private static final String gift_url = "http://127.0.0.1:8080/gift/sendgift";
	private static final String useractive_url = "http://10.0.0.11:8081/statistics/useractive";
	private static final String NEWSKEY_NEWSINFO = "_ninfo";
	private static String jsonStr = "{\"ctype\":\"2\",\"requestid\":\"10120190522112152038ACE1BFE64E563F4E\",\"nid\":\"26AA59D107654F66B099F8797CA269E4\",\"cversion\":\"3.5.0.19041601\",\"userid\":\"DA9EBE7F2D464F9D878B2CBA4A08F0BC\",\"token\":\"78C39120786046FE85A82C263A2F316F\"}";
	private static Integer[] gifts = new Integer[] { 10, 11, 15, 2, 4, 1, 14, 5 };
	private static ThreadPoolExecutor threadpool = new ThreadPoolExecutor(10, 100, 1000L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());
	private volatile static int islikecount = 0;
	private volatile static int isgiftcount = 0;
	private volatile static int isfollowcount = 0;
	private volatile static Future<?> islikeexcutetask;
	private volatile static Future<?> giftexcutetask;
	private volatile static Future<?> followxcutetask;
	private volatile static Map<String,Runnable> map=Maps.newConcurrentMap();
	private volatile static Set<String> isuseuserids=Sets.newConcurrentHashSet();//10S内使用过的userid
	
	@Autowired
	private  BotService botService;
//	@Autowired
//	private BotService botService2;
	static {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				Timer t=new Timer();
				t.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						isuseuserids.clear();
					}
				}, 0, 10000);
			}
		};
		threadpool.submit(r);
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				while (true) {
				//每天重置应完成的任务数
				long sleeptime= LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(2, 0, 1)).toInstant(ZoneOffset.of("+8")).toEpochMilli();
				sleeptime = sleeptime - System.currentTimeMillis();
				try {
					Thread.sleep(sleeptime);
					logger.error("重置各个任务数");
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, "cms_bot_task", "islikecount","0");
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, "cms_bot_task", "isgiftcount","0");
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, "cms_bot_task", "isfollowcount","0");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
		};
		threadpool.submit(r2);
		map.put("init", r2);
	}
	
	@PostConstruct
	public void init() {
//		botService=botService2;
		String lcount=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, "cms_bot_task", "islikecount");
		BotUserController.islikecount=(lcount==null)?0:Integer.parseInt(lcount);
		lcount=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, "cms_bot_task", "isgiftcount");
		BotUserController.isgiftcount=(lcount==null)?0:Integer.parseInt(lcount);
		lcount=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, "cms_bot_task", "isfollowcount");
		BotUserController.isfollowcount=(lcount==null)?0:Integer.parseInt(lcount);
//		startjob(0, 5000, 800, 1777);
	}



	@RequestMapping(value = "/startjob", method = RequestMethod.POST)
	@ResponseBody
	public String startjob(int status, final int islikecount, final int isgiftcount,final int isfollowcount) {
		if (status == 0) {
			if (islikeexcutetask != null) {
				islikeexcutetask.cancel(true);
			}
			Runnable r = new Runnable() {
				public void run() {
					long excuteRatetime = getExcuteRate(BotUserController.islikecount,islikecount);
					if (excuteRatetime < 0) {
						return;
					}
					excuteRatetime = task(BotUserController.islikecount,islikecount, excuteRatetime,1);
				}
			};
			try {
				BotUserController.islikeexcutetask= threadpool.submit(r);
				map.put("islikeexcutetask", r);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (giftexcutetask != null) {
				giftexcutetask.cancel(true);
			}
			 r = new Runnable() {
					public void run() {
						long excuteRatetime = getExcuteRate(BotUserController.isgiftcount,isgiftcount);
						if (excuteRatetime < 0) {
							return;
						}
						excuteRatetime = task(BotUserController.isgiftcount,isgiftcount, excuteRatetime,2);
					}
				};
				try {
					BotUserController.giftexcutetask= threadpool.submit(r);
					map.put("giftexcutetask", r);
				} catch (Exception e) {
					e.printStackTrace();
				}

				//关注
				if (followxcutetask != null) {
					followxcutetask.cancel(true);
				}
				 r = new Runnable() {
						public void run() {
							long excuteRatetime = getExcuteRate(BotUserController.isfollowcount,isfollowcount);
							if (excuteRatetime < 0) {
								return;
							}
							excuteRatetime = task(BotUserController.isfollowcount,isfollowcount, excuteRatetime,3);
						}
					};
					try {
						BotUserController.followxcutetask= threadpool.submit(r);
						map.put("followxcutetask", r);
					} catch (Exception e) {
						e.printStackTrace();
					}
		} else if (status == 1) {
			try {
				threadpool.awaitTermination(5*1000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.error("线程池状态："+JSON.toJSONString(threadpool));
//			if (islikeexcutetask != null) {
//				islikeexcutetask.cancel(true);
//			}
//			if(getTaskState(islikeexcutetask)) {
//				threadpool.remove(map.get("islikeexcutetask"));
//			}
//			if (giftexcutetask != null) {
//				giftexcutetask.cancel(true);
//			}
//			if(getTaskState(giftexcutetask)) {
//				threadpool.remove(map.get("giftexcutetask"));
//			}
//			if (followxcutetask != null) {
//				followxcutetask.cancel(true);
//			}
//			if(getTaskState(giftexcutetask)) {
//				threadpool.remove(map.get("followxcutetask"));
//			}
			
		}
		return "success";
	}



	private boolean getTaskState(Future<?> task) {
		if(task==null||task.isCancelled()||task.isDone()) {
			return false;
		}else {
			return true;
		}
	}
	
	private  long task(int finishedcount,final int shouldcount, long excuteRatetime, int tasktype) {
		while (true) {
			try {
				LocalTime res = LocalTime.now();
				if (res.isAfter(LocalTime.of(7, 0, 0))) {
					if (!Thread.currentThread().isInterrupted()) {
						if (finishedcount < shouldcount) {
							Clock c = Clock.systemDefaultZone();
							long t1 = c.millis();
								job(shouldcount,tasktype);
							long t2 = excuteRatetime-(c.millis() - t1);
							if(t2<=0){
								continue;
							}else{
								Thread.sleep(t2);
							}
						} else {
							LocalDate date = LocalDate.now();
							date = date.plusDays(1);
							LocalTime time = LocalTime.of(7, 0, 1);
							excuteRatetime = sleep(finishedcount, shouldcount, tasktype, date, time);
						}

					} else {
						logger.error("结束执行" + "---" + finishedcount+"------"+tasktype);
						break;
					}
				} else {
					if(res.isBefore(LocalTime.of(2, 0, 0))){
						if (!Thread.currentThread().isInterrupted()) {
							if (finishedcount< shouldcount) {
								Clock c = Clock.systemDefaultZone();
								long t1 = c.millis();
									job(shouldcount,tasktype);
									long t2 = excuteRatetime-(c.millis() - t1);
									if(t2<=0){
										continue;
									}else{
										Thread.sleep(t2);
									}
							} else {
								LocalDate date = LocalDate.now();
								LocalTime time = LocalTime.of(7, 0, 1);
								excuteRatetime = sleep(finishedcount, shouldcount, tasktype, date, time);
							}
						} else {
							logger.error("结束执行" + "---" + finishedcount+"------"+tasktype);
							break;
						}
					}else{
						LocalDate date = LocalDate.now();
						LocalTime time = LocalTime.of(7, 0, 1);
						excuteRatetime = sleep(finishedcount, shouldcount, tasktype, date, time);
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.error("结束执行" + "---" + finishedcount+"------"+tasktype);
				break;
			}
		}
		return excuteRatetime;
	}



	private long sleep(int finishedcount, final int shouldcount, int tasktype, LocalDate date, LocalTime time)
			throws InterruptedException {
		long excuteRatetime;
		LocalDateTime beginTime = LocalDateTime.of(date, time);
		long sleeptime = beginTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
		sleeptime = sleeptime - System.currentTimeMillis();
		if(sleeptime<=0){
			Thread.sleep(100);
			sleep(finishedcount, shouldcount, tasktype, date, time);
		}
		float day=sleeptime/(1000*3600);
		logger.error( "---" + finishedcount + "----" + shouldcount+"---睡眠"+sleeptime+"------"+tasktype+"总计"+day+"小时");
		Thread.sleep(sleeptime);
		excuteRatetime = getExcuteRate(finishedcount,shouldcount);
		return excuteRatetime;
	}

	/**
	 * 获取任务执行频率
	 * 
	 * @param islikecount
	 * @return
	 */
	protected static long getExcuteRate( int shouldcount,int finishedcount) {
		long taskcount = finishedcount - shouldcount;
		if (taskcount < 0) {
			return -1;
		} else {
			LocalDate date = LocalDate.now();
			date = date.plusDays(1);
			LocalTime time = LocalTime.of(2, 0, 0);
			LocalDateTime beginTime = LocalDateTime.of(date, time);
			long sleeptime = beginTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
			sleeptime = sleeptime - System.currentTimeMillis();
			long rate = sleeptime / taskcount + 1;
			float day=sleeptime/(1000*3600);
			logger.error("今日可执行" + sleeptime + "毫秒,共执行间隔为" + rate + "毫秒"+"总计"+day+"小时");
			return rate;
		}
	}

	public static void main(String[] args) {
		
//		float day=17998870/(1000*3600);
//		System.out.println(day);
//		System.out.println(Integer.compare(7, 7));
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						System.out.println(RandomUtils.nextLong(0, 65000));
						try {
							Thread.currentThread().sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}


//		LocalDate date = LocalDate.now();
//		date = date.plusDays(1);
//		LocalTime time = LocalTime.of(7, 0, 0);
//		LocalDateTime beginTime = LocalDateTime.of(date, time);
//		long sleeptime = beginTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
//		logger.error(sleeptime - Instant.now().atOffset(ZoneOffset.of("+8")).toInstant().toEpochMilli());
		// 将时间进行格式化
//	    String time1= beginTime.format(dtf);
//	    logger.error(time1);
//		for (int i = 0; i < 5000; i++) {
//			logger.error(gifts[RandomUtils.nextInt(0, gifts.length)]);
//			logger.error(RandomUtils.nextInt(13, 36));;
//		}
//		logger.error(LocalDateTime.of(LocalDate.now(), LocalTime.now().plusHours(-1)).toInstant(ZoneOffset.of("+8")).toEpochMilli());
	}


	protected  void job(int count,int tasktype) {
		if(logger.isDebugEnabled()) {
			logger.debug(tasktype+"-------------");
		}
		Map<String, Object> param = JSON.parseObject(jsonStr);
		String userid = RedisManager.getInstance().srandmember(Consts.REDIS_SCHEME_USER, "bot_user");
		while (userid==null||userid.equals("")||isuseuserids.contains(userid)) {
			userid = RedisManager.getInstance().srandmember(Consts.REDIS_SCHEME_USER, "bot_user");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		switch (tasktype) {
		case 1:
			//点赞
			if (StringUtils.isNotBlank(userid)) {
				if (BotUserController.islikecount < count) {
					try {
						param.put("userid", userid);
						sendHttpRequest(param, useractive_url);
						String nid = getLikeNewsId(userid,tasktype);
						param.put("nid", nid);
						BotLogModel log=new BotLogModel();
						log.setBotuserid(userid);
						log.setLogtime(System.currentTimeMillis());
						log.setLogtype((byte)tasktype);
						log.setNid(nid);
						botService.insertOrUpdateQijiModel(log);
						sendHttpRequest(param, like_url);
						logger.error("start---" + tasktype+"---" + BotUserController.islikecount+"---nid:"+nid);

					} catch (Exception e) {
						e.printStackTrace();
					}
					
					BotUserController.islikecount++;
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, "cms_bot_task", "islikecount",BotUserController.islikecount+"");
				}
			}
				break;
		case 2:
			//送礼物
				if (StringUtils.isNotBlank(userid)) {
					if (BotUserController.isgiftcount < count) {
						param.put("userid", userid);
						sendHttpRequest(param, useractive_url);
						String nid = getLikeNewsId(userid,tasktype);
						int giftid=gifts[RandomUtils.nextInt(0, gifts.length)];
						param.put("giftid", gifts[RandomUtils.nextInt(0, gifts.length)]);
						String creater = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + NEWSKEY_NEWSINFO, "userid");
						param.put("nid",nid);
						param.put("touserid",creater);
						if(giftid==11||giftid==10) {
							String gender=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, creater + "_user", "gender");
							if(StringUtils.isNotBlank(gender)) {
								if(Integer.parseInt(gender)==0) {
									giftid=11;
								}else {
									giftid=10;
								}
							}else {
								giftid=10;
							}
						}
						sendHttpRequest(param, gift_url);
						BotUserController.isgiftcount++;
						RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, "cms_bot_task", "isgiftcount",BotUserController.isgiftcount+"");
						BotLogModel log=new BotLogModel();
						log.setBotuserid(userid);
						log.setLogtime(System.currentTimeMillis());
						log.setLogtype((byte)tasktype);
						log.setNid(nid);
						log.setGiftid(giftid);
						botService.insertOrUpdateQijiModel(log);
						logger.error("start---" + tasktype+"---" + BotUserController.isgiftcount+"---nid:"+nid);
					}
				}
				break;
		case 3:
			//关注
			if (StringUtils.isNotBlank(userid)) {
				if (BotUserController.isfollowcount < count) {
					param.put("userid", userid);
					sendHttpRequest(param, useractive_url);
					String nid = getLikeNewsId(userid,tasktype);
					String creater = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + NEWSKEY_NEWSINFO, "userid");
					param.put("targetid",creater);
					sendHttpRequest(param, follow_url);
					BotUserController.isfollowcount++;
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, "cms_bot_task", "isfollowcount",BotUserController.isfollowcount+"");
					BotLogModel log=new BotLogModel();
					log.setBotuserid(userid);
					log.setLogtime(System.currentTimeMillis());
					log.setLogtype((byte)tasktype);
					log.setNid(nid);
					botService.insertOrUpdateQijiModel(log);
					logger.error("start---" + tasktype+"---" + BotUserController.isfollowcount+"---nid:"+nid);
				}
			}
			break;
		default:
			break;
		}
	}
	public static final String NEWSKEY_MOMENTNEWS = "news_momentnews";
	
	private  String getLikeNewsId(String botuserid,int tasktype) {
		Double min=BigDecimal.valueOf(LocalDateTime.of(LocalDate.now().plusMonths(-1),LocalTime.now()).toInstant(ZoneOffset.of("+8")).toEpochMilli()).doubleValue();
		Double max=BigDecimal.valueOf(LocalDateTime.of(LocalDate.now(),LocalTime.now()).toInstant(ZoneOffset.of("+8")).toEpochMilli()).doubleValue();
		long count=RedisManager.getInstance().zcount(Consts.REDIS_SCHEME_NEWS, NEWSKEY_MOMENTNEWS, min, max);
		long index=RandomUtils.nextLong(0, count);
		Set<String> nids=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, NEWSKEY_MOMENTNEWS, index, index);
		String nid=nids.iterator().next();
		while (!RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid + NEWSKEY_NEWSINFO)) {
			nids=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, NEWSKEY_MOMENTNEWS, index, index);
			nid=nids.iterator().next();
		}
		if(tasktype==1||tasktype==3) {
			Map<String,Object> map=Maps.newHashMap();
			map.put("nid", nid);
			map.put("logtype", tasktype);
			map.put("botuserid", botuserid);
			try {
				BotLogModel model=botService.getModelById(map);
				if(model!=null) {
					return getLikeNewsId(botuserid, tasktype);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}

		return nid;
	}

	private  void sendHttpRequest(Map<String, Object> map, String url) {
		map.remove("sign");
		String content = RSAUtil.getSignContent(map);
		String md5str = MD5.stringToMD5(content);
		try {
			String sign_ = RSAUtil.sign(md5str.getBytes(), app_privateKey);
			map.put("sign", sign_);
			Calendar cd = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			String timeStr = sdf.format(cd.getTime());
			String sig = sign(secretKey, timeStr);
			String authen = "hmac id=\"" + secretId
					+ "\", algorithm=\"hmac-sha1\", headers=\"date source\", signature=\"" + sig + "\"";
			Headers headers = new Headers.Builder().add("Date", timeStr).add("Source", "source")
					.add("Authorization", authen).build();
			OkHttpClientUtil.getInstance().okHttpPostAsync(url, JSON.toJSONString(map), headers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String sign(String secret, String timeStr) throws Exception {
		// get signStr
		String signStr = "date: " + timeStr + "\n" + "source: " + "source";
		// get sig
		String sig = null;
		Mac mac1 = Mac.getInstance(HMAC_ALGORITHM);
		byte[] hash;
		SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(CONTENT_CHARSET), mac1.getAlgorithm());
		mac1.init(secretKey);
		hash = mac1.doFinal(signStr.getBytes(CONTENT_CHARSET));
		sig = new String(Base64Utils.encode(hash));
		return sig;
	}

	
	@RequestMapping(value = "/botuserlist", method = RequestMethod.GET)
	public void boxlist(Model model,int page,
			int limit, int order, String orderConditions)throws Exception {
		if(islikeexcutetask==null||islikeexcutetask.isCancelled()||islikeexcutetask.isDone()) {
			model.addAttribute("islikeexcutetask", false);
		}else {
			model.addAttribute("islikeexcutetask", true);
		}
		if(giftexcutetask==null||giftexcutetask.isCancelled()||giftexcutetask.isDone()) {
			model.addAttribute("giftexcutetask", false);
		}else {
			model.addAttribute("giftexcutetask", true);
		}
		
		if(followxcutetask==null||followxcutetask.isCancelled()||followxcutetask.isDone()) {
			model.addAttribute("followxcutetask", false);
		}else {
			model.addAttribute("followxcutetask", true);
		}
		PageBean<BotLogModel> pageBean = new PageBean<BotLogModel>();
		if (page == 0) {
			page = 1;
		}
		if (limit == 0) {
			limit = 20;
		}
		if (orderConditions == null || orderConditions == "") {
			orderConditions = "";
		}
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int start = (page - 1) * limit;
		int totalCount =0;
		List<BotLogModel> qijiModels = null;
		try {
			qijiModels = botService.getList(start, limit);
			for (BotLogModel botLogModel : qijiModels) {
				botLogModel.headurl=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, botLogModel.botuserid + "_user", "headurl");
				botLogModel.nickname=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, botLogModel.botuserid + "_user", "nickname");
			}
			totalCount = botService.getCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);

		ArrayList<Integer> limitList = new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("openqijilists", qijiModels);
		model.addAttribute("pageBean", pageBean);

		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
		model.addAttribute("islikecount",islikecount);
		model.addAttribute("isgiftcount",isgiftcount);
		model.addAttribute("isfollowcount",isfollowcount);
	}
}
