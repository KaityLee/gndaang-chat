package com.kt.java.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatService {

	@Autowired
	public ChatRepository chatRepository;
	
	public void saveMessage(Chat chat) {
        chatRepository.save(chat);
    }

    public List<Chat> findByRoomId(String roomId, int page, int size) {
    	Pageable pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
		Page<Chat> chatPage = chatRepository.findByRoomId(roomId, pageable);
		return chatPage.getContent();
    }
}
