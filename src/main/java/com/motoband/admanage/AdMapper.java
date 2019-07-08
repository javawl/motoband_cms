package com.motoband.admanage;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motoband.boxmanage.Boxlist;
import com.motoband.news.topic;
import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface AdMapper {
	int getAdListCount();

	ArrayList<AdModel> getAdList(@Param("userGuid")String userGuid, @Param("start")int start,@Param("limit") int limit,
			@Param("order")int order);

	AdModel selectAdById(@Param("adid")int adid);

	void deleteAdById(@Param("adid")int adid);

	void updateAd(AdModel adModel);

	void insertAdImg(AdimgModel adimgModel);

	void addAd(AdModel adModel);

	List<AdimgModel> getAdimgByadid(@Param("adid")int adid);

	void deleteAdimgById(@Param("adimgid")int adimgid);

	AdimgModel getAdimgByadImgid(@Param("adimgid")int adimgid);

	void updateAdImg(AdimgModel adimgModel);

	void deleteAdimgByAdId(@Param("adid")int adid);

	List<MotobandGPModel> getmotoGp();

	List<topic> getmotoKeywords();

	List<Boxlist> getmotoBoxid();

	

}
