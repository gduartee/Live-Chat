package com.chat.livechat.domain.dto.user;

public record UpdateUserRequest(
        String name,
        String companyName
) {
}
