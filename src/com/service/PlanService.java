package com.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.entity.Category;
import com.entity.Character;
import com.entity.Food;
import com.entity.Plan;
import com.entity.FeedBack;

public interface PlanService {


	public Map<String,Object> getWeekCalendar();
	
	public List<Category> getCidsByDayPid(Integer day, Integer pid);
	
	public List<Food> getFoodsByDayPidCid(Integer day, Integer pid, Integer cid);
	
//	public int getBuyTimesByUserId(int foodId, int userId);
//	
//	public int getSearchTimesByUserId(String keyword, int userId);
//	
//	public int getMarkTimesByUserId(int foodId, int userId);
//	
//	public int getTasteByUserId2(int userId);
//	
//	public Character myTest(int userId);
	public int getTasteByUserId3(int userId);
}
