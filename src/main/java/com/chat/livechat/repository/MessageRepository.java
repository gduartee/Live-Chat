package com.chat.livechat.repository;

import com.chat.livechat.domain.entity.Conversation;
import com.chat.livechat.domain.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    // ===== Busca de mensagens =====

    List<Message> findByConversationIdOrderBySentAtAsc(UUID conversationId);

    Page<Message> findByConversationId(UUID conversationId, Pageable pageable);

    Optional<Message> findTopByConversationOrderBySentAtDesc(Conversation conversation);

    @Query("""
        SELECT m FROM Message m
        WHERE m.conversation.id = :conversationId
        AND m.sentAt > :since
        ORDER BY m.sentAt ASC
    """)
    List<Message> findByConversationIdSince(@Param("conversationId") UUID conversationId, @Param("since") LocalDateTime since);

    // ===== Contagem =====

    long countByConversationId(UUID conversationId);

    @Query("""
        SELECT COUNT(m) FROM Message m
        WHERE m.conversation.id = :conversationId
        AND m.readAt IS NULL
        AND m.senderType = 'VISITOR'
    """)
    int countUnreadByConversation(@Param("conversationId") UUID conversationId);

    @Query("""
        SELECT COUNT(m) FROM Message m
        WHERE m.conversation.widget.user.email = :email
        AND m.readAt IS NULL
        AND m.senderType = 'VISITOR'
    """)
    int countUnreadByUserEmail(@Param("email") String email);

    // ===== Atualização =====

    @Modifying
    @Query("""
        UPDATE Message m
        SET m.readAt = :readAt
        WHERE m.id IN :messageIds
        AND m.readAt IS NULL
    """)
    void markAsRead(@Param("messageIds") List<UUID> messageIds, @Param("readAt") LocalDateTime readAt);

    @Modifying
    @Query("""
        UPDATE Message m
        SET m.readAt = :readAt
        WHERE m.conversation.id = :conversationId
        AND m.senderType = :senderType
        AND m.readAt IS NULL
    """)
    void markAllAsReadByConversationAndSenderType(
            @Param("conversationId") UUID conversationId,
            @Param("senderType") Message.SenderType senderType,
            @Param("readAt") LocalDateTime readAt
    );

    @Modifying
    @Query("UPDATE Message m SET m.deleted = true WHERE m.id = :id")
    void softDelete(@Param("id") UUID id);

    // ===== Estatísticas =====

    @Query("""
        SELECT COUNT(m) FROM Message m
        WHERE m.conversation.widget.id = :widgetId
        AND m.sentAt >= :since
    """)
    long countByWidgetIdSince(@Param("widgetId") UUID widgetId, @Param("since") LocalDateTime since);

    @Query("""
        SELECT AVG(TIMESTAMPDIFF(SECOND, c.startedAt, m.sentAt))
        FROM Message m
        JOIN m.conversation c
        WHERE c.widget.id = :widgetId
        AND m.senderType = 'OPERATOR'
        AND m.sentAt >= :since
        AND m.id = (
            SELECT MIN(m2.id) FROM Message m2
            WHERE m2.conversation = c
            AND m2.senderType = 'OPERATOR'
        )
    """)
    Double averageFirstResponseTimeByWidgetSince(@Param("widgetId") UUID widgetId, @Param("since") LocalDateTime since);
}