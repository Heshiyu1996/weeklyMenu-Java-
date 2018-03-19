package com.test;


import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entity.Food;
import com.entity.Character;
import com.service.AdminService;


public class AdminDaoTest {
	ApplicationContext context = null;
	AdminService adminService = null;
	@Before
	public void init(){
		String[] configs = {"applicationContext.xml"};
		context = new ClassPathXmlApplicationContext(configs);
		adminService = (AdminService)context.getBean("adminService");
	}
	
	@Test
	public void testQueryOne(){
//		System.out.println("测试结果为：" + adminService.getCharacterList());
		List<Character> list = adminService.getCharacterList();
		for (int i=0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
//		System.out.println(list);
	}
}
