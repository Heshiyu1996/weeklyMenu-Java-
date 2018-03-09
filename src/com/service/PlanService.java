package com.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.entity.Category;
import com.entity.Food;
import com.entity.Plan;
import com.entity.FeedBack;

public interface PlanService {


	public Map<String,Object> getWeekCalendar();
	
	public List<Category> getCidsByDayPid(Integer day, Integer pid);
	
	public List<Food> getFoodsByDayPidCid(Integer day, Integer pid, Integer cid);
}
