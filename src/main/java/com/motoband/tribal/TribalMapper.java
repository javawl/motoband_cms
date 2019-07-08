package com.motoband.tribal;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface TribalMapper {

	void addOrUpdateTribal(TribalModel tb);

	List<TribalModel> getTribalListAll(@Param("type")int type);

	TribalModel getTribalById(@Param("tribalid")String tribalid);

	void updateTribalOrderindex(@Param("tribalid")String tribalid, @Param("orderindex")long orderindex);

	int getTribalCountByType(@Param("type")int tribaltype);

	List<TribalModel> getTribalListByType(@Param("type")int tribaltype, @Param("start")int start, @Param("limit")int limit);

	ArrayList<TribalTypeModel> getTribalTypeList();


}
