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
	
}
