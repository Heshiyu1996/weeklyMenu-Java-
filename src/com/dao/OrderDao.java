package com.dao;

import java.util.List;

import com.entity.Order;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
	
	public List<Order> queryOrdersByDateCodeUserId(String dateCode, int userId);
	
}
