package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.StaffDao;
import com.entity.Staff;
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
	public Staff queryStaff(int sid) {
		return staffDao.queryStaff(sid);
	}

}
