package com.controller;

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

import com.entity.FeedBack;
import com.service.FeedBackService;

@Controller
@RequestMapping("/feedBack")
public class FeedBackController {

	@Autowired
	private FeedBackService FeedBackService = null;

	public FeedBackService getFeedBackService() {
		return FeedBackService;
	}

	public void setFeedBackService(FeedBackService FeedBackService) {
		this.FeedBackService = FeedBackService;
	}
//	获取反馈列表
	@ResponseBody
	@RequestMapping(value ="/getFeedBackList")
	public Map<String, Object> getFeedBackList(HttpSession session){
		String sid=(String)session.getAttribute("sid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(sid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			List<FeedBack> feedBack = FeedBackService.getFeedBackList();
			Map<String, Object> listMap=new HashMap<String, Object>();
			listMap.put("myList", feedBack);
			map.put("msg", "获取反馈列表成功");
			map.put("relatedObject", listMap);
			map.put("success", true);
		}
		return map;
	}
}
