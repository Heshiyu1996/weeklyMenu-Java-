package com.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.CommonDao;
import com.dao.DBAccess;
import com.dao.FeedBackDao;
import com.entity.Common;
import com.entity.FeedBack;
@Repository("commonDao")
public class CommonDaoImpl implements CommonDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public Common getNowTime() {
		Common common = sqlSession.selectOne("getCommon");
		return common;
	}

	@Override
	public int updateCommon(Date nowTime) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("nowTime", nowTime);
		int result = sqlSession.update("updateCommonSQL", args);
		sqlSession.commit();
		return result;
	}

}