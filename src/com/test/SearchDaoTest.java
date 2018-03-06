package com.test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.FoodService;
import com.service.SearchService;


public class SearchDaoTest {
	ApplicationContext context = null;
	SearchService searchService = null;
	@Before
	public void init(){
		String[] configs = {"applicationContext.xml"};
		context = new ClassPathXmlApplicationContext(configs);
		searchService = (SearchService)context.getBean("searchService");
	}
	
	@Test
	public void testQueryOne(){
		System.out.println("员工号为1000的员工信息：" + searchService.getKeywords());
	}
}
