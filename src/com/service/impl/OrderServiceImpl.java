package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderDao;
import com.entity.FeedBack;
import com.entity.Food;
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

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		return orderDao.queryOrdersByUserId(userId);
	}

	@Override
	public List<Order> getOrdersByOrderId(String orderId) {
		return orderDao.queryOrdersByOrderId(orderId);
	}

	@Override
	public int getBuyTimesByUserId(int foodId, int userId) {
		return orderDao.queryBuyTimesByUserId(foodId, userId);
	}

	@Override
	public int getSearchTimesByUserId(String keyword, int userId) {
		return orderDao.querySearchTimesByUserId(keyword, userId);
	}

	@Override
	public int getMarkTimesByUserId(int foodId, int userId) {
		return orderDao.queryMarkTimesByUserId(foodId, userId);
	}

	@Override
	public int getTasteByUserId(int userId) {
		return orderDao.queryTasteByUserId(userId);
	}

	@Override
	public List<Food> getFoodsByDayPidCid2(Integer day, Integer pid, Integer cid) {
		return orderDao.queryFoodsByDayPidCid2(day, pid, cid);
	}
}
