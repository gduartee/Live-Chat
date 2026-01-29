package com.chat.livechat.domain.dto.conversation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record RateConversationRequest(
        @Min(1) @Max(5) Integer rating,
        String comment
) {
}
