package de.norcom.ingester.endpoint.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import de.norcom.ingester.entity.FooDto;

@Component
public class Consumer {

	private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	@Qualifier("inputChannel")
	private MessageChannel inputChannel;

	@KafkaListener(id = "foo", topics = "ingester-topic")
	public void listen(@Payload String foo,
					   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
					   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

		LOG.info("From Kafka: Payload: " + foo + "Headers: partition: " + partition + " topic:" + topic);

		Message<FooDto> builder = MessageBuilder
				.withPayload(new FooDto(this.getClass().getSimpleName()))
				.build();

		inputChannel.send(builder);
	}

}
