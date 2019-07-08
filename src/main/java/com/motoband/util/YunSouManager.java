package com.motoband.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.motoband.util.Module.Yunsou;

public class YunSouManager {
	private static final YunSouManager yunsouManager = new YunSouManager();

	private YunSouManager() {
		
	}

	public static YunSouManager getInstance() {
		return yunsouManager;
	}

	public static String searchYuSou(String temp, int pagenum) {

		TreeMap<String, Object> config = new TreeMap<String, Object>();
		config.put("SecretId", Consts.SECRETID);
		config.put("SecretKey", Consts.SECRETKEY);
		config.put("RequestMethod", Consts.REQUESTMETHOD);
		config.put("DefaultRegion", Consts.DEFAULTREGION);
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Yunsou(), config);

		TreeMap<String, Object> params = new TreeMap<String, Object>();

		params.put("appId", Consts.YUNSOUAPPID);
		params.put("search_query", temp);
		params.put("page_id", pagenum);
		params.put("num_per_page", Consts.NUM_PER_PAGE);
		params.put("query_encode", 0);

		String result = null;
		try {

			result = module.call("DataSearch", params);
		

		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());

		}
		return result;
	}

	public static void addYuSou(Map<String, Object> tempmap) {

		TreeMap<String, Object> config = new TreeMap<String, Object>();
		config.put("SecretId", Consts.SECRETID);
		config.put("SecretKey", Consts.SECRETKEY);
		config.put("RequestMethod", Consts.REQUESTMETHOD);
		config.put("DefaultRegion", Consts.DEFAULTREGION);
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Yunsou(), config);

		TreeMap<String, Object> params = new TreeMap<String, Object>();

		params.put("appId", Consts.YUNSOUAPPID);
		params.put("op_type", "add");
		params.putAll(tempmap);

		String result = null;
		try {

			result = module.call("DataManipulation", params);

			System.out.println(result);
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
		}
	}

	public static void delYuSou(Map<String, Object> tempmap) {

		TreeMap<String, Object> config = new TreeMap<String, Object>();
		config.put("SecretId", Consts.SECRETID);
		config.put("SecretKey", Consts.SECRETKEY);
		config.put("RequestMethod", Consts.REQUESTMETHOD);
		config.put("DefaultRegion", Consts.DEFAULTREGION);
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Yunsou(), config);

		TreeMap<String, Object> params = new TreeMap<String, Object>();

		params.put("appId", Consts.YUNSOUAPPID);
		params.put("op_type", "del");

		params.putAll(tempmap);

		String result = null;
		try {
			result = module.call("DataManipulation", params);

			System.out.println(result);
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
		}
	}

	
	public static void main(String[] args) {
	
		
		
		Map<String, Object> addMap = new HashMap<String, Object>();
		addMap.put("contents.0.boxtitle", "1");
		addMap.put("contents.0.boxcontent", "2");
		addMap.put("contents.0.boxkeyword", "3");
		addMap.put("contents.0.boxid", "4");
		addMap.put("contents.0.newsid", 1100);
		addMap.put("contents.0.newscontent", "5");
		addMap.put("contents.0.newskeyword", "6");
		addMap.put("contents.0.newstitle", "第一个测试故事");
		addMap.put("contents.0.TP", 1110);
		addMap.put("contents.0.boxlookcount", 0);
		addMap.put("contents.0.newslikecount", 0);
		
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		config.put("SecretId", "AKIDl8fUHCxeOZaB8gzRsipx6AsJKb4NatKS");
		config.put("SecretKey", "14FYwQ4PtIeui1qk35XwUoi6gOiaY2SO");
		config.put("RequestMethod","GET");
		config.put("DefaultRegion", "bj");
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Yunsou(), config);

		TreeMap<String, Object> params = new TreeMap<String, Object>();

		params.put("appId",47810002);
		params.put("op_type", "add");
		params.put("query_encode", 0);
		params.putAll(addMap);
		System.out.println(module.generateUrl("DataManipulation", params));
		String result = null;
		try {

			result = module.call("DataManipulation", params);

			System.out.println(result);
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
		}
		
		
	}
}
