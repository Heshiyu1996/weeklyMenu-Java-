package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.FoodDao;
import com.entity.Food;
@Repository("foodDao")
public class FoodDaoImpl implements FoodDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Food queryByFoodId(int foodId) {
		return sqlSession.selectOne("queryByFoodId", foodId);
	}

	@Override
	public int addVisitCount(Food food) {
		int result = sqlSession.update("addVisitCount", food);
		sqlSession.commit();
		return result;
	}

	@Override
	public List<Food> queryByKeyword(String keyword) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("keyword", keyword);
		List<Food> list = sqlSession.selectList("queryByKeyword", args);
		return list;
	}

}