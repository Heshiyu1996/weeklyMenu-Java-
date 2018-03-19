package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdminDao;
import com.entity.Character;
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
	public int insertFood(Food food, List<String> plans) {
		boolean flag=false;
		int newFoodId = -1;
		try {
			Map<String, Integer> resultMap = adminDao.insertFood(food);
			flag=(resultMap.get("row")==1)?true:false;
			int foodId = resultMap.get("foodId");
			

			for (int i=0; i<plans.size(); i++) {
				System.out.println(plans.get(i));
				String plan = plans.get(i);
				String[] strs = plan.split("-");
				for (int j=1,len=strs.length; j<len; j++) {
					System.out.println(strs[j].toString());
					flag=(adminDao.insertPlan(Integer.parseInt(strs[0]), Integer.parseInt(strs[j]), foodId)==1)?true:false;
				}
			}
			newFoodId = foodId;
		} catch (Exception e) {
			newFoodId = -1;
			e.printStackTrace();
		}
		return newFoodId;
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
				adminDao.deletePlan(foodsId[i]);
			}
		} catch (Exception e) {
			System.out.println(e);
			flag=false;
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

	@Override
	public List<Character> getAnalysisByProvince() {
		return adminDao.getAnalysisByProvince();
	}

}
