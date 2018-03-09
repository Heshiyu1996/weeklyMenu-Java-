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
}