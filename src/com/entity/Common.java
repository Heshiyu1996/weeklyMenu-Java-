package com.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tool.DateSerializer;


public class Common {

    @JsonSerialize(using=DateSerializer.class)
	private Date nowTime;

	public Common() {
	}

	public Common(Date NowTime) {
		super();
		this.nowTime = nowTime;
	}


	public Date getNowTime() {
		return nowTime;
	}


	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}

	@Override
	public String toString() {
		return "Common [nowTime=" + nowTime + "]";
	}

}
