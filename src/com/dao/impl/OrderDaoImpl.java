package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.FeedBackDao;
import com.dao.OrderDao;
import com.entity.FeedBack;
import com.entity.Food;
import com.entity.Order;
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Order> queryOrdersByDateCodeUserId(String dateCode, int userId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("dateCode", dateCode);
		args.put("userId", userId);
		List<Order> list = sqlSession.selectList("queryOrdersByDateCodeUserId", args);
		return list;
	}

	@Override
	public int insertOrder(Order order) {
		System.out.println("order：" + order);
		int row = sqlSession.insert("insertOrder", order);
		sqlSession.commit();
		return row;
	}

	@Override
	public int insertOrderDetail(int foodId, int count, String orderId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("foodId", foodId);
		args.put("count", count);
		args.put("orderId", orderId);
		int result = sqlSession.insert("insertOrderDetail", args);
		sqlSession.commit();
		return result;
	}

	@Override
	public List<Order> queryOrdersByUserId(int userId) {
		List<Order> list = sqlSession.selectList("queryOrdersByUserId", userId);
		return list;
	}

	@Override
	public List<Order> queryOrdersByOrderId(String orderId) {
		List<Order> list = sqlSession.selectList("queryOrdersByOrderId", orderId);
		return list;
	}

	@Override
	public int queryBuyTimesByUserId(int foodId, int userId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("foodId", foodId);
		args.put("userId", userId);
		int times = sqlSession.selectOne("queryBuyTimesByUserId", args);
		return times;
	}

	@Override
	public int querySearchTimesByUserId(String keyword, int userId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("keyword", keyword);
		args.put("userId", userId);
		int times = sqlSession.selectOne("querySearchTimesByUserId", args);
		return times;
	}

	@Override
	public int queryMarkTimesByUserId(int foodId, int userId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("foodId", foodId);
		args.put("userId", userId);
		int times = sqlSession.selectOne("queryMarkTimesByUserId", args);
		return times;
	}

	@Override
	public int queryTasteByUserId(int userId) {
		int times = sqlSession.selectOne("queryTasteByUserId", userId);
		return times;
	}
	
	@Override
	public List<Food> queryFoodsByDayPidCid2(Integer day, Integer pid, Integer cid) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("day", day);
		args.put("pid", pid);
		args.put("cid", cid);
		List<Food> list = sqlSession.selectList("queryFoodsByDayPidCid2", args);
		return list;
	}

}