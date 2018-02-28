package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FeedBackDao;
import com.entity.FeedBack;
import com.tool.Encryption;
import com.service.FeedBackService;

@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {
	
	@Autowired
	private FeedBackDao feedBackDao = null;

	public FeedBackDao getfeedBackDao() {
		return feedBackDao;
	}

	public void setfeedBackDao(FeedBackDao feedBackDao) {
		this.feedBackDao = feedBackDao;
	}

	@Override
	public List<FeedBack> getFeedBackList() {
		return feedBackDao.getFeedBackList();
	}
	
}
