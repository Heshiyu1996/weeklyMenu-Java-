package com.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.FeedBackDao;
import com.entity.FeedBack;
@Repository("feedBackDao")
public class FeedBackDaoImpl implements FeedBackDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<FeedBack> getFeedBackList(FeedBack fb) {
		System.out.println(fb);
		List<FeedBack> list = sqlSession.selectList("selectAllFeedBack", fb);
		return list;
	}

	@Override
	public List<FeedBack> getFeedBackListByUserId(String userId) {
		List<FeedBack> list = sqlSession.selectList("selectFeedBackListByUserId", userId);
		return list;
	}

	@Override
	public FeedBack getFeedBackById(int fid) {
		FeedBack fb = sqlSession.selectOne("selectFeedBackListById", fid);
		return fb;
	}

	@Override
	public int insertFeedBack(FeedBack fb) {
		int row =sqlSession.insert("insertFeedBack", fb);
		sqlSession.commit();
		return row;
	}

	@Override
	public int updateFeedBack(FeedBack fb) {
		int result = sqlSession.update("updateFeedBack", fb);
		sqlSession.commit();
		return result;
	}


}