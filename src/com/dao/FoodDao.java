package com.dao;

import java.util.Date;
import java.util.List;

import com.entity.Category;
import com.entity.Food;

import org.springframework.stereotype.Repository;

@Repository
public interface FoodDao {

	List<Food> queryByKeyword(String keyword);
    
    Food queryByFoodId(int foodId);
    
    int addVisitCount(int foodId);
    
    int addMarkCount(int foodId);
    
    int insertMarks(Integer foodId, Integer userId, Date createTime);
    
    int decMarkCount(int foodId);
    
    int removeMarks(Integer foodId, Integer userId);
	
    int ifExistsMarks(Integer foodId, Integer userId);
    
	List<Food> getMyMarksList(Integer userId);
    
	List<Category> getAllCategories();
	
}
