package com.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.UserDao;
import com.entity.User;
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

//	@Override
//	public List<Staff> quertyAllStaff() {
//		List<Staff> list = sqlSession.selectList("selectAllDept");
//		return list;
//	}

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


}