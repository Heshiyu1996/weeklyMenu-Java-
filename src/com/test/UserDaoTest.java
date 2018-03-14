package com.test;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entity.User;
import com.service.UserService;
import com.entity.Character;



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
		Character character = new Character();
		character.setUserId(2);
		character.setProvince("3");
		character.setProvinceCode("3");
		character.setCity("3");
		character.setCityCode("3");
		character.setNation("3");
		character.setTaste("3");
		character.setTall("3");
		character.setHeight("3");
		character.setEatHabit("3");
		character.setPrepare("3");
		character.setAlcohol(0);
		character.setAttention("3");
		System.out.println("测试结果为：" + userService.updateCharacter(character));
//		System.out.println(QiniuUtil.getUpToken(););
	}
}
