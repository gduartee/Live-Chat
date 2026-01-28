package com.chat.livechat.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_widgets")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String widgetKey; // Chave única para o script JS

    @Builder.Default
    private String name = "Chat";

    @Builder.Default
    private String primaryColor = "#0066FF";

    @Builder.Default
    private String welcomeMessage = "Olá! Como posso ajudar?";

    private String offlineMessage;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Position position = Position.BOTTOM_RIGHT;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private Boolean active = true;

    public enum Position {
        BOTTOM_RIGHT, BOTTOM_LEFT
    }
}
