package com.chat.livechat.domain.dto.conversation;

import java.util.UUID;

public record ConversationStartResponse(
        UUID conversationId,
        String status,
        String welcomeMessage,
        boolean isNew
) {
}
