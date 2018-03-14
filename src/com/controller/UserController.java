package com.controller;

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
import com.entity.User;
import com.entity.Character;
import com.service.UserService;

@Controller
@RequestMapping("/staff")
public class UserController {


	@Autowired
	private UserService userService = null;

	public UserService getStaffService() {
		return userService;
	}

	public void setStaffService(UserService userService) {
		this.userService = userService;
	}
//	查询员工
	@ResponseBody
	@RequestMapping(value ="/getUserInfo")
	public Map<String, Object> queryStaff(HttpSession session){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			User user = userService.getStaffByUid(uid);
			Map<String, Object> userMap=new HashMap<String, Object>();
			userMap.put("uid",user.getUid());
			userMap.put("uname", user.getUname());
			userMap.put("umobile", user.getUmobile());
			userMap.put("utype", user.getUtype());
			map.put("msg", "获取用户信息成功哦哦");
			map.put("relatedObject", userMap);
			map.put("success", true);
		}
		return map;
	}
//	登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> loginUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="uid")String uid, @RequestParam(value="upassword")String upassword) {
		System.out.println("用户名："+uid);
		Map<String, Object> map = new HashMap<String, Object>();
		User checkUser = userService.checkPassword(uid, upassword);
		// 登录成功
		if (checkUser != null) {
			session.setAttribute("uid_session", checkUser.getUid());
			session.setAttribute("utype_session", checkUser.getUtype());
			map.put("msg", "登录成功");
			map.put("uid", checkUser.getUid());
			map.put("success", true);
		} else {
			map.put("msg", "登录失败，请检查密码是否正确");
			map.put("success", false);
		}

		return map;
	}
//	退出登录
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> logoutUser(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		session.setAttribute("uid_session", null);
		if((String)session.getAttribute("uid_session")==null){
			map.put("msg", "注销成功");
			map.put("success", true);
		}else {
			map.put("msg", "注销失败");
			map.put("success", false);
		}

		return map;
	}
	
//	注册
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> regist(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="uid")String uid,
			@RequestParam(value="uname")String uname,
			@RequestParam(value="umobile")String umobile,
			@RequestParam(value="upassword")String upassword
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		user.setUid(uid);
		user.setUname(uname);
		user.setUmobile(umobile);
		user.setUpassword(upassword);
		boolean isAdd = userService.addtUser(user);
		// 登录成功
		if (isAdd) {
			session.setAttribute("uid_session", uid);
			session.setAttribute("utype_session", 0);
			map.put("msg", "注册成功");
			map.put("success", true);
		} else {
			map.put("msg", "注册失败");
			map.put("success", false);
		}

		return map;
	}
	
//	获取我的喜好
	@ResponseBody
	@RequestMapping(value ="/ifExistCharacter")
	public Map<String, Object> ifExistCharacter(HttpSession session) {
		
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("msg", "检查个人喜好失败，请重新登录！");
			map.put("success", false);
		} else {
			Boolean ifExist = false;
			ifExist = userService.ifExistCharacter(Integer.parseInt(uid));
			Map<String,Object> resultMap=new HashMap<String, Object>();
			resultMap.put("ifExist", ifExist);
			map.put("msg", "检查我的喜好成功");
			map.put("relatedObject", resultMap);
			map.put("success", true);
		}
		return map;
	}
	
//	获取我的喜好
	@ResponseBody
	@RequestMapping(value ="/getCharacter")
	public Map<String, Object> getCharacter(HttpSession session) {
		
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "获取个人喜好失败，请重新登录！");
		} else {
			Character character = userService.getCharacter(Integer.parseInt(uid));
			map.put("msg", "获取我的喜好成功");
			map.put("relatedObject", character);
			map.put("success", true);
		}
		return map;
	}
	
//	保存“我的喜好”
	@RequestMapping(value = "/updateCharacter", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateCharacter(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="provinceCode")String provinceCode,
			@RequestParam(value="province")String province,
			@RequestParam(value="cityCode")String cityCode,
			@RequestParam(value="city")String city,
			@RequestParam(value="nation")String nation,
			@RequestParam(value="taste")String taste,
			@RequestParam(value="tall")String tall,
			@RequestParam(value="height")String height,
			@RequestParam(value="eatHabit")String eatHabit,
			@RequestParam(value="prepare")String prepare,
			@RequestParam(value="alcohol")int alcohol,
			@RequestParam(value="attention", required=false)String attention
			) {
		Map<String,Object> map=new HashMap<String, Object>();
		String uid=(String)session.getAttribute("uid_session");
		if(uid==null){
			map.put("success", false);
			map.put("msg", "获取个人喜好失败，请重新登录！");
			return map;
		}
		
		int userId = Integer.parseInt(uid);
		boolean isExist = userService.ifExistCharacter(userId);
		boolean isSuccess = false;
		Character character = new Character();
		character.setUserId(userId);
		character.setProvince(province);
		character.setProvinceCode(provinceCode);
		character.setCity(city);
		character.setCityCode(cityCode);
		character.setNation(nation);
		character.setTaste(taste);
		character.setTall(tall);
		character.setHeight(height);
		character.setEatHabit(eatHabit);
		character.setPrepare(prepare);
		character.setAlcohol(alcohol);
		character.setAttention(attention);
		
		if (isExist) {
			isSuccess = userService.updateCharacter(character);
		} else {
			isSuccess = userService.addCharacter(character);
		}
		if (isSuccess) {
			map.put("msg", "更新我的喜好成功");
			map.put("success", true);
		} else {
			map.put("msg", "更新我的喜好失败");
			map.put("success", false);
		}

		return map;
	}
}
