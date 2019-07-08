package com.motoband.carmanage;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.motoband.brandparentmanage.BrandparentModel;
import com.motoband.user.userMapper;


@Component
public class carService {

	@Autowired
	private carMapper carMapper;
	@Autowired
	private userMapper userMapper;

	public ArrayList<carlist> getCarList(String userGuid, String nowtypeid,
			String nowbrandid,int start,int limit,int order,String orderConditions) {
		// TODO Auto-generated method stub
		int sel = userMapper.checkPrivilege("car_sel", userGuid);
		if (sel != 0) {
		//	System.out.println("有查询权限");
			return carMapper.getCarList(nowtypeid,nowbrandid,start,limit,order,orderConditions);
		} else {
		//	System.out.println("没有查询权限");
			return null;
		}
		
	}

	public ArrayList<motobrand> getMotoBrandList() {
		// TODO Auto-generated method stub
		return carMapper.getMotoBrandList();
	}

	public ArrayList<mototype> getMotoTypeList() {
		// TODO Auto-generated method stub
		return carMapper.getMotTypeList();
	}

	public int getCarCheck(String keyString, String userGuid) {
		// TODO Auto-generated method stub
		return carMapper.checkPrivilege(keyString,userGuid);
	}

	public com.motoband.carmanage.carModel checkCarName(String newcarname) {
		// TODO Auto-generated method stub
		return carMapper.checkCarName(newcarname);
	}

	public void saveNewCar(carModel carModel) {
		// TODO Auto-generated method stub
		carMapper.saveNewCar(carModel);
	}

	public String checkCarType(String newCarTypeName) {
		// TODO Auto-generated method stub
		return carMapper.checkCarType(newCarTypeName);
	}

	public void insertNewCarType(String newcartype) {
		// TODO Auto-generated method stub
		carMapper.insertNewCarType(newcartype);
	}

	public String checkCarBrand(String newCarBrand) {
		// TODO Auto-generated method stub
		return carMapper.checkCarBrand(newCarBrand);
	}

	public void insertNewCarBrand(String newcarbrand,String bpid) {
		// TODO Auto-generated method stub
		carMapper.insertNewCarBrand(newcarbrand,bpid);
	}

	public com.motoband.carmanage.carModel getCarModelById(String motoid) {
		// TODO Auto-generated method stub
		return carMapper.getCarModelById(motoid);
	}

	public void saveCarMessage(carModel carModel) {
		// TODO Auto-generated method stub
		carMapper.saveCarMessage(carModel);
	}

	public com.motoband.carmanage.carModel checkCarNewName(String car_id,
			String newcarname) {
		// TODO Auto-generated method stub
		return carMapper.checkCarNewName(car_id,newcarname);
	}

	public void delCarMessage(String car_id) {
		// TODO Auto-generated method stub
		carMapper.delCarMessage(car_id);
	}

	public int getCarListCount(String nowtypeid,String nowbrandid) {
		// TODO Auto-generated method stub
		return carMapper.getCarListCount(nowtypeid,nowbrandid);
	}

	public List<BrandparentModel> selectCarbrandParentname() {
		
		return carMapper.selectCarbrandParentname();
	}

	public List<carModel>  getCarModelList(Integer[] brandids){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandids", brandids);
		return carMapper.getCarModelList(map);
	}
	
  public motobrand  getMotobrandByid(String brandid){
	  return carMapper.getMotobrandByid(brandid);
  }

public int getNewmotomodelListCount() {
	
	return carMapper.getNewmotomodelListCount();
}

public ArrayList<NewMotoModel> getNewmotomodelList( int start, int limit, int order, String orderConditions) {
	
	return carMapper.getNewmotomodelList(start,  limit,  order,  orderConditions);
}

public NewMotoModel getNewmotomodelByid(String mid) {
	
	return carMapper.getNewmotomodelByid(mid) ;
}

public List<motobrand> getBrandListByBpid(String bpid) {
	return carMapper.getBrandListByBpid( bpid);
}

public List<carModel> getMotomodelByBrandid(String brandid) {
	return carMapper.getMotomodelByBrandid( brandid);
}

public List<String> getNewmotomodelpicListBymid(String mid) {
	
	return carMapper.getNewmotomodelpicListBymid( mid);
}
@Transactional
public void dowithNewMotomodelPic(String mid, List<NewMotoModelPicModel> piclist) {
	 carMapper.deletnewmotomodelpicBymid(mid);
	 carMapper.insertnewmotomodelpic(piclist);
}

public void insertOrUpdateNewMotomodel(NewMotoModel newMotoModel) {
	carMapper.insertOrUpdateNewMotomodel(newMotoModel);
	
}
@Transactional
public void deleteNewMotomodelbymid(String mid) {
	carMapper.deleteNewMotomodelbymid(mid);
	carMapper.deletnewmotomodelpicBymid(mid);
	
}

}
