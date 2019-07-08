package com.motoband.credit;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.motoband.util.mapper.MyBatisRepository;

@MyBatisRepository
public interface CreditMapper {

	long selectUserCredit(@Param("userid") String userid);

	void updateUserCredit(Map<String, Object> map);

	void insertCreditLog(CreditLogModel model);

}
