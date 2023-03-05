package com.kt.java.chat;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("chat_message")
public class Chat {
	  @Id
	    private String id;
	    private String sender;
	    private String recipient;
	    private String content;
	    private String roomId;
	    private Date timestamp;

}
