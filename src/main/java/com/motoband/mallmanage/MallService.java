package com.motoband.mallmanage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MallService {
@Autowired
private MallMapper mallMapper;

public ArrayList<MallParentTypeModel> getMallParentTypeList() {
	
	return mallMapper.getMallParentTypeList();
}

public ArrayList<MallTypeModel> getTypeListByParenttypeid(String parentid) {
	return mallMapper.getTypeListByParenttypeid(parentid) ;
}

public ArrayList<MallStyleModel> getMallStyleList() {
	
	return mallMapper.getMallStyleList();
}
public MallStyleModel getMallStyleModelByid(String styleid){
	return mallMapper.getMallStyleModelByid(styleid);
}
public ArrayList<MallBrandModel> getMallBrandList(String name) {
	
	return mallMapper.getMallBrandList(name);
}

/*public int getMallProductListCount() {
	return mallMapper.getMallProductListCount();
}*/

/*public ArrayList<MallProductModel> getMallProductList(String userGuid,
		int start, int limit, int order) {
	return mallMapper.getMallProductList( start,limit,order);
}*/

public void updateProductState(String id, String state) {
	mallMapper.updateProductState(id,state);
	
}

public void deleteProductByid(String id) {
	mallMapper.deleteProductByid(id);
	
}

public ArrayList<MallLabelModel> getMallLabelList() {
	return mallMapper.getMallLabelList();
}
public MallLabelModel getMallLabelModelByid(String labelid){
	return mallMapper.getMallLabelModelByid(labelid);
}
public void insertMallProduct(MallProductModel mallProductModel) {
	mallMapper.insertMallProduct(mallProductModel);
	
}

public ArrayList<MallProductModel> getMallProductList(String userGuid,
		int start, int limit, int order, String search_parenttype,
		String search_type, Integer[] search_probrand_arr,
		String[] search_style_arr_str, String search_level,
		String search_price_start, String search_price_end,
		String search_input, Integer[] search_state_arr) {
	
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("start", start);
	map.put("limit", limit);
	map.put("order", order);
	map.put("search_parenttype", search_parenttype);
	map.put("search_type", search_type);
	map.put("search_probrand_arr", search_probrand_arr);
	map.put("search_style_arr_str", search_style_arr_str);
	map.put("search_level", search_level);
	map.put("search_price_start", search_price_start);
	map.put("search_price_end", search_price_end);
	map.put("search_input", search_input);
	map.put("search_state_arr", search_state_arr);
	return mallMapper.getMallProductList(map);
}

public MallParentTypeModel getMallParentTypeModelByPid(Integer parenttype) {
	return mallMapper.getMallParentTypeModelByPid(parenttype);
}

public MallTypeModel getMallTypeModelByid(Integer type) {
	return mallMapper.getMallTypeModelByid(type);
}

public MallBrandModel getMallBrandModelBybid(Integer probrand) {
	return mallMapper.getMallBrandModelBybid(probrand);
}

public int getMallProductListCount(String search_parenttype,
		String search_type, Integer[] search_probrand_arr,
		String[] search_style_arr_str, String search_level,
		String search_price_start, String search_price_end,
		String search_input, Integer[] search_state_arr) {

	Map<String, Object> map = new HashMap<String, Object>();
	map.put("search_parenttype", search_parenttype);
	map.put("search_type", search_type);
	map.put("search_probrand_arr", search_probrand_arr);
	map.put("search_style_arr_str", search_style_arr_str);
	map.put("search_level", search_level);
	map.put("search_price_start", search_price_start);
	map.put("search_price_end", search_price_end);
	map.put("search_input", search_input);
	map.put("search_state_arr", search_state_arr);
	return mallMapper.getMallProductListCount(map);
}

public int getMallParenttypeListCount(String name) {
	
	return mallMapper.getMallParenttypeListCount(name);
}

public ArrayList<MallParentTypeModel> getMallParentTypeListPage(int start,
		int limit, int order, String orderConditions, String name) {
	
	return mallMapper.getMallParentTypeListPage(start,
			limit, order, orderConditions, name);
}

public void insMallParentType(String name,String brandids) {
	mallMapper.insMallParentType(name,brandids);
	
}

public int getMalltypeListCount(String name) {
	
	return mallMapper.getMalltypeListCount( name);
}

public ArrayList<MallTypeModel> getMallTypeListPage(int start, int limit,
		int order, String orderConditions, String name) {
	
	return mallMapper.getMallTypeListPage(start, limit,
			order, orderConditions, name);
}

public void insMallType(String name,String parentid) {
	
	mallMapper.insMallType( name,parentid);
}

public int getMallBrandListCount(String name) {
	
	return mallMapper.getMallBrandListCount( name);
}

public ArrayList<MallBrandModel> getMallBrandListPage(int start, int limit,
		int order, String orderConditions, String name) {
	
	return mallMapper.getMallBrandListPage(start,  limit,
			 order,  orderConditions,name);
}

public void insMallBrand(String name) {
	
	mallMapper.insMallBrand(name);
}

public int getMallLabelListCount(String name) {
	
	return mallMapper.getMallLabelListCount( name);
}

public ArrayList<MallLabelModel> getMallLabelListPage(int start, int limit,
		int order, String orderConditions, String name) {
	
	return mallMapper.getMallLabelListPage(start,limit,
			order, orderConditions,name);
}

public void insMallLabel(String name,String color) {
	mallMapper.insMallLabel( name,color);
	
}

public int getMallStyleListCount(String name) {
	return mallMapper.getMallStyleListCount(name);
}

public ArrayList<MallStyleModel> getMallStyleListPage(int start, int limit,
		int order, String orderConditions, String name) {
	return mallMapper.getMallStyleListPage(start,limit,
			order, orderConditions, name);
}

public void insMallStyle(String name) {
	mallMapper.insMallStyle(name);
}

public int getMallNotifyListCount(String name) {
	return mallMapper.getMallNotifyListCount(name);
}

public ArrayList<MallNotifyModel> getMallNotifyListPage(int start, int limit,
		int order, String orderConditions, String name) {
	return mallMapper.getMallNotifyListPage(start, limit,
			order,  orderConditions, name);
}

public int insMallNotify(MallNotifyModel mallNotifyModel) {
	return mallMapper.insMallNotify(mallNotifyModel);
}

public MallProductModel getMallProductById(String id) {
	return mallMapper.getMallProductById(id);
}

public void updateMallProduct(MallProductModel mallProductModel) {
	mallMapper.updateMallProduct(mallProductModel);	
}

public ArrayList<MallLabelModel> getMallLabelByName(String name) {
	return mallMapper.getMallLabelByName( name);
}

public ArrayList<MallStyleModel> getMallStyleByName(String name) {
	return mallMapper.getMallStyleByName(name);
}

public ArrayList<MallBrandModel> getMallBrandByName(String name) {
	return mallMapper.getMallBrandByName(name);
}

public MallNotifyModel getMallNotifyByid(String notifyid) {
	return mallMapper.getMallNotifyByid(notifyid);
}

public void delMallNotifyByid(String notifyid) {
	mallMapper.delMallNotifyByid(notifyid);
}

public void updateMallNotify(Map<String, String> map) {
	mallMapper.updateMallNotify(map);
	
}

public int getMallProductRecommendCount() {
	
	return mallMapper.getMallProductRecommendCount();
}

public ArrayList<MallProductModel> getMallProductRecommendList(String userGuid,
		int start, int limit, int order, String orderConditions) {
	
	return mallMapper.getMallProductRecommendList(userGuid, start, limit, order, orderConditions);
}



public int getProductCountByBrandId(Integer brandid) {
	
	return mallMapper.getProductCountByBrandId(brandid);
}

public ArrayList<MallProductModel> getMallProductByProbrand(String probrand) {
	
	return mallMapper.getMallProductByProbrand(probrand);
}

public ArrayList<MallTypeModel> getMallTypeList() {
	return mallMapper.getMallTypeList();
}

public void updateMallParentType(String parentid, String name, String brandids) {
	 mallMapper.updateMallParentType( parentid,name, brandids);
	}

public int getMallbaseCount(int groupid, int grouptype) {
	
	return  mallMapper.getMallbaseCount( groupid,grouptype);
}

public List<MallBaseModel> getMallbaselist(int start, int limit, int order, String orderConditions, int groupid, int grouptype) {
	
	return mallMapper.getMallbaselist( start,  limit,  order,  orderConditions,  groupid,grouptype) ;
}

public List<EquippingGroupModel> getEquippingGroupList(int state) {
	
	return  mallMapper.getEquippingGroupList( state);
}

public MallBaseModel getmallbasebymid(String mid) {
	return mallMapper.getmallbasebymid(mid);
}

public void insertOrupdateMallbase(MallBaseModel mallBaseModel) {
	mallMapper.insertOrupdateMallbase(mallBaseModel);
	
}

public int getEquippingGroupCount(int state, int grouptype) {
	return mallMapper.getEquippingGroupCount( state,grouptype);
}

public List<EquippingGroupModel> getEquippingGrouplistWithLimit(int state, int start, int limit, int order, String orderConditions, int grouptype) {
	return mallMapper.getEquippingGrouplistWithLimit( state,  start,  limit,  order,  orderConditions,grouptype);
}

public EquippingGroupModel getEquippinggroupByGroupid(String groupid) {
	return mallMapper.getEquippinggroupByGroupid( groupid);
}

public void insertOrupdateEquippingGroup(EquippingGroupModel equippingGroupModel) {
	mallMapper.insertOrupdateEquippingGroup(equippingGroupModel);
}

public void insertMallProductKeyword(String id, String keyword) {
	mallMapper.insertMallProductKeyword( id,  keyword);
	
}
	
}



