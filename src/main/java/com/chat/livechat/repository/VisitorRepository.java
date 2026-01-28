package com.chat.livechat.repository;

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
public interface VisitorRepository extends JpaRepository<Visitor, UUID> {

    Optional<Visitor> findBySessionId(String sessionId);

    List<Visitor> findByWidget(Widget widget);

    Page<Visitor> findByWidget(Widget widget, Pageable pageable);

    @Query("""
        SELECT v FROM Visitor v
        WHERE v.widget = :widget
        AND v.lastSeenAt >= :since
        ORDER BY v.lastSeenAt DESC
    """)
    List<Visitor> findRecentByWidget(@Param("widget") Widget widget, @Param("since") LocalDateTime since);

    @Query("""
        SELECT v FROM Visitor v
        WHERE v.widget.user.email = :email
        ORDER BY v.lastSeenAt DESC
    """)
    Page<Visitor> findAllByUserEmail(@Param("email") String email, Pageable pageable);

    @Query("""
        SELECT COUNT(DISTINCT v) FROM Visitor v
        WHERE v.widget = :widget
        AND v.firstVisitAt >= :since
    """)
    long countNewVisitorsSince(@Param("widget") Widget widget, @Param("since") LocalDateTime since);

    @Query("""
        SELECT COUNT(DISTINCT v) FROM Visitor v
        WHERE v.widget = :widget
        AND v.lastSeenAt >= :since
    """)
    long countActiveVisitorsSince(@Param("widget") Widget widget, @Param("since") LocalDateTime since);
}
