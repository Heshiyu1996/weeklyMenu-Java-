package com.dao;

import java.util.Date;

import com.entity.FeedBack;
import com.entity.User;
import com.entity.Character;







import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	
	public User queryStaff(String uid);
	
    User queryByUid(String uid);
    
    int registUser(User user);
	
    int ifExistCharacter(Integer userId);
    
    int insertCharacter(Character character);
    
    int updateCharacter(Character character);
    
    Character getCharacter(Integer userId);
	
}
