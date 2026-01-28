package com.chat.livechat.domain.dto.conversation;

import jakarta.validation.constraints.NotBlank;

public record StartConversationRequest(
        @NotBlank String widgetKey,
        @NotBlank String visitorSessionId,
        String visitorName,
        String visitorEmail,
        String currentPage,
        String userAgent
) {
}
