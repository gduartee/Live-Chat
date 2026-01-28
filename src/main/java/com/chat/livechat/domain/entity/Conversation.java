package com.chat.livechat.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.message.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_conversations")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "widget_id", nullable = false)
    private Widget widget;

    @Column(nullable = false)
    private String visitorSessionId;

    private String visitorName;

    private String visitorEmail;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.WAITING;

    @Builder.Default
    private LocalDateTime startedAt = LocalDateTime.now();

    private LocalDateTime closedAt;

    private Integer rating; // 1-5 estrelas

    private String ratingComment;

    @Column(length = 500)
    private String subject; // Assunto/resumo da conversa

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Message> messages = new ArrayList<>();

    public enum Status {
        WAITING, ACTIVE, CLOSED, MISSED // Timeout -> N atendida
    }
}