package com.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PlanDao;
import com.dao.FeedBackDao;
import com.entity.Plan;
import com.entity.FeedBack;
import com.tool.Encryption;
import com.service.PlanService;
import com.service.FeedBackService;

@Service("planService")
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanDao planDao = null;

	public PlanDao getCommonDao() {
		return planDao;
	}

	public void setCommonDao(PlanDao planDao) {
		this.planDao = planDao;
	}

	@Override
	public Plan getNowTime() {
		// TODO 自动生成的方法存根
		return null;
	}
}
