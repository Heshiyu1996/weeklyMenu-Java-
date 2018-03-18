package com.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.PlanDao;
import com.dao.DBAccess;
import com.dao.FeedBackDao;
import com.entity.Category;
import com.entity.Character;
import com.entity.Food;
import com.entity.Plan;
import com.entity.FeedBack;
import com.tool.WeekCalendar;

@Repository("planDao")
public class PlanDaoImpl implements PlanDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Map<String, Object> queryWeekCalendar() {
		return WeekCalendar.getWeekCalendar();
	}

	@Override
	public List<Category> queryCidsByDayPid(Integer day, Integer pid) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("day", day);
		args.put("pid", pid);
		List<Category> list = sqlSession.selectList("queryCidsByDayPid", args);
		return list;
	}

	@Override
	public List<Food> queryFoodsByDayPidCid(Integer day, Integer pid, Integer cid) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("day", day);
		args.put("pid", pid);
		args.put("cid", cid);
		List<Food> list = sqlSession.selectList("queryFoodsByDayPidCid", args);
		return list;
	}

//	@Override
//	public int queryBuyTimesByUserId(int foodId, int userId) {
//		Map<String, Object> args = new HashMap<String, Object>();
//		args.put("foodId", foodId);
//		args.put("userId", userId);
//		int times = sqlSession.selectOne("queryBuyTimesByUserId", args);
//		return times;
//	}
//
//	@Override
//	public int querySearchTimesByUserId(String keyword, int userId) {
//		Map<String, Object> args = new HashMap<String, Object>();
//		args.put("keyword", keyword);
//		args.put("userId", userId);
//		int times = sqlSession.selectOne("querySearchTimesByUserId", args);
//		return times;
//	}
//
//	@Override
//	public int queryMarkTimesByUserId(int foodId, int userId) {
//		Map<String, Object> args = new HashMap<String, Object>();
//		args.put("foodId", foodId);
//		args.put("userId", userId);
//		int times = sqlSession.selectOne("queryMarkTimesByUserId", args);
//		return times;
//	}
//
//	@Override
//	public int queryTasteByUserId2(int userId) {
//		return sqlSession.selectOne("queryTasteByUserId2", userId);
//	}
//
//	@Override
//	public Character queryTest(Integer userId) {
//		return sqlSession.selectOne("queryTest", userId);
//	}

	@Override
	public int queryTasteByUserId3(int userId) {
		int times = sqlSession.selectOne("queryTasteByUserId3", userId);
		return times;
	}
}