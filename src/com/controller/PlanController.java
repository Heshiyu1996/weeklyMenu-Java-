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

import com.entity.Food;
import com.entity.Plan;
import com.entity.FeedBack;
import com.service.PlanService;

@Controller
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	private PlanService planService = null;

	public PlanService getPlanService() {
		return planService;
	}

	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}
	
//	获取菜品列表
	@ResponseBody
	@RequestMapping(value ="/getWeekCalendar")
	public Map<String, Object> getWeekCalendar(HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> calendarMap = planService.getWeekCalendar();
			map.put("msg", "获取周日历成功");
			map.put("relatedObject", calendarMap);
			map.put("success", true);
		return map;
	}
	
}
