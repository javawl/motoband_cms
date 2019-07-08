package com.motoband.brandparentmanage;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface BrandparentMapper {

	int getBrandparentlistCount();

	ArrayList<BrandparentModel> getBrandparentlist(@Param("start")int start, @Param("limit")int limit,
			@Param("order")int order);

	BrandparentModel getBrandparentByid(@Param("bpid")String bpid);

	void updatebrandparentByid(BrandparentModel brandparentModel);

	void insbrandparent(BrandparentModel brandparentModel);

	ArrayList<BrandparentModel> getBrandparentlistSimple();

}
