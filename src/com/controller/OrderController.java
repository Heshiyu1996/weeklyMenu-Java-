package com.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.FeedBack;
import com.entity.Order;
import com.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService = null;

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
//	根据dateCode、userId获取订单条数
	@ResponseBody
	@RequestMapping(value ="/getOrdersByDateAndUid")
	public Map<String, Object> getOrdersByDateAndUid(HttpSession session, 
			@RequestParam(value="dateCode")String dateCode){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			List<Order> order = orderService.getOrdersByDateCodeUserId(dateCode, Integer.parseInt(uid));
			System.out.println(order);
			List<Boolean> periodList = new ArrayList<Boolean>();
			periodList.add(true);
			periodList.add(true);
			periodList.add(true);
			for (int i=0; i<order.size(); i++) {
				periodList.set(order.get(i).getPid()-1, false);
			}
			
			map.put("msg", "获取该用户在 " + dateCode + " 的订餐信息成功，注意：为false则表示用户在该时间段已经点过了");
			map.put("relatedObject", periodList);
			map.put("success", true);
		}
		return map;
	}
//	根据duserId获取订单条数
	@ResponseBody
	@RequestMapping(value ="/getMyOrders")
	public Map<String, Object> getMyOrders(HttpSession session){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			List<Order> order = orderService.getOrdersByUserId(Integer.parseInt(uid));
			
			map.put("msg", "获取该用户的订单成功");
			map.put("relatedObject", order);
			map.put("success", true);
		}
		return map;
	}
	// 新增订单order、以及orderDetail
	@ResponseBody
	@RequestMapping(value ="/addOrder", method = RequestMethod.POST)
	public Map<String,Object> addOrder(HttpSession session,
			@RequestBody JSONObject bookDetail){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
			return map;
		}
		
		String dateCode = bookDetail.getString("dateCode");
		System.out.println(bookDetail);
		System.out.println(dateCode);
		int pid = bookDetail.getInt("pid");
		int totalMoney = bookDetail.getInt("totalMoney");
		String orderId = dateCode + "0" + Integer.toString(pid) + uid;
		System.out.println("orderId：" + orderId);
		// 组件一张“新订单”
		Order order = new Order();
		order.setOrderId(orderId);
		order.setDateCode(dateCode);
		order.setPid(pid);
		order.setUserId(Integer.parseInt(uid));
		order.setTotalMoney(totalMoney);
		order.setCreateTime(new Date());

		boolean isAdd = true;
		
		isAdd = orderService.addOrder(order);
		
		
		System.out.println("mydata");
        Iterator iterator = bookDetail.keys();
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			String value = bookDetail.getString(key);
			if (key == "dateCode") break;
			String[] strs = value.split("-");
			int foodId = Integer.parseInt(key);
			int price = Integer.parseInt(strs[0]);
			int count = Integer.parseInt(strs[1]);
			isAdd = orderService.addOrderDetail(foodId, count, orderId);
		}
		if (isAdd) {
			map.put("success", true);
			map.put("msg", "新增订单成功！");
			return map;
		} else {
			map.put("success", false);
			map.put("msg", "订单新增失败啦！");
			return map;
		}
			
	}
}
