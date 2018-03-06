package com.dao;

import com.entity.Food;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodDao {
	
    Food queryByFoodId(int foodId);
    
    int addVisitCount(Food food);
	
}
