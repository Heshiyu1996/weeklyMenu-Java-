package com.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FoodDao;
import com.dao.SearchDao;
import com.entity.Category;
import com.entity.Food;
import com.tool.Encryption;
import com.service.FoodService;

@Service("foodService")
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodDao foodDao = null;

	public FoodDao getFoodDao() {
		return foodDao;
	}

	public void setFoodDao(FoodDao foodDao) {
		this.foodDao = foodDao;
	}

	@Override
	public Food getFoodDetailByFoodId(int foodId) {
//		foodDao.addVisitCount(-1);// 未知，一定要这个才能查到最新数据
		return foodDao.queryByFoodId(foodId);
	}

	@Override
	public boolean addVisitCount(int foodId) {
		boolean flag=false;
		try {
			flag=(foodDao.addVisitCount(foodId)==1)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Food> getFoodsByKeyword(String keyword) {
//		foodDao.addVisitCount(-1);// 未知，一定要这个才能查到最新数据
		return foodDao.getFoodsByKeyword(keyword);
	}

	@Override
	public boolean insertMarks(Integer foodId, Integer userId, Date createTime) {
		boolean flag=false;
		try {
			flag=(foodDao.insertMarks(foodId, userId, createTime)==1)?true:false;
			flag=(foodDao.addMarkCount(foodId)==1)?true:false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;
	}

	@Override
	public boolean removeMarks(Integer foodId, Integer userId) {
		boolean flag=false;
		try {
			flag=(foodDao.removeMarks(foodId, userId)==1)?true:false;
			flag=(foodDao.decMarkCount(foodId)==1)?true:false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;
	}

	@Override
	public boolean ifExistsMarks(Integer foodId, Integer userId) {
		boolean flag=false;
		System.out.println(foodId);
		try {
			flag=(foodDao.ifExistsMarks(foodId, userId)==1)?true:false;
		} catch (Exception e) {
			System.out.println("“查询是否存在于收藏表”出错了");
		}
		return flag;
	}

	@Override
	public List<Food> getMyMarksList(Integer userId) {
		return foodDao.getMyMarksList(userId);
	}

	@Override
	public List<Category> getAllCategories() {
		return foodDao.getAllCategories();
	}

	@Override
	public List<Food> getHotFoods() {
		return foodDao.queryHotFoods();
	}
}
