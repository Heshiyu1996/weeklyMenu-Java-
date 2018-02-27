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

import com.entity.Staff;
import com.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {


	@Autowired
	private StaffService staffService = null;

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
//	查询员工
	@ResponseBody
	@RequestMapping(value ="/getUserInfo")
	public Map<String, Object> queryStaff(HttpSession session){
		String sid=(String)session.getAttribute("sid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(sid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			Staff staff = staffService.getStaffBySid(sid);
			Map<String, Object> userMap=new HashMap<String, Object>();
			userMap.put("sid",staff.getsid());
			userMap.put("sname", staff.getSname());
			userMap.put("smobile", staff.getSmobile());
			map.put("msg", "获取用户信息");
			map.put("relatedObject", userMap);
			map.put("success", true);
		}
		return map;
	}
//	登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> loginUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="sid")String sid, @RequestParam(value="spassword")String spassword) {
		System.out.println("用户名："+sid);
		Map<String, Object> map = new HashMap<String, Object>();
		Staff checkUser = staffService.checkPassword(sid, spassword);
		// 登录成功
		if (checkUser != null) {
			session.setAttribute("sid_session", checkUser.getsid());
			map.put("msg", "登录成功");
			map.put("uid", checkUser.getsid());
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
		session.setAttribute("sid_session", null);
		if((String)session.getAttribute("sid_session")==null){
			map.put("msg", "注销成功");
			map.put("success", true);
		}else {
			map.put("msg", "注销失败");
			map.put("success", false);
		}

		return map;
	}
}
