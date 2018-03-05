package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdminDao;
import com.entity.FeedBack;
import com.tool.Encryption;
import com.service.AdminService;
import com.service.FeedBackService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao = null;

	public AdminDao getfeedBackDao() {
		return adminDao;
	}

	public void setadminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public List<FeedBack> loadFeedBackList(Integer isReplied, String order) {
		return adminDao.loadFeedBackList(isReplied, order);
	}

	@Override
	public boolean updateFeedBack(FeedBack fb) {
		boolean flag=false;
		try {
			flag=(adminDao.updateFeedBack(fb)==1)?true:false;
		} catch (Exception e) {
		}
		return flag;
	}

}
