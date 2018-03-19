package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Character;
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
	
    int deletePlan(int foodId);
	
    int updateFood(Food food);
	
    int deleteFood(int foodId);
    
	public List<Food> getFoodsList();
    
	public List<Character> getCharacterList();
	
}
