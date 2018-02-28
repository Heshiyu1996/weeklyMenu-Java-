package com.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.StaffDao;
import com.entity.Staff;
@Repository("staffDao")
public class StaffDaoImpl implements StaffDao {

	
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
	public Staff queryStaff(String sid) {
		return sqlSession.selectOne("selectStaff", sid);
	}

	@Override
	public Staff queryBySid(String sid) {
		return sqlSession.selectOne("queryBySid", sid);
	}


}