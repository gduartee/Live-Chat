package com.chat.livechat.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_visitors")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "widget_id", nullable = false)
    private Widget widget;

    @Column(nullable = false, unique = true)
    private String sessionId; // Identificador único gerado no browser

    private String name; // Pode ser preenchido depois

    private String email; // Pode ser preenchido depois

    private String phone;

    private String ipAddress;

    @Column(length = 500)
    private String userAgent;

    private String currentPage; // URL da página atual

    private String referrer; // De onde veio

    private String city;

    private String country;

    @Builder.Default
    private LocalDateTime firstVisitAt = LocalDateTime.now();

    private LocalDateTime lastSeenAt;

    @Builder.Default
    private Integer totalVisits = 1;
}