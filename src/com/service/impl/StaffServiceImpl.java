package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StaffDao;
import com.entity.Staff;
import com.tool.Encryption;
import com.service.StaffService;


@Service("staffService")
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffDao staffDao = null;

	public StaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	@Override
	public Staff getStaffBySid(String sid) {
		return staffDao.queryStaff(sid);
	}

	@Override
	public Staff checkPassword(String sid,String spassword) {

		Staff staff = staffDao.queryBySid(sid.trim());
		// 不存在用户
		if (staff == null)
			return null;
		// 密码相同，使用MD5
		System.out.println("密码验证");
//		if (staff.getSpassword().equals(Encryption.getMD5Encryption(spassword)))
		if (staff.getSpassword().equals(spassword))
			return staff;

		return null;
	}
	

}
