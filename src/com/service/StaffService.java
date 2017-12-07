package com.service;

import com.entity.Staff;

public interface StaffService {

	/**
	 * 根据工号获取用户
	 * @param 	sid   工号
	 * @return  Staff 员工
	 */
	public Staff queryStaff(String sid);
	
	/**
	 * 根据昵称和密码获取用户
	 * @param 	sid  		工号
	 * @param	spassword   密码
	 * @return  Staff 		员工
	 */
	Staff checkPassword(String sid,String spassword);
}
