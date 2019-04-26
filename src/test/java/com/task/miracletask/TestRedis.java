package com.task.miracletask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestRedis {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void test() {
		stringRedisTemplate.opsForValue().set("name", "kevin");
		Assert.assertEquals("kevin1", stringRedisTemplate.opsForValue().get("name"));
		
	}
	
	@Test
	public void testDate() {
	    Date d = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
	    System.out.println("格式化输出：" + sdf.format(d));

	    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
	    System.out.println("Asia/Shanghai:" + sdf.format(d));

	    System.out.println( "格式化输出：" + sdf.format(d) + '\n' + "Asia/Shanghai:" + sdf.format(d));
	
	}
}
