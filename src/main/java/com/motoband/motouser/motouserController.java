package com.motoband.motouser;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.credit.CreditLogModel;
import com.motoband.credit.CreditService;
import com.motoband.news.BannerModel;
import com.motoband.news.CommentModel;
import com.motoband.news.newsService;
import com.motoband.util.CollectionUtil;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.ESManager;
import com.motoband.util.ExcelUtils;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;
import com.motoband.util.RedisManager;
import com.motoband.util.Utilities.MD5;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.gen.v3_0_1.api.YouzanCrmCustomerPointsDecrease;
import com.youzan.open.sdk.gen.v3_0_1.api.YouzanCrmCustomerPointsIncrease;
import com.youzan.open.sdk.gen.v3_0_1.model.YouzanCrmCustomerPointsDecreaseParams;
import com.youzan.open.sdk.gen.v3_0_1.model.YouzanCrmCustomerPointsDecreaseResult;
import com.youzan.open.sdk.gen.v3_0_1.model.YouzanCrmCustomerPointsIncreaseParams;
import com.youzan.open.sdk.gen.v3_0_1.model.YouzanCrmCustomerPointsIncreaseResult;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
@RequestMapping(value = "/motouser")
public class motouserController {
	@Autowired
	private motouserService motouserService;
	@Autowired
	private newsService newsService;
	@Autowired
	private CreditService creditService;
	@Autowired
	private boxService boxService;
	@Autowired
	private MBMessageManager mBMessageManager;

	public static Logger logger = LoggerFactory.getLogger(motouserController.class);

	private static final String USERKEY_BLACK = "BlackList";
	public static final String USERKEY_USER = "_user";
	public static final String USERKEY_USERMAP = "_usermap";// 商家用户map
	public static final String USER_CREDITLOGMAP = "_creditlogmap";
	public static final String USER_CREDITLOGLIST = "_creditloglist";
	public static final String USER_MEDIAUSER = "user_mediauser";// 媒体号用户

	public static final String MAPKEY_NICKNAME = "nickname";
	public static final String MAPKEY_ADDTIME = "addtime";
	public static final String MAPKEY_FRIENDRECOMMENDSTATE = "friendrecommendstate";
	public static final String MAPKEY_COMMENTSWITCH = "commentswitch";
	public static final String MAPKEY_LIKESWITCH = "likeswitch";
	public static final String MAPKEY_IMSWITCH = "imswitch";
	public static final String MAPKEY_SYSTEMSWITCH = "systemswitch";
	public static final String MAPKEY_FOLLOWSWITCH = "followswitch";
	public static final String MAPKEY_RIDESHARESWITCH = "rideshareswitch";
	public static final String MAPKEY_NEARUSERSWITCH = "nearuserswitch";
	public static final String MAPKEY_PASSWORD = "password";
	public static final String MAPKEY_MOBILENO = "mobileno";
	public static final String MAPKEY_LASTPUSHNEWSTIME = "lastpushnewstime";
	public static final String MAPKEY_NATIONCODE = "nationcode";
	public static final String MAPKEY_COOKIE_KEY = "cookie_key";
	public static final String MAPKEY_COOKIE_VALUE = "cookie_value";
	public static final String MAPKEY_ACCESS_TOKEN = "access_token";
	public static final String MAPKEY_YZ_TIME = "yz_time";
	public static final String MAPKEY_USERID = "userid";
	public static final String MAPKEY_GENDER = "gender";
	public static final String MAPKEY_BIRTH = "birth";
	public static final String MAPKEY_AREA = "area";
	public static final String MAPKEY_CITY = "city";
	public static final String MAPKEY_PROVINCE = "province";
	public static final String MAPKEY_HEADURL = "headurl";
	public static final String MAPKEY_MBID = "mbid";
	public static final String MAPKEY_SIGNATURE = "signature";
	public static final String MAPKEY_CHANNEL = "channel";
	public static final String MAPKEY_LEVEL = "level";
	public static final String MAPKEY_FOLLOWCOUNT = "followcount";
	public static final String MAPKEY_FOLLOWEDCOUNT = "followedcount";
	public static final String MAPKEY_NEWSCOUNT = "newscount";
	public static final String MAPKEY_FRIENDCOUNT = "friendcount";
	public static final String MAPKEY_UPDATETIME = "updatetime";
	public static final String MAPKEY_RECOMMENDCOUNT = "recommendcount";
	public static final String MAPKEY_STORYRECOMMENDCOUNT = "storyrecommendcount";
	public static final String MAPKEY_USERTYPE = "usertype";
	public static final String MAPKEY_WATERMARKSWTICH = "watermarkswtich";
	public static final String MAPKEY_CREDIT = "credit";
	public static final String MAPKEY_PKSWITCH = "pkswitch";
	public static final String MAPKEY_WEIXINID = "weixinid";
	public static final String MAPKEY_WEIXINMODEL = "weixinmodel";
	public static final String MAPKEY_WEIXINMODELSTR = "weixinmodelstr";
	public static final String MAPKEY_QQID = "qqid";
	public static final String MAPKEY_QQMODEL = "qqmodel";
	public static final String MAPKEY_QQMODELSTR = "qqmodelstr";
	public static final String MAPKEY_WEIBOID = "weiboid";
	public static final String MAPKEY_WEIBOMODEL = "weibomodel";
	public static final String MAPKEY_WEIBOMODELSTR = "weibomodelstr";
	public static final String MAPKEY_WINCOUNT = "wincount";
	public static final String MAPKEY_DEFEATCOUNT = "defeatcount";
	public static final String MAPKEY_DOGFALLCOUNT = "dogfallcount";
	public static final String MAPKEY_VIPTYPE = "viptype";
	public static final String MAPKEY_USERPRIVILEGELONG = "userprivilegelong";
	public static final String MAPKEY_APPROVEDES = "approvedes";

	// 2.6
	public static final String RUNKEY_OPENSCREENMAP = "_openscreen_map";// 开屏图map
	public static final String RUNKEY_OPENSCREENLIST = "openscreen_list";// 开屏图list

	public static final byte CreditActionType_Add = 0;// 增加
	public static final byte CreditActionType_Subtract = 1;// 减少

	public static final int CreditType_PubNews = 0;
	public static final int CreditType_PubStory = 1;
	public static final int CreditType_PubComment = 3;
	public static final int CreditType_PubLike = 4;

	public static final int CreditType_ReceiveComment = 5;
	public static final int CreditType_ReceiveLike = 6;

	public static final int CreditType_Logon1 = 7;
	public static final int CreditType_Logon2 = 8;
	public static final int CreditType_Logon3 = 9;
	public static final int CreditType_Logon4 = 10;
	public static final int CreditType_Logon5 = 11;
	public static final int CreditType_Logon6 = 12;
	public static final int CreditType_Logon7 = 13;
	// public static final int CreditType_LogonMoreThan7 = 14;

	public static final int CreditType_NewsRecommend = 15;
	public static final int CreditType_StoryRecommend = 16;
	public static final int CreditType_NewsRecommendMain = 17;
	public static final int CreditType_StoryRecommendMain = 18;

	public static final int CreditType_ShareContent = 19;
	public static final int CreditType_ShareAppStore = 20;

	// 得勋章 升级 骑行都无上限
	public static final int CreditType_GetAchievement = 21;
	public static final int CreditType_Ride = 22;
	public static final int CreditType_LevelUp = 23;

	public static final int CreditType_CompleteUserProfile = 24;

	public static final int CreditType_BusinessComment = 25;

	// 官方运营增加积分
	public static final int CreditType_Operation = 30;

	public static final int CreditType_PubSendCar = 40;// 发布二手车专用（个人扣积分）

	public static final int CreditType_DelNews = 100;
	public static final int CreditType_DelStory = 101;
	public static final int CreditType_SendGift = 102;
	public static final int CreditType_DiscussPublish = 103;

	public static final int CreditType_BreakNews = 200;
	public static final int CreditType_BreakStory = 201;
	public static final int CreditType_BreakComment = 202;

	// 用户各种权限类型判断
	public static final byte UserAllPrivilege_Add = 0;// 增加
	public static final byte UserAllPrivilege_Subtract = 1;// 减少

	public static final int UserAllPrivilege_VoiceVip = 1;
	public static final int UserAllPrivilege_SilverVip = 2;
	public static final int UserAllPrivilege_GoldVip = 3;
	public static final int UserAllPrivilege_DiamondVip = 4;
	public static final int UserAllPrivilege_BlueVip = 5;
	public static final int UserAllPrivilege_YellowVip = 6;
	public static final int UserAllPrivilege_GreenVip = 7;
	public static final int UserAllPrivilege_OldDriver = 8;

	public static final String MAPKEY_VOICEVIP = "voicevip";
	public static final String MAPKEY_SILVERVIP = "silvervip";
	public static final String MAPKEY_GOLDVIP = "goldvip";
	public static final String MAPKEY_DIAMONDVIP = "diamondvip";

	public static final MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");

	private static OkHttpClient httpClient = new OkHttpClient();
	/**
	 * 已完成的任务数据
	 */
	public static Map<String, BlockingQueue<Map<String, Object>>> taskMapQueue = Maps.newConcurrentMap();
//	/**
//	 * 未完成的推送用户
//	 */
//	public static BlockingQueue<Map<String, Object>> unfinishUserTaskQueue = Queues.newArrayBlockingQueue(10000);

	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(20, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

	@PostConstruct
	public void init() {
		executor.allowCoreThreadTimeOut(true);
		executor.execute(new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
//					if(ConfigManager.ServiceName.equalsIgnoreCase("MotoBandService")) {
//						Thread.currentThread().getThreadGroup().list();
//					}
					logger.error("motouser----executor status:"+executor.getActiveCount()+"-----------"+executor.getPoolSize());
					try {
						Thread.sleep(10*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}));

		List<MessageTaskModel> taskModel = motouserService.getUnFinishTask();
		for (MessageTaskModel messageTaskModel : taskModel) {
			if(messageTaskModel.starttime!=0) {
				againTask(messageTaskModel);
			}
		}
	}

	private void againTask(MessageTaskModel messageTaskModel) {
		if (messageTaskModel!=null&&messageTaskModel.state == 0) {
			// 执行任务数是否完成但是没有更改状态
			if (motouserService.checkTask(messageTaskModel.taskid)) {
				TaskFinshe(messageTaskModel.taskid);
			}else {
				executor.submit(new Runnable() {
					@Override
					public void run() {
						try {
							List<String> userids = motouserService.getUnFinishTaskUsers(messageTaskModel.taskid);
							MBMessageModel model = gettaskMessageModel(messageTaskModel.taskid);
							String pushMsg = "您有一条新的消息，点击查看";
							if (logger.isErrorEnabled()) {
								logger.error("开始补救task----" + messageTaskModel.taskid);
							}
							long currenttime=System.currentTimeMillis();
							long sleeptime=1;
							if(currenttime<messageTaskModel.starttime) {
								sleeptime=messageTaskModel.starttime-currenttime;
							}
							try {
								Thread.currentThread().sleep(sleeptime);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							batchSendCMSMessage(messageTaskModel.taskid, model, pushMsg, 0, userids);
							TaskFinshe(messageTaskModel.taskid);
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error(ExceptionUtils.getStackTrace(e));

							}
						}

					}
				});
			}

		}
	}
	
	private void againTaskAll(MessageTaskModel messageTaskModel) {
		executor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					List<String> userids = motouserService.getTaskUsers(messageTaskModel.taskid);
					MBMessageModel model = gettaskMessageModel(messageTaskModel.taskid);
					String pushMsg = "您有一条新的消息，点击查看";
					if (logger.isErrorEnabled()) {
						logger.error("开始补救task----" + messageTaskModel.taskid);
					}
					batchSendCMSMessage(messageTaskModel.taskid, model, pushMsg, 0, userids);
					TaskFinshe(messageTaskModel.taskid);
				} catch (Exception e) {
					if (logger.isErrorEnabled()) {
						logger.error(ExceptionUtils.getStackTrace(e));

					}
				}

			}
		});
	}

	@RequestMapping(value = "/motouserlist", method = RequestMethod.GET)
	public void userlist(Model model, HttpSession session, HttpServletRequest request, String userGuid,
			String motouserid) {

		model.addAttribute("motouserid", motouserid);

		int updateCheck = motouserService.getMotoUserUpdateCheck(userGuid);
		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
	}

	@RequestMapping(value = "/searchUserRideLine", method = RequestMethod.POST)
	public void searchUserRideLine(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) {
		List<ridelineModel> ridelines = motouserService.searchUserRideLine(userid);
		out.print(JSONArray.toJSONString(ridelines));
	}

	@RequestMapping(value = "/searchUserRideDateLog", method = RequestMethod.POST)
	public void searchUserRideDateLog(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) {
		List<ridedatelog> ridedatelogs = motouserService.searchUserRideDateLog(userid);
		out.print(JSONArray.toJSONString(ridedatelogs));
	}

	@RequestMapping(value = "/searchUserGarage", method = RequestMethod.POST)
	public void searchUserGarage(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) {
		List<garageModel> garageModels = motouserService.searchUserGarage(userid);
		out.print(JSONArray.toJSONString(garageModels));
	}

	@RequestMapping(value = "/addblack", method = RequestMethod.POST)
	public void addblack(Model model, HttpSession session, HttpServletRequest request, String userid, PrintWriter out) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("userid", "86-13810008710");
		Map<String, Object> dataMap = Maps.newHashMap();
		dataMap.put("targetid", userid);
		dataMap.put("informtype", 3);
		map.put("data", JSON.toJSONString(dataMap));
		map.put("token", "cmstoken");
		map.put("requestid", "cmsrequestid");
		map.put("cversion", "cmsverision");
		map.put("ctype", "4");
		MbUtil.handleReqAndSendReq("/operate/inform", JSON.toJSONString(map));
//		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, USERKEY_BLACK)) {
//			if (RedisManager.getInstance().lexist(Consts.REDIS_SCHEME_USER, USERKEY_BLACK, userid)) {
//
//			} else {
//				RedisManager.getInstance().rpush(Consts.REDIS_SCHEME_USER, USERKEY_BLACK, userid);
//			}
//		} else {
//			RedisManager.getInstance().rpush(Consts.REDIS_SCHEME_USER, USERKEY_BLACK, userid);
//		}
//		OkHttpClientUtil.getInstance().okHttpPost(url, reqJsonContent)
//		Random random = new Random();
//		String urlString = String.format(Consts.TIM_UserBlackListBanUrl, Consts.TIM_AdminSig, Consts.TIM_AdminUserID, Consts.TIM_Appid,random.nextInt(100000000));
//		
//		JSONObject reqObject = new JSONObject();
//		reqObject.put("Set_Account", userid);
//		reqObject.put("C2CmsgNospeakingTime", 4294967295l);
//		reqObject.put("GroupmsgNospeakingTime", 4294967295l);
//		try {
//			String data = JSON.toJSONString(reqObject);
//			RequestBody body = RequestBody.create(MediaType_JSON, data);
//			Request req = new Request.Builder().url(urlString).post(body).build();
//			Response resp=httpClient.newCall(req).execute();
//			String result =new String(resp.body().bytes(), "utf-8");
//		} catch (IOException e) {
//			if (logger.isErrorEnabled()) {logger.error(ExceptionUtils.getStackTrace(e));}
//		}

		out.print("success");

	}

	@RequestMapping(value = "/searchByusernickname", method = RequestMethod.POST)
	public void searchByusernickname(Model model, HttpSession session, HttpServletRequest request, String usernickname,
			PrintWriter out) {
		List<MBUserModel> userModels = motouserService.searchByusernickname(usernickname);
		out.print(JSONArray.toJSONString(userModels));
	}

	@RequestMapping(value = "/searchByusermobileno", method = RequestMethod.POST)
	public void searchByusermobileno(Model model, HttpSession session, HttpServletRequest request, String mobileno,
			PrintWriter out) {
		MBUserModel userModel = motouserService.searchByusermobileno(mobileno);
		out.print(JSONObject.toJSONString(userModel));
	}

	@RequestMapping(value = "/addUserRecommend", method = RequestMethod.POST)
	public void addUserRecommend(Model model, HttpSession session, HttpServletRequest request, String score,
			String approvedes, String uid, PrintWriter out) throws Exception {
		if (approvedes == null) {
			approvedes = "";
		}
		motouserService.updateUsertypeAndapprovedes(2, approvedes, uid);

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, uid + "_user")) {

			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "usertype", "2");
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "approvedes", approvedes);
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_USER, "user_recommend", Long.valueOf(score), uid);
		}
		Set<String> listSet = null;
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, "user_olddriver")) {
			long listcount = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, "user_olddriver");
			if (listcount > 0) {
				listSet = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_USER, "user_olddriver", 0,
						listcount);
				for (String s : listSet) {
					if (s.equals(uid)) {
						RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, "user_olddriver", uid);
					}
				}

			}
		}

		JSONObject obj = new JSONObject();
		obj.put("type", 1);// 0 动态 1 达人 2 认证
		obj.put("targetid", uid);

//		String urlString = Consts.ServiceUrl + "data/sendcmsmessage";
//		logger.info("addUserRecommend  request data:[url:"+urlString+"  param:"+data+"]");
//		RequestBody body = RequestBody.create(MediaType_JSON, data);
//		Request req = new Request.Builder().url(urlString).post(body).build();
//		Response resp=httpClient.newCall(req).execute();
//		String result =new String(resp.body().bytes(), "utf-8");
//		logger.info("addUserRecommend  response data:[result:"+result+"]");

		obj.put("userid", Consts.TIM_AdminUserID);
		obj.put("token", "token");
		obj.put("requestid", "12345678");
		obj.put("cversion", "1");
		obj.put("ctype", "1");
		String data = JSON.toJSONString(obj);
		String responstr = MbUtil.handleReqAndSendReq("/cms/sendmessage", data);
		String resultstr = MbUtil.handleResp(responstr);
		String str = "1";
		if (StringUtils.isNotBlank(resultstr)) {
			JSONObject json = JSON.parseObject(resultstr);
			str = (String) json.get("code");
		}
		if (Integer.parseInt(str) == 0) {
			// 3.1.0
			changePrivilegelongForUserType(uid, 1);

			out.write("success");
		} else {
			out.write("fail");
		}

	}

	@RequestMapping(value = "/addUserOperation", method = RequestMethod.POST)
	public void addUserOperation(Model model, HttpSession session, HttpServletRequest request, String uid,
			String approvedes, PrintWriter out) throws Exception {
		if (approvedes == null) {
			approvedes = "";
		}
		motouserService.updateUsertypeAndapprovedes(1, approvedes, uid);

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, uid + "_user")) {
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "usertype", "1");
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "approvedes", approvedes);
		}

		Set<String> listSet = null;
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, "user_recommend")) {
			long listcount = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, "user_recommend");
			if (listcount > 0) {
				listSet = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_USER, "user_recommend", 0,
						listcount);
				for (String s : listSet) {
					if (s.equals(uid)) {
						RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, "user_recommend", uid);
					}
				}

			}
		}
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, "user_olddriver")) {
			long listcount = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, "user_olddriver");
			if (listcount > 0) {
				listSet = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_USER, "user_olddriver", 0,
						listcount);
				for (String s : listSet) {
					if (s.equals(uid)) {
						RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, "user_olddriver", uid);
					}
				}

			}
		}

		// 3.1.0
		changePrivilegelongForUserType(uid, 2);

		out.write("success");

	}

	@RequestMapping(value = "/addUserOlddriver", method = RequestMethod.POST)
	public void addUserOlddriver(Model model, HttpSession session, HttpServletRequest request, String score, String uid,
			String approvedes, PrintWriter out) throws Exception {
		if (approvedes == null) {
			approvedes = "";
		}
		motouserService.updateUsertypeAndapprovedes(4, approvedes, uid);

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, uid + "_user")) {
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "usertype", "4");
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "approvedes", approvedes);
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_USER, "user_olddriver", Long.valueOf(score), uid);
		}

		Set<String> listSet = null;

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, "user_recommend")) {
			long listcount = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, "user_recommend");
			if (listcount > 0) {
				listSet = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_USER, "user_recommend", 0,
						listcount);
				for (String s : listSet) {
					if (s.equals(uid)) {
						RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, "user_recommend", uid);
					}
				}

			}
		}

		// 3.1.0
		changePrivilegelongForUserType(uid, 5);

		out.write("success");

	}

	@RequestMapping(value = "/addUserOrdinary", method = RequestMethod.POST)
	public void addUserOrdinary(Model model, HttpSession session, HttpServletRequest request, String uid,
			String approvedes, PrintWriter out) throws Exception {
		if (approvedes == null) {
			approvedes = "";
		}
		motouserService.updateUsertypeAndapprovedes(0, approvedes, uid);

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, uid + "_user")) {
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "usertype", "0");
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "approvedes", approvedes);
		}

		Set<String> listSet = null;

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, "user_recommend")) {
			long listcount = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, "user_recommend");
			if (listcount > 0) {
				listSet = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_USER, "user_recommend", 0,
						listcount);
				for (String s : listSet) {
					if (s.equals(uid)) {
						RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, "user_recommend", uid);
					}
				}

			}
		}
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, "user_olddriver")) {
			long listcount = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, "user_olddriver");
			if (listcount > 0) {
				listSet = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_USER, "user_olddriver", 0,
						listcount);
				for (String s : listSet) {
					if (s.equals(uid)) {
						RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, "user_olddriver", uid);
					}
				}

			}
		}

		// 3.1.0
		changePrivilegelongForUserType(uid, 4);

		out.write("success");

	}

	@RequestMapping(value = "/addUserBoxApprove", method = RequestMethod.POST)
	public void addUserBoxApprove(Model model, HttpSession session, HttpServletRequest request, String approvedes,
			String media, String uid, PrintWriter out) throws Exception {
		motouserService.addUserBoxApprove(uid, media);
		if (approvedes == null) {
			approvedes = "";
		}
		motouserService.updateUsertypeAndapprovedes(3, approvedes, uid);

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, uid + "_user")) {
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "usertype", "3");
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "approvedes", approvedes);
		}

		Set<String> listSet = null;

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, "user_recommend")) {
			long listcount = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, "user_recommend");
			if (listcount > 0) {
				listSet = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_USER, "user_recommend", 0,
						listcount);
				for (String s : listSet) {
					if (s.equals(uid)) {
						RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, "user_recommend", uid);
					}
				}

			}
		}
		// 添加到媒体号池
		// RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_USER, USER_MEDIAUSER,
		// System.currentTimeMillis(), uid);

		JSONObject obj = new JSONObject();
		obj.put("type", 2);// 0 动态 1 达人 2 认证
		obj.put("targetid", uid);
//		obj.put("userid", Consts.TIM_AdminUserID);
//		obj.put("sign", Consts.TIM_AdminSig);
//		String data = JSON.toJSONString(obj);
//		String urlString = Consts.ServiceUrl + "data/sendcmsmessage";
//		logger.info("addUserBoxApprove  request data:[url:"+urlString+"  param:"+data+"]");
//		
//		RequestBody body = RequestBody.create(MediaType_JSON, data);
//		Request req = new Request.Builder().url(urlString).post(body).build();
//		Response resp=httpClient.newCall(req).execute();
//		String result =new String(resp.body().bytes(), "utf-8");
//		logger.info("addUserBoxApprove response data:[result:"+result+"]");
//		JSONObject json = JSON.parseObject(result);
//		String str =(String) json.get("code");

		obj.put("userid", Consts.TIM_AdminUserID);
		obj.put("token", "token");
		obj.put("requestid", "12345678");
		obj.put("cversion", "1");
		obj.put("ctype", "1");
		String data = JSON.toJSONString(obj);
		String responstr = MbUtil.handleReqAndSendReq("/cms/sendmessage", data);
		String resultstr = MbUtil.handleResp(responstr);
		String str = "1";
		if (StringUtils.isNotBlank(resultstr)) {
			JSONObject json = JSON.parseObject(resultstr);
			str = (String) json.get("code");
		}
		if (Integer.parseInt(str) == 0) {

			// 3.1.0
			changePrivilegelongForUserType(uid, 3);

			out.write("success");
		} else {
			out.write("fail");
		}

	}

	@RequestMapping(value = "/updateApprovedes", method = RequestMethod.POST)
	public void updateApprovedes(Model model, HttpSession session, HttpServletRequest request, String approvedes,
			String uid, PrintWriter out) {
		if (approvedes == null) {
			approvedes = "";
		}
		motouserService.updateUsertypeAndapprovedes(null, approvedes, uid);

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, uid + "_user")) {
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, uid + "_user", "approvedes", approvedes);
		}

		out.write("success");

	}

	@RequestMapping(value = "/b", method = RequestMethod.GET)
	public void b(Model model, HttpSession session, HttpServletRequest request, String userGuid, String motouserid) {
		int updateCheck = motouserService.getUserUpdateCheck(userGuid);

		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		int insCheck = motouserService.getUserInsCheck(userGuid);
		if (insCheck > 0) {
			model.addAttribute("delCheck", "lock");
		} else {
			model.addAttribute("delCheck", "unlock");
		}

		long count = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, "user_recommend");
		Set<String> userSet = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_USER, "user_recommend", 0,
				count);
		List<MBUserModel> userModels = new ArrayList<MBUserModel>();
		for (String uid : userSet) {
			MBUserModel mbUserModel = MBUserModel
					.convertToModel(RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER, uid + "_user"));
			if (mbUserModel != null) {
				double d = RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_USER, "user_recommend", uid);
				mbUserModel.setScore(Math.round(d));
				userModels.add(mbUserModel);
			}

		}
		model.addAttribute("userModels", userModels);
	}

	@RequestMapping(value = "/userolddriver", method = RequestMethod.GET)
	public void userolddriver(Model model, HttpSession session, HttpServletRequest request, String userGuid,
			String motouserid) {
		int updateCheck = motouserService.getUserUpdateCheck(userGuid);

		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		int insCheck = motouserService.getUserInsCheck(userGuid);
		if (insCheck > 0) {
			model.addAttribute("delCheck", "lock");
		} else {
			model.addAttribute("delCheck", "unlock");
		}

		long count = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, "user_olddriver");
		Set<String> userSet = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_USER, "user_olddriver", 0,
				count);
		List<MBUserModel> userModels = new ArrayList<MBUserModel>();
		for (String uid : userSet) {
			MBUserModel mbUserModel = MBUserModel
					.convertToModel(RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER, uid + "_user"));
			if (mbUserModel != null) {
				double d = RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_USER, "user_olddriver", uid);
				mbUserModel.setScore(Math.round(d));
				userModels.add(mbUserModel);
			}

		}
		model.addAttribute("userModels", userModels);
	}

	@RequestMapping(value = "/useroperation", method = RequestMethod.GET)
	public void useroperation(Model model, HttpSession session, HttpServletRequest request, String userGuid,
			String motouserid) {
		int updateCheck = motouserService.getUserUpdateCheck(userGuid);

		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		int insCheck = motouserService.getUserInsCheck(userGuid);
		if (insCheck > 0) {
			model.addAttribute("delCheck", "lock");
		} else {
			model.addAttribute("delCheck", "unlock");
		}

		List<MBUserModel> userModels = motouserService.getUserByUserType(1);

		model.addAttribute("userModels", userModels);
	}

	@RequestMapping(value = "/userboxapprove", method = RequestMethod.GET)
	public void userboxapprove(Model model, HttpSession session, HttpServletRequest request, String userGuid,
			String motouserid) {
		int updateCheck = motouserService.getUserUpdateCheck(userGuid);

		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		int insCheck = motouserService.getUserInsCheck(userGuid);
		if (insCheck > 0) {
			model.addAttribute("delCheck", "lock");
		} else {
			model.addAttribute("delCheck", "unlock");
		}

		List<MBUserModel> userModels = motouserService.getUserByUserType(3);

		model.addAttribute("userModels", userModels);
	}

	@RequestMapping(value = "/userrecommend", method = RequestMethod.GET)
	public void userrecommend(Model model, HttpSession session, HttpServletRequest request, String userGuid,
			PrintWriter out) {

	}

//	@RequestMapping(value = "/publishUserRecommend", method = RequestMethod.POST)
//	public void publishUserRecommend(Model model, HttpSession session, HttpServletRequest request, PrintWriter out) throws UnsupportedEncodingException {
//
//		Set<String> set = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_USER, "user_temprecommend", 0, RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, "news_temprecommendnews"));
//		RedisManager.getInstance().delbykey(Consts.REDIS_SCHEME_USER, "user_recommend");
//		for (String nid : set) {
//			double score = RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_USER, "user_temprecommend", nid);
//			Long lscore = new Double(score).longValue();
//			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_USER, "user_recommend", lscore, nid);
//		}
//		out.print("success");
//
//	}

	@RequestMapping(value = "/searchUserAddtime", method = RequestMethod.POST)
	public void searchUserAddtime(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) throws UnsupportedEncodingException {
		long addtime = motouserService.searchUserAddtime(userid);
		out.print(addtime);
	}

	@RequestMapping(value = "/getachievement", method = RequestMethod.POST)
	public void getachievement(Model model, HttpSession session, HttpServletRequest request, String achid,
			PrintWriter out) throws UnsupportedEncodingException {
		AchievementModel achievementModel = motouserService.getachievement(achid);
		String jsonString = "";
		if (achievementModel != null) {
			jsonString = JSONObject.toJSONString(achievementModel);
		}
		out.print(jsonString);
	}

	@RequestMapping(value = "/lookBlacklist", method = RequestMethod.POST)
	public void lookBlacklist(Model model, HttpSession session, HttpServletRequest request, PrintWriter out) {
		List<String> templist = RedisManager.getInstance().lgetList(Consts.REDIS_SCHEME_USER, USERKEY_BLACK);
		String jsonString = null;
		if (templist != null && templist.size() > 0) {
			jsonString = JSONObject.toJSONString(templist);
		}
		out.print(jsonString);

	}

	@RequestMapping(value = "/blackUserRemove", method = RequestMethod.POST)
	public void blackUserRemove(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) {
		if (RedisManager.getInstance().lexist(Consts.REDIS_SCHEME_USER, USERKEY_BLACK, userid)) {
			RedisManager.getInstance().lrem(Consts.REDIS_SCHEME_USER, USERKEY_BLACK, userid);

			Random random = new Random();
			String urlString = String.format(Consts.TIM_UserBlackListBanUrl, Consts.TIM_AdminSig,
					Consts.TIM_AdminUserID, Consts.TIM_Appid, random.nextInt(100000000));

			JSONObject reqObject = new JSONObject();
			reqObject.put("Set_Account", userid);
			reqObject.put("C2CmsgNospeakingTime", 0);
			reqObject.put("GroupmsgNospeakingTime", 0);
			try {
				String data = JSON.toJSONString(reqObject);
				RequestBody body = RequestBody.create(MediaType_JSON, data);
				Request req = new Request.Builder().url(urlString).post(body).build();
				Response resp = httpClient.newCall(req).execute();
				String result = new String(resp.body().bytes(), "utf-8");
			} catch (IOException e) {
				if (logger.isErrorEnabled()) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}
			}

			out.print("success");
		}

	}

	@RequestMapping(value = "/getuserprofile", method = RequestMethod.POST)
	public void getuserprofile(Model model, HttpSession session, HttpServletRequest request, String uid,
			PrintWriter out) {
		MBUserModel mbuser = null;
		long newscount = 0;
		long friendcount = 0;
		long followcount = 0;
		long followedcount = 0;
		long recommendcount = 0;
		String jsonString = null;
		if (uid != null && uid != "") {
			mbuser = motouserService.getUserByID(uid);
			Map<String, String> map = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER, uid + "_user");
			if (map != null) {

				// long createruncount=0;
				// long createverifycount=0;
				// long signruncount=0;
				// long verifycount=0;
				String recommendcountStr = map.get("recommendcount");
				String followcountStr = map.get("followcount");
				String followedcountStr = map.get("followedcount");
				String newscountStr = map.get("newscount");
				String friendcountStr = map.get("friendcount");

				if (newscountStr != null && newscountStr != "") {
					newscount = Long.parseLong(newscountStr);
				}
				if (friendcountStr != null && friendcountStr != "") {
					friendcount = Long.parseLong(friendcountStr);
				}
				if (recommendcountStr != null && recommendcountStr != "") {
					recommendcount = Long.parseLong(recommendcountStr);
				}
				if (followcountStr != null && followcountStr != "") {
					followcount = Long.parseLong(followcountStr);
				}
				if (followedcountStr != null && followedcountStr != "") {
					followedcount = Long.parseLong(followedcountStr);
				}
			}
			if (mbuser != null) {
				mbuser.setNewscount(newscount);
				mbuser.setFriendcount(friendcount);
				mbuser.setFollowcount(followcount);
				mbuser.setFollowedcount(followedcount);
				mbuser.setRecommendcount(recommendcount);
				StringBuffer sb = new StringBuffer();
				if (mbuser.getUserprivilegelong() > 0) {
					String oldlongstr = Long.toBinaryString(mbuser.getUserprivilegelong());
					if (oldlongstr.length() < 8) {
						for (int i = 0; i < 8 - oldlongstr.length(); i++) {
							sb.append("0");
						}
						sb.append(oldlongstr);
					} else {
						sb.append(oldlongstr);
					}
				} else {
					for (int i = 0; i < 8; i++) {
						sb.append("0");
					}
				}

				mbuser.setUserprivilegelongstr(sb.toString());
				jsonString = JSONObject.toJSONString(mbuser);
			}
		}
		out.print(jsonString);

	}

	@RequestMapping(value = "/getuserachievement", method = RequestMethod.POST)
	public void getuserachievement(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) {
		List<UserAchievementModel> ualist = null;
		String jsonString = null;
		if (userid != null && userid != "") {
			ualist = motouserService.getuserachievement(userid);

			if (ualist != null && ualist.size() > 0) {
				jsonString = JSONObject.toJSONString(ualist);
			}
		}
		out.print(jsonString);

	}

	@RequestMapping(value = "/deleteUserAllNews", method = RequestMethod.POST)
	public void deleteUserAllNews(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) throws IOException {
		Set<String> unlistSet = null;
		Set<String> anlistSet = null;
		Set<String> followAnlistSet = null;
		if (userid != null && !"".equals(userid)) {
			if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, userid + "_unlist")) {
				long count = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, userid + "_unlist");
				if (count > 0) {
					unlistSet = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, userid + "_unlist",
							0, count);

					/*
					 * if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS,userid+
					 * "_anlist")){ long anlistcount =
					 * RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS,userid+"_anlist");
					 * if(anlistcount>0){ anlistSet=
					 * RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS,userid+
					 * "_anlist", 0, anlistcount); if(anlistSet.retainAll(unlistSet)){ for(String
					 * anls:anlistSet ){
					 * RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS,userid+"_anlist",
					 * anls);
					 * 
					 * } } } }
					 * 
					 * if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_FOLLOW,userid+
					 * "_followed")){ long followlistcount
					 * =RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_FOLLOW,userid+
					 * "_followed"); if(followlistcount>0){
					 * followAnlistSet=RedisManager.getInstance().zrangebyindex(Consts.
					 * REDIS_SCHEME_FOLLOW,userid+"_followed", 0, followlistcount); for(String
					 * s:followAnlistSet){
					 * if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS,
					 * s+"_anlist")){ long anlistcount =
					 * RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS,s+"_anlist");
					 * if(anlistcount>0){ anlistSet=
					 * RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS,s+
					 * "_anlist", 0, anlistcount); if(anlistSet.retainAll(unlistSet)){ for(String
					 * anls:anlistSet ){
					 * RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS,s+"_anlist", anls);
					 * } } } } } } }
					 */

					for (String uls : unlistSet) {
						// RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS,userid+"_unlist",
						// uls);
						JSONObject obj = new JSONObject();
						obj.put("actiontype", 1);// 0 普通用户 1 管理员
						obj.put("userid", userid);
						obj.put("nid", uls);
//							obj.put("sign", Consts.TIM_AdminSig);
//							obj.put("nid",uls);
//							String data = JSON.toJSONString(obj);
//							String urlString = Consts.ServiceUrl + "news/delnews";
//							logger.info("deleteUserAllNews request data:[url:"+urlString+"  param:"+data+"]");
//							
//							RequestBody body = RequestBody.create(MediaType_JSON, data);
//							Request req = new Request.Builder().url(urlString).post(body).build();
//							Response resp=httpClient.newCall(req).execute();
//							String result =new String(resp.body().bytes(), "utf-8");
//							logger.info("deleteUserAllNews response data:[result:"+result+"]");
//							JSONObject json = JSON.parseObject(result);
//							String str =(String) json.get("code");
						obj.put("token", "token");
						obj.put("requestid", "12345678");
						obj.put("cversion", "1");
						obj.put("ctype", "1");
						String data = JSON.toJSONString(obj);
						String responstr = MbUtil.handleReqAndSendReq("/operate/news/delnews", data);
						String resultstr = MbUtil.handleResp(responstr);
						String str = "1";
						if (StringUtils.isNotBlank(resultstr)) {
							JSONObject json = JSON.parseObject(resultstr);
							str = (String) json.get("code");
						}
						if (Integer.parseInt(str) == 0) {
							out.write("0");
						} else {
							out.write("1");
						}
					}

				}

			} else {
				out.write("1");
			}

		} else {
			out.write("1");
		}

	}

	@RequestMapping(value = "/deleteUserAllComments", method = RequestMethod.POST)
	public void deleteUserAllComments(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) throws IOException {
		if (userid != null && !"".equals(userid)) {
			List<CommentModel> commentModels = newsService.getcommentModelByuserid(userid);
			JSONObject obj = new JSONObject();
			for (CommentModel commentModel : commentModels) {
				obj.put("actiontype", 1);// 0 普通用户 1 管理员
				obj.put("userid", userid);
				obj.put("nid", commentModel.getNid());
				obj.put("cid", commentModel.getCid());
//				String data = JSON.toJSONString(obj);
//				String urlString = Consts.ServiceUrl + "news/delnewscomments";
//				logger.info("deleteUserAllComments  request data:[url:"+urlString+"  param:"+data+"]");
//				
//				RequestBody body = RequestBody.create(MediaType_JSON, data);
//				Request req = new Request.Builder().url(urlString).post(body).build();
//				Response resp=httpClient.newCall(req).execute();
//				String result =new String(resp.body().bytes(), "utf-8");
//				logger.info("deleteUserAllComments response data:[result:"+result+"]");
//				JSONObject json = JSON.parseObject(result);
//				String str =(String) json.get("code");
				obj.put("token", "token");
				obj.put("requestid", "12345678");
				obj.put("cversion", "1");
				obj.put("ctype", "1");
				String data = JSON.toJSONString(obj);
				String responstr = MbUtil.handleReqAndSendReq("/operate/news/delnews", data);
				String resultstr = MbUtil.handleResp(responstr);
				String str = "1";
				if (StringUtils.isNotBlank(resultstr)) {
					JSONObject json = JSON.parseObject(resultstr);
					str = (String) json.get("code");
				}
				if (Integer.parseInt(str) == 0) {
					out.write("0");
				} else {
					out.write("1");
				}
			}

		}

	}

	@RequestMapping(value = "/deleteUserRecommend", method = RequestMethod.POST)
	public void deleteUserRecommend(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) {

		RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, "user_recommend", userid);

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, userid + "_user")) {
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + "_user", "usertype", "0");
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + "_user", "approvedes", "");
		}

		motouserService.updateUsertypeAndapprovedes(0, "", userid);

		out.print("success");
	}

	@RequestMapping(value = "/deleteUserOlddriver", method = RequestMethod.POST)
	public void deleteUserOlddriver(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) {

		RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, "user_olddriver", userid);

		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, userid + "_user")) {
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + "_user", "usertype", "0");
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + "_user", "approvedes", "");
		}

		motouserService.updateUsertypeAndapprovedes(0, "", userid);

		out.print("success");
	}

	@RequestMapping(value = "/updateUserRecommend", method = RequestMethod.POST)
	public void updateUserRecommend(Model model, HttpSession session, HttpServletRequest request, String uid,
			String score, PrintWriter out) {

		RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_USER, "user_recommend", Long.valueOf(score), uid);
		out.print("success");
	}

	@RequestMapping(value = "/updateUserOlddriver", method = RequestMethod.POST)
	public void updateUserOlddriver(Model model, HttpSession session, HttpServletRequest request, String uid,
			String score, PrintWriter out) {

		RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_USER, "user_olddriver", Long.valueOf(score), uid);
		out.print("success");
	}

	@RequestMapping(value = "/deleteUserSingleNews", method = RequestMethod.POST)
	public void deleteUserSingleNews(Model model, HttpSession session, HttpServletRequest request, String userid,
			String nid, PrintWriter out) throws IOException {
		if (userid != null && !"".equals(userid) && nid != null && !"".equals(nid)) {
			if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid + "_ninfo")) {

				JSONObject obj = new JSONObject();
				obj.put("actiontype", 1);// 0 普通用户 1 管理员
				obj.put("userid", userid);
				obj.put("nid", nid);
//							String data = JSON.toJSONString(obj);
//							String urlString = Consts.ServiceUrl + "news/delnews";
//							logger.info("deleteUserSingleNews  request data:[url:"+urlString+"  param:"+data+"]");
//							
//							RequestBody body = RequestBody.create(MediaType_JSON, data);
//							Request req = new Request.Builder().url(urlString).post(body).build();
//							Response resp=httpClient.newCall(req).execute();
//							String result =new String(resp.body().bytes(), "utf-8");
//							
//							logger.info("deleteUserSingleNews  response data:[result:"+result+"]");
//							JSONObject json = JSON.parseObject(result);
//							String str =(String) json.get("code");
				obj.put("token", "token");
				obj.put("requestid", "12345678");
				obj.put("cversion", "1");
				obj.put("ctype", "1");
				String data = JSON.toJSONString(obj);
				String responstr = MbUtil.handleReqAndSendReq("/operate/news/delnews", data);
				String resultstr = MbUtil.handleResp(responstr);
				String str = "1";
				if (StringUtils.isNotBlank(resultstr)) {
					JSONObject json = JSON.parseObject(resultstr);
					str = (String) json.get("code");
				}
				if (Integer.parseInt(str) == 0) {
					out.write("success");
				} else {
					out.write("fail");
				}

			} else {
				out.write("fail");
			}

		} else {

			out.write("fail");
		}

	}

	@RequestMapping(value = "/adduserCredit", method = RequestMethod.POST)
	public void adduserCredit(Model model, HttpSession session, HttpServletRequest request, String userid,
			String addcredit, PrintWriter out) throws Exception {
		if (userid != null && !"".equals(userid) && addcredit != null && !"".equals(addcredit)) {
			CreditLogModel creditLogModel = new CreditLogModel();
			creditLogModel.actiontype = CreditActionType_Add;
			creditLogModel.credittype = CreditType_Operation;
			creditLogModel.datades = "";
			creditLogModel.logtime = System.currentTimeMillis();
			creditLogModel.score = Long.valueOf(addcredit);
			creditLogModel.userid = userid;

			if (Long.valueOf(addcredit) < 0) {
				creditLogModel.actiontype = CreditActionType_Subtract;
				creditLogModel.score = Math.abs(Long.valueOf(addcredit));
			}

			Map<String, Object> resultMap = creditService.updateUserCredit(creditLogModel);
			long balance = Long.parseLong((String) resultMap.get("balance"));
			int result = Integer.parseInt((String) resultMap.get("result"));

			if (result == 0) {
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, creditLogModel.userid + USERKEY_USER,
						"credit", String.valueOf(balance));
				out.write("success");
			} else {
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, creditLogModel.userid + USERKEY_USER,
						"credit", String.valueOf(balance));
				Map<String, String> map = CreditLogModel.convertToMap(creditLogModel);

				RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_USER,
						creditLogModel.creditlogid + USER_CREDITLOGMAP, map);
				// 设置30天超期时间
				RedisManager.getInstance().setExpireAt(Consts.REDIS_SCHEME_USER,
						creditLogModel.creditlogid + USER_CREDITLOGMAP, 2592000000l + creditLogModel.logtime);

				RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_USER, creditLogModel.userid + USER_CREDITLOGLIST,
						creditLogModel.logtime, creditLogModel.creditlogid);

				// 同步有赞
				syncUserYZCredit(userid, creditLogModel.score, creditLogModel.actiontype);

				out.write("success");
			}

		} else {
			out.write("fail");
		}
	}

	@RequestMapping(value = "/resetUserPSW", method = RequestMethod.POST)
	public void resetUserPSW(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) throws Exception {
		if (userid != null && !"".equals(userid)) {
			String password = MD5.stringToMD5("123456");
			password = MD5.stringToMD5(password);
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, "password", password);
			motouserService.updateUserPSWByUserid(userid, password);

			out.write("success");
		} else {
			out.write("fail");
		}
	}

	public void syncUserToES(String userid) {
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER)) {
			Map<String, String> usermap = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER,
					userid + USERKEY_USER);
			MBUserModel user = MBUserModel.convertToModel(usermap);
			// 同步ES
			MBUserModel newuser = new MBUserModel();
			newuser.setMbid(user.getMbid());
			newuser.setUserid(user.getUserid());
			newuser.setMobileno(user.getMobileno());
			newuser.setNickname(user.getNickname());
			ESManager.syncUserEs(newuser);
		}

	}

	@RequestMapping(value = "/openscreenlist", method = RequestMethod.GET)
	public void openscreenlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,
			int limit, int order, String orderConditions) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		PageBean<OpenScreenModel> pageBean = new PageBean<OpenScreenModel>();
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
		int totalCount = motouserService.getOpenScreenListCount();

		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int start = (page - 1) * limit;
		List<OpenScreenModel> openScreenModels = motouserService.getOpenScreenList(start, limit, order,
				orderConditions);

		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);

		ArrayList<Integer> limitList = new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("openscreenlists", openScreenModels);
		model.addAttribute("pageBean", pageBean);

		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
	}

	@RequestMapping(value = "/getOpenscreenById", method = RequestMethod.POST)
	public void getOpenscreenById(Model model, HttpSession session, HttpServletRequest request, String osid,
			PrintWriter out) {
		String jsonString = "";
		OpenScreenModel openScreenModel = null;
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, osid + RUNKEY_OPENSCREENMAP)) {
			Map<String, String> map = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN,
					osid + RUNKEY_OPENSCREENMAP);
			openScreenModel = OpenScreenModel.convertToModel(map);
		}
		if (openScreenModel != null) {
			jsonString = JSON.toJSONString(openScreenModel);
		}
		out.print(jsonString);
	}

	@RequestMapping(value = "/insertOrUpdateOpenscreen", method = RequestMethod.POST)
	public void insertOrUpdateOpenscreen(Model model, HttpSession session, HttpServletRequest request, String osid,
			String userid, String pic, PrintWriter out) {
		String jsonString = "";
		OpenScreenModel openScreenModel = new OpenScreenModel();
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER)) {
			if (osid == null || "".equals(osid)) {
				osid = MbUtil.getUUID();
			}
			openScreenModel.setOsid(osid);
			openScreenModel.setUserid(userid);
			String nickname = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER,
					"nickname");
			if (nickname != null && !"".equals(nickname)) {
				openScreenModel.setNickname(nickname);
			}
			openScreenModel.setPic(pic);
			openScreenModel.setAddtime(System.currentTimeMillis());
			motouserService.insertOrUpdateOpenscreen(openScreenModel);
			RedisManager.getInstance().sadd(Consts.REDIS_SCHEME_RUN, RUNKEY_OPENSCREENLIST, osid);
			Map<String, String> map = OpenScreenModel.convertToMap(openScreenModel);
			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, osid + RUNKEY_OPENSCREENMAP, map);

			jsonString = "success";
		} else {
			jsonString = "fail";
		}

		out.print(jsonString);
	}

	@RequestMapping(value = "/delopenscreen", method = RequestMethod.POST)
	public void delopenscreen(Model model, HttpSession session, HttpServletRequest request, String osid,
			PrintWriter out) {
		String jsonString = "";
		motouserService.delopenscreen(osid);
		RedisManager.getInstance().srem(Consts.REDIS_SCHEME_RUN, RUNKEY_OPENSCREENLIST, osid);
		RedisManager.getInstance().hdel(Consts.REDIS_SCHEME_RUN, osid + RUNKEY_OPENSCREENMAP);
		jsonString = "success";
		out.print(jsonString);
	}

	@RequestMapping(value = "/searchUserCreditLog", method = RequestMethod.POST)
	public void searchUserCreditLog(Model model, HttpSession session, HttpServletRequest request, String userid,
			PrintWriter out) {
		Set<String> set = new HashSet<String>();
		List<CreditLogModel> list = new ArrayList<CreditLogModel>();
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, userid + USER_CREDITLOGLIST)) {

			// 去掉30天以前的记录
			long curtime = System.currentTimeMillis() - 2592000000l;// 当前时间减30天
			Set<String> tempset = RedisManager.getInstance().zrevrangeByScore(Consts.REDIS_SCHEME_USER,
					userid + USER_CREDITLOGLIST, 0, curtime);
			for (String s : tempset) {
				RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, userid + USER_CREDITLOGLIST, s);
			}

			long start = 0;
			long end = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, userid + USER_CREDITLOGLIST);
			set = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_USER, userid + USER_CREDITLOGLIST,
					start, end);
			list = getCreditLogModelListFromIdSet(set);
		}
		out.print(JSONArray.toJSONString(list));
	}

	private List<CreditLogModel> getCreditLogModelListFromIdSet(Set<String> set) {

		List<CreditLogModel> list = new ArrayList<CreditLogModel>();
		if (set.isEmpty()) {
			return null;
		}
		for (String cid : set) {
			CreditLogModel model = this.getCreditLogModel(cid);
			if (model != null) {
				switch (model.credittype) {
				case CreditType_PubNews:
					model.title = "发动态";
					break;
				case CreditType_PubStory:
					model.title = "发故事";
					break;
				case CreditType_PubComment:
					model.title = "发表评论";
					break;
				case CreditType_PubLike:
					model.title = "点赞他人动态或故事";
					break;
				case CreditType_ReceiveComment:
					model.title = "收到他人评论";
					break;
				case CreditType_ReceiveLike:
					model.title = "收到他人点赞";
					break;
				case CreditType_Logon1:
				case CreditType_Logon2:
				case CreditType_Logon3:
				case CreditType_Logon4:
				case CreditType_Logon5:
				case CreditType_Logon6:
				case CreditType_Logon7:
					model.title = "连续登录";
					break;
				case CreditType_NewsRecommend:
					model.title = "动态被加精";
					break;
				case CreditType_StoryRecommend:
					model.title = "故事被加精";
					break;
				case CreditType_NewsRecommendMain:
					model.title = "动态被首页显示";
					break;
				case CreditType_StoryRecommendMain:
					model.title = "故事被首页显示";
					break;
				case CreditType_ShareContent:
					model.title = "分享内容至第三方平台";
					break;
				case CreditType_ShareAppStore:
					model.title = "App Store五星好评";
					break;
				case CreditType_GetAchievement:
					model.title = "获得勋章";
					break;
				case CreditType_Ride:
					model.title = "记录骑行";
					break;
				case CreditType_LevelUp:
					model.title = "升级骑士等级";
					break;
				case CreditType_CompleteUserProfile:
					model.title = "完善个人资料";
					break;
				case CreditType_BusinessComment:
					model.title = "评价商家";
					break;
				case CreditType_Operation:
					model.title = "官方奖励";
					break;
				case CreditType_DelNews:
					model.title = "删除个人动态";
					break;
				case CreditType_DelStory:
					model.title = "删除个人故事";
					break;
				case CreditType_SendGift:
					model.title = "送出礼物";
					break;
				case CreditType_DiscussPublish:
					model.title = "发布讨论";
					break;
				case CreditType_BreakNews:
					model.title = "动态违规";
					break;
				case CreditType_BreakStory:
					model.title = "故事违规";
					break;
				case CreditType_BreakComment:
					model.title = "评论违规";
					break;
				case CreditType_PubSendCar:
					model.title = "发布二手车";
					break;
				default:
					model.title = "未知";
					break;
				}
				list.add(model);
			}
		}
		return list;
	}

	private CreditLogModel getCreditLogModel(String creditlogid) {
		CreditLogModel model = null;
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, creditlogid + USER_CREDITLOGMAP)) {
			Map<String, String> map = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER,
					creditlogid + USER_CREDITLOGMAP);
			if (map != null && !map.isEmpty()) {
				model = CreditLogModel.convertToModel(map);
			}
		}

		return model;
	}

	public String getNumber(int weishu) {
		StringBuffer sb = new StringBuffer();
		sb.append("1");
		if (weishu > 1) {
			for (int i = 1; i < weishu; i++) {
				sb.append("0");
			}
		}
		return sb.toString();

	}

	public long updateUserAllPrivilege(long oldprivilegelong, int privilegetype, byte addorsubtract) {
		long finallong = 0;
		long resultlong = 0;
		if (oldprivilegelong > 0) {
			String oldlongstr = Long.toBinaryString(oldprivilegelong);
			int oldlen = oldlongstr.length();
			if (addorsubtract == UserAllPrivilege_Add) {
				if (privilegetype > 0) {
					if (privilegetype > oldlen) {
						String typestr = getNumber(privilegetype);
						resultlong = Long.parseLong(typestr) + Long.parseLong(oldlongstr);
					} else if (privilegetype == oldlen) {
						resultlong = Long.parseLong(oldlongstr);
					} else {
						String indexstr = oldlongstr.substring(oldlen - privilegetype, oldlen - privilegetype + 1);
						int indexint = Integer.parseInt(indexstr);
						if (indexint == 0) {
							String typestr = getNumber(privilegetype);
							resultlong = Long.parseLong(typestr) + Long.parseLong(oldlongstr);
						} else {
							resultlong = Long.parseLong(oldlongstr);
						}

					}
				}

			} else if (addorsubtract == UserAllPrivilege_Subtract) {
				if (privilegetype > 0) {
					if (privilegetype < oldlen) {
						String indexstr = oldlongstr.substring(oldlen - privilegetype, oldlen - privilegetype + 1);
						int indexint = Integer.parseInt(indexstr);
						if (indexint == 1) {
							String typestr = getNumber(privilegetype);
							resultlong = Long.parseLong(oldlongstr) - Long.parseLong(typestr);
						} else {
							resultlong = Long.parseLong(oldlongstr);
						}

					} else if (privilegetype == oldlen) {
						String typestr = getNumber(privilegetype);
						resultlong = Long.parseLong(oldlongstr) - Long.parseLong(typestr);

					} else {
						resultlong = Long.parseLong(oldlongstr);
					}
				}

			}

		} else if (oldprivilegelong == 0) {
			if (privilegetype > 0) {
				if (addorsubtract == UserAllPrivilege_Add) {
					String typestr = getNumber(privilegetype);
					resultlong = Long.parseLong(typestr);
				}
			}

		}

		finallong = Long.parseLong(String.valueOf(resultlong), 2);
		return finallong;

	}

	public void updatePrivilegelongForUser(String userid, long privilegelong) throws Exception {
		motouserService.updatePrivilegelongForUser(userid, privilegelong);
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, "userprivilegelong",
				String.valueOf(privilegelong));

	}

	private void changePrivilegelongForUserType(String userid, int type) throws Exception {
		long privilegelong = 0;
		long oldprivilegelong = 0;
		String oldprivilegelongstr = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER,
				"userprivilegelong");
		if (oldprivilegelongstr != null && !"".equals(oldprivilegelongstr)) {
			oldprivilegelong = Integer.parseInt(oldprivilegelongstr);
		}
		if (type == 1) {// 加精
			privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_GreenVip, UserAllPrivilege_Add);
		} else if (type == 2) {// 官方运营
			privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_YellowVip, UserAllPrivilege_Add);
		} else if (type == 3) {// 认证
			privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_BlueVip, UserAllPrivilege_Add);
		} else if (type == 4) {// 普通
			privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_GreenVip,
					UserAllPrivilege_Subtract);
			privilegelong = updateUserAllPrivilege(privilegelong, UserAllPrivilege_YellowVip,
					UserAllPrivilege_Subtract);
			privilegelong = updateUserAllPrivilege(privilegelong, UserAllPrivilege_BlueVip, UserAllPrivilege_Subtract);
			privilegelong = updateUserAllPrivilege(privilegelong, UserAllPrivilege_OldDriver,
					UserAllPrivilege_Subtract);
		} else if (type == 5) {// 老司机
			privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_OldDriver, UserAllPrivilege_Add);
		}
		updatePrivilegelongForUser(userid, privilegelong);
	}

	@RequestMapping(value = "/deleteuservip", method = RequestMethod.POST)
	public void deleteuservip(Model model, HttpSession session, HttpServletRequest request, String userid, String deletevip,
			PrintWriter out) throws Exception {
		//从高级会员变成低级会员
		//从会员当中移除
		if (userid != null && !"".equals(userid) && deletevip != null && !"".equals(deletevip) && !"0".equals(deletevip)) {
			int viptype = Integer.parseInt(deletevip);
			int count = motouserService.checkvipmember(userid, viptype);
			if (count == 0) {
				out.write("success");
				return ;
			}else {
				motouserService.deleteVip(userid,viptype);
			}
			List<VipCardMemberModel> vipCardMemberModellist = motouserService.getVipCardMemberByuserid(userid);
			if (vipCardMemberModellist != null && vipCardMemberModellist.size() > 0) {
				VipCardMemberModel vipCardMemberModelfirst = vipCardMemberModellist.get(0);
				int viptypehigh = vipCardMemberModelfirst.viptype;
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, "viptype",
						String.valueOf(viptypehigh));

				// 放至usermap中
				switch (viptypehigh) {
				case 1:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_SILVERVIP,
							vipCardMemberModelfirst.starttime + "-" + vipCardMemberModelfirst.endtime);
					break;
				case 2:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_GOLDVIP,
							vipCardMemberModelfirst.starttime + "-" + vipCardMemberModelfirst.endtime);
					break;
				case 3:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_DIAMONDVIP,
							vipCardMemberModelfirst.starttime + "-" + vipCardMemberModelfirst.endtime);
					break;
				case 4:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_VOICEVIP,
							vipCardMemberModelfirst.starttime + "-" + vipCardMemberModelfirst.endtime);
					break;

				default:
					break;
				}
				long privilegelong = 0;
				long oldprivilegelong = 0;
				String oldprivilegelongstr = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER,
						userid + USERKEY_USER, "userprivilegelong");
				if (oldprivilegelongstr != null && !"".equals(oldprivilegelongstr)) {
					oldprivilegelong = Integer.parseInt(oldprivilegelongstr);
				}
				if (viptype == 1) {
					privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_SilverVip, UserAllPrivilege_Subtract);
				} else if (viptype == 2) {
					privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_GoldVip, UserAllPrivilege_Subtract);
				} else if (viptype == 3) {
					privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_DiamondVip, UserAllPrivilege_Subtract);
				} else if (viptype == 4) {
					privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_VoiceVip, UserAllPrivilege_Subtract);
				}
				updatePrivilegelongForUser(userid, privilegelong);

			} else {
				RedisManager.getInstance().hdelElement(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, "viptype");
				// 放至usermap中
				switch (viptype) {
				case 1:
					RedisManager.getInstance().hdelElement(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_SILVERVIP);
					break;
				case 2:
					RedisManager.getInstance().hdelElement(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_GOLDVIP);
					break;
				case 3:
					RedisManager.getInstance().hdelElement(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_DIAMONDVIP);
					break;
				case 4:
					RedisManager.getInstance().hdelElement(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_VOICEVIP);
					break;

				default:
					break;
				}
				updatePrivilegelongForUser(userid, 0);
			}

			out.write("success");

		} else {
			out.write("fail");
		}
	}
	
	@RequestMapping(value = "/adduservip", method = RequestMethod.POST)
	public void adduservip(Model model, HttpSession session, HttpServletRequest request, String userid, String addvip,
			PrintWriter out) throws Exception {
		if (userid != null && !"".equals(userid) && addvip != null && !"".equals(addvip) && !"0".equals(addvip)) {
			int viptype = Integer.parseInt(addvip);
			int count = motouserService.checkvipmember(userid, viptype);
			int level = 0;
			int vipcardid = 0;
			switch (viptype) {
			case 1:
				level = 10;
				vipcardid = 1;
				break;
			case 2:
				level = 20;
				vipcardid = 2;
				break;
			case 3:
				level = 30;
				vipcardid = 3;
				break;
			case 4:
				level = 5;
				vipcardid = 4;
				break;
			default:
				break;
			}
			VipCardMemberModel vipCardMemberModel = new VipCardMemberModel();
			vipCardMemberModel.setUserid(userid);
			vipCardMemberModel.setVipcardid(vipcardid);
			vipCardMemberModel.setLevel(level);
			vipCardMemberModel.setStarttime(System.currentTimeMillis());
			vipCardMemberModel.setEndtime(System.currentTimeMillis() + 31536000000l);
			vipCardMemberModel.setViptype(viptype);
			if (count == 0) {
				motouserService.insertVipCardMember(vipCardMemberModel);
			}
			List<VipCardMemberModel> vipCardMemberModellist = motouserService.getVipCardMemberByuserid(userid);
			if (vipCardMemberModellist != null && vipCardMemberModellist.size() > 0) {
				VipCardMemberModel vipCardMemberModelfirst = vipCardMemberModellist.get(0);
				int viptypehigh = vipCardMemberModelfirst.viptype;
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, "viptype",
						String.valueOf(viptypehigh));

				// 放至usermap中
				switch (viptypehigh) {
				case 1:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_SILVERVIP,
							vipCardMemberModelfirst.starttime + "-" + vipCardMemberModelfirst.endtime);
					break;
				case 2:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_GOLDVIP,
							vipCardMemberModelfirst.starttime + "-" + vipCardMemberModelfirst.endtime);
					break;
				case 3:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_DIAMONDVIP,
							vipCardMemberModelfirst.starttime + "-" + vipCardMemberModelfirst.endtime);
					break;
				case 4:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_VOICEVIP,
							vipCardMemberModelfirst.starttime + "-" + vipCardMemberModelfirst.endtime);
					break;

				default:
					break;
				}

				// 3.1.0
				changePrivilegelongForYZVip(userid, viptypehigh);
			} else {
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, "viptype",
						String.valueOf(vipCardMemberModel.viptype));
				// 放至usermap中
				switch (vipCardMemberModel.viptype) {
				case 1:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_SILVERVIP,
							vipCardMemberModel.starttime + "-" + vipCardMemberModel.endtime);
					break;
				case 2:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_GOLDVIP,
							vipCardMemberModel.starttime + "-" + vipCardMemberModel.endtime);
					break;
				case 3:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_DIAMONDVIP,
							vipCardMemberModel.starttime + "-" + vipCardMemberModel.endtime);
					break;
				case 4:
					RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_VOICEVIP,
							vipCardMemberModel.starttime + "-" + vipCardMemberModel.endtime);
					break;

				default:
					break;
				}

				// 3.1.0
				changePrivilegelongForYZVip(userid, vipCardMemberModel.viptype);
			}

			out.write("success");

		} else {
			out.write("fail");
		}
	}

	private void changePrivilegelongForYZVip(String outeruserid, int viptypehigh) throws Exception {
		long privilegelong = 0;
		long oldprivilegelong = 0;
		String oldprivilegelongstr = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER,
				outeruserid + USERKEY_USER, "userprivilegelong");
		if (oldprivilegelongstr != null && !"".equals(oldprivilegelongstr)) {
			oldprivilegelong = Integer.parseInt(oldprivilegelongstr);
		}
		if (viptypehigh == 1) {
			privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_SilverVip, UserAllPrivilege_Add);
		} else if (viptypehigh == 2) {
			privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_GoldVip, UserAllPrivilege_Add);
		} else if (viptypehigh == 3) {
			privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_DiamondVip, UserAllPrivilege_Add);
		} else if (viptypehigh == 4) {
			privilegelong = updateUserAllPrivilege(oldprivilegelong, UserAllPrivilege_VoiceVip, UserAllPrivilege_Add);
		}
		updatePrivilegelongForUser(outeruserid, privilegelong);
	}

	public void syncUserYZCredit(String userid, long creditcount, int type) throws Exception {

		String YZ_LOGIN = "https://uic.youzan.com/sso/open/login";
		String YZ_TOKEN = "https://open.youzan.com/oauth/token";
		JSONObject tokenobj = new JSONObject();
		tokenobj.put("client_id", Consts.YZ_CLIENT_ID);
		tokenobj.put("client_secret", Consts.YZ_CLIENT_SECRET);
		tokenobj.put("grant_type", "silent");
		tokenobj.put("kdt_id", Consts.YZ_MALL_ID);
		String tokendata = JSON.toJSONString(tokenobj);
		RequestBody tokenbody = RequestBody.create(MediaType_JSON, tokendata);
		Request tokenreq = new Request.Builder().url(YZ_TOKEN).post(tokenbody).build();
		Response tokenresp = httpClient.newCall(tokenreq).execute();
		if (tokenresp.isSuccessful()) {
			String toeknresult = new String(tokenresp.body().bytes(), "utf-8");
			if (toeknresult != null && !"".equals(toeknresult)) {
				JSONObject tokenjson = JSON.parseObject(toeknresult);
				String accessToken = tokenjson.getString("access_token");
				if (accessToken != null && !"".equals(accessToken)) {
					RequestBody loginbody = new FormBody.Builder().add("client_id", Consts.YZ_CLIENT_ID)
							.add("client_secret", Consts.YZ_CLIENT_SECRET).add("open_user_id", userid)
							.add("kdt_id", Consts.YZ_MALL_ID).build();
					Request loginreq = new Request.Builder().url(YZ_LOGIN).post(loginbody).build();
					Response loginresp = httpClient.newCall(loginreq).execute();
					String loginresult = new String(loginresp.body().bytes(), "utf-8");

					if (loginresp.isSuccessful() && StringUtils.isNotBlank(loginresult)) {
						JSONObject loginjson = JSON.parseObject(loginresult);
						int code = loginjson.getIntValue("code");
						if (code == 0) {
							YZClient client = new DefaultYZClient(new Token(accessToken));
							if (type == CreditActionType_Add) {
								try {
									YouzanCrmCustomerPointsIncreaseParams youzanCrmCustomerPointsIncreaseParams = new YouzanCrmCustomerPointsIncreaseParams();

									youzanCrmCustomerPointsIncreaseParams.setPoints(creditcount);
									youzanCrmCustomerPointsIncreaseParams.setOpenUserId(userid);

									YouzanCrmCustomerPointsIncrease youzanCrmCustomerPointsIncrease = new YouzanCrmCustomerPointsIncrease();
									youzanCrmCustomerPointsIncrease.setAPIParams(youzanCrmCustomerPointsIncreaseParams);
									YouzanCrmCustomerPointsIncreaseResult result1 = client
											.invoke(youzanCrmCustomerPointsIncrease);
									if ("true".equals(result1.getIsSuccess())) {
										logger.info("syncUserYZCredit success,param:{userid:" + userid
												+ ",type:add,count:" + creditcount + "}");
									} else {
										logger.error("syncUserYZCredit fail,param:{userid:" + userid
												+ ",type:add,count:" + creditcount + "}");
									}
								} catch (Exception e) {
									logger.error("syncUserYZCredit Exception,param:{userid:" + userid
											+ ",type:add,count:" + creditcount + "}   e:" + e);
								}

							} else if (type == CreditActionType_Subtract) {
								try {
									YouzanCrmCustomerPointsDecreaseParams youzanCrmCustomerPointsDecreaseParams = new YouzanCrmCustomerPointsDecreaseParams();

									youzanCrmCustomerPointsDecreaseParams.setPoints(creditcount);
									youzanCrmCustomerPointsDecreaseParams.setOpenUserId(userid);

									YouzanCrmCustomerPointsDecrease youzanCrmCustomerPointsDecrease = new YouzanCrmCustomerPointsDecrease();
									youzanCrmCustomerPointsDecrease.setAPIParams(youzanCrmCustomerPointsDecreaseParams);
									YouzanCrmCustomerPointsDecreaseResult result1 = client
											.invoke(youzanCrmCustomerPointsDecrease);
									if ("true".equals(result1.getIsSuccess())) {
										logger.info("syncUserYZCredit success,param:{userid:" + userid
												+ ",type:subtract,count:" + creditcount + "}");
									} else {
										logger.error("syncUserYZCredit fail,param:{userid:" + userid
												+ ",type:subtract,count:" + creditcount + "}");
									}
								} catch (Exception e) {
									logger.error("syncUserYZCredit Exception,param:{userid:" + userid
											+ ",type:subtract,count:" + creditcount + "}  e;" + e);
								}

							}

						} else {
							logger.error("syncUserYZCredit fail,param:{userid:" + userid + ",type:" + type + ",count:"
									+ creditcount + ",youzanloginresponse:" + loginresp + "}");
						}
					} else {
						logger.error("syncUserYZCredit error,youzanlogin error,param:{userid:" + userid + ",type:"
								+ type + ",count:" + creditcount + "}");
					}

				} else {

				}
			}
		}

	}

	@RequestMapping(value = "/getridedata", method = RequestMethod.POST)
	public void getridedata(Model model, HttpSession session, HttpServletRequest request, String targetid,
			PrintWriter out) throws UnsupportedEncodingException {
		JSONObject obj = new JSONObject();
		obj.put("token", "token");
		obj.put("requestid", "12345678");
		obj.put("cversion", "1");
		obj.put("ctype", "1");
		obj.put("targetid", targetid);

		String data = JSON.toJSONString(obj);
		String responstr = MbUtil.handleReqAndSendReq("/ridedata/getridedata", data);
		String resultstr = MbUtil.handleResp(responstr);
		String str = "1";
		if (StringUtils.isNotBlank(resultstr)) {
			JSONObject json = JSON.parseObject(resultstr);
			str = (String) json.get("code");
		}
		String jsonString = "";
		if (Integer.parseInt(str) == 0) {
			jsonString = resultstr;
		}

		out.print(jsonString);
	}

	@RequestMapping(value = "/sendSystemMessage", method = RequestMethod.GET)
	public void sendSystemMessage(Model model, HttpSession session, HttpServletRequest request, String userGuid) {
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);

		List<MessageTaskModel> messagetaskmodels = motouserService.getMessageTasks();
		List<MessageTaskModel> messagetaskmodellist = new ArrayList<MessageTaskModel>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (messagetaskmodels != null && messagetaskmodels.size() > 0) {
			for (MessageTaskModel mtm : messagetaskmodels) {
//				if(mtm.getState()==0) {
//					mtm.setSumcount(motouserService.getUserTaskCount(mtm.taskid,-1));
//					mtm.setFailcount(motouserService.getUserTaskCount(mtm.taskid,2));
//					mtm.setSuccesscount(motouserService.getUserTaskCount(mtm.taskid,1));
//				}
//				mtm.setHandlecount(mtm.getSuccesscount()+mtm.failcount);
				if (mtm.getCreatetime() > 0) {
					Date date = new Date();
					date.setTime(mtm.getCreatetime());
					mtm.setCreatetimeString(sdf.format(date));
				}
				if (mtm.getUpdatetime() > 0) {
					Date date = new Date();
					date.setTime(mtm.getUpdatetime());
					mtm.setUpdatetimeString(sdf.format(date));
				}

				messagetaskmodellist.add(mtm);

			}
		}

		model.addAttribute("messagetaskmodellist", messagetaskmodellist);
	}

	@RequestMapping(value = "/gettaskcountnow", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getTaskCountNow(HttpServletRequest request, HttpServletResponse response, String taskid)
			throws IOException, InterruptedException {
		Map<String, Object> map = Maps.newHashMap();
		BlockingQueue<Map<String, Object>> queue = taskMapQueue.get(taskid);
		if (queue == null || queue.isEmpty()) {
			MessageTaskModel model = motouserService.getTaskMsgByTaskid(taskid);
			map.put("state", model.state);
			map.put("sumcount", motouserService.getUserTaskCount(taskid, -1));
			map.put("failcount", motouserService.getUserTaskCount(taskid, 2));
			map.put("successcount", motouserService.getUserTaskCount(taskid, 1));
		} else {
//			map = queue.take();
			MessageTaskModel model = motouserService.getTaskMsgByTaskid(taskid);
			map.put("state", model.state);
			map.put("sumcount", motouserService.getUserTaskCount(taskid, -1));
			map.put("failcount", motouserService.getUserTaskCount(taskid, 2));
			map.put("successcount", motouserService.getUserTaskCount(taskid, 1));
		}
		return map;
	}

	@RequestMapping(value = "batchimport", method = RequestMethod.POST)
	public void batchimport(@RequestParam(value = "filename") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response, PrintWriter out) throws IOException {
		logger.debug("motouserController ..batchimport() start");
		String res = "";
		// 判断文件是否为空
		if (file == null)
			return;
		// 获取文件名
		String name = file.getOriginalFilename();
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if (name == null || ("").equals(name) && size == 0)
			return;

		String contextpath = request.getRealPath("/fileupload");
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String taskid = sdf.format(date);
		// 创建处理EXCEL
		ExcelUtils excelUtils = new ExcelUtils();
		boolean b = excelUtils.writeExcelToLocal(name, file, contextpath, taskid);

		if (b) {
			JSONObject json = new JSONObject();
			json.put("msg", "EXCEL上传成功！");
			json.put("taskid", taskid);
			res = JSON.toJSONString(json);

		} else {
			JSONObject json = new JSONObject();
			json.put("msg", "EXCEL上传失败！");
			json.put("taskid", "");
			res = JSON.toJSONString(json);
		}

		out.print(res);
	}

	@RequestMapping(value = "/addmsgtask", method = RequestMethod.POST)
	public void addmsgtask(Model model, HttpSession session, HttpServletRequest request, String name, String title,
			String subtitle, String des, String userSelectType, String linktype, String imgurl, String linkurl,
			String gpid, String nid, String keyword, String secondcarid, String buserid, String groupid,String miniprogramid,
			String taskid,long starttime, PrintWriter out) throws UnsupportedEncodingException {

		MessageTaskModel banner = new MessageTaskModel();
		long currenttime = System.currentTimeMillis();
		banner.setName(name);
		if (Integer.parseInt(userSelectType) == 2) {
			banner.setTaskid(taskid);
		} else if (Integer.parseInt(userSelectType) == 1) {
			Date date = new Date();
			date.setTime(currenttime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			taskid = sdf.format(date) + "_all";
			banner.setTaskid(taskid);
		}

		banner.setCreatetime(currenttime);
		banner.setTitle(title);
		banner.setSubtitle(subtitle);
		banner.setDes(des);
		banner.setLinktype(Integer.parseInt(linktype));
		banner.setImgurl(imgurl);
		if (linkurl != null && !"".equals(linkurl)) {
			banner.setLinkurl(linkurl);
		}
		if (gpid != null && !"".equals(gpid)) {
			banner.setGpid(Integer.parseInt(gpid));
		}
		if (nid != null && !"".equals(nid)) {
			banner.setNid(nid);
		}
		if (keyword != null && !"".equals(keyword)) {
			banner.setKeyword(keyword);
		}
		if (secondcarid != null && !"".equals(secondcarid)) {
			banner.setSecondcarid(secondcarid);
		}
		if (miniprogramid != null && !"".equals(miniprogramid)) {
			banner.setMiniprogramid(miniprogramid);
		}
		if (buserid != null && !"".equals(buserid)) {
			banner.setBuserid(buserid);
		}
		if(StringUtils.isNotBlank(groupid)) {
			banner.setGroupid(groupid);
		}
		banner.starttime=starttime;

		motouserService.addMessageTask(banner);
		// 处理文件
		if (banner.getTaskid().contains("all")) {

			final String taskid1 = banner.getTaskid();
			executor.submit(new Runnable() {
				public void run() {
					// 数据存入数据库
					motouserService.addMessageTaskUserAll(taskid1);
					long currenttime=System.currentTimeMillis();
					long sleeptime=1;
					if(currenttime<starttime) {
						sleeptime=starttime-currenttime;
					}
					try {
						RedisManager.getInstance().string_set(Consts.REDIS_SCHEME_RUN, taskid1+"_cms_task", String.valueOf(starttime));
						RedisManager.getInstance().lpush(Consts.REDIS_SCHEME_RUN, "cms_task_list", taskid1);
						RedisManager.getInstance().setExpireAt(Consts.REDIS_SCHEME_RUN, taskid1+"_cms_task", starttime);
						Thread.currentThread().sleep(sleeptime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 处理数据
					doMessageTaskUser(taskid1);
					RedisManager.getInstance().lrem(Consts.REDIS_SCHEME_RUN, "cms_task_list", taskid1);

				}
			});

		} else {

			final String taskid2 = banner.getTaskid();
			String contextpath = request.getRealPath("/fileupload");
			executor.submit(new Runnable() {
				public void run() {
					// 数据存入数据库
					ExcelUtils excelUtils = new ExcelUtils();
					List<String> userids = excelUtils.getExcelInfo(contextpath, taskid2);
//					List<String> userids = excelUtils.getExcelInfo("/data/motocms/fileupload", taskid2); // request.getRealPath("/fileupload")
					Set<String> useridsset = new HashSet<>(userids);// 生产环境加上去重
					if (useridsset != null && useridsset.size() > 5000) {
						double forcountdouble = Math.ceil(useridsset.size() / 5000.0);
						int forcount = (int) forcountdouble;
						List<List<String>> msglist = CollectionUtil.averageAssign(new ArrayList<String>(useridsset),
								forcount);
						for (List<String> batchlist : msglist) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("list", batchlist);
							map.put("taskid", taskid2);
							motouserService.batchaddMessageTaskUser(map);
						}
					} else {
						if(useridsset.isEmpty()) {
							return;
						}
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("list", useridsset);
						map.put("taskid", taskid2);
						motouserService.batchaddMessageTaskUser(map);
					}

					long currenttime=System.currentTimeMillis();
					long sleeptime=1;
					if(currenttime<starttime) {
						sleeptime=starttime-currenttime;
					}
					RedisManager.getInstance().string_set(Consts.REDIS_SCHEME_RUN, taskid2+"_cms_task", String.valueOf(starttime));
					RedisManager.getInstance().lpush(Consts.REDIS_SCHEME_RUN, "cms_task_list", taskid2);
					RedisManager.getInstance().setExpireAt(Consts.REDIS_SCHEME_RUN, taskid2+"_cms_task", starttime);
					try {
						Thread.currentThread().sleep(sleeptime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 处理数据
					doMessageTaskUser(taskid2);
				}
			});

		}

		out.print("success");
	}

	public void doMessageTaskUser(String taskid) {

		if (taskid.contains("all")) {
			MBMessageModel model = gettaskMessageModel(taskid);
			String pushMsg = "您有一条新的消息，点击查看";
			FenPiSendtaskMsg_new(taskid, model, pushMsg, 0);

		} else {// 指定用户
			Map<String, Object> dataMap1 = new HashMap<String, Object>();
			dataMap1.put("taskid", taskid);
			dataMap1.put("pici", 0);
			List<String> userids = motouserService.getMessageTaskUserByTaskid(dataMap1);
			MBMessageModel model = gettaskMessageModel(taskid);
			String pushMsg = "您有一条新的消息，点击查看";
			if (userids != null && userids.size() > 0) {
				if (userids.size() > 400) {
					FenPiSendtaskMsg_new(taskid, model, pushMsg, 0);
				} else {
					singleSendtaskMsg(taskid, model, pushMsg, userids);
					// 所有执行完毕 修改任务状态
					TaskFinshe(taskid);
				}

			}
		}

	}

	private void TaskFinshe(String taskid) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("updatetime", System.currentTimeMillis());
		if (motouserService.checkTask(taskid)) {
			dataMap.put("state", 1);
			if(logger.isErrorEnabled()) {
				logger.trace("taskid is finshed -------"+taskid+"-----"+JSON.toJSONString(dataMap) );
			}
		} else {
			dataMap.put("state", 0);
		}
		dataMap.put("taskid", taskid);
		dataMap.put("sumcount", motouserService.getUserTaskCount(taskid, -1));
		dataMap.put("successcount", motouserService.getUserTaskCount(taskid, 1));
		dataMap.put("failcount", motouserService.getUserTaskCount(taskid, 2));
		motouserService.updatetaskmsgliststate(dataMap);

	}

	public void FenPiSendtaskMsg_new(String taskid, MBMessageModel model, String pushMsg, int pici) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("taskid", taskid);
		dataMap.put("pici", pici * 80000);
		Clock c=Clock.systemUTC();
		long time=c.millis();
		List<String> userids = motouserService.getMessageTaskUserByTaskid(dataMap);
		if (logger.isErrorEnabled()) {
			logger.error(
					String.format("taskid:%s find user time :%s,pici:%s,userid count:%s", taskid, c.millis()-time,pici, userids.size()));
		}
		if(userids==null||userids.size()==0) {
			TaskFinshe(taskid);
			return ;
		}
		pici = batchSendCMSMessage(taskid, model, pushMsg, pici, userids);
		// 所有执行完毕 修改任务状态
		if (userids != null && userids.size() < 80 * 1000) {
			TaskFinshe(taskid);
		}
		FenPiSendtaskMsg_new(taskid, model, pushMsg, pici);

	}

	private int batchSendCMSMessage(String taskid, MBMessageModel model, String pushMsg, int pici,
			List<String> userids) {
		if (userids != null && userids.size() > 0) {
			List<List<String>> res = CollectionUtil.averageAssign(userids, Runtime.getRuntime().availableProcessors()*5);
//			List<List<String>> res = CollectionUtil.averageAssign(userids, 50);
			CyclicBarrier cb = new CyclicBarrier(res.size() + 1);
			AtomicInteger groupcountAtomic = new AtomicInteger(0);
			for (List<String> innerlist : res) {
				final int thread_pici = pici;
				executor.submit(new Runnable() {
					@Override
					public void run() {
						int groupcount = groupcountAtomic.incrementAndGet();
						double forcountdouble = Math.ceil(innerlist.size() / 400.0);
						int forcount = (int) forcountdouble;
						List<List<String>> msglist = CollectionUtil.averageAssign(innerlist, forcount);
						int classcount = 0;
						for (List<String> sendlist : msglist) {
							if (logger.isErrorEnabled()) {
								logger.error(String.format("taskid:%s,pici:%s,groupcount:%s,classcount:%s", taskid,
										thread_pici, groupcount, classcount));
							}
							classcount++;
							singleSendtaskMsg(taskid, model, pushMsg, sendlist);
							if (logger.isErrorEnabled()) {
								logger.error(String.format("taskid:%s,pici:%s,groupcount:%s,classcount:%s", taskid,
										thread_pici, groupcount, classcount));
							}
							// System.out.print("====400===");
							// System.out.println("====400===");
						}

						try {
//							executor.submit(new Runnable() {
//							@Override
//							public void run() {
								
//							}
//						});
							cb.await();
						} catch (InterruptedException | BrokenBarrierException e) {
							if (logger.isErrorEnabled()) {
								logger.error(ExceptionUtils.getStackTrace(e));
							}
						}

					}

				});
			}
			try {
				cb.await();
				pici++;
				sucessUserHandle(taskid);
				if (logger.isErrorEnabled()) {
					logger.error(String.format("taskid:%s,pici:%s is over,start next", taskid, pici - 1));
				}

			} catch (InterruptedException e) {
				if (logger.isErrorEnabled()) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}
			} catch (BrokenBarrierException e) {
				if (logger.isErrorEnabled()) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}
			}
		}

		return pici;
	}

	public void singleSendtaskMsg(String taskid, MBMessageModel model, String pushMsg, List<String> sendlist) {
		List<String> errorlist = mBMessageManager.sendMessage(model, sendlist, pushMsg);
		if (errorlist != null && errorlist.size() > 0) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			sendlist.removeAll(errorlist);
			dataMap.put("userids", sendlist);
			dataMap.put("state", 1);
			dataMap.put("taskid", taskid);
			// 更新用户任务完成情况
			motouserService.updateUsertaskmsg(dataMap);
//			executor.submit(new Runnable() {
//				@Override
//				public void run() {
//					sucessUserHandle(taskid);
//				}
//
//			});
			dataMap.clear();
			if (logger.isErrorEnabled()) {
				logger.error("任务id" + taskid + "-失败用户:" + JSON.toJSONString(errorlist));
			}
			// 失败任务
			dataMap.put("userids", errorlist);
			dataMap.put("state", 2);
			dataMap.put("taskid", taskid);
			motouserService.updateUsertaskmsg(dataMap);
//			executor.submit(new Runnable() {
//				@Override
//				public void run() {
//					Map<String, Object> map = Maps.newHashMap();
//					map.put("taskid", taskid);
//					for (String userid : errorlist) {
//						List<String> list = (List<String>) map.get(taskid);
//						if (list == null) {
//							list = Lists.newArrayList();
//						}
//						list.add(userid);
//						map.put("userid", list);
//					}
//					unfinishUserTaskQueue.offer(map);
//
//				}
//
//			});
		} else {
//			executor.submit(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						Thread.currentThread().sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("userids", sendlist);
					dataMap.put("state", 1);
					dataMap.put("taskid", taskid);
					if (logger.isErrorEnabled()) {
						logger.error(String.format("taskid:%s,useridlist_first:%s,insert start", taskid,sendlist.get(0)));
					}
					motouserService.updateUsertaskmsg(dataMap);
					if (logger.isErrorEnabled()) {
						logger.error(String.format("taskid:%s,useridlist_first:%s,insert end", taskid,sendlist.get(0)));
					}
//					sucessUserHandle(taskid);
				}
//			});
//		}
	}

	/**
	 * 成功处理用户
	 * 
	 * @param taskid
	 */
	private void sucessUserHandle(String taskid) {
		BlockingQueue<Map<String, Object>> queue = taskMapQueue.get(taskid);
		if (queue == null) {
			// 最大支持同一个任务20000次数据更新,800W信息推送
			queue = Queues.newArrayBlockingQueue(20000);
		}
		Map<String, Object> map = Maps.newHashMap();
//		map.put("sumcount", motouserService.getMessageTaskAllUserByTaskid(taskid));
//		map.put("successcount", motouserService.getUserTaskCount(taskid, 1));
//		map.put("failcount", motouserService.getUserTaskCount(taskid, 2));
		map.put("state", motouserService.getTaskMsgByTaskid(taskid).getState());
		try {
			queue.put(map);
			taskMapQueue.put(taskid, queue);
		} catch (InterruptedException e) {
			if (logger.isErrorEnabled()) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
	}

	@RequestMapping(value="/failTaskHandle")
	@ResponseBody
	private String failTaskHandle(String taskid) {
		if(StringUtils.isNotBlank(taskid)) {
//			if (unfinishUserTaskQueue != null) {
//				try {
						//未处理和处理失败
						againTask(motouserService.getTaskMsgByTaskid(taskid));
//					Map<String, Object> map = unfinishUserTaskQueue.take();
//					if (map != null) {
//						executor.submit(new Runnable() {
//							@Override
//							public void run() {
//								String taskid = map.get("taskid").toString();
//								if(taskid.equals(taskid)) {
//									MBMessageModel model = gettaskMessageModel(taskid);
//									String pushMsg = "您有一条新的消息，点击查看";
//									FenPiSendtaskMsg_new(taskid, model, pushMsg, 0);
//								}else {
//									unfinishUserTaskQueue.offer(map);
//								}
//								
//							}
//
//						});
//						
//					}
//				} catch (InterruptedException e) {
//					if (logger.isErrorEnabled()) {
//						logger.error(ExceptionUtils.getStackTrace(e));
//					}
//				}
				
				return "success";
//			}
		}
		return "error";


	}
	
	@RequestMapping(value="/allTaskHandle")
	@ResponseBody
	private String allTaskHandle(String taskid) {
		if(StringUtils.isNotBlank(taskid)) {
			doMessageTaskUser(taskid);
			return "success";
		}
		return "error";


	}

	public MBMessageModel gettaskMessageModel(String taskid) {

		MBMessageModel model = new MBMessageModel();
		BannerModel bannermodel = new BannerModel();
		MessageTaskModel messageTaskModel = motouserService.getTaskMsgByTaskid(taskid);
		if (messageTaskModel != null) {
			bannermodel.setTitle(messageTaskModel.getTitle());
			bannermodel.setSubtitle(messageTaskModel.getSubtitle());
			bannermodel.setDes(messageTaskModel.getDes());
			bannermodel.setLinktype(messageTaskModel.getLinktype());
			bannermodel.setImgurl(messageTaskModel.getImgurl());
			bannermodel.setLinkurl(messageTaskModel.getLinkurl());
			bannermodel.setGpid(messageTaskModel.getGpid());
			bannermodel.setNid(messageTaskModel.getNid());
			String type=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, messageTaskModel.getNid()+"_ninfo", "type");
			if(StringUtils.isNotBlank(type)) {
				bannermodel.setNtype(Integer.parseInt(type));
			}else {
				bannermodel.setNtype(0);
			}
			bannermodel.setKeyword(messageTaskModel.getKeyword());
			bannermodel.setSecondcarid(messageTaskModel.getSecondcarid());
			bannermodel.setMiniprogramid(messageTaskModel.getMiniprogramid());
			bannermodel.setBuserid(messageTaskModel.getBuserid());
			bannermodel.setGroupid(messageTaskModel.getGroupid());
		}
		model.setBannermodel(bannermodel);
		model.setMsgtype(MBMessageModel.MBMsgType_Banner);
		model.setMsgtime(System.currentTimeMillis());
		model.setContent(messageTaskModel.getTitle());
		SimpleUserModel simpleUserInfo = getSimpleUserInfo(Consts.TIM_ACTIVITYCENTERID);
		if (simpleUserInfo != null) {
			model.setSimpleusermodel(simpleUserInfo);
		}
		return model;

	}

	public SimpleUserModel getSimpleUserInfo(String userid) {
		if (userid == null) {
			return null;
		}
		SimpleUserModel mbUserModel = new SimpleUserModel();
		boolean isbusiness = userid.startsWith("business");
		if (isbusiness) {
			if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, userid + USERKEY_USERMAP)) {
				mbUserModel.userid = userid;
				mbUserModel.nickname = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER,
						userid + USERKEY_USERMAP, "name");
				mbUserModel.headurl = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER,
						userid + USERKEY_USERMAP, MAPKEY_HEADURL);
			}

		} else {
			String userkey = userid + USERKEY_USER;
			if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, userkey)
					&& RedisManager.getInstance().hexists(Consts.REDIS_SCHEME_USER, userkey, MAPKEY_USERID)) {
				Map<String, String> usermap = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER, userkey);
				mbUserModel.userid = usermap.get(MAPKEY_USERID);
				mbUserModel.gender = Integer
						.parseInt(usermap.get(MAPKEY_GENDER) == null ? "0" : usermap.get(MAPKEY_GENDER));
				mbUserModel.nickname = usermap.get(MAPKEY_NICKNAME);
				if (usermap.get(MAPKEY_HEADURL) != null && usermap.get(MAPKEY_HEADURL).length() > 0) {
					mbUserModel.headurl = usermap.get(MAPKEY_HEADURL);
				} else {
					mbUserModel.headurl = null;
				}
				mbUserModel.userprivilegelong = Long.parseLong(
						usermap.get(MAPKEY_USERPRIVILEGELONG) == null ? "0" : usermap.get(MAPKEY_USERPRIVILEGELONG));
				mbUserModel.level = Integer
						.parseInt(usermap.get(MAPKEY_LEVEL) == null ? "0" : usermap.get(MAPKEY_LEVEL));
				mbUserModel.signature = usermap.get(MAPKEY_SIGNATURE) == null ? "" : usermap.get(MAPKEY_SIGNATURE);
				mbUserModel.city = usermap.get(MAPKEY_CITY) == null ? "" : usermap.get(MAPKEY_CITY);
				mbUserModel.province = usermap.get(MAPKEY_PROVINCE) == null ? "" : usermap.get(MAPKEY_PROVINCE);
				mbUserModel.approvedes = usermap.get(MAPKEY_APPROVEDES) == null ? "" : usermap.get(MAPKEY_APPROVEDES);
				mbUserModel.mobileno = usermap.get(MAPKEY_MOBILENO) == null ? "" : usermap.get(MAPKEY_MOBILENO);

			}
			if (mbUserModel != null && StringUtils.isNotBlank(mbUserModel.userid)
					&& (mbUserModel.nickname == null || mbUserModel.nickname.isEmpty())) {
				Random rand = new Random();
				mbUserModel.nickname = "MotoBand+" + (rand.nextInt(5000000) + 1000000);
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + USERKEY_USER, MAPKEY_NICKNAME,
						mbUserModel.nickname);
			}
		}
		if (!(mbUserModel != null && StringUtils.isNotBlank(mbUserModel.userid))) {
			return null;
		}

		return mbUserModel;
	}

}
