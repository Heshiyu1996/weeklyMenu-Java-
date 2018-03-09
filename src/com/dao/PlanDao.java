package com.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Plan;
import com.entity.FeedBack;

import org.springframework.stereotype.Repository;

@Repository
public interface PlanDao {
	
	public Map<String,Object> getThisWeek();
	
}
