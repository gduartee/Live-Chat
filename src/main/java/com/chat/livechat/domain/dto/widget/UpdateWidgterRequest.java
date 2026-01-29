package com.chat.livechat.domain.dto.widget;

public record UpdateWidgterRequest(
        String name,
        String primaryColor,
        String welcomeMessage,
        String offlineMessage,
        String position
) {
}
