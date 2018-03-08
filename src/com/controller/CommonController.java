package com.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
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

import com.entity.Common;
import com.entity.FeedBack;
import com.service.CommonService;

@Controller
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private CommonService commonService = null;

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
//	获取反馈列表
	@ResponseBody
	@RequestMapping(value ="/getCommonInfo")
	public Map<String, Object> getFeedBackList(HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
			Date nowTime = new Date();
			Common common = commonService.getCommon(nowTime);
			map.put("msg", "获取反馈列表成功");
			map.put("relatedObject", common);
			map.put("success", true);

		return map;
	}
}
