package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
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

import com.alibaba.fastjson.JSONObject;
import com.entity.FeedBack;
import com.entity.Food;
import com.service.AdminService;
import com.service.FoodService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService = null;

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	//	获取（管理员）反馈列表
	@ResponseBody
	@RequestMapping(value ="/loadFeedBackList")
	public Map<String, Object> getFeedBackList(HttpSession session, @RequestParam(value="isReplied", required=false)Integer isReplied, @RequestParam(value="order", required=false)String order){
		Integer utype=(Integer)session.getAttribute("utype_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(utype==null){
			map.put("success", false);
			map.put("msg", "权限不足，接口调用失败！");
		} else {
			List<FeedBack> feedBack = adminService.loadFeedBackList(isReplied, order);
			Map<String, Object> listMap=new HashMap<String, Object>();
			listMap.put("myList", feedBack);
			map.put("msg", "（管）获取反馈列表成功");
			map.put("relatedObject", listMap);
			map.put("success", true);
		}
		return map;
	}

//	回复反馈
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateFeedBack(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="fid")int fid, @RequestParam(value="isReplied")int isReplied, @RequestParam(value="repliedMsg")String repliedMsg) {
		FeedBack fb = new FeedBack();
		fb.setFid(fid);
		fb.setIsReplied(isReplied);
		fb.setRepliedMsg(repliedMsg);
		fb.setRepliedTime(new Date());
		boolean isAdd = adminService.updateFeedBack(fb);
		Map<String, Object> map = new HashMap<String, Object>();
		if (isAdd == true) {
			map.put("msg", "回复反馈成功");
			map.put("success", true);
		} else {
			map.put("msg", "回复反馈失败");
			map.put("success", false);
		}

		return map;
	}

//	新增菜品
	@RequestMapping(value = "/insertFood", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> insertFood(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="name")String name, @RequestParam(value="imgUrl")String imgUrl, @RequestParam(value="material")String material, @RequestParam(value="description")String description, @RequestParam(value="categoryId")int categoryId, @RequestParam(value="plans")String[] plans) {
		Food food = new Food();
		food.setName(name);
		food.setImgUrl(imgUrl);
		food.setMaterial(material);
		food.setDescription(description);
		food.setCategoryId(categoryId);
		System.out.println("收到plans");
		System.out.println(plans);
//		boolean isAdd = ((Boolean)adminService.insertFood(food).get("flag")).booleanValue();
		Map<String, Object> map = new HashMap<String, Object>();
//		if (isAdd == true) {
//			map.put("msg", "添加菜品成功");
//			map.put("foodId", adminService.insertFood(food).get("foodId"));
//			map.put("success", true);
//		} else {
//			map.put("msg", "添加菜品失败");
//			map.put("success", false);
//		}
//
		return map;
	}

//	编辑菜品
	@RequestMapping(value = "/updateFood", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateFood(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="foodId")int foodId, @RequestParam(value="name")String name, @RequestParam(value="imgUrl")String imgUrl, @RequestParam(value="material")String material, @RequestParam(value="description")String description, @RequestParam(value="categoryId")int categoryId) {
		Food food = new Food();
		food.setFoodId(foodId);
		food.setName(name);
		food.setImgUrl(imgUrl);
		food.setMaterial(material);
		food.setDescription(description);
		food.setCategoryId(categoryId);
		boolean isAdd = adminService.updateFood(food);
		Map<String, Object> map = new HashMap<String, Object>();
		if (isAdd == true) {
			map.put("msg", "编辑菜品成功");
			map.put("success", true);
		} else {
			map.put("msg", "编辑菜品失败");
			map.put("success", false);
		}
		return map;
	}


//	删除菜品
	@RequestMapping(value = "/deleteFood", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> deleteFood(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="foodsId")int[] foodsId) {
		boolean isAdd = adminService.deleteFood(foodsId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (isAdd == true) {
			map.put("msg", "删除菜品成功");
			map.put("success", true);
		} else {
			map.put("msg", "删除菜品失败");
			map.put("success", false);
		}
		return map;
	}

//	获取菜品列表
	@ResponseBody
	@RequestMapping(value ="/getFoodsList")
	public Map<String, Object> getFoodsList(HttpSession session){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			List<Food> food = adminService.getFoodsList();
			Map<String, Object> listMap=new HashMap<String, Object>();
			listMap.put("myList", food);
			map.put("msg", "获取菜品列表成功");
			map.put("relatedObject", listMap);
			map.put("success", true);
		}
		return map;
	}
	
//	根据keyword获取食物详情
	@ResponseBody
	@RequestMapping(value ="/getFoodsByKeyword")
	public Map<String, Object> getFoodsByKeyword(HttpSession session, @RequestParam(value="keyword")String keyword) throws ServletException, IOException{
		String word = new String(keyword.getBytes("iso8859-1"), "utf-8");
		Map<String,Object> map=new HashMap<String, Object>();
		List<Food> food = adminService.getFoodsByKeyword(word);
		Map<String, Object> listMap=new HashMap<String, Object>();
		listMap.put("myList", food);
		map.put("msg", "根据keyword获取食物信息成功呀呀呀");
		map.put("relatedObject", listMap);
		map.put("success", true);
		return map;
	}
}
