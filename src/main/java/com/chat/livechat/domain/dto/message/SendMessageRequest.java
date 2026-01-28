package com.chat.livechat.domain.dto.message;

import com.chat.livechat.domain.entity.Message;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SendMessageRequest(
        @NotBlank String content,
        Message.SenderType senderType,
        UUID senderId,
        String senderName
) {
}
