package com.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CommonDao;
import com.dao.FeedBackDao;
import com.entity.Common;
import com.entity.FeedBack;
import com.tool.Encryption;
import com.service.CommonService;
import com.service.FeedBackService;

@Service("commonService")
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	private CommonDao commonDao = null;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public Common getCommon(Date nowTime) {
		try {
			commonDao.updateCommon(nowTime);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("更新为当前时间错误啊！！！");
		}
		return commonDao.getNowTime();
	}
}
