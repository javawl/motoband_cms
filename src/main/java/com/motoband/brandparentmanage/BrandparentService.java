package com.motoband.brandparentmanage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandparentService {
@Autowired
private BrandparentMapper brandparentMapper;

public int getBrandparentlistCount() {
	
	return brandparentMapper.getBrandparentlistCount();
}

public ArrayList<BrandparentModel> getBrandparentlist(int start, int limit,
		int order) {
	
	return brandparentMapper.getBrandparentlist(start, limit,order);
}

public BrandparentModel getBrandparentByid(String bpid) {
	return brandparentMapper.getBrandparentByid(bpid);
}

public void updatebrandparentByid(BrandparentModel brandparentModel) {
	 brandparentMapper.updatebrandparentByid(brandparentModel);
	
}

public void insbrandparent(BrandparentModel brandparentModel) {
	 brandparentMapper.insbrandparent(brandparentModel);
	
}
public ArrayList<BrandparentModel> getBrandparentlistSimple() {
	
	return brandparentMapper.getBrandparentlistSimple();
}
}
