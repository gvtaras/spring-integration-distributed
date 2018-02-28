package de.norcom.ingester.endpoint.producer;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import de.norcom.ingester.entity.FooDto;

@Component
public class Producer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public Message<?> process(Message<String> msg) {

		LOG.info("Write to Kafka goes here?");

		MessageBuilder<FooDto> builder = MessageBuilder
				.withPayload(new FooDto(this.getClass().getSimpleName()))
				.copyHeadersIfAbsent(msg.getHeaders());

		ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("ingester-topic", "bla-bla");

		try {
			send.get();
			return builder
					.setHeader("http_statusCode", HttpStatus.OK)
					.build();
		} catch (InterruptedException | ExecutionException e) {
			return builder
					.withPayload(e.toString())
					.setHeader("http_statusCode", HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}

	}

}
