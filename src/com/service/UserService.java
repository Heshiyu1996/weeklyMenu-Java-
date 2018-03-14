package com.service;

import com.entity.User;
import com.entity.Character;

public interface UserService {
	
	public User getStaffByUid(String uid);
	
	User checkPassword(String uid,String upassword);
	
    boolean addtUser(User user);
	
	public boolean ifExistCharacter(Integer userId);

	public boolean addCharacter(Character character);

	public boolean updateCharacter(Character character);
    
	public Character getCharacter(Integer userId);
}
