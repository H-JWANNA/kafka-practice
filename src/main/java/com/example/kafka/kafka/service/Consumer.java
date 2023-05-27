package com.example.kafka.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.kafka.kafka.dto.MyMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Consumer {
	private static final String TOPIC_NAME = "topic5";

	ObjectMapper objectMapper = new ObjectMapper();

	@KafkaListener(topics = TOPIC_NAME)
	public void listenMessage(String jsonMessage) {
		try {
			MyMessage message = objectMapper.readValue(jsonMessage, MyMessage.class);
			System.out.println(">>> " + message.getName() + ", " + message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
