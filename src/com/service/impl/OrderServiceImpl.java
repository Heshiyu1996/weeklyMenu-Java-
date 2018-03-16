package com.service.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public boolean addOrder(Order order) {
		boolean flag=false;
		try {
			int row = orderDao.insertOrder(order);
			flag=(row==1)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addOrderDetail(int foodId, int count, String orderId) {
		boolean flag=false;
		try {
			flag=(orderDao.insertOrderDetail(foodId, count, orderId)==1)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("新增订单详情错误！！！");
		}
		return flag;
	}
}
