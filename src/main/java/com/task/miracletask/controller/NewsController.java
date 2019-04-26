package com.task.miracletask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.task.miracletask.entity.News;

@Controller
@RequestMapping("news")
public class NewsController {
	@Autowired StringRedisTemplate stringRedisTemplate;

	@RequestMapping("/index")
	@ResponseBody
	public String index() {
		return "this is spring boot project1";
	}
	
	@RequestMapping("/getNews")
	@ResponseBody
	public News getNews() {
		News news = new News();
		news.setId(1);
		news.setTitle("news title");
		
		return news;
	}
	
	@RequestMapping("/getIndexPage")
	public String indexPage() {
		
		return "indexPage";
	}
	
	@RequestMapping("/redisTest")
	@ResponseBody
	public String setRedis() {
		stringRedisTemplate.opsForValue().set("project", "srping boot");
		String project = stringRedisTemplate.opsForValue().get("project");
		System.out.println("----------redis values: " + project);
		
		return project;
	}
	
	@RequestMapping("/getTest")
	@ResponseBody
	public String getRedis() {
		String project = stringRedisTemplate.opsForValue().get("project");
		System.out.println("----------redis values: " + project);
		
		return project;
	}
	
	@RequestMapping("/addNews")
	@ResponseBody
	public String addNews(){
		News news = new News();
		news.setNewsId(1234888);
		news.setTitle("test");
		news.setContent("news content");
		news.setAuditStatus(1);
		news.setAuditor(1);
		news.setCategoryId(1);
		//news.setPublishTime(publishTime);
		news.setSource("spring boot");

		return "add succesfully";
	}
	
	@RequestMapping("test")
	@ResponseBody
	public String test() {
		
		return "test Page for test";
	}
}
