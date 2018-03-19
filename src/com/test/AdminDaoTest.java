package com.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Map<String, Object>> resultList=new ArrayList<Map<String, Object>>();
		List<Character> list = adminService.getAnalysisByProvince();
		
		for (int i=0; i < list.size(); i++) {
			String province = list.get(i).getProvince();
			boolean isExist = false;
			for (int j=0; j < resultList.size() ||  j == 0; j++) {
				// 第一次，产出的长度为0时
				if (resultList.size() == 0) {
					Map<String, Object> provMap=new HashMap<String, Object>();
					provMap.put("name", province);
					provMap.put("value", 1);
					resultList.add(provMap);
					isExist = true;
					break;
				}
				System.out.println("即将要比的是"+ resultList.get(j).get("name"));
				System.out.println("和"+ province);
				if (resultList.get(j).get("name").equals(province)) {
					isExist = true;
					String name = (String) resultList.get(j).get("name");
					int count = (Integer) resultList.get(j).get("value");
					Map<String, Object> provMap=new HashMap<String, Object>();
					provMap.put("name", name);
					provMap.put("value", ++count);
					resultList.set(j, provMap);
					break;
				} else {
					// 没找到，应该是j网上加，再比
				}
			}
			if (!isExist){
				// 最后真美找到，再新增吧
				Map<String, Object> provMap=new HashMap<String, Object>();
				provMap.put("name", province);
				provMap.put("value", 1);
				resultList.add(provMap);
			}
		}
		System.out.println(resultList);
	}
}
