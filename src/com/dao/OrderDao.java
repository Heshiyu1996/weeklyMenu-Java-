package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.FeedBack;
import com.entity.Order;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
	
	public List<Order> queryOrdersByDateCodeUserId(String dateCode, int userId);
	
	public List<Order> queryOrdersByUserId(int userId);
	
    int insertOrder(Order order);
	
    int insertOrderDetail(int foodId, int count, String orderId);
	
}
