package com.motoband.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.alibaba.fastjson.JSON;

/**
 * jsonArray自定义处理器
 * Created by junfei.Yang on 2019年9月16日.
 */
public class JsonArrayTypeHandle<T> implements TypeHandler<T> {

	@Override
	public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
		if(parameter!=null) {
			ps.setString(i, JSON.toJSONString(parameter));
			
		}else {
			ps.setString(i, null);
		}
        
	}

	@Override
	public T getResult(ResultSet rs, String columnName) throws SQLException {
		String result=rs.getString(columnName);
		T t = converList(result);
		return t;
	}

	@Override
	public T getResult(ResultSet rs, int columnIndex) throws SQLException {
		String result=rs.getString(columnIndex);
		T t = converList(result);
		return t;
	}

	@Override
	public T getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String result=cs.getString(columnIndex);
		T t = converList(result);
		return t;
	}
	
	private T converList(String result) {
		T t=null;
		if(result!=null) {
			t=(T) JSON.parseArray(result);
		}
		return t;
	}

}
