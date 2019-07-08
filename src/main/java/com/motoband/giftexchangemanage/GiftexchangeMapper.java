package com.motoband.giftexchangemanage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;
@MyBatisRepository
public interface GiftexchangeMapper {

	int getGiftexchangeCount();

	List<GiftexchangeModel> getGiftexchangelist(@Param("start")int start, @Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions);

	List<GiftModel> getGiftList();

	void addGiftexchange(GiftexchangeModel giftexchangeModel);

	void insertBusinessGiftexchange(@Param("buserid")String buserid, @Param("exchangeid")String exchangeid);

	GiftexchangeModel getGiftexchangeByid(@Param("exchangeid")String exchangeid);

	List<BusinessGiftexchangeModel> getBusergiftexchangeByid(@Param("exchangeid")String exchangeid);

	void updateGiftexchange(GiftexchangeModel giftexchangeModel);

	void deleteBusinessGiftexchange(@Param("exchangeid")String exchangeid);

}
