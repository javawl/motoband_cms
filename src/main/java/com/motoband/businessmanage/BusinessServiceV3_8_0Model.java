package com.motoband.businessmanage;

import java.util.List;

/**
 * 商家提供的服务
 * Created by junfei.Yang on 2019年9月16日.
 */
public class BusinessServiceV3_8_0Model {

	public int bsid;
	/**
	 * 商家id
	 * @see com.motoband.model.business.v_3_8_0.BusinessUserV3_8_0Model
	 */
	public int buid;
	public String icon;//图标
	public String name;
	public String value;
	public List<String> lables;//服务标签
	public int linktype;//1 动态  2 话题   3问答    4 有赞  5 内部链接  6 外部链接  7小程序 8 motogp 9 二手车  10 本地商家  11 话题列表  12 问答列表  13 此刻
	public int order;//排序 越大越优先展示
	public int state;//1上线，2下线
}
