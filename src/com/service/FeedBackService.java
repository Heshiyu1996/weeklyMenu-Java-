package com.service;

import java.util.List;

import com.entity.FeedBack;

public interface FeedBackService {

	/**
	 * 获取反馈列表
	 * @param 	sid   工号
	 * @return  Staff 员工
	 */
	public List<FeedBack> getFeedBackList();
}
