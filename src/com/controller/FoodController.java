package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.FeedBack;
import com.entity.Food;
import com.service.FoodService;

@Controller
@RequestMapping("/food")
public class FoodController {


	@Autowired
	private FoodService foodService = null;

	public FoodService getFoodService() {
		return foodService;
	}

	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}
	
//	根据foodId获取食物详情
	@ResponseBody
	@RequestMapping(value ="/getFoodInfoById")
	public Map<String, Object> getFeedBackListById(HttpSession session, @RequestParam(value="foodId")int foodId){
		Map<String,Object> map=new HashMap<String, Object>();

		Food food = foodService.getFoodDetailByFoodId(foodId);
		map.put("msg", "获取食物信息成功");
		map.put("relatedObject", food);
		map.put("success", true);
		return map;
	}


//	新增浏览量
	@RequestMapping(value = "/addVisitCount", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateFeedBack(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="foodId")int foodId) {
		Food food = new Food();
		food.setFoodId(foodId);
		boolean isAdd = foodService.addVisitCount(food);
		Map<String, Object> map = new HashMap<String, Object>();
		if (isAdd == true) {
			map.put("msg", "新增浏览量成功");
			map.put("success", true);
		} else {
			map.put("msg", "新增浏览量失败");
			map.put("success", false);
		}

		return map;
	}
}
