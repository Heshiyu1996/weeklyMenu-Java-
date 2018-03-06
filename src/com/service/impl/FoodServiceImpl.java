package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FoodDao;
import com.entity.Food;
import com.tool.Encryption;
import com.service.FoodService;

@Service("foodService")
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodDao foodDao = null;

	public FoodDao getStaffDao() {
		return foodDao;
	}

	public void setStaffDao(FoodDao foodDao) {
		this.foodDao = foodDao;
	}

	@Override
	public Food getFoodDetailByFoodId(int foodId) {
		return foodDao.queryByFoodId(foodId);
	}

}
