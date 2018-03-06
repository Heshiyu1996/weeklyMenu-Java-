package com.dao;

import com.entity.User;



import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	
	public User queryStaff(String uid);
	
    User queryByUid(String uid);
	
}
