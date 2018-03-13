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
	public Map<String, Integer> insertFood(Food food) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int row =sqlSession.insert("insertFood", food);
		map.put("row", row);
		map.put("foodId", food.getFoodId());
		sqlSession.commit();
		return map;
	}

	@Override
	public int updateFood(Food food) {
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

	@Override
	public int insertPlan(int day, int pid, int foodId) {
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("day", day);
		args.put("pid", pid);
		args.put("foodId", foodId);
		int row =sqlSession.insert("insertPlan", args);
		sqlSession.commit();
		return row;
	}

	@Override
	public int deletePlan(int foodId) {
		int row =sqlSession.delete("deletePlanByFoodId", foodId);
		sqlSession.commit();
		return row;
	}


}