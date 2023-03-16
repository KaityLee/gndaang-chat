package com.kt.java.chat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	@Autowired
    private ChatService chatService;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatService chatService) {
        this.messagingTemplate = messagingTemplate;
        this.chatService = chatService;
    }

    @MessageMapping("/chat/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, @Payload Chat chat) {
        chat.setRoomId(roomId);
        chat.setTimestamp(new Date());
        chatService.saveMessage(chat);

        messagingTemplate.convertAndSend( "/topic/messages/" + chat.getRoomId(), chat);
    }

    @GetMapping("/messages")
    public List<Chat> getMessagesByRoomId(@RequestParam String roomId) {
        return chatService.findByRoomId(roomId);
    }

    
}