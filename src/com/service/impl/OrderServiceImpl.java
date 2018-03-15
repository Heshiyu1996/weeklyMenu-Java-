package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderDao;
import com.entity.FeedBack;
import com.entity.Order;
import com.tool.Encryption;
import com.service.FeedBackService;
import com.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao = null;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public List<Order> getOrdersByDateCodeUserId(String dateCode, int userId) {
		return orderDao.queryOrdersByDateCodeUserId(dateCode, userId);
	}
}
