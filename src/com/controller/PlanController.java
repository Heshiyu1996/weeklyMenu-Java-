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

import com.entity.Category;
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

//	获取周日历
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

//	根据day、pid获取cids列表
	@ResponseBody
	@RequestMapping(value ="/getCidsByDayPid")
	public Map<String, Object> getCidsByDayPid(HttpSession session, @RequestParam(value="day")int day, @RequestParam(value="pid")int pid) {
		Map<String,Object> map=new HashMap<String, Object>();
			List<Category> cids = planService.getCidsByDayPid(day, pid);
			Map<String, Object> listMap=new HashMap<String, Object>();
			listMap.put("categories", cids);
			map.put("msg", "根据day、pid获取cids列表成功");
			map.put("relatedObject", listMap);
			map.put("success", true);
		return map;
	}
	

//	根据day、pid、cid获取foods列表
	@ResponseBody
	@RequestMapping(value ="/getFoodsByDayPidCid")
	public Map<String, Object> getFoodsByDayPidCid(HttpSession session, @RequestParam(value="day")int day, @RequestParam(value="pid")int pid, @RequestParam(value="cid")int cid) {
		Map<String,Object> map=new HashMap<String, Object>();
			List<Food> foods = planService.getFoodsByDayPidCid(day, pid, cid);
			Map<String, Object> listMap=new HashMap<String, Object>();
			listMap.put("foods", foods);
			map.put("msg", "根据day、pid、cid获取foods列表成功");
			map.put("relatedObject", listMap);
			map.put("success", true);
		return map;
	}
}
