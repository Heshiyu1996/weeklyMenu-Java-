package com.service;

import com.entity.User;

public interface UserService {

	/**
	 * 根据工号获取用户
	 * @param 	sid   工号
	 * @return  Staff 员工
	 */
	public User getStaffByUid(String uid);
	
	/**
	 * 根据昵称和密码获取用户
	 * @param 	sid  		工号
	 * @param	spassword   密码
	 * @return  Staff 		员工
	 */
	User checkPassword(String uid,String upassword);
}
