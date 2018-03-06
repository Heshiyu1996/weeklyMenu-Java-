package com.service;

import com.entity.FeedBack;
import com.entity.Food;

public interface FoodService {
	
	public Food getFoodDetailByFoodId(int foodId);

	public boolean addVisitCount(Food food);
	
}
