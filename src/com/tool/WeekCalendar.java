package com.tool;

import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.Calendar;  
import java.util.Date;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import com.entity.FeedBack;

public class WeekCalendar {

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天 
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }  
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天 
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值 
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Date getThisWeek(Date date, int sub) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, sub);
        return cal.getTime();
    }

    public static int getWhichDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek>1) {
			return dayOfWeek-1;
		} else {
			return 7;
		}

    }
    
	public static Map<String, Object> getWeekCalendar() {
		Map<String,Object> calendarMap=new HashMap<String, Object>();
		Map<String,Object> todayDateMap=new HashMap<String, Object>();
    	Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        List<Object> listObj=new ArrayList<Object>();
        try {
        	// json对象：目前时分
    		calendarMap.put("time", sdf2.format(currentTime));
    		
        	// json对象：今天的日期
            Date date = sdf.parse(sdf.format(currentTime));
            todayDateMap.put("day", getWhichDay(date));
            todayDateMap.put("date", sdf.format(currentTime));
    		calendarMap.put("today", todayDateMap);

        	// json对象：本周的日期
            for(int i=-1; i<6; i++) {
            	Map<String, Object> newDayMap=new HashMap<String, Object>();
            	newDayMap.put("day", i+1);
            	newDayMap.put("date", sdf.format(getThisWeek(date, i)));
            	listObj.add(newDayMap);
            }
    		calendarMap.put("weekCalendar", listObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return calendarMap;
	}
}