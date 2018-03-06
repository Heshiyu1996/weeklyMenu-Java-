package com.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.SearchDao;
import com.entity.FeedBack;
import com.entity.Search;
@Repository("searchDao")
public class SearchDaoImpl implements SearchDao {
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int ifExistsKeyword(String keyword) {
		return sqlSession.selectOne("ifExistKeyword", keyword);
	}

	@Override
	public int recordKeyword(String keyword) {
		int result = sqlSession.update("recordKeyword", keyword);
		sqlSession.commit();
		return result;
	}

	@Override
	public int addKeywordCount(String keyword) {
		int result = sqlSession.update("addKeywordCount", keyword);
		sqlSession.commit();
		return result;
	}

	@Override
	public List<Search> getKeywords() {
		List<Search> list = sqlSession.selectList("selectHotKeywords");
		return list;
	}


}