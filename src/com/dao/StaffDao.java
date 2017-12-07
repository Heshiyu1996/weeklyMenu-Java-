package com.dao;

import com.entity.Staff;



import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao {
	
	public Staff queryStaff(String sid);
	
    Staff queryBySid(String sid);
	
}
