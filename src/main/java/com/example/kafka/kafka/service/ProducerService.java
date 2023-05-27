package com.example.kafka.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProducerService {
	private static final String TOPIC_NAME = "topic5";

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void send(String message) {
		kafkaTemplate.send(TOPIC_NAME, message);
	}
}
