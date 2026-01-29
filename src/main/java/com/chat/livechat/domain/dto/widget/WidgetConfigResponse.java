package com.chat.livechat.domain.dto.widget;

public record WidgetConfigResponse(
        String widgetKey,
        String name,
        String primaryColor,
        String welcomeMessage,
        String offlineMessage,
        String position
) {
}
