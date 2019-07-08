package com.motoband.motoschool;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MotoschoolService {
@Autowired
private MotoschoolMapper motoschoolMapper;

public int getMotoschoolPackageListCount() {
	return motoschoolMapper.getMotoschoolPackageListCount();
}

public ArrayList<MotoschoolPackageModel> getMotoschoolPackageList(int start, int limit,
		int order, String orderConditions) {
	return motoschoolMapper.getMotoschoolPackageList(start,  limit,
			 order, orderConditions);
}

public MotoschoolPackageModel getPackageBypid(String pid) {
	return motoschoolMapper.getPackageBypid(pid);
}

public void updatePackage(MotoschoolPackageModel motoschoolPackageModel) {
	motoschoolMapper.updatePackage(motoschoolPackageModel);
}

public void insPackage(MotoschoolPackageModel motoschoolPackageModel) {
	motoschoolMapper.insPackage(motoschoolPackageModel);
}

public int getMotoschoolVideoListCount() {
	return motoschoolMapper.getMotoschoolVideoListCount();
}

public ArrayList<MotoschoolVideoModel> getMotoschoolVideoList(int start,
		int limit, int order, String orderConditions) {
	return motoschoolMapper.getMotoschoolVideoList(start,limit, order,orderConditions);
}

public ArrayList<MotoschoolPackageModel> getPackageTitleList() {
	return  motoschoolMapper.getPackageTitleList();
}

public MotoschoolVideoModel getVideoBysid(String sid) {
	return motoschoolMapper.getVideoBysid(sid);
}

public void updateVideo(MotoschoolVideoModel motoschoolVideoModel) {
	motoschoolMapper.updateVideo(motoschoolVideoModel);
	
}

public void insVideo(MotoschoolVideoModel motoschoolVideoModel) {
	motoschoolMapper.insVideo( motoschoolVideoModel);
	
}

public int getMotoschoolBoxListCount() {
	return motoschoolMapper.getMotoschoolBoxListCount();
}

public ArrayList<MotoschoolBoxModel> getMotoschoolBoxList(int start, int limit,
		int order, String orderConditions) {
	return motoschoolMapper.getMotoschoolBoxList(start,  limit, order,  orderConditions);
}

public MotoschoolBoxModel getBoxBybid(String bid) {
	return motoschoolMapper.getBoxBybid(bid);
}

public void updateBox(MotoschoolBoxModel motoschoolBoxModel) {
	motoschoolMapper.updateBox(motoschoolBoxModel);	
}

public void insBox(MotoschoolBoxModel motoschoolBoxModel) {
	motoschoolMapper.insBox(motoschoolBoxModel);
	
}
}
