package com.kt.java.chat;

import java.util.List;

import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {
	
	  List<Chat> findByRoomId(String roomId);
}