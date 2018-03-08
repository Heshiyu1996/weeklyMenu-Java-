package com.test;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.CommonService;
import com.service.FoodService;


public class CommonTest {
	ApplicationContext context = null;
	CommonService commonService = null;
	@Before
	public void init(){
		String[] configs = {"applicationContext.xml"};
		context = new ClassPathXmlApplicationContext(configs);
		commonService = (CommonService)context.getBean("commonService");
	}
	
	@Test
	public void testQueryOne(){
		Date nowTime = new Date();
		System.out.println("测试结果为：" + commonService.getCommon(nowTime));
	}
}
