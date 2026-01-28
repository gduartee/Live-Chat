package com.chat.livechat.domain.dto.message;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageResponse(
        UUID id,
        UUID conversationId,
        String senderType,
        UUID senderId,
        String senderName,
        String content,
        String messageType,
        LocalDateTime sentAt,
        LocalDateTime readAt
) {
}
