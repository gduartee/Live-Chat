package com.chat.livechat.domain.dto.conversation;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConversationDetailResponse(
        UUID id,
        String widgetName,
        String widgetKey,
        UUID visitorId,
        String visitorName,
        String visitorEmail,
        String visitorCurrentPage,
        String status,
        UUID operatorId,
        String operatorName,
        LocalDateTime startedAt,
        LocalDateTime closedAt,
        Integer rating
) {
}
