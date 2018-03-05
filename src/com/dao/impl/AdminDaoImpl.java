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


}