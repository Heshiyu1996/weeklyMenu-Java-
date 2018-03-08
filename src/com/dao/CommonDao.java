package com.dao;

import java.util.Date;
import java.util.List;

import com.entity.Common;
import com.entity.FeedBack;

import org.springframework.stereotype.Repository;

@Repository
public interface CommonDao {
	
	public Common getNowTime();

    int updateCommon(Date nowTime);
	
}
