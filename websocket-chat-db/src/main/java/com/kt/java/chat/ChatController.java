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

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRepository chatRepository;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatRepository chatRepository) {
        this.messagingTemplate = messagingTemplate;
        this.chatRepository = chatRepository;
    }

    @MessageMapping("/chat/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, @Payload Chat chat) {
        chat.setRoomId(roomId);
        chat.setTimestamp(new Date());
        chatRepository.save(chat);

        String recipient = chat.getRecipient();
        String sender = chat.getSender();

        messagingTemplate.convertAndSendToUser(recipient, "/queue/messages", chat);
        messagingTemplate.convertAndSendToUser(sender, "/queue/messages", chat);
    }

    @GetMapping("/messages/{roomId}")
    public List<Chat> getMessagesByRoomId(@PathVariable String roomId) {
        return chatRepository.findByRoomId(roomId);
    }

    
}