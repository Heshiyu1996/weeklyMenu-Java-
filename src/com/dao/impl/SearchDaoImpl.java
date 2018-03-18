package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public int recordKeywordWithUserId(String keyword, int userId) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("keyword", keyword);
		args.put("userId", userId);
		int result = sqlSession.update("recordKeywordWithUserId", args);
		sqlSession.commit();
		return result;
	}


}