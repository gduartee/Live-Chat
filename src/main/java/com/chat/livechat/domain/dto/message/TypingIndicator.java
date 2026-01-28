package com.chat.livechat.domain.dto.message;

import java.util.UUID;

public record TypingIndicator(
        UUID senderId,
        String senderName,
        String senderType,
        boolean isTyping
) {
}
