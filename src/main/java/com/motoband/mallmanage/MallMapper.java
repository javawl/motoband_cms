package com.motoband.mallmanage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface MallMapper {

	ArrayList<MallParentTypeModel> getMallParentTypeList();

	ArrayList<MallTypeModel> getTypeListByParenttypeid(@Param("parentid")String parentid);

	ArrayList<MallStyleModel> getMallStyleList();

	ArrayList<MallBrandModel> getMallBrandList(@Param("name")String name);

	/*ArrayList<MallProductModel> getMallProductList(@Param("start")int start, @Param("limit")int limit,
			@Param("order")int order);*/

	/*int getMallProductListCount();*/

	void updateProductState(@Param("id")String id, @Param("state")String state);

	void deleteProductByid(@Param("id")String id);

	ArrayList<MallLabelModel> getMallLabelList();

	void insertMallProduct(MallProductModel mallProductModel);

	MallStyleModel getMallStyleModelByid(@Param("styleid")String styleid);

	MallLabelModel getMallLabelModelByid(@Param("labelid")String labelid);

	ArrayList<MallProductModel> getMallProductList(Map<String, Object> map);

	MallParentTypeModel getMallParentTypeModelByPid(@Param("parentid")Integer parenttype);

	MallTypeModel getMallTypeModelByid(@Param("typeid")Integer type);

	MallBrandModel getMallBrandModelBybid(@Param("brandid")Integer probrand);

	int getMallProductListCount(Map<String, Object> map);

	int getMallParenttypeListCount(@Param("name")String name);

	ArrayList<MallParentTypeModel> getMallParentTypeListPage(@Param("start")int start,
			@Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions, @Param("name")String name);

	void insMallParentType(@Param("name")String name,@Param("brandids")String brandids);

	int getMalltypeListCount(@Param("name")String name);

	ArrayList<MallTypeModel> getMallTypeListPage(@Param("start")int start,
			@Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions, @Param("name")String name);

	void insMallType(@Param("name")String name,@Param("parentid")String parentid);

	int getMallBrandListCount(@Param("name")String name);

	ArrayList<MallBrandModel> getMallBrandListPage(@Param("start")int start,
			@Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions, @Param("name")String name);

	void insMallBrand(@Param("name")String name);

	int getMallLabelListCount(@Param("name")String name);

	ArrayList<MallLabelModel> getMallLabelListPage(@Param("start")int start,
			@Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions, @Param("name")String name);

	void insMallLabel(@Param("name")String name,@Param("color")String color);

	int getMallStyleListCount(@Param("name")String name);

	ArrayList<MallStyleModel> getMallStyleListPage(@Param("start")int start,
			@Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions, @Param("name")String name);

	void insMallStyle(@Param("name")String name);

	int getMallNotifyListCount(@Param("name")String name);

	ArrayList<MallNotifyModel> getMallNotifyListPage(@Param("start")int start,
			@Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions, @Param("name")String name);

	int insMallNotify(MallNotifyModel mallNotifyModel);

	MallProductModel getMallProductById(@Param("id")String id);

	void updateMallProduct(MallProductModel mallProductModel);

	ArrayList<MallLabelModel> getMallLabelByName(@Param("name")String name);

	ArrayList<MallStyleModel> getMallStyleByName(@Param("name")String name);

	ArrayList<MallBrandModel> getMallBrandByName(@Param("name")String name);

	MallNotifyModel getMallNotifyByid(@Param("notifyid")String notifyid);

	void delMallNotifyByid(@Param("notifyid")String notifyid);

	void updateMallNotify(Map<String, String> map);

	int getMallProductRecommendCount();

	ArrayList<MallProductModel> getMallProductRecommendList(@Param("userGuid")String userGuid,
			@Param("start")int start, @Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions);

	int getProductCountByBrandId(@Param("brandid")Integer brandid);

	ArrayList<MallProductModel> getMallProductByProbrand(@Param("probrand")String probrand);

	ArrayList<MallTypeModel> getMallTypeList();

	Object updateMallParentType(@Param("parentid")String parentid, @Param("name")String name, @Param("brandids")String brandids);

	int getMallbaseCount(@Param("groupid")int groupid);

	List<MallBaseModel> getMallbaselist(@Param("start")int start, @Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions, @Param("groupid")int groupid);

	List<EquippingGroupModel> getEquippingGroupList(@Param("state")int state);

	MallBaseModel getmallbasebymid(@Param("mid")String mid);

	void insertOrupdateMallbase(MallBaseModel mallBaseModel);

	int getEquippingGroupCount(@Param("state")int state);

	List<EquippingGroupModel> getEquippingGrouplistWithLimit(@Param("state")int state, @Param("start")int start,@Param("limit") int limit, @Param("order")int order, @Param("orderConditions")String orderConditions);

	EquippingGroupModel getEquippinggroupByGroupid(@Param("groupid")String groupid);

	void insertOrupdateEquippingGroup(EquippingGroupModel equippingGroupModel);

	void insertMallProductKeyword(@Param("id")String id, @Param("keyword")String keyword);


}
