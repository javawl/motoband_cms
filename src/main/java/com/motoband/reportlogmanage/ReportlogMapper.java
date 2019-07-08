package com.motoband.reportlogmanage;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface ReportlogMapper {

	int getReportloglistCount();

	ArrayList<ReportlogModel> getReportloglist(@Param("start")int start, @Param("limit")int limit,@Param("order") int order,
			@Param("orderConditions")String orderConditions);

}
