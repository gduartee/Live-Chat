package com.chat.livechat.domain.dto.conversation;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConversationResponse(
        UUID id,
        String widgetName,
        String visitorName,
        String visitorEmail,
        String status,
        String operatorName,
        LocalDateTime startedAt,
        String lastMessage,
        Integer unreadCount
) {
}
