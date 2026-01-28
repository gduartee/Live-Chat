package com.chat.livechat.domain.dto.auth;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        String name,
        String companyName,
        String plan,
        LocalDateTime createdAt
) {
}
