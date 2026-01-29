package com.chat.livechat.domain.dto.message;

import java.util.UUID;

public record JoinConversationRequest(
        UUID operatorId
) {
}
