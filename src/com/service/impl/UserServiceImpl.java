package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;
import com.tool.Encryption;
import com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao = null;

	public UserDao getStaffDao() {
		return userDao;
	}

	public void setStaffDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getStaffBySid(String sid) {
		return userDao.queryStaff(sid);
	}

	@Override
	public User checkPassword(String sid,String spassword) {

		User user = userDao.queryBySid(sid.trim());
		// 不存在用户
		if (user == null)
			return null;
		// 密码相同，使用MD5
		System.out.println("密码验证");
//		if (staff.getSpassword().equals(Encryption.getMD5Encryption(spassword)))
		if (user.getSpassword().equals(spassword))
			return user;

		return null;
	}
	

}
