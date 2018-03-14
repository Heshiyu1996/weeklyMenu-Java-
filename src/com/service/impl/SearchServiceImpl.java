package com.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SearchDao;
import com.entity.Search;
import com.tool.Encryption;
import com.service.SearchService;

@Service("searchService")
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao = null;

	public SearchDao getSearchDao() {
		return searchDao;
	}

	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}

	@Override
	public boolean recordKeyword(String keyword) {
		boolean isExists=false;
		boolean flag=false;
		try {
			isExists=(searchDao.ifExistsKeyword(keyword)==1)?true:false;
		} catch (Exception e) {
			System.out.println("“查询关键词”出错了");
		}
		if(!isExists) {
			// 不存在时，插入新的关键词
			try {
				flag=(searchDao.recordKeyword(keyword)==1)?true:false;
			} catch (Exception e) {
				System.out.println("“记录关键词”出错了");
			}
		} else {
			// 存在时，加1
			try {
				flag=(searchDao.addKeywordCount(keyword)==1)?true:false;
			} catch (Exception e) {
				System.out.println("“增加关键词次数”出错了");
				System.out.println(e);
			}
		}
		return flag;
	}

	@Override
	public List<Search> getKeywords() {
		return searchDao.getKeywords();
	}

}
