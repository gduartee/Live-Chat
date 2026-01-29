package com.chat.livechat.domain.dto.operator;

import java.time.LocalDateTime;
import java.util.UUID;

public record OperatorResponse(
        UUID id,
        String name,
        String email,
        String avatarUrl,
        String status,
        LocalDateTime lastSeenAt,
        LocalDateTime createdAd
) {
}
