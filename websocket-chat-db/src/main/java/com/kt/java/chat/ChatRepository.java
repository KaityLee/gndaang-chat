package com.kt.java.chat;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ChatRepository extends MongoRepository<Chat, String> {
	
	List<Chat> findByRoomId(String roomId);
	Page<Chat> findByRoomId(String roomId, Pageable pageable);
}