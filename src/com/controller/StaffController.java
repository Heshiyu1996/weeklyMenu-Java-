package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@ResponseBody
	@RequestMapping(value ="/queryStaff.do")
	public Map<String, Object> queryStaff(int sid){
		Staff staff = staffService.queryStaff(sid);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sid",staff.getsid());
		map.put("sname", staff.getSname());
		map.put("smobile", staff.getSmobile());
		return map;
	}
}
