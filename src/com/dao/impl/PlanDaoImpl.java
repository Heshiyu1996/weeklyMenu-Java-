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
	public Map<String, Object> getThisWeek() {
		return WeekCalendar.getWeekCalendar();
	}
}