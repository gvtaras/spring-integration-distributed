package de.norcom.ingester.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import de.norcom.ingester.entity.FooDto;

@Component
public class EchoEndpoint {

	private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public Message<?> get(Message<?> msg) {

		LOG.info("Headers: " + msg.getHeaders() + ", payload: " + msg.getPayload().toString());

		return MessageBuilder
				.withPayload(new FooDto(this.getClass().getSimpleName()))
				.copyHeadersIfAbsent(msg.getHeaders())
				.build();
	}
}
