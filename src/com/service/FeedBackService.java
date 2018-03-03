package com.service;

import java.util.List;

import com.entity.FeedBack;

public interface FeedBackService {

	// 获取反馈列表
	public List<FeedBack> getFeedBackList();

	// 新增反馈
	public boolean insertFeedBack(FeedBack fb);
	
	// 回复反馈
	public boolean updateFeedBack(FeedBack fb);
	
}
