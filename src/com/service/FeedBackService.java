package com.service;

import java.util.List;

import com.entity.FeedBack;

public interface FeedBackService {

	// 获取反馈列表
	public List<FeedBack> getFeedBackList(Integer isReplied, String order, String userId);
	
	// 根据fid获取反馈详情
	public FeedBack getFeedBackListById(int fid);

	// 新增反馈
	public boolean insertFeedBack(FeedBack fb);
	
}
