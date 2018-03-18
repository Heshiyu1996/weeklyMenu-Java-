package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Food;
import com.entity.Search;
import com.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService = null;

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

//	获取关键词列表
	@ResponseBody
	@RequestMapping(value ="/getKeywords")
	public Map<String, Object> getFeedBackList(HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
			List<Search> search = searchService.getKeywords();
			Map<String, Object> listMap=new HashMap<String, Object>();
			listMap.put("myList", search);
			map.put("msg", "获取关键词列表成功");
			map.put("relatedObject", listMap);
			map.put("success", true);
		return map;
	}
	
	
//	新增关键词搜索次数
	@RequestMapping(value = "/addKeywordCount", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateFeedBack(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, 
			@RequestParam(value="keyword")String keyword) {
		String uid=(String)session.getAttribute("uid_session");
		boolean isAdd = searchService.recordKeyword(keyword, Integer.parseInt(uid));
		Map<String, Object> map = new HashMap<String, Object>();
		if (isAdd == true) {
			map.put("msg", "记录关键词成功");
			map.put("success", true);
		} else {
			map.put("msg", "记录关键词失败");
			map.put("success", false);
		}

		return map;
	}
}
