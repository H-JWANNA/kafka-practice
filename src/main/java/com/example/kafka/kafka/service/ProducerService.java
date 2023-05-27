package com.example.kafka.kafka.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.kafka.kafka.dto.MyMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProducerService {
	private static final String TOPIC_NAME = "topic5";

	private final KafkaTemplate<String, MyMessage> kafkaTemplate;

	public void send(MyMessage message) {
		kafkaTemplate.send(TOPIC_NAME, message);
	}

	/*
	CompletableFuture Ref.
	https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/CompletableFuture.html
	https://www.hungrydiver.co.kr/bbs/detail/develop?id=2
 	*/
	public void sendWithCallback(MyMessage message) {
		CompletableFuture<SendResult<String, MyMessage>> future = kafkaTemplate.send(TOPIC_NAME, message);

		future
			.thenAcceptAsync(msg ->
				log.info("Sent Message {}, offset : {}", message.getMessage(), msg.getRecordMetadata().offset()))
			.exceptionally(ex -> {
				log.error("Failed {} due to : {}", message.getMessage(), ex.getMessage());
				return null;
			});
	}

	/*
	 * D.1.5. KafkaTemplate Changes
	 * In 3.0, the futures returned by this class will be CompletableFuture s instead of ListenableFuture s.
	 * See Using KafkaTemplate for assistance in transitioning when using this release.

	public void sendWithCallback_Legacy(String message) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Failed " + message + " due to : " + ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("Sent " + message + " offset : " + result.getRecordMetadata().offset());
			}
		});
	}
	 */
}
