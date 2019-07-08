package com.motoband.tribal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TribalService {
@Autowired
private TribalMapper tribalMapper;

public boolean addOrUpdateTribal(TribalModel tb) {
	boolean b= false;
	try {
		tribalMapper.addOrUpdateTribal(tb);
		b=true;
	} catch (Exception e) {
		e.printStackTrace();
		b=false;
	}
	
	return b;
}

public List<TribalModel> getTribalListAll(int type) {
	
	return tribalMapper.getTribalListAll(type);
}

public TribalModel getTribalById(String tribalid) {
	
	return tribalMapper.getTribalById(tribalid);
}

public void updateTribalOrderindex(String tribalid, long orderindex) {
	
	tribalMapper.updateTribalOrderindex( tribalid,  orderindex);
}

public int getTribalCountByType(int tribaltype) {
	
	return tribalMapper.getTribalCountByType( tribaltype);
}

public List<TribalModel> getTribalListByType(int tribaltype, int start, int limit) {
	
	return tribalMapper.getTribalListByType(tribaltype, start, limit);
}

public ArrayList<TribalTypeModel> getTribalTypeList() {
	
	return tribalMapper.getTribalTypeList();
}
}
