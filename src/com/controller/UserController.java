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

import com.entity.User;
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
}
