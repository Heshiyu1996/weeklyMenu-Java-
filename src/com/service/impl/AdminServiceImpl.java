package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdminDao;
import com.entity.FeedBack;
import com.entity.Food;
import com.tool.Encryption;
import com.service.AdminService;
import com.service.FeedBackService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao = null;

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
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
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertFood(Food food, int day, int pid) {
//		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag=false;
		try {
			flag=(adminDao.insertFood(food).get("row")==1)?true:false;
//			map.put("flag", flag);
			int foodId = adminDao.insertFood(food).get("foodId");
//			map.put("foodId", foodId);
			flag=(adminDao.insertPlan(day, pid, foodId)==1)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return map;
		return flag;
	}

	@Override
	public boolean updateFood(Food food) {
		boolean flag=false;
		try {
			flag=(adminDao.updateFood(food)==1)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteFood(int[] foodsId) {
		boolean flag=false;
		try {
			for (int i=0; i<foodsId.length; i++) {
				flag=(adminDao.deleteFood(foodsId[i])==1)?true:false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;
	}

	@Override
	public List<Food> getFoodsList() {
		return adminDao.getFoodsList();
	}

	@Override
	public List<Food> getFoodsByKeyword(String keyword) {
		return adminDao.getFoodsByKeyword(keyword);
	}

}
