package com.example.kafka.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.kafka.dto.MyMessage;
import com.example.kafka.kafka.service.ProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publish")
public class ProducerController {
	private final ProducerService producerService;

	@GetMapping
	public String publish(MyMessage message) {
		producerService.send(message);

		return "published a message : " + message;
	}

	@GetMapping("/v1")
	public String publishWithCallback(MyMessage message) {
		producerService.sendWithCallback(message);

		return "published a message with callback : " + message.getName() + ", " + message.getMessage();
	}
}
