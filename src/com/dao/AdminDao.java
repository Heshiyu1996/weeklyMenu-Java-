package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.FeedBack;
import com.entity.Food;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {
	List<Food> getFoodsByKeyword(String keyword);
	
	public List<FeedBack> loadFeedBackList(Integer isReplied, String order);
	
    int updateFeedBack(FeedBack fb);

    Map<String, Integer> insertFood(Food food);
    
    int insertPlan(int day, int pid, int foodId);
	
    int updateFood(Food food);
	
    int deleteFood(int foodId);
    
	public List<Food> getFoodsList();
	
}
