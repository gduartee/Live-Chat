package com.chat.livechat.repository;

import org.springframework.stereotype.Repository;
import com.chat.livechat.domain.entity.Operator;
import com.chat.livechat.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, UUID>{


    List<Operator> findAllByUserAndActiveTrue(User user);

    Optional<Operator> findByIdAndUserAndActiveTrue(UUID id, User user);

    Optional<Operator> findByEmailAndUser(String email, User user);

    boolean existsByEmailAndUser(String email, User user);

    List<Operator> findByUserAndStatusAndActiveTrue(User user, Operator.Status status);

    @Query("""
        SELECT o FROM Operator o
        WHERE o.user.id = (
            SELECT w.user.id FROM Widget w WHERE w.id = :widgetId
        )
        AND o.status = 'ONLINE'
        AND o.active = true
    """)
    List<Operator> findOnlineByWidgetId(@Param("widgetId") UUID widgetId);

    @Query("""
        SELECT COUNT(o) FROM Operator o
        WHERE o.user = :user
        AND o.status = 'ONLINE'
        AND o.active = true
    """)
    long countOnlineByUser(@Param("user") User user);
}
