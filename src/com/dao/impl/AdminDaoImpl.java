package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.AdminDao;
import com.dao.DBAccess;
import com.dao.FeedBackDao;
import com.entity.FeedBack;
import com.entity.Food;
@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<FeedBack> loadFeedBackList(Integer isReplied, String order) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("isReplied", isReplied);
		args.put("order", order);
		List<FeedBack> list = sqlSession.selectList("selectAllFeedBackAdmin", args);
		return list;
	}

	@Override
	public int updateFeedBack(FeedBack fb) {
		int result = sqlSession.update("updateFeedBackAdmin", fb);
		sqlSession.commit();
		return result;
	}
	
	@Override
	public int insertFood(Food food) {
		int row =sqlSession.insert("insertFood", food);
		sqlSession.commit();
		return row;
	}

	@Override
	public int updateFood(Food food) {
		System.out.println("在在在");
		int result = sqlSession.update("updateFoodAdmin", food);
		sqlSession.commit();
		return result;
	}

	@Override
	public int deleteFood(int foodId) {
		int row =sqlSession.delete("deleteFood", foodId);
		sqlSession.commit();
		return row;
	}

	@Override
	public List<Food> getFoodsList() {
		List<Food> list = sqlSession.selectList("selectAllFood");
		return list;
	}

	@Override
	public List<Food> getFoodsByKeyword(String keyword) {
		List<Food> list = sqlSession.selectList("queryFoodsByKeyword", keyword);
		return list;
	}


}