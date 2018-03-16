package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.entity.FeedBack;
import com.entity.Food;
import com.service.AdminService;

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
	Map<String, Object> updateFeedBack(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="fid")int fid, @RequestParam(value="isReplied")int isReplied, @RequestParam(value="repliedMsg")String repliedMsg, @RequestParam(value="imageFile")MultipartFile imageFile) {
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
	Map<String, Object> insertFood(HttpServletRequest request, HttpServletResponse response, HttpSession session, 
			@RequestParam(value="name")String name, 
			@RequestParam(value="imgUrl")String imgUrl, 
			@RequestParam(value="material")String material, 
			@RequestParam(value="description")String description, 
			@RequestParam(value="categoryId")int categoryId, 
			@RequestParam(value="plans")List<String> plans, 
			@RequestParam(value="price")int price) {
		Food food = new Food();
		food.setName(name);
		food.setImgUrl(imgUrl);
		food.setMaterial(material);
		food.setDescription(description);
		food.setCategoryId(categoryId);
		food.setPrice(price);
		System.out.println(plans);
		int newFoodId = adminService.insertFood(food, plans);
		System.out.println("newFoodId为：" + newFoodId);

		// 图片替换操作
        File oldfile=new File(request.getServletContext().getRealPath("/")+"img/image.jpg"); 
        File newfile=new File(request.getServletContext().getRealPath("/")+"img/foodImg" + newFoodId + ".jpg"); 
        if(!oldfile.exists()){
            System.out.println("重命名文件不存在");
        }
        if(newfile.exists()){ 
            System.out.println("新文件已经存在，正在尝试删除"); 
        	newfile.delete();
        }
        oldfile.renameTo(newfile); 
		food.setImgUrl("/img/foodImg" + newFoodId + ".jpg");
		boolean isAdd = adminService.updateFood(food);
        
		Map<String, Object> map = new HashMap<String, Object>();
		if ((isAdd) && (newFoodId != -1)) {
			map.put("msg", "添加菜品成功");
			map.put("success", true);
		} else {
			map.put("msg", "添加菜品失败");
			map.put("success", false);
		}
		return map;
	}
	
//	编辑菜品
	@RequestMapping(value = "/updateFood", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateFood(HttpServletRequest request, HttpServletResponse response, HttpSession session, 
			@RequestParam(value="foodId")int foodId, 
			@RequestParam(value="name")String name, 
			@RequestParam(value="imgUrl")String imgUrl, 
			@RequestParam(value="material")String material, 
			@RequestParam(value="description")String description, 
			@RequestParam(value="categoryId")int categoryId, 
			@RequestParam(value="price")int price) {
		
		// 图片替换操作
        File oldfile=new File(request.getServletContext().getRealPath("/")+"img/image.jpg"); 
        File newfile=new File(request.getServletContext().getRealPath("/")+"img/foodImg" + foodId + ".jpg"); 
        if(!oldfile.exists()){
            System.out.println("重命名文件不存在");
        }
        if(newfile.exists()){ 
            System.out.println("新文件已经存在，正在尝试删除"); 
        	newfile.delete();
        }
        oldfile.renameTo(newfile); 
        
		Food food = new Food();
		food.setFoodId(foodId);
		food.setName(name);
		food.setImgUrl("/img/foodImg" + foodId + ".jpg");
		food.setMaterial(material);
		food.setDescription(description);
		food.setCategoryId(categoryId);
		food.setPrice(price);
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

//	上传图片
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> uploadFile(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="imageFile")MultipartFile imageFile) throws IOException {
		String fileName="image.jpg";
		File file=new File(request.getServletContext().getRealPath("/")+"img/"+fileName);
		if(file.exists()){
			System.out.println("文件重名啊");
			file.delete();
		}
		System.out.println(request.getServletContext().getRealPath("/"));
		FileOutputStream fileOutputStream=new FileOutputStream(file);
		fileOutputStream.write(imageFile.getBytes());
		fileOutputStream.close();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "图片上传成功");
		map.put("success", true);
		return map;
	}

//	删除菜品
	@RequestMapping(value = "/deleteFood", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> deleteFood(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="foodsId")int[] foodsId) {
		boolean isAdd = adminService.deleteFood(foodsId);
		for (int i=0; i< foodsId.length; i++) {
	        File foodImg=new File(request.getServletContext().getRealPath("/")+"img/foodImg" + foodsId[i] + ".jpg"); 
	        if(foodImg.exists()){ 
	            System.out.println("要删的菜品存在图片，正在尝试删除"); 
	            foodImg.delete();
	        }
		}
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
	//	测试
	@ResponseBody
	@RequestMapping(value ="/getTest", method = RequestMethod.POST)
	public void getTest(HttpSession session,
			@RequestBody JSONObject mydata){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			System.out.println("mydata");
	        Iterator iterator = mydata.keys();
			while(iterator.hasNext()){
				String key = (String) iterator.next();
				String value = mydata.getString(key);
				System.out.println("key" + key);
	            System.out.println("value" + value);
				String[] strs = value.split("-");
				int price = (Integer.parseInt(strs[0]));
				int count = (Integer.parseInt(strs[1]));
				System.out.println("price" + price);
				System.out.println("count" + count);
			}
		}
	}
}
