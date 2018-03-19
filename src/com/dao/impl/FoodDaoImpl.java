package com.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.FoodDao;
import com.entity.Category;
import com.entity.FeedBack;
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
	public int addVisitCount(int foodId) {
		System.out.println("打印foodId：" + foodId);
		int result = sqlSession.update("addVisitCount", foodId);
		sqlSession.commit();
		return result;
	}

	@Override
	public int addMarkCount(int foodId) {
		int result = sqlSession.update("addMarkCount", foodId);
		sqlSession.commit();
		return result;
	}

	@Override
	public List<Food> getFoodsByKeyword(String keyword) {
		List<Food> list = sqlSession.selectList("queryByKeyword", keyword);
		System.out.println(list);
		return list;
	}

	@Override
	public int insertMarks(Integer foodId, Integer userId, Date createTime) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("foodId", foodId);
		args.put("userId", userId);
		args.put("createTime", createTime);
		int row =sqlSession.insert("insertMarks", args);
		sqlSession.commit();
		return row;
	}

	@Override
	public int decMarkCount(int foodId) {
		int result = sqlSession.update("decMarkCount", foodId);
		sqlSession.commit();
		return result;
	}

	@Override
	public int removeMarks(Integer foodId, Integer userId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("foodId", foodId);
		args.put("userId", userId);
		System.out.println("我是daoImpl：");
		System.out.println(args);
		int row =sqlSession.delete("removeMarks", args);
		sqlSession.commit();
		return row;
	}

	@Override
	public int ifExistsMarks(Integer foodId, Integer userId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("foodId", foodId);
		args.put("userId", userId);
		return sqlSession.selectOne("ifExistMarks", args);
	}

	@Override
	public List<Food> getMyMarksList(Integer userId) {
		List<Food> list = sqlSession.selectList("getMyMarksList", userId);
		return list;
	}

	@Override
	public List<Category> getAllCategories() {
		List<Category> list = sqlSession.selectList("getAllCategories");
		return list;
	}

	@Override
	public List<Food> queryHotFoods() {
		List<Food> list = sqlSession.selectList("queryHotFoods");
		return list;
	}

	@Override
	public List<Map<String, Object>> queryPlanByFoodId(int foodId) {
		List<Map<String, Object>> list = sqlSession.selectList("queryPlanByFoodId", foodId);
		return list;
	}

	@Override
	public List<Food> queryFoodRecommondByOrder(int foodId) {
		List<Food> list = sqlSession.selectList("queryFoodRecommondByOrder", foodId);
		System.out.println(list);
		return list;
	}

}