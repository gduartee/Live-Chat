package com.chat.livechat.domain.dto.auth;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        Long expiresIn,
        String tokenType
) {
}
