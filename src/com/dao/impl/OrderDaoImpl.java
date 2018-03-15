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

}