package com.ssafy.api.controller;

import com.ssafy.api.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/livechat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/livechat/room/" + message.getRoomId(), message);
    }

    @PostMapping("/livechat/create")
    public ResponseEntity createChatroom(){

        return null;
    }

}
