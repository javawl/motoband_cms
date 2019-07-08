package com.motoband.motoschool;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface MotoschoolMapper {

	int getMotoschoolPackageListCount();

	ArrayList<MotoschoolPackageModel> getMotoschoolPackageList(@Param("start")int start, @Param("limit")int limit,
			@Param("order")int order, @Param("orderConditions")String orderConditions);

	MotoschoolPackageModel getPackageBypid(@Param("pid")String pid);

	void updatePackage(MotoschoolPackageModel motoschoolPackageModel);

	void insPackage(MotoschoolPackageModel motoschoolPackageModel);

	int getMotoschoolVideoListCount();

	ArrayList<MotoschoolVideoModel> getMotoschoolVideoList(@Param("start")int start, @Param("limit")int limit,
			@Param("order")int order, @Param("orderConditions")String orderConditions);

	ArrayList<MotoschoolPackageModel> getPackageTitleList();

	MotoschoolVideoModel getVideoBysid(@Param("sid")String sid);

	void updateVideo(MotoschoolVideoModel motoschoolVideoModel);

	void insVideo(MotoschoolVideoModel motoschoolVideoModel);

	int getMotoschoolBoxListCount();

	ArrayList<MotoschoolBoxModel> getMotoschoolBoxList(@Param("start")int start, @Param("limit")int limit,
			@Param("order")int order, @Param("orderConditions")String orderConditions);

	MotoschoolBoxModel getBoxBybid(@Param("bid")String bid);

	void updateBox(MotoschoolBoxModel motoschoolBoxModel);

	void insBox(MotoschoolBoxModel motoschoolBoxModel);

}
