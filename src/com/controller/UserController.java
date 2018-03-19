package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.entity.User;
import com.entity.Character;
import com.service.UserService;

@Controller
@RequestMapping("/staff")
public class UserController {


	@Autowired
	private UserService userService = null;

	public UserService getStaffService() {
		return userService;
	}

	public void setStaffService(UserService userService) {
		this.userService = userService;
	}
//	查询员工
	@ResponseBody
	@RequestMapping(value ="/getUserInfo")
	public Map<String, Object> queryStaff(HttpSession session){
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "Session已过期，请重新登录！");
		} else {
			User user = userService.getStaffByUid(uid);
			Map<String, Object> userMap=new HashMap<String, Object>();
			userMap.put("uid",user.getUid());
			userMap.put("uname", user.getUname());
			userMap.put("umobile", user.getUmobile());
			userMap.put("utype", user.getUtype());
			map.put("msg", "获取用户信息成功哦哦");
			map.put("relatedObject", userMap);
			map.put("success", true);
		}
		return map;
	}
//	登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> loginUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="uid")String uid, @RequestParam(value="upassword")String upassword) {
		System.out.println("用户名："+uid);
		Map<String, Object> map = new HashMap<String, Object>();
		User checkUser = userService.checkPassword(uid, upassword);
		// 登录成功
		if (checkUser != null) {
			session.setAttribute("uid_session", checkUser.getUid());
			session.setAttribute("utype_session", checkUser.getUtype());
			map.put("msg", "登录成功");
			map.put("uid", checkUser.getUid());
			map.put("success", true);
		} else {
			map.put("msg", "登录失败，请检查密码是否正确");
			map.put("success", false);
		}

		return map;
	}
//	退出登录
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> logoutUser(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		session.setAttribute("uid_session", null);
		if((String)session.getAttribute("uid_session")==null){
			map.put("msg", "注销成功");
			map.put("success", true);
		}else {
			map.put("msg", "注销失败");
			map.put("success", false);
		}

		return map;
	}
	
//	注册
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> regist(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="uid")String uid,
			@RequestParam(value="uname")String uname,
			@RequestParam(value="umobile")String umobile,
			@RequestParam(value="upassword")String upassword
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		user.setUid(uid);
		user.setUname(uname);
		user.setUmobile(umobile);
		user.setUpassword(upassword);
		boolean isAdd = userService.addtUser(user);
		// 登录成功
		if (isAdd) {
			session.setAttribute("uid_session", uid);
			session.setAttribute("utype_session", 0);
			map.put("msg", "注册成功");
			map.put("success", true);
		} else {
			map.put("msg", "注册失败");
			map.put("success", false);
		}

		return map;
	}
	
//	获取我的喜好
	@ResponseBody
	@RequestMapping(value ="/ifExistCharacter")
	public Map<String, Object> ifExistCharacter(HttpSession session) {
		
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("msg", "检查个人喜好失败，请重新登录！");
			map.put("success", false);
		} else {
			Boolean ifExist = false;
			ifExist = userService.ifExistCharacter(Integer.parseInt(uid));
			Map<String,Object> resultMap=new HashMap<String, Object>();
			resultMap.put("ifExist", ifExist);
			map.put("msg", "检查我的喜好成功");
			map.put("relatedObject", resultMap);
			map.put("success", true);
		}
		return map;
	}
	
//	获取我的喜好
	@ResponseBody
	@RequestMapping(value ="/getCharacter")
	public Map<String, Object> getCharacter(HttpSession session) {
		
		String uid=(String)session.getAttribute("uid_session");
		Map<String,Object> map=new HashMap<String, Object>();
		if(uid==null){
			map.put("success", false);
			map.put("msg", "获取个人喜好失败，请重新登录！");
		} else {
			Character character = userService.getCharacter(Integer.parseInt(uid));
			map.put("msg", "获取我的喜好成功");
			map.put("relatedObject", character);
			map.put("success", true);
		}
		return map;
	}
	
//	保存“我的喜好”
	@RequestMapping(value = "/updateCharacter", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> updateCharacter(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value="provinceCode")String provinceCode,
			@RequestParam(value="province")String province,
			@RequestParam(value="cityCode")String cityCode,
			@RequestParam(value="city")String city,
			@RequestParam(value="nation")String nation,
			@RequestParam(value="taste")String taste,
			@RequestParam(value="tall")String tall,
			@RequestParam(value="height")String height,
			@RequestParam(value="eatHabit")String eatHabit,
			@RequestParam(value="prepare")String prepare,
			@RequestParam(value="alcohol")int alcohol,
			@RequestParam(value="attention", required=false)String attention
			) {
		Map<String,Object> map=new HashMap<String, Object>();
		String uid=(String)session.getAttribute("uid_session");
		if(uid==null){
			map.put("success", false);
			map.put("msg", "获取个人喜好失败，请重新登录！");
			return map;
		}
		
		int userId = Integer.parseInt(uid);
		boolean isExist = userService.ifExistCharacter(userId);
		boolean isSuccess = false;
		Character character = new Character();
		character.setUserId(userId);
		character.setProvince(province);
		character.setProvinceCode(provinceCode);
		character.setCity(city);
		character.setCityCode(cityCode);
		character.setNation(nation);
		character.setTaste(taste);
		character.setTall(tall);
		character.setHeight(height);
		character.setEatHabit(eatHabit);
		character.setPrepare(prepare);
		character.setAlcohol(alcohol);
		character.setAttention(attention);
		
		if (isExist) {
			isSuccess = userService.updateCharacter(character);
		} else {
			isSuccess = userService.addCharacter(character);
		}
		if (isSuccess) {
			map.put("msg", "更新我的喜好成功");
			map.put("success", true);
		} else {
			map.put("msg", "更新我的喜好失败");
			map.put("success", false);
		}

		return map;
	}

//	注册test
	@RequestMapping(value = "/registTest", method = RequestMethod.POST)
	public @ResponseBody
	void registTest(HttpServletRequest request, HttpServletResponse response, HttpSession session
			) {
		String[] xing = new String[] {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "楮", "卫", "蒋", "沈", "韩", "杨", 
		                 "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", 
		                 "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", 
		                 "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", 
		                 "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", 
		                 "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", 
		                 "和", "穆", "萧", "尹"};
		String[] ming = new String[] {"爱", "安", "百", "邦", "宝", "保", "抱", "贝", "倍", "蓓", "本", 
				 "必", "碧", "璧", "斌", "冰", "兵", "炳", "步", "彩", "曹", "昌", "长", "常", "超", 
				 "朝", "陈", "晨", "成", "呈", "承", "诚", "崇", "楚", "传", "春", "纯", "翠", "村", 
				 "殿", "丁", "定", "东", "冬", "二", "凡", "方", "芳", "昉", "飞", "菲", "纷", "芬", 
				 "奋", "风", "峰", "锋", "凤", "芙", "福", "付", "复", "富", "改", "刚", "高", "阁", 
				 "铬", "根", "庚", "耕", "公", "功", "冠", "光", "广", "归", "桂", "国", "海", "寒", 
				 "翰", "昊", "浩", "荷", "红", "宏", "洪", "鸿", "厚", "华", "存", "大", "丹", "道", 
				 "德", "登", "砥", "典", "佃"};
		Random rand = new Random();
		System.out.println(xing.length);
		System.out.println(ming.length);
		String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
		for (int uid = 1021; uid <= 1050; uid++) {
			int xingIndex = rand.nextInt(100);
			int mingIndex = rand.nextInt(100);
			User user = new User();
			user.setUid(Integer.toString(uid));
			user.setUname(xing[xingIndex] + ming[mingIndex]);
			user.setUmobile("13555550000");
			user.setUpassword("1");
			userService.addtUser(user);
			System.out.println(user);
		}
	}
	

//	爱好test
	@RequestMapping(value = "/characterTest", method = RequestMethod.POST)
	public @ResponseBody
	void characterTest(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String[] nations = new String[] {"汉族","蒙古族","回族","藏族","维吾尔族","苗族","彝族","壮族","布依族","朝鲜族","满族","侗族","瑶族","白族","土家族",               "哈尼族","哈萨克族","傣族","黎族","傈僳族","佤族","畲族","高山族","拉祜族","水族","东乡族","纳西族","景颇族","柯尔克孜族",
	               "土族","达斡尔族","仫佬族","羌族","布朗族","撒拉族","毛南族","仡佬族","锡伯族","阿昌族","普米族","塔吉克族","怒族", "乌孜别克族",
	               "俄罗斯族","鄂温克族","德昂族","保安族","裕固族","京族","塔塔尔族","独龙族","鄂伦春族","赫哲族","门巴族","珞巴族","基诺族"};
		String[] tastes = new String[] {"清淡", "麻辣", "油炸", "都可以"};
		String[] habits = new String[] {"多肉少菜", "荤素均衡", "素食为主", "都可以"};
		String[] prepares = new String[] {"口味", "价格", "营养价值", "都可以"};

		String[] provincesCode 
		= new String[] {"440000", "140000", "130000", "230000", "320000", "430000", "420000", "460000", "450000"};
		
		String[] provinces 
		= new String[] {"广东省", "山西省", "河北省", "黑龙江省", "江苏省", "湖南省", "湖北省", "海南省", "广西壮族自治区"};
		
		String[] citesCode 
		= new String[] {"440100", "140300", "130100", "230100", "320100", "430100", "420100", "460100", "450300"};
		
		String[] cites 
		= new String[] {"广州市", "阳泉市", "石家庄市", "哈尔滨市", "南京市", "长沙市", "武汉市", "海口市", "桂林市"};
		
		Random rand = new Random();
		for (int uid = 1016; uid <= 1050; uid++) {
			int nationIndex = rand.nextInt(56);
			int tastesIndex = rand.nextInt(4);
			int habitsIndex = rand.nextInt(4);
			int preparesIndex = rand.nextInt(4);
			int tallRange = rand.nextInt(24) + 159;
			int heightRange = rand.nextInt(36) + 55;
			int provincesIndex = rand.nextInt(9);

			
			Character character = new Character();
			character.setUserId(uid);
			character.setProvinceCode(provincesCode[provincesIndex]);
			character.setProvince(provinces[provincesIndex]);
			character.setCityCode(citesCode[provincesIndex]);
			character.setCity(cites[provincesIndex]);
			character.setNation(nations[nationIndex]);
			character.setTaste(tastes[tastesIndex]);
			character.setTall(Integer.toString(tallRange));
			character.setHeight(Integer.toString(heightRange));
			character.setEatHabit(habits[habitsIndex]);
			character.setPrepare(prepares[preparesIndex]);
			character.setAlcohol(rand.nextInt(2));
			character.setAttention("");
			

			
			userService.addCharacter(character);
			System.out.println(character);
		}
		
		
		
		
		
	}
}
