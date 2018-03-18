package com.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Category;
import com.entity.Character;
import com.entity.Food;
import com.entity.Plan;
import com.entity.FeedBack;

import org.springframework.stereotype.Repository;

@Repository
public interface PlanDao {
	// 获取本周日历
	public Map<String,Object> queryWeekCalendar();
	
	List<Category> queryCidsByDayPid(Integer day, Integer pid);
	
	List<Food> queryFoodsByDayPidCid(Integer day, Integer pid, Integer cid);
    
//    int queryBuyTimesByUserId(int foodId, int userId);
//    
//    int querySearchTimesByUserId(String keyword, int userId);
//    
//    int queryMarkTimesByUserId(int foodId, int userId);
//    
//    int queryTasteByUserId2(int userId);
//	
//	Character queryTest(Integer userId);
//	
    int queryTasteByUserId3(int userId);
}
