package com.dao;

import java.util.List;

import com.entity.FeedBack;

import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackDao {
	
	public List<FeedBack> getFeedBackList(Integer isReplied, String order, String userId);
	
	public FeedBack getFeedBackById(int fid);
	
    int insertFeedBack(FeedBack fb);
	
    int updateFeedBack(FeedBack fb);
	
}
