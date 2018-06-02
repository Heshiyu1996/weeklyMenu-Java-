package com.tool;

import java.text.ParseException;
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
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }   
        cal.setFirstDayOfWeek(Calendar.MONDAY); 
        int day = cal.get(Calendar.DAY_OF_WEEK); 
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
        	// json对象属性time：目前时间
    		calendarMap.put("time", sdf2.format(currentTime));
        	// jso对象属性today：今天日期
            Date date = sdf.parse(sdf.format(currentTime));
            todayDateMap.put("day", getWhichDay(date));
            todayDateMap.put("date", sdf.format(currentTime));
    		calendarMap.put("today", todayDateMap);
        	// json对象属性weekCalendar：本周的日历
    		int Incre = 0;
    		if (getWhichDay(date) == 7) {
    			// 若今日为星期天，则获取下周日历
    			Incre = 7;
    		}
            for(int i=-1+Incre; i<6+Incre; i++) {
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