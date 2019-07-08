package com.motoband.carmanage;





import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.motoband.brandparentmanage.BrandparentModel;
import com.motoband.util.mapper.MyBatisRepository;


@MyBatisRepository
public interface carMapper {

	ArrayList<carlist> getCarList(@Param("nowtypeid")String nowtypeid,@Param("nowbrandid") String nowbrandid,@Param("start") int start,@Param("limit") int limit,@Param("order") int order,@Param("orderConditions")String orderConditions);

	ArrayList<motobrand> getMotoBrandList();

	ArrayList<mototype> getMotTypeList();

	int checkPrivilege(@Param("privilege_key")String privilege_key, @Param("userGuid")String userGuid);

	carModel checkCarName(@Param("newcarname")String newcarname);

	void saveNewCar(carModel carModel);

	String checkCarType(@Param("newCarTypeName")String newCarTypeName);

	void insertNewCarType(@Param("newcartype")String newcartype);

	String checkCarBrand(@Param("newCarBrand")String newCarBrand);

	void insertNewCarBrand(@Param("newcarbrand")String newcarbrand,@Param("bpid")String bpid);

	carModel getCarModelById(@Param("motoid")String motoid);

	void saveCarMessage(carModel carModel);

	carModel checkCarNewName(@Param("car_id")String car_id,@Param("newcarname")String newcarname);

	void delCarMessage(@Param("car_id")String car_id);

	int getCarListCount(@Param("nowtypeid")String nowtypeid,@Param("nowbrandid") String nowbrandid);

	List<BrandparentModel> selectCarbrandParentname();

	List<carModel> getCarModelList(Map<String, Object> map);

	motobrand getMotobrandByid(String brandid);

	int getNewmotomodelListCount();

	ArrayList<NewMotoModel> getNewmotomodelList( @Param("start")int start, @Param("limit")int limit, @Param("order")int order, @Param("orderConditions")String orderConditions);

	NewMotoModel getNewmotomodelByid(@Param("mid")String mid);

	List<motobrand> getBrandListByBpid(@Param("bpid")String bpid);

	List<carModel> getMotomodelByBrandid(@Param("brandid")String brandid);

	List<String> getNewmotomodelpicListBymid(@Param("mid")String mid);

	void deletnewmotomodelpicBymid(@Param("mid")String mid);

	void insertnewmotomodelpic(@Param("piclist")List<NewMotoModelPicModel> piclist);

	void insertOrUpdateNewMotomodel(NewMotoModel newMotoModel);

	void deleteNewMotomodelbymid(@Param("mid")String mid);


	

}
