package com.motoband.boxmanage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class boxService {

	@Autowired
	private boxMapper boxMapper;

	public ArrayList<Boxlist> getBoxList(String userGuid, int start,int limit,int order, String tittle,String orderConditions) {

		int sel = boxMapper.checkPrivilege("box_sel", userGuid);
		if (sel != 0) {
			return boxMapper.getBoxList(start,limit,order,tittle,orderConditions);
		} else {
			return null;
		}
	}
	public int getBoxListCount(String tittle){
		return boxMapper.getBoxListCount(tittle);
	}

	public int getUserUpdateCheck(String userGuid) {

		return boxMapper.checkPrivilege("box_upd", userGuid);
	}

	public int getUserDelCheck(String userGuid) {

		return boxMapper.checkPrivilege("box_del", userGuid);
	}

	public int getUserInsCheck(String userGuid) {

		return boxMapper.checkPrivilege("box_ins", userGuid);
	}

	public ArrayList<Boxtype> getBoxTypeList() {

		return boxMapper.getBoxTypeList();
	}

	public int insMotoBox(motobox motobox) {

		boxMapper.insMotoBox(motobox);
		return Integer.parseInt(motobox.getBoxid());
	}

	public void insMotoNote(noteModel noteModel) {

		boxMapper.insMotoNote(noteModel);
	}

	public com.motoband.boxmanage.motobox getMotoBoxMessageByBoxid(String box_id) {

		return boxMapper.getMotoBoxMessageByBoxid(box_id);
	}

	public com.motoband.boxmanage.noteModel getNoteModelMessageByBoxid(String box_id) {

		return boxMapper.getNoteModelMessageByBoxid(box_id);
	}

	public ArrayList<motoimg> getMotoImgList() {

		return boxMapper.getMotoImgList();
	}

	public void insMotoImg(motoimg motoimg) {

		boxMapper.insMotoImg(motoimg);
	}

	public void delMotoImg(String img_guid) {

		boxMapper.delMotoImg(img_guid);
	}

	public void updateBoxMessage(motobox motobox) {

		boxMapper.updateBoxMessage(motobox);
	}

	public void updateNoteMessage(noteModel noteModel) {

		boxMapper.updateNoteMessage(noteModel);
	}

	public void updatePath(String box_id, String localpath, String showurl) {

		boxMapper.updatePath(box_id, localpath, showurl);
	}

	public String getPageLocalPath() {

		return boxMapper.getPageLocalPath();
	}

	public String getBoxUrlIP() {

		return boxMapper.getBoxUrlIP();
	}

	public ArrayList<Boxlist> getBoxList(String userGuid, String nowtypeid,int start,int limit,int order,String tittle,String orderConditions) {

		int sel = boxMapper.checkPrivilege("box_sel", userGuid);
		if (sel != 0) {
			return boxMapper.getBoxListByType(nowtypeid,start,limit,order,tittle,orderConditions);
		} else {
			return null;
		}

	}

	@Transactional
	public void delBoxMessage(String box_id) {

		boxMapper.delBoxMessage(box_id);
		boxMapper.delNoteMessage(box_id);
	}

	public ArrayList<imggroup> getImgGroupList() {

		return boxMapper.getImgGroupList();
	}

	public ArrayList<motoimg> getMotoImgListByGroupGuid(String group_guid) {

		return boxMapper.getMotoImgListByGroupGuid(group_guid);

	}

	public void updateImgName(String img_guid, String newname) {

		boxMapper.updateImgName(img_guid, newname);

	}

	public String selectBoxIdByGuid(String img_guid) {

		return boxMapper.selectBoxIdByGuid(img_guid);
	}

	public void saveimgNewGroup(String img_guid, String imgnewgroup) {

		boxMapper.saveimgNewGroup(img_guid, imgnewgroup);
	}

	public String checkGroupName(String groupname) {

		return boxMapper.checkGroupName(groupname);
	}

	public void addImgGroup(String groupid, String groupname) {

		boxMapper.addImgGroup(groupid, groupname);
	}

	public void addImgBox(String img_guid, int boxid) {

		String box_id = String.valueOf(boxid);
		String ib_guid = UUID.randomUUID().toString();
		motoimgbox motoimgbox = new motoimgbox();
		motoimgbox.setIb_guid(ib_guid);
		motoimgbox.setImg_guid(img_guid);
		motoimgbox.setBox_id(box_id);
		boxMapper.addImgBox(motoimgbox);

	}

	public void delImgBox(String box_id) {

		boxMapper.delImgBox(box_id);
	}

	public void addTitleImg(String box_id, String titleimg) {

		boxMapper.addTitleImg(box_id, titleimg);
	}

	public String getTitleImage(String box_id) {

		return boxMapper.getTitleImage(box_id);
	}

	public long getBoxLook(String boxid) {
		
		return boxMapper.getBoxLook(boxid);
	}

	public List<BoxRecommendModel> getBoxRecommendList() {
		
		return boxMapper.getBoxRecommendList();
	}

	public int checkUserApprove(String userid) {
		return boxMapper.checkUserApprove(userid);
		
	}

	public BoxRecommendModel getRecommend(Long recommendBoxID) {
		return boxMapper.getRecommend(recommendBoxID);
		
	}

	public void updateBoxRecommdState(String recommendBoxID) {
		boxMapper.updateBoxRecommdState(recommendBoxID);
		
	}

	public void delFromBoxRecommend(String recommendBoxID) {
		boxMapper.updateBoxRecommdState(recommendBoxID);
	}

	public void addHotBox(String boxid) {
		boxMapper.addHotBox(boxid);	
	}

	public void delHotBox(String boxid) {
		boxMapper.delHotBox(boxid);	
	}

	public List<BoxBannerModel> getBoxBannerList() {
		
		return boxMapper.getBoxBannerList();
	}

	public void delBanner(String bannerid) {
		boxMapper.delBanner(bannerid);
	}

	public void updatebanner(BoxBannerModel banner) {
		boxMapper.updatebanner(banner);
		
	}

	public motobox getBoxByBoxid(String boxid) {
		
		return boxMapper.getBoxByBoxid(boxid);
	}

	public void addBoxBanner(BoxBannerModel banner) {
		boxMapper.addBoxBanner(banner);
		
	}

	public List<BoxKeywordModel> getBoxKeywordList() {
		return boxMapper.getBoxKeywordList();
	}

	public void delboxkeyword(String bannerid) {
		 boxMapper.delboxkeyword(bannerid);
		
	}

	public void updateboxkeyword(BoxKeywordModel boxKeywordModel) {
		boxMapper.updateboxkeyword(boxKeywordModel);
		
	}

	public void addBoxKeyword(String keyword) {
		boxMapper.addBoxKeyword(keyword);
	}
	public int getBoxListBytypidCount(String nowtypeid, String tittle) {
		
		return boxMapper.getBoxListBytypidCount(nowtypeid,tittle);
	}
	public Boxtype getBoxTypeByTypeid(String typeid) {
		return boxMapper.getBoxTypeByTypeid(typeid);
	}
	public ArrayList<Boxlist> getBoxTitleList() {
		return boxMapper.getBoxTitleList();
	}
	public void updateNewsStatus(int news,String boxid) {
		boxMapper.updateNewsStatus(news,boxid);
		
	}

}
