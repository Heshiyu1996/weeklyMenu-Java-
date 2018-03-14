package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.UserDao;
import com.entity.User;
import com.entity.Character;
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public User queryStaff(String Uid) {
		return sqlSession.selectOne("selectStaff", Uid);
	}

	@Override
	public User queryByUid(String Uid) {
		return sqlSession.selectOne("queryBySid", Uid);
	}

	@Override
	public int registUser(User user) {
		int row =sqlSession.insert("registUser", user);
		sqlSession.commit();
		return row;
	}

	@Override
	public int ifExistCharacter(Integer userId) {
		return sqlSession.selectOne("ifExistCharacter", userId);
	}

	@Override
	public int insertCharacter(Character character) {
		int row =sqlSession.insert("insertCharacter", character);
		sqlSession.commit();
		return row;
	}

	@Override
	public Character getCharacter(Integer userId) {
		return sqlSession.selectOne("getCharacter", userId);
	}

	@Override
	public int updateCharacter(Character character) {
		int result = sqlSession.update("updateCharacter", character);
		sqlSession.commit();
		return result;
	}
}