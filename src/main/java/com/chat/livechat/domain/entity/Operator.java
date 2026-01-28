package com.chat.livechat.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "tb_operators")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Dono da conta (cliente do SaaS)

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.OFFLINE;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime lastSeenAt;

    @Builder.Default
    private Boolean active = true;

    public enum Status {
        ONLINE, OFFLINE, BUSY, AWAY
    }
}
