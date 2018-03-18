package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.FeedBack;
import com.entity.Food;
import com.entity.Order;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
	
	public List<Order> queryOrdersByDateCodeUserId(String dateCode, int userId);
	
	public List<Order> queryOrdersByUserId(int userId);
	
	public List<Order> queryOrdersByOrderId(String orderId);
	
    int insertOrder(Order order);
	
    int insertOrderDetail(int foodId, int count, String orderId);
    
    int queryBuyTimesByUserId(int foodId, int userId);
    
    int querySearchTimesByUserId(String keyword, int userId);
    
    int queryMarkTimesByUserId(int foodId, int userId);
    
    int queryTasteByUserId(int userId);
	
	List<Food> queryFoodsByDayPidCid2(Integer day, Integer pid, Integer cid);
    
}
