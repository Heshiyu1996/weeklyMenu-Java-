package com.dao;

import java.util.List;

import com.entity.FeedBack;

import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackDao {
	
	public List<FeedBack> getFeedBackList();
	
	public List<FeedBack> getFeedBackListByUserId(String userId);
	
	public FeedBack getFeedBackById(int fid);
	
    int insertFeedBack(FeedBack fb);
	
    int updateFeedBack(FeedBack fb);
	
}
