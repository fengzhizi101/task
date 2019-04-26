package com.task.miracletask.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.task.miracletask.entity.User;
import com.task.miracletask.library.annotation.Auth;
import com.task.miracletask.service.user.UserService;
import com.task.miracletask.utils.IpUtil;

@Controller
@RequestMapping("user")
public class UserController<K> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired 
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	private UserService userService;
	
	@RequestMapping("/getSessionId")
	@ResponseBody
	public String getSessionId(HttpSession session) {
		String sessionId = session.getId();
		UUID uuid = (UUID) session.getAttribute("uid");
		if (null == uuid) {
			uuid = UUID.randomUUID();
		}
		session.setAttribute("uid", uuid);
		return "session is:" + session.getId();
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, String> test() {
		Map<String, String> testMap = new HashMap<String, String>();
		testMap.put("name", "kevin");
		testMap.put("email", "kevin@test.com");
		
		return testMap;
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	@Auth(token="123")
	public String getUser(){
	    String phone =  "156252524571"; 
	    User user = userService.findUserByPhone(phone);
		if (null != user) {
			String name = user.getUid();
			
		    return name;			
		}
		
		return "can not found user";
	}
	
	@RequestMapping("/getAllUser")
	@ResponseBody
	@Auth(token="123")
	public String getAllUser(){
		String ip = IpUtil.getIpAddr(request);
	    List<User> users = userService.getAllUsers();
		if ( ! users.isEmpty()) {
			List<Map<String, String>> userList = new ArrayList<Map<String, String>>(); 
			Map<String, String> userMap = new HashMap<String, String>();
			for (User user : users) {
				userMap.put("uid", user.getUid());
				userMap.put("phone", user.getMobile());
				userList.add(userMap);
			}
		    return "total: " + userList.size();			
		}
		
		return "can not found user";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	@ResponseBody
	public String login(@RequestParam("phone") String phone, @RequestParam("password") String postPassword) {
		
		User user = userService.findUserByPhone(phone);
		if (null == user) {
			return "user is null";
		}
		
		String password = user.getPassword();
		System.out.println("pwd: " + password);
		System.out.println("psotpwd: " + postPassword);
		if ( ! password.equals(postPassword)){
			return "password is wrong";
		}
		
		return "login successfully: " + user.getUid();
	}
	
		
}
