package com.service;

import java.util.List;
import java.util.Map;

import com.entity.FeedBack;
import com.entity.Food;

public interface AdminService {

	// 获取反馈列表
	public List<FeedBack> loadFeedBackList(Integer isReplied, String order);
	// 回复反馈
	public boolean updateFeedBack(FeedBack fb);

//	public boolean insertFood(Food food, int day, int pid);
	
	public int insertFood(Food food, List<String> plans);
	// 编辑菜品
	public boolean updateFood(Food food);
	// 编辑菜品
	public boolean deleteFood(int[] foodsId);
	// 获取菜品列表
	public List<Food> getFoodsList();
	// 获取菜品列表
	public List<Food> getFoodsByKeyword(String keyword);
	
}
