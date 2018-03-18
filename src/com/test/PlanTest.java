package com.test;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.PlanService;
import com.service.FoodService;
import com.tool.WeekCalendar;


public class PlanTest {
	ApplicationContext context = null;
	PlanService planService = null;
	@Before
	public void init(){
		String[] configs = {"applicationContext.xml"};
		context = new ClassPathXmlApplicationContext(configs);
		planService = (PlanService)context.getBean("planService");
	}
	
	@Test
	public void testQueryOne(){
//		System.out.println(planService.getFoodsByDayPidCid(1, 1, 1));
//		System.out.println(planService.getTasteByUserId2(1));
	}
}
