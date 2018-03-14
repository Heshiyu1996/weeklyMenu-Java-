package com.test;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entity.User;
import com.service.UserService;


public class UserDaoTest {
	ApplicationContext context = null;
	UserService userService = null;
	@Before
	public void init(){
		String[] configs = {"applicationContext.xml"};
		context = new ClassPathXmlApplicationContext(configs);
		userService = (UserService)context.getBean("userService");
	}
	
	@Test
	public void testQueryOne(){
		User user = new User();
		user.setUid("5");
		user.setUname("测试者");
		user.setUmobile("13922610083");
		user.setUpassword("123");
		System.out.println("测试结果为：" + userService.addtUser(user));
//		System.out.println(QiniuUtil.getUpToken(););
	}
}
