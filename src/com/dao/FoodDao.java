package com.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.entity.Category;
import com.entity.Food;

import org.springframework.stereotype.Repository;

@Repository
public interface FoodDao {

	List<Food> getFoodsByKeyword(String keyword);

	List<Food> queryHotFoods();
    
    Food queryByFoodId(int foodId);
    
    int addVisitCount(int foodId);
    
    int addMarkCount(int foodId);
    
    int insertMarks(Integer foodId, Integer userId, Date createTime);
    
    int decMarkCount(int foodId);
    
    int removeMarks(Integer foodId, Integer userId);
	
    int ifExistsMarks(Integer foodId, Integer userId);
    
	List<Food> getMyMarksList(Integer userId);
    
	List<Category> getAllCategories();
	
	List<Map<String, Object>> queryPlanByFoodId(int foodId);
	
	List<Food> queryFoodRecommondByOrder(int foodId);
	
}
