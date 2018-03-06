package com.service;

import java.util.List;

import com.entity.FeedBack;
import com.entity.Food;

public interface FoodService {

	public List<Food> getFoodsByKeyword(String keyword);
	
	public Food getFoodDetailByFoodId(int foodId);

	public boolean addVisitCount(Food food);
	
}
