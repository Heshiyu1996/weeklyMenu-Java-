package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
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
	

//	根据day、pid、cid获取foods列表（旧）
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

//	根据day、pid获取foods列表（新）
	@ResponseBody
	@RequestMapping(value ="/getFoodsByDayPid")
	public Map<String, Object> getFoodsByDayPid(HttpSession session, 
			@RequestParam(value="day")int day, 
			@RequestParam(value="pid")int pid) {
			Map<String,Object> map=new HashMap<String, Object>();
			// 先根据day、pid拿到cid
			List<Category> cids = planService.getCidsByDayPid(day, pid);
			List<Object> allFoodsListMap=new ArrayList<Object>();
			System.out.println("cids:");
			System.out.println(cids);
			// 再根据cid一个个去拿到foods
			for (int i=0; i < cids.size(); i++) {
				// 获取当前cid下的所有foods
				List<Food> foods = planService.getFoodsByDayPidCid(day, pid, cids.get(i).getCid());
				// 新建一个cid的json对象，第一个字段cid，第二个cname，第三个foods来装“当前cid下的所有foods”
				Map<String, Object> cidsListMap=new HashMap<String, Object>();
				cidsListMap.put("cid", cids.get(i).getCid());
				cidsListMap.put("cname", cids.get(i).getCname());
				cidsListMap.put("foods", foods);
				allFoodsListMap.add(cidsListMap);
			}
			map.put("msg", "获取星期" + day + "的第" + pid + "时段的菜单成功！");
			map.put("relatedObject", allFoodsListMap);
			map.put("success", true);
		return map;
	}

//	猜你喜欢
	@ResponseBody
	@RequestMapping(value ="/getRecommendFoodsByDayPid")
	public Map<String, Object> getRecommendFoodsByDayPid(HttpSession session, 
			@RequestParam(value="day")int day, 
			@RequestParam(value="pid")int pid) {
			String uid=(String)session.getAttribute("uid_session");
			Map<String,Object> map=new HashMap<String, Object>();
			List<Object> allFoodsListMap=new ArrayList<Object>();

			List<Food> foods = planService.getFoodsByDayPidCid(day, pid, null);
			System.out.println(foods);
//			System.out.println(planService.myTest(1));
			//打分环节：
			for (int i=0; i<foods.size(); i++) {
				int foodid = foods.get(i).getFoodId();
				int userId = Integer.parseInt(uid);
				
				int searchPoint = 5;
				int characterPoint = 10;
				int markPoint = 10;
				int buyPoint = 25;
//				System.out.println( planService.getSearchTimesByUserId(foods.get(i).getName(), userId));
				System.out.println(planService.getTasteByUserId3(1));
//				int searchTimes = planService.getSearchTimesByUserId(foods.get(i).getName(), userId);

				int searchTimes = 1;
				
				int characterTimes = 1;
//				if (foods.get(i).getTaste() == planService.getTasteByUserId(1)) {
//					characterTimes = 2;
//				}
				int markTimes = 1;
//				if (planService.getMarkTimesByUserId(foodid, userId) > 0) {
//					markTimes = 2;
//				}
				
//				int buyTimes = planService.getBuyTimesByUserId(foodid, userId);
				int buyTimes = 1;
				int totalPoint = (searchPoint * searchTimes) 
						+ (characterPoint * characterTimes) 
						+ (markPoint * markTimes)
						+ (buyPoint * buyTimes);
						
				System.out.println("id=" + foodid + "的得分是：" + totalPoint);
				foods.get(i).setTotalPoint(totalPoint);
			}

			allFoodsListMap.add(foods);
			System.out.println(allFoodsListMap);
			map.put("msg", "获取星期" + day + "的第" + pid + "时段的菜单成功！");
			map.put("relatedObject", allFoodsListMap);
			map.put("success", true);
		return map;
	}
}
