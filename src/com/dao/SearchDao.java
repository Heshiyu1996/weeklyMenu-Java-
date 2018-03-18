package com.dao;

import java.util.List;

import com.entity.FeedBack;
import com.entity.Search;

import org.springframework.stereotype.Repository;

@Repository
public interface SearchDao {
	public List<Search> getKeywords();
	
    int ifExistsKeyword(String keyword);
    
    int recordKeyword(String keyword);
    
    int recordKeywordWithUserId(String keyword, int userId);
    
    int addKeywordCount(String keyword);
	
}
