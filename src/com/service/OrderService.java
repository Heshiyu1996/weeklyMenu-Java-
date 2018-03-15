package com.service;

import java.util.List;

import com.entity.FeedBack;
import com.entity.Order;

public interface OrderService {

	// 获取指定用户的指定日期订单列表
	public List<Order> getOrdersByDateCodeUserId(String dateCode, int userId);
	
}
