package com.chat.livechat.domain.dto.widget;

import java.time.LocalDateTime;
import java.util.UUID;

public record WidgetResponse(
        UUID id,
        String widgetKey,
        String name,
        String primaryColor,
        String welcomeMessage,
        String offlineMessage,
        String position,
        Boolean active,
        LocalDateTime createdAt
) {
}
