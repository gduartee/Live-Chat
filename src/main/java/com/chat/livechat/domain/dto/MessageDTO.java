package com.chat.livechat.domain.dto;

import com.chat.livechat.domain.entity.Message;

import java.time.LocalDateTime;

public record MessageDTO(
        String id,
        String conversationId,
        Message.SenderType senderType,
        String senderId,
        String senderName,
        String content,
        LocalDateTime sentAt
) {
}
