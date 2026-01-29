package com.chat.livechat.domain.dto.operator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateOperatorRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        String password,
        String avatarUrl
) {
}
