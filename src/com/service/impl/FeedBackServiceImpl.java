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

	public FeedBackDao getFeedBackDao() {
		return feedBackDao;
	}

	public void setFeedBackDao(FeedBackDao feedBackDao) {
		this.feedBackDao = feedBackDao;
	}

	@Override
	public List<FeedBack> getFeedBackList(Integer isReplied, String order, String userId) {
		return feedBackDao.getFeedBackList(isReplied, order, userId);
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
			e.printStackTrace();
			System.out.println("错误！！！");
		}
		return flag;
	}
}
