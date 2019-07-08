package com.motoband.admin.admin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class AdminService {
	
	@Autowired
	private AdminMapper userMapper;

	/** 
	* @Title: getAdminMessageByLoginnameAndPassword 
	* @Description: 通过用户名密码找到账号
	* @param username
	* @param pwd
	* @return Admin    
	* @throws 
	*/
	public Admin getAdminMessageByLoginnameAndPassword(String username,String pwd) {
		return userMapper.getAdminMessageByLoginnameAndPassword(username,pwd);
	}

	/** 
	* @Title: insertAdmin 
	* @Description: 添加账号
	* @param admin
	* @return int    
	* @throws 
	*/
	public int insertAdmin(Admin admin){
		//密码加密
		//admin.setPwd(Base64.encodeBase64String(DESedeCoder.encrypt(admin.getPwd().getBytes())).replaceAll("\r\n", ""));
		return userMapper.insertAdmin(admin);
	}

	public Admin getAdminMessageByLoginname(String username) {
		return userMapper.getAdminMessageByLoginname(username);
	}

	/** 
	* @Title: updateAdmin 
	* @Description:  修改账号，可修改密码和员工号
	* @param admin
	* @return int    
	* @throws 
	*/
	public int updateAdmin(Admin admin) {
		/*Admin tmp = this.getAdminMessageByLoginname(admin.getName());
		if(admin.getSno()!=null){
			tmp.setSno(admin.getSno());
		}
		admin.setPwd(Base64.encodeBase64String(DESedeCoder.encrypt(admin.getPwd().getBytes())).replaceAll("\r\n", ""));*/
		return userMapper.updateAdmin(admin);
	}

	public ArrayList<Privilege> getPrivilegeByloginname(
			String username) {
		// TODO Auto-generated method stub
		return userMapper.getPrivilegesByloginname(username);
	}

}
