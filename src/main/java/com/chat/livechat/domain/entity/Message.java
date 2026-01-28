package com.chat.livechat.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "tb_messages")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SenderType senderType;

    private UUID senderId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MessageType messageType = MessageType.TEXT;

    private String attachmentUrl; // URL do arquivo se for ATTACHMENT

    private String attachmentName;

    @Builder.Default
    private LocalDateTime sentAt = LocalDateTime.now();

    public enum SenderType {
        VISITOR, OPERATOR, SYSTEM
    }

    public enum MessageType {
        TEXT, IMAGE, FILE, SYSTEM
    }
}