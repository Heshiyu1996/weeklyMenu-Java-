package com.service;

import com.entity.User;

public interface UserService {
	
	public User getStaffByUid(String uid);
	
	User checkPassword(String uid,String upassword);
	
    boolean addtUser(User user);
}
