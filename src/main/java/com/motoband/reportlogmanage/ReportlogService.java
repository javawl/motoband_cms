package com.motoband.reportlogmanage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportlogService {
 @Autowired
 private ReportlogMapper reportlogMapper;

public int getReportloglistCount() {
	return reportlogMapper.getReportloglistCount();
}

public ArrayList<ReportlogModel> getReportloglist(int start, int limit,
		int order, String orderConditions) {
	return reportlogMapper.getReportloglist(start,limit, order,orderConditions);
}
}
