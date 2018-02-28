package de.norcom.ingester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import de.norcom.ndos.n.kafka.integration.AbstractKafkaLocalServerTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngesterApplicationTests {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Test
	public void contextLoads() throws Exception {

		ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("ingester-topic-test", "bla-bla");

		RecordMetadata metadata = send.get().getRecordMetadata();

		String s = "" + metadata.topic() + " " + metadata.checksum() + " " + metadata.offset() + " " + metadata.timestamp();

		System.out.println(s);

//		URL url = new URL("http://127.0.0.1:8080/welcome/Test");
//		InputStream stream = url.openStream();
//		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(stream))) {
//			System.out.println(buffer.lines().collect(Collectors.joining("\n")));
//		}
	}

}
