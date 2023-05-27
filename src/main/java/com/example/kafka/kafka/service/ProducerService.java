package com.example.kafka.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
	private static final String TOPIC_NAME = "topic5";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String message) {
		kafkaTemplate.send(TOPIC_NAME, message);
	}
}
