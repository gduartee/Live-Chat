package com.chat.livechat.domain.dto.operator;

public record UpdateOperatorRequest(
        String name,
        String email,
        String password,
        String avatarUrl
) {
}
