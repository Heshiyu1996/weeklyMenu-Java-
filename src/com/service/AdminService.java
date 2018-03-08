package com.service;

import java.util.List;

import com.entity.FeedBack;
import com.entity.Food;

public interface AdminService {

	// 获取反馈列表
	public List<FeedBack> loadFeedBackList(Integer isReplied, String order);
	// 回复反馈
	public boolean updateFeedBack(FeedBack fb);

	public boolean insertFood(Food food);
	// 编辑菜品
	public boolean updateFood(Food food);
	// 编辑菜品
	public boolean deleteFood(int[] foodsId);
	
}
