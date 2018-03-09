package com.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tool.DateSerializer;


public class Plan {
    
    private String day;
    
	private Date date;
    
    private String time;

	public Plan() {
	}

	public Plan(String day, Date date, String time) {
		super();
		this.day = day;
		this.date = date;
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Plan [day=" + day + ", date=" + date + ", time=" + time + "]";
	}


}
