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
	@RequestMapping(value ="/queryStaff.do")
	public Map<String, Object> queryStaff(String sid){
		Staff staff = staffService.queryStaff(sid);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sid",staff.getsid());
		map.put("sname", staff.getSname());
		map.put("smobile", staff.getSmobile());
		return map;
	}
//	登录
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> loginUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="sid")String sid, @RequestParam(value="spassword")String spassword) {
		System.out.println("用户名："+sid);
		Map<String, Object> map = new HashMap<String, Object>();
		Staff checkUser = staffService.checkPassword(sid, spassword);
		// 登录成功
		if (checkUser != null) {
			session.setAttribute("sid", checkUser.getsid());
			map.put("status", true);
			map.put("msg", "登录成功");
			map.put("uid", checkUser.getsid());
		} else {
			map.put("status", false);
			map.put("msg", "登录失败");
		}

		return map;
	}
}
