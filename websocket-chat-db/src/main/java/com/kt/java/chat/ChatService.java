package com.kt.java.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	@Autowired
	public ChatRepository chatRepository;
	
	public void saveMessage(Chat chat) {
        chatRepository.save(chat);
    }

    public List<Chat> findByRoomId(String roomId) {
        return chatRepository.findByRoomId(roomId);
    }
}
