package de.norcom.ingester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:producer.xml", "classpath:consumer.xml"})
public class IngesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngesterApplication.class, args);
	}

}
