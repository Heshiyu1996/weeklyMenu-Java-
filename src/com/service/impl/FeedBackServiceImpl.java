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
	public List<FeedBack> getFeedBackList(FeedBack fb) {
		return feedBackDao.getFeedBackList(fb);
	}

	@Override
	public List<FeedBack> getFeedBackListByUserId(String userId) {
		return feedBackDao.getFeedBackListByUserId(userId);
	}

	@Override
	public FeedBack getFeedBackListById(int fid) {
		return feedBackDao.getFeedBackById(fid);
	}

	@Override
	public boolean insertFeedBack(FeedBack fb) {
		boolean flag=false;
		try {
			flag=(feedBackDao.insertFeedBack(fb)==1)?true:false;
		} catch (Exception e) {
		}
		return flag;
	}

	@Override
	public boolean updateFeedBack(FeedBack fb) {
		boolean flag=false;
		try {
			flag=(feedBackDao.updateFeedBack(fb)==1)?true:false;
		} catch (Exception e) {
		}
		return flag;
	}

}
