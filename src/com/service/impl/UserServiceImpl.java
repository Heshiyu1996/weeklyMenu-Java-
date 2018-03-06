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

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getStaffByUid(String uid) {
		return userDao.queryStaff(uid);
	}

	@Override
	public User checkPassword(String uid,String upassword) {

		User user = userDao.queryByUid(uid.trim());
		// 不存在用户
		if (user == null)
			return null;
		// 密码相同，使用MD5
		System.out.println("密码验证");
//		if (staff.getSpassword().equals(Encryption.getMD5Encryption(upassword)))
		if (user.getUpassword().equals(upassword))
			return user;

		return null;
	}
	

}
