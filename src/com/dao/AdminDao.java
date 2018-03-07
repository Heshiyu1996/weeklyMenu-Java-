package com.dao;

import java.util.List;

import com.entity.FeedBack;
import com.entity.Food;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {
	
	public List<FeedBack> loadFeedBackList(Integer isReplied, String order);
	
    int updateFeedBack(FeedBack fb);

	int insertFood(Food food);
	
}
