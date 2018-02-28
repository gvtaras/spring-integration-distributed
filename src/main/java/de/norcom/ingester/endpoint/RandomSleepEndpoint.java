package de.norcom.ingester.endpoint;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import de.norcom.ingester.entity.FooDto;

@Component
public class RandomSleepEndpoint {

	private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private Random random = new Random();

	public Message<?> get(Message<FooDto> msg) throws InterruptedException {

		int millis = random.nextInt(10) * 100;

		LOG.info("=> Sleep: " + random);

		Thread.sleep(millis);

		return MessageBuilder
				.withPayload(new FooDto(this.getClass().getSimpleName()))
				.copyHeadersIfAbsent(msg.getHeaders())
				.build();
	}
}
