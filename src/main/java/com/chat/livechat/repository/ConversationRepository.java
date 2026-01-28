package com.chat.livechat.repository;

import com.chat.livechat.domain.entity.Conversation;
import com.chat.livechat.domain.entity.User;
import com.chat.livechat.domain.entity.Visitor;
import com.chat.livechat.domain.entity.Widget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, UUID> {

    // ===== Por Widget =====

    Page<Conversation> findByWidgetId(UUID widgetId, Pageable pageable);

    Page<Conversation> findByWidgetIdAndStatus(UUID widgetId, Conversation.Status status, Pageable pageable);

    List<Conversation> findByWidgetIdAndStatusOrderByStartedAtAsc(UUID widgetId, Conversation.Status status);

    List<Conversation> findByWidgetAndStatusIn(Widget widget, List<Conversation.Status> statuses);

    // ===== Por User (dono do widget) =====

    @Query("SELECT c FROM Conversation c WHERE c.widget.user = :user")
    Page<Conversation> findByWidgetUser(@Param("user") User user, Pageable pageable);

    @Query("SELECT c FROM Conversation c WHERE c.widget.user = :user AND c.status = :status")
    Page<Conversation> findByWidgetUserAndStatus(@Param("user") User user, @Param("status") Conversation.Status status, Pageable pageable);

    @Query("SELECT c FROM Conversation c WHERE c.widget.user = :user AND c.status = :status ORDER BY c.startedAt ASC")
    List<Conversation> findByWidgetUserAndStatusOrderByStartedAtAsc(@Param("user") User user, @Param("status") Conversation.Status status);

    @Query("SELECT c FROM Conversation c WHERE c.id = :id AND c.widget.user = :user")
    Optional<Conversation> findByIdAndWidgetUser(@Param("id") UUID id, @Param("user") User user);

    // ===== Por Visitor =====

    List<Conversation> findByVisitor(Visitor visitor);

    Optional<Conversation> findByVisitorAndStatusIn(Visitor visitor, List<Conversation.Status> statuses);

    @Query("SELECT c FROM Conversation c WHERE c.visitor.sessionId = :sessionId AND c.status IN :statuses")
    Optional<Conversation> findByVisitorSessionIdAndStatusIn(@Param("sessionId") String sessionId, @Param("statuses") List<Conversation.Status> statuses);

    // ===== Por Operator =====

    @Query("SELECT c FROM Conversation c WHERE c.operator.id = :operatorId AND c.status = :status")
    List<Conversation> findByOperatorIdAndStatus(@Param("operatorId") UUID operatorId, @Param("status") Conversation.Status status);

    @Query("SELECT c FROM Conversation c WHERE c.operator.id = :operatorId")
    Page<Conversation> findByOperatorId(@Param("operatorId") UUID operatorId, Pageable pageable);

    // ===== Estatísticas =====

    @Query("""
        SELECT COUNT(c) FROM Conversation c
        WHERE c.widget = :widget
        AND c.startedAt >= :since
    """)
    long countByWidgetSince(@Param("widget") Widget widget, @Param("since") LocalDateTime since);

    @Query("""
        SELECT COUNT(c) FROM Conversation c
        WHERE c.widget = :widget
        AND c.status = :status
    """)
    long countByWidgetAndStatus(@Param("widget") Widget widget, @Param("status") Conversation.Status status);

    @Query("""
        SELECT AVG(c.rating) FROM Conversation c
        WHERE c.widget = :widget
        AND c.rating IS NOT NULL
        AND c.closedAt >= :since
    """)
    Double averageRatingByWidgetSince(@Param("widget") Widget widget, @Param("since") LocalDateTime since);

    @Query("""
        SELECT COUNT(c) FROM Conversation c
        WHERE c.widget.user = :user
        AND c.status = 'WAITING'
    """)
    long countWaitingByUser(@Param("user") User user);
}