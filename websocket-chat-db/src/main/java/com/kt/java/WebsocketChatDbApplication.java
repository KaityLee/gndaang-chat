package com.kt.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class WebsocketChatDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketChatDbApplication.class, args);
	}

}
