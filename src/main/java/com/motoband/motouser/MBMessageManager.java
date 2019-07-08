package com.motoband.motouser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.motoband.util.Consts;
import com.motoband.util.RedisManager;
import com.motoband.util.tim.TIMCustomElem;
import com.motoband.util.tim.TIMMessage;
import com.motoband.util.tim.TIMMessageBatchResp;
import com.motoband.util.tim.TIMMessageError;
import com.motoband.util.tim.TIMMessageErrorCode;
import com.motoband.util.tim.TIMMsgElement;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class MBMessageManager {

	private static final  Logger logger = LoggerFactory.getLogger(MBMessageManager.class);

	public static final MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");

	private static OkHttpClient httpClient = new OkHttpClient();

	private MBMessageManager() {

	}

	
	
	public static final String USERKEY_USER = "_user";
	
	
	public static final String MAPKEY_COMMENTSWITCH = "commentswitch";
	public static final String MAPKEY_LIKESWITCH = "likeswitch";
	public static final String MAPKEY_IMSWITCH = "imswitch";
	public static final String MAPKEY_SYSTEMSWITCH = "systemswitch";
	public static final String MAPKEY_FOLLOWSWITCH = "followswitch";
	public static final String MAPKEY_RIDESHARESWITCH = "rideshareswitch";
	public static final String MAPKEY_NEARUSERSWITCH = "nearuserswitch";
	
	

	public List<String> sendMessage(MBMessageModel model, List<String> inviteList, String pushMsg) {
		List<String>  returnList = new ArrayList<String>();
		try {
			List<String> pushList=new ArrayList<String>();
			List<String> nopushList=new ArrayList<String>();
			if(inviteList==null || inviteList.size()==0){
				return null;
			}
			
			
			
			int pushType = 0;
			int PushFlag = 1;
			String switchcheck = null;
			for (int i = 0; i < inviteList.size(); i++) {
				switch (model.msgtype) {
					case MBMessageModel.MBMsgType_FollowNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_FOLLOWSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_FollowNotify;
	
						break;
					case MBMessageModel.MBMsgType_NewsLikeNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_LIKESWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_NewsLikeNotify;
						break;
					case MBMessageModel.MBMsgType_NewsCommentNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_COMMENTSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_NewsCommentNotify;
						break;
					case MBMessageModel.MBMsgType_NewsCommentReplyNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_COMMENTSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_NewsCommentReplyNotify;
						break;
					case MBMessageModel.MBMsgType_NewsRecommendNotify:
						pushType = PushManager.PushType_NewsRecommendNotify;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_UserRecommendNotify:
						pushType = PushManager.PushType_UserRecommendNotify;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_GiftNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_SYSTEMSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_GiftNotify;
						break;
					case MBMessageModel.MBMsgType_DiscussInviteNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_SYSTEMSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_DiscussInviteNotify;
						break;
					case MBMessageModel.MBMsgType_BusinessCommentNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_COMMENTSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_BusinessCommentNotify;
						break;
					case MBMessageModel.MBMsgType_BusinessReplyCommentNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_SYSTEMSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_BusinessReplyCommentNotify;
						break;
					case MBMessageModel.MBMsgType_JoinDiscussNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_SYSTEMSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_JoinDiscussNotify;
						break;
					case MBMessageModel.MBMsgType_CommentSecondCarNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_COMMENTSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_CommentSecondCarNotify;
						break;
					case MBMessageModel.MBMsgType_CommentReplySecondCarNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_COMMENTSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_CommentReplySecondCarNotify;
						break;
					case MBMessageModel.MBMsgType_MentionNotify:
						switchcheck = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, inviteList.get(i) + USERKEY_USER, MAPKEY_SYSTEMSWITCH);
						if ((switchcheck == null ? "0" : switchcheck).equals("0")) {
							PushFlag = 0;
						}
						pushType = PushManager.PushType_MentionNotify;
						break;
					case MBMessageModel.MBMsgType_JoinVoiceRoom:
	
						pushType = PushManager.PushType_JoinVoiceRoom;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_ExitVoiceRoom:
	
						pushType = PushManager.PushType_ExitVoiceRoom;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_DissolveVoiceRoom:
	
						pushType = PushManager.PushType_DissolveVoiceRoom;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_KickoutVoiceRoom:
	
						pushType = PushManager.PushType_KickoutVoiceRoom;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_UpdateMemeberid:
	
						pushType = PushManager.PushType_UpdateMemeberid;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_JoinLBSTeam:
	
						pushType = PushManager.PushType_JoinLBSTeam;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_ExitLBSTeam:
	
						pushType = PushManager.PushType_ExitLBSTeam;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_DissolveLBSTeam:
	
						pushType = PushManager.PushType_DissolveLBSTeam;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_KickoutLBSTeam:
	
						pushType = PushManager.PushType_KickoutLBSTeam;
						PushFlag = 0;
						break;
	
//					case MBMessageModel.MBMsgType_ContinueVoiceRoom:
//	
//						pushType = PushManager.PushType_ContinueVoiceRoom;
//						PushFlag = 0;
//						break;
					case MBMessageModel.MBMsgType_ReplaceVoiceRoomAdmin:
	
						pushType = PushManager.PushType_ReplaceVoiceRoomAdmin;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_ReplaceLBSTeamAdmin:
	
						pushType = PushManager.PushType_ReplaceLBSTeamAdmin;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_Banner:
						
						pushType = PushManager.PushType_Banner;
						PushFlag = 0;
						break;
					case MBMessageModel.MBMsgType_YZCustomServiceMessage:
						
						pushType = PushManager.PushType_YZ_CustomService_Message;
						PushFlag = 0;
						break;
					default:
						break;
				}
				
			   if(PushFlag == 0){//0表示推送，1表示不离线推送。
				   pushList.add(inviteList.get(i));
				}else{
				   nopushList.add(inviteList.get(i));
				}
				
			}
			
            if(pushList!=null && pushList.size()>0){
            	List<String> pushRetuenList =sendMessageMethod(model, pushList, pushMsg, pushType, 0);
            	if(pushRetuenList!=null && pushRetuenList.size()>0){
            		returnList.addAll(pushRetuenList);
            	}
            }
            
            if(nopushList!=null && nopushList.size()>0){
            	List<String>  nopushRetuenList =sendMessageMethod(model, nopushList, pushMsg, pushType, 1);
            	if(nopushRetuenList!=null && nopushRetuenList.size()>0){
            		returnList.addAll(nopushRetuenList);
            	}
            }

		

		} catch (Exception e) {
				logger.error(this.getClass().getSimpleName(), e);
		}
		return returnList;
	}

	private List<String> sendMessageMethod(MBMessageModel model, List<String> inviteList, String pushMsg, int pushType, int PushFlag) {
		List<String>   errorrespuseridlist = new ArrayList<String>();
		TIMMessage timMessage = new TIMMessage();
		timMessage.To_Account = inviteList;
		timMessage.MsgRandom = (int) (Math.random() * 100000000);

		TIMMsgElement timMsgElement = new TIMMsgElement();
		timMsgElement.MsgType = TIMMessage.MessageType_TIMCustomElem;

		TIMCustomElem timCustomElem = new TIMCustomElem();
		timCustomElem.Data = JSON.toJSONString(model);
		timCustomElem.Desc = pushMsg;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ptype", pushType);
		timCustomElem.Ext = JSON.toJSONString(map);
		timMsgElement.MsgContent = timCustomElem;

		ArrayList<TIMMsgElement> list = new ArrayList<TIMMsgElement>();
		list.add(timMsgElement);

		timMessage.MsgBody = list;
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("PushFlag", PushFlag);
		tempMap.put("Desc", pushMsg);
		tempMap.put("Ext", JSON.toJSONString(map));
		timMessage.OfflinePushInfo = tempMap;

		logger.info(this.getClass().getSimpleName() + "	sendMessage TIMMessage is:" + timMessage.toString());

		String reqBody = JSON.toJSONString(timMessage);

		RequestBody body = RequestBody.create(MediaType_JSON, reqBody);
		// 新IM 消息发送
		String timsig=Consts.TIM_AdminSig;
		String timuserid=Consts.TIM_AdminUserID;
		if(model.msgtype == MBMessageModel.MBMsgType_YZCustomServiceMessage){
			 timsig=Consts.TIM_YZCUSTOMERSIG;
			 timuserid=Consts.TIM_YZCUSTOMERID;
		}else if(model.msgtype == MBMessageModel.MBMsgType_Banner){
			 timsig=Consts.TIM_ACTIVITYCENTERSIG;
			 timuserid=Consts.TIM_ACTIVITYCENTERID;
		}
		Random random = new Random();
		String url = String.format(Consts.TIM_SendBatchMessageUrl, timsig, timuserid, Consts.TIM_Appid,random.nextInt(100000000));
		
		Request request = new Request.Builder().url(url).post(body).build();
		httpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
					logger.error(this.getClass().getSimpleName() + "  SendBatchMessage onFailure.", e);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String result = new String(response.body().bytes(),"utf-8");
				if(logger.isInfoEnabled()) {
					logger.info(this.getClass().getSimpleName() + "  SendBatchMessage onResponse:" + response);
				}
					

				if (response.isSuccessful()) {
					TIMMessageBatchResp timMessageBatchResp = JSON.parseObject(result, TIMMessageBatchResp.class);

					if (timMessageBatchResp.ActionStatus.equals(TIMMessageErrorCode.TIM_ErrorCode_OK)) {
							logger.info(this.getClass().getSimpleName() + "  SendBatchMessage  successful .Resp is :" + result);

					} else {
						ArrayList<TIMMessageError> errorList = timMessageBatchResp.ErrorList;

						if (errorList != null) {
							for (TIMMessageError timMessageError : errorList) {
								//
								errorrespuseridlist.add(timMessageError.To_Account);
							}
						}

							logger.error(this.getClass().getSimpleName() + "  SendBatchMessage resp not successful .Resp is :" + result);
					}
				} else {
						logger.error(this.getClass().getSimpleName() + "  SendBatchMessage error:resp code :" + response.code());
				}

			}
		});
		return errorrespuseridlist;
	}

//	/***
//	 * 发送cms的消息
//	 * 
//	 * @param type
//	 *            0内容精选 1达人 2认证
//	 * @param nid
//	 * @param userid
//	 * @return
//	 */
//	public MBResponse sendCMSMessage(int type, String nid, String userid) {
//		MBMessageModel model = new MBMessageModel();
//		String pushMsg = "";
//		switch (type) {
//		case 0:
//			model.msgtype = MBMessageModel.MBMsgType_NewsRecommendNotify;
//			model.content = "你发布的内容已被添加为精选";
//			model.nid = nid;
//			model.ntype = Integer.parseInt(RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + NewsManager.NEWSKEY_NEWSINFO, NewsManager.MAPKEY_TYPE));
//			model.picurl = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + NewsManager.NEWSKEY_NEWSINFO, NewsManager.MAPKEY_PICURL);
//			pushMsg = "你发布的内容已被添加为精选";
//			break;
//		case 1:
//			model.msgtype = MBMessageModel.MBMsgType_UserRecommendNotify;
//			model.content = "恭喜你被评为达人";
//			pushMsg = "恭喜你被评为达人";
//			break;
//		case 2:
//			model.msgtype = MBMessageModel.MBMsgType_UserRecommendNotify;
//			model.content = "恭喜你成为认证用户";
//			pushMsg = "恭喜你成为认证用户";
//			break;
//		case 3:
//			model.msgtype = MBMessageModel.MBMsgType_NewsRecommendNotify;
//			model.content = "你发布的内容已评为精选并入选摩托邦首页";
//			model.nid = nid;
//			model.ntype = Integer.parseInt(RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + NewsManager.NEWSKEY_NEWSINFO, NewsManager.MAPKEY_TYPE));
//			model.picurl = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + NewsManager.NEWSKEY_NEWSINFO, NewsManager.MAPKEY_PICURL);
//			pushMsg = "你发布的内容已评为精选并入选摩托邦首页";
//			break;
//		default:
//			break;
//		}
//		model.msgtime = System.currentTimeMillis();
//		model.msgid = MBUtil.getUUID();
//		SimpleUserModel simuser= getInstance().getSimpleUserInfo("admin", "admin", false, false);
//		if(simuser!=null){
//			model.simpleusermodel=simuser;
//			//兼容老版本
//			model.fromuserid="admin";
//			model.fromnickname=model.simpleusermodel.nickname;
//		}
//
//		List<String> list = new ArrayList<String>();
//
//		list.add(userid);
//		
//		MBMessageManager.getInstance().sendMessage(model, list, pushMsg);
//		return MBResponse.getMBResponse(MBResponseCode.SUCCESS);
//	}
}
