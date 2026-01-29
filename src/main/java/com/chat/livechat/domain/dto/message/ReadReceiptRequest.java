package com.chat.livechat.domain.dto.message;

import java.util.List;
import java.util.UUID;

public record ReadReceiptRequest(
        List<UUID> messageIds
) {
}
