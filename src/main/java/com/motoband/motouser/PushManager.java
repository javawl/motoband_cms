package com.motoband.motouser;

public class PushManager {
	private static final PushManager pushManager = new PushManager();
//	private static final Tracer _tracer = Tracer.create(PushManager.class);

//	public static final int PushType_Other = 0;
//	public static final int PushType_FollowNotify = 3;
//	public static final int PushType_NewsLikeNotify = 4;
//	public static final int PushType_NewsCommentNotify = 5;
//	public static final int PushType_NewsCommentReplyNotify = 6;
//	public static final int PushType_IMMessageNotify = 18;
//
//	public static final int PushType_NewsRecommendNotify = 20;// 动态加精
//	public static final int PushType_UserRecommendNotify = 21;// 用户加推荐
//	public static final int PushType_MallNotify = 30;
//	public static final int PushType_TopicNotify = 31;
//	public static final int PushType_MGPNotify = 32;
//	public static final int PushType_NewsNormalNotify = 35;
//	public static final int PushType_NewsStoryNotify = 36;
//	public static final int PushType_NewsBoxNotify = 37;
//	public static final int PushType_RunNotify = 38;
//
//	public static final int PushType_DiscussInviteNotify = 39;// 讨论邀请
//	public static final int PushType_DiscussNotify = 40;// 讨论推送
//	public static final int PushType_NewsDiscussNotify = 41;// 讨论类型动态推送 ：参与讨论
//
//	public static final int PushType_BusinessCommentNotify = 42; // 评价商家
//	public static final int PushType_BusinessReplyCommentNotify = 43;// 商家回复
//	public static final int PushType_GiftNotify = 45;// 收到礼物
//	public static final int PushType_GiftGivenNotify = 46;// 转增礼物
//	public static final int PushType_JoinDiscussNotify = 47;//参与讨论
//	public static final int PushType_CommentSecondCarNotify = 48;//留言二手车
//	public static final int PushType_CommentReplySecondCarNotify = 49;//回复二手车留言
//	public static final int PushType_YZMallNotify=50;//有赞商城通知
//	public static final int PushType_YZMallProductNotify=51;//有赞商城商品通知
//	public static final int PushType_SecondCarNotify=52;//二手车
//	
//	public static final int PushType_MentionNotify=54;//@通知
//	
//	//2.8.0
//	public static final int PushType_JoinVoiceRoom=56;//加入语音房间
//	public static final int PushType_ExitVoiceRoom=57;//退出语音房间
//	public static final int PushType_DissolveVoiceRoom=58;//解散语音房间
//	public static final int PushType_KickoutVoiceRoom=59;//被踢出语音房间
//	public static final int PushType_UpdateMemeberid=60;//更新Memeberid
//	
//	public static final int PushType_JoinLBSTeam=61;//加入组队
//	public static final int PushType_ExitLBSTeam=62;//退出组队
//	public static final int PushType_DissolveLBSTeam=63;//解散组队
//	public static final int PushType_KickoutLBSTeam=64;//被踢出组队
//	//3.2.0
////	public static final int PushType_ContinueVoiceRoom=65;//续语音房间
//	public static final int PushType_YZ_CustomService_Message=66;//有赞客服消息
//	public static final int PushType_ReplaceVoiceRoomAdmin=67;//更换语音房主
//	public static final int PushType_ReplaceLBSTeamAdmin=68;//更换组队房主
//	
//	
	public static final int PushType_Banner=69;//banner类型消息
	
	
	private PushManager() {
		
	}

	public static PushManager getInstance() {
		return pushManager;
	}
	

	/**
	 * 单个用户推送,无时间间隔限制
	 * @param userid
	 * @param pushContent
	 * @param pushModel
	 * @throws Exception 
	 */
//	public void sendPushToUser(String userid, String content, PushModel pushModel) throws Exception {
//		List<String> list=Lists.newArrayListWithCapacity(1);
//		if(StringUtils.isNotBlank(userid)) {
//			if(UserManager.getInstance().checkUserExist(userid)){
//				String systemswitchstr=UserManager.getInstance().getUserSomeProperty(userid, UserManager.MAPKEY_SYSTEMSWITCH);
//				String channelstr=UserManager.getInstance().getUserSomeProperty(userid, UserManager.MAPKEY_CHANNEL);
//				if(StringUtils.isNotBlank(systemswitchstr)){
//					if(Integer.parseInt(systemswitchstr)==0 ) {
//						list.add(userid);
//						if (channelstr.equals("10000")) {
//							this.pushIOSUser(list, content, pushModel);
//						}else {
//							this.pushAndroidUser(list, content, pushModel);
//						}
//
//					}
//				}
//			}
//			
//		}
//	}
//
//	public void sendPushToUser(List<String> accountList, String content, PushModel pushModel) {
//		try {
//			List<String> iosList = new ArrayList<String>();
//			List<String> androidList = new ArrayList<String>();
//			long currenttime = System.currentTimeMillis();
//			Date d = new Date();
//			int hours = d.getHours();
//			for (String userid : accountList) {
//				if(UserManager.getInstance().checkUserExist(userid)){
//					String systemswitchstr=UserManager.getInstance().getUserSomeProperty(userid, UserManager.MAPKEY_SYSTEMSWITCH);
//					String lastpushnewstimestr=UserManager.getInstance().getUserSomeProperty(userid, UserManager.MAPKEY_LASTPUSHNEWSTIME);
//					String channelstr=UserManager.getInstance().getUserSomeProperty(userid, UserManager.MAPKEY_CHANNEL);
//					if(StringUtils.isNotBlank(systemswitchstr) &&StringUtils.isNotBlank(lastpushnewstimestr) ){
//						if (Integer.parseInt(systemswitchstr) == 1 || (currenttime - Long.parseLong(lastpushnewstimestr)) < 3600 * 1000 ||(hours<8 ||hours>23)) {
//							continue;
//						}
//						UserManager.getInstance().updateUserValue(userid, UserManager.MAPKEY_LASTPUSHNEWSTIME, String.valueOf(currenttime));
//						
//						if (channelstr.equals("10000")) {// IOS
//							iosList.add(userid);
//						}else {// Android
//							androidList.add(userid);
//						}
//					}
//				}
//				
//			}
//			if(iosList!=null&&iosList.size()>0) {
//				this.pushIOSUser(iosList, content, pushModel);
//			}
//			if(androidList!=null&&androidList.size()>0) {
//				this.pushAndroidUser(androidList, content, pushModel);
//			}
//		} catch (Exception e) {
//			if (_tracer.ErrorAvailable()) {
//				_tracer.Error("PushManager sendPushToUser Exception:" + e);
//			}
//		}
//	}
//
//	private void pushIOSUser(List<String> accountList, String content, PushModel pushModel) {
//
//		try {
//			XingeApp push = new XingeApp(Consts.XG_ACCESSID_IOS, Consts.XG_SECRETKEY_IOS);
//			MessageIOS message = new MessageIOS();
//			message.setAlert(content);
//			message.setSound("default");
//			Map<String, Object> custom = new HashMap<String, Object>();
//			custom.put("ext", JSONObject.toJSONString(pushModel));
//
//			message.setCustom(custom);
//			org.json.JSONObject ret = push.createMultipush(message, Consts.XINGIOSENV);
//
//			if (ret.getInt("ret_code") != 0) {
//				if (_tracer.ErrorAvailable()) {
//					_tracer.Error("PushManager pushIOSUser Error :" + ret.toString());
//				}
//			} else {
//
//				long pushID = ret.getJSONObject("result").getLong("push_id");
//
//				List<List<String>> groupList = createList(accountList, 1000);
//				for (List<String> list : groupList) {
//					org.json.JSONObject result = null;
//					result = push.pushAccountListMultiple(pushID, list);
//					if (_tracer.InfoAvailable()) {
//						_tracer.Info("PushManager pushIOSUser :" + result.toString());
//					}
//				}
//
//			}
//		} catch (Exception e) {
//			if (_tracer.ErrorAvailable()) {
//				_tracer.Error("PushManager pushIOSUser Exception:" + e);
//			}
//			e.printStackTrace();
//		}
//	}
//
//	private void pushAndroidUser(List<String> accountList, String content, PushModel pushModel) {
//
//		try {
//			XingeApp push = new XingeApp(Consts.XG_ACCESSID_ANDROID, Consts.XG_SECRETKEY_ANDROID);
//			Message message = new Message();
//
//			message.setTitle("摩托邦");
//			message.setContent(content);
//			message.setType(Message.TYPE_NOTIFICATION);
//
//			Map<String, Object> custom = new HashMap<String, Object>();
//			custom.put("ext", JSONObject.toJSONString(pushModel));
//
//			message.setCustom(custom);
//
//			org.json.JSONObject ret = push.createMultipush(message);
//			if (ret.getInt("ret_code") != 0) {
//				if (_tracer.ErrorAvailable()) {
//					_tracer.Error("PushManager pushAndroidUser Error :" + ret.toString());
//				}
//			} else {
//
//				long pushID = ret.getJSONObject("result").getLong("push_id");
//
//				List<List<String>> groupList = createList(accountList, 1000);
//				for (List<String> list : groupList) {
//					org.json.JSONObject result = null;
//					result = push.pushAccountListMultiple(pushID, list);
//					if (_tracer.InfoAvailable()) {
//						_tracer.Info("PushManager pushAndroidUser :" + result.toString());
//					}
//				}
//
//			}
//
//		} catch (Exception e) {
//			if (_tracer.ErrorAvailable()) {
//				_tracer.Error("PushManager pushAndroidUser Exception:" + e);
//			}
//			e.printStackTrace();
//		}
//	}
//
//	public static List<List<String>> createList(List<String> targe, int size) {
//		List<List<String>> listArr = new ArrayList<List<String>>();
//		// 获取被拆分的数组个数
//		int arrSize = targe.size() % size == 0 ? targe.size() / size : targe.size() / size + 1;
//		for (int i = 0; i < arrSize; i++) {
//			List<String> sub = new ArrayList<String>();
//			// 把指定索引数据放入到list中
//			for (int j = i * size; j <= size * (i + 1) - 1; j++) {
//				if (j <= targe.size() - 1) {
//					sub.add(targe.get(j));
//				}
//			}
//			listArr.add(sub);
//		}
//		return listArr;
//	}
//
//	public static void main(String agrs[]) {
//		
//		String aa="86-15588655565";
//		System.out.println(aa.hashCode()%10);
////		Date d = new Date();
////		int hours = d.getHours();
////		System.out.println(hours);
////		List<String> accountList = new ArrayList<String>();
////		accountList.add("86-13810008710");
////		accountList.add("86-15910301209");
////		accountList.add("86-18810091131");
////		accountList.add("86-18236889261");
////		PushModel pushModel = new PushModel();
////		pushModel.ptype = 36;
////		pushModel.nid = "920940CE0E08411C8145DF2B96109670";
////
////		PushManager.getInstance().sendPushToUser(accountList, "xg test11111", pushModel);
//	}

}
