package com.dao;

import java.util.List;

import com.entity.Food;

import org.springframework.stereotype.Repository;

@Repository
public interface FoodDao {

	List<Food> queryByKeyword(String keyword);
    
    Food queryByFoodId(int foodId);
    
    int addVisitCount(Food food);
	
}
