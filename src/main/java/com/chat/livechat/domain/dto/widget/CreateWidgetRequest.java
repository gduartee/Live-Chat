package com.chat.livechat.domain.dto.widget;

import jakarta.validation.constraints.NotBlank;

public record CreateWidgetRequest(
        @NotBlank String name,
        String primaryColor,
        String welcomeMessage,
        String offlineMessage,
        String position
) {
}
