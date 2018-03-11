package com.service;

import java.util.Date;
import java.util.List;

import com.entity.Category;
import com.entity.FeedBack;
import com.entity.Food;

public interface FoodService {

	public List<Food> getHotFoods();
	
	public List<Food> getFoodsByKeyword(String keyword);
	
	public Food getFoodDetailByFoodId(int foodId);

	public boolean addVisitCount(int foodId);

	public boolean insertMarks(Integer foodId, Integer userId, Date createTime); // 插入“收藏表”，包含了收藏数加1

	public boolean removeMarks(Integer foodId, Integer userId); // 从“收藏表”移除，包含了收藏数减1
	
	public boolean ifExistsMarks(Integer foodId, Integer userId);

	public List<Food> getMyMarksList(Integer userId);

	public List<Category> getAllCategories();
	
}
