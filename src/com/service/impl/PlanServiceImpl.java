package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PlanDao;
import com.dao.FeedBackDao;
import com.entity.Category;
import com.entity.Character;
import com.entity.Food;
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
	public List<Food> getFoodsByDayPidCid(Integer day, Integer pid, Integer cid) {
		return planDao.queryFoodsByDayPidCid(day, pid, cid);
	}

	@Override
	public Map<String, Object> getWeekCalendar() {
		return planDao.queryWeekCalendar();
	}

	@Override
	public List<Category> getCidsByDayPid(Integer day, Integer pid) {
		return planDao.queryCidsByDayPid(day, pid);
	}

//	@Override
//	public int getBuyTimesByUserId(int foodId, int userId) {
//		System.out.println("foodid");
//		System.out.println(foodId);
//		return planDao.queryBuyTimesByUserId(foodId, userId);
//	}
//
//	@Override
//	public int getSearchTimesByUserId(String keyword, int userId) {
//		System.out.println(keyword);
//		System.out.println(userId);
//		return planDao.querySearchTimesByUserId(keyword, userId);
//	}
//
//	@Override
//	public int getMarkTimesByUserId(int foodId, int userId) {
//		return planDao.queryMarkTimesByUserId(foodId, userId);
//	}
//
//	@Override
//	public int getTasteByUserId2(int userId) {
//		System.out.println(userId);
//		return planDao.queryTasteByUserId2(userId);
//	}
//
//	@Override
//	public Character myTest(int userId) {
//		return planDao.queryTest(userId);
//	}


	@Override
	public int getTasteByUserId3(int userId) {
		return planDao.queryTasteByUserId3(userId);
	}
}
