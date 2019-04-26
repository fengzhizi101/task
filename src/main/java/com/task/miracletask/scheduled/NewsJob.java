package com.task.miracletask.scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class NewsJob {
	static int nums;
	
	//@Scheduled(cron = "0/5 * * * * ?")
	public void getNews() {
		 System.out.println(">>>>>>>>>:"+nums);
	        nums++;
	}
	
}
