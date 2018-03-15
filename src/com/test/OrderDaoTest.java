package com.test;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.OrderService;


public class OrderDaoTest {
	ApplicationContext context = null;
	OrderService orderService = null;
	@Before
	public void init(){
		String[] configs = {"applicationContext.xml"};
		context = new ClassPathXmlApplicationContext(configs);
		orderService = (OrderService)context.getBean("orderService");
	}
	
	@Test
	public void testQueryOne(){
		System.out.println("测试结果为：" + orderService.getOrdersByDateCodeUserId("20180306", 2));
//		System.out.println(QiniuUtil.getUpToken(););
	}
}
