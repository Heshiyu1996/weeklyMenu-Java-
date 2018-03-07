package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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

import com.entity.Category;
import com.entity.FeedBack;
import com.entity.Food;
import com.entity.Search;
import com.entity.User;
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
	
//	根据keyword获取食物详情
	@ResponseBody
	@RequestMapping(value ="/getFoodsByKeyword")
	public Map<String, Object> getFeedBackListById(HttpSession session, @RequestParam(value="keyword")String keyword) throws ServletException, IOException{
		String word = new String(keyword.getBytes("iso8859-1"), "utf-8");
		System.out.println("拿到word了：" +word+","+keyword);
		Map<String,Object> map=new HashMap<String, Object>();
		List<Food> food = foodService.getFoodsByKeyword(word);
		Map<String, Object> listMap=new HashMap<String, Object>();
		listMap.put("myList", food);
		map.put("msg", "获取食物信息成功");
		map.put("relatedObject", listMap);
		map.put("success", true);
		return map;
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
		boolean isAdd = foodService.addVisitCount(foodId);
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
	
//	加入收藏
	@RequestMapping(value = "/insertMarks", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> insertFeedBack(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="foodId")int foodId, @RequestParam(value="userId")int userId) {
		boolean isAdd = foodService.insertMarks(foodId, userId, new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		if (isAdd == true) {
			map.put("msg", "加入收藏成功");
			map.put("success", true);
		} else {
			map.put("msg", "加入收藏失败");
			map.put("success", false);
		}

		return map;
	}
	
//	加入收藏
	@RequestMapping(value = "/removeMarks", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> removeFeedBack(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="foodId")int foodId, @RequestParam(value="userId")int userId) {
		boolean isAdd = foodService.removeMarks(foodId, userId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (isAdd == true) {
			map.put("msg", "删除收藏成功");
			map.put("success", true);
		} else {
			map.put("msg", "删除收藏失败");
			map.put("success", false);
		}

		return map;
	}

//	检查是否已收藏
	@ResponseBody
	@RequestMapping(value ="/checkMarks")
	public Map<String, Object> checkMarks(HttpSession session, @RequestParam(value="foodId")int foodId){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			boolean isExist = foodService.ifExistsMarks(foodId, Integer.valueOf(uid));
			Map<String, Object> existMap = new HashMap<String, Object>();
			existMap.put("isExist", isExist);
			map.put("msg", "检查“是否被收藏”成功");
			map.put("relatedObject", existMap);
			map.put("success", true);
		}
		return map;
	}
	
//	根据userId获取收藏列表
	@ResponseBody
	@RequestMapping(value ="/getMyMarksList")
	public Map<String, Object> getMyMarksList(HttpSession session){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			List<Food> foodList = foodService.getMyMarksList(Integer.valueOf(uid));
			map.put("msg", "获取我的收藏成功");
			map.put("relatedObject", foodList);
			map.put("success", true);
		}
		return map;
	}

//	获取所有食品类别
	@ResponseBody
	@RequestMapping(value ="/getAllCategories")
	public Map<String, Object> getAllCategories(HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		List<Category> category = foodService.getAllCategories();
		map.put("msg", "获取所有菜品分类成功");
		map.put("relatedObject", category);
		map.put("success", true);
		return map;
	}
}

