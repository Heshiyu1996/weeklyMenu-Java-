package com.test;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.FoodService;


public class FoodDaoTest {
	ApplicationContext context = null;
	FoodService foodService = null;
	@Before
	public void init(){
		String[] configs = {"applicationContext.xml"};
		context = new ClassPathXmlApplicationContext(configs);
		foodService = (FoodService)context.getBean("foodService");
	}
	
	@Test
	public void testQueryOne(){
		System.out.println("测试结果为：" + foodService.getFoodRecommondByOrder(36));
//		System.out.println(QiniuUtil.getUpToken(););
	}
}
