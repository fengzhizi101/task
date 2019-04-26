package com.task.miracletask.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send() throws Exception {
		String exchange = "user-exchange";
		String routingKey = "user.key";
		String message = "hello kevin";
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}
}
