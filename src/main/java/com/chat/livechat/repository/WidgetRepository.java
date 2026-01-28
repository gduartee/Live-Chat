package com.chat.livechat.repository;

import com.chat.livechat.domain.entity.User;
import com.chat.livechat.domain.entity.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, UUID> {
    List<Widget> findAllByUserAndActiveTrue(User user);

    Optional<Widget> findByIdAndUserAndActiveTrue(UUID id, User user);

    Optional<Widget> findByWidgetKeyAndActiveTrue(String widgetKey);

    Optional<Widget> findByWidgetKey(String widgetKey);

    long countByUserAndActiveTrue(User user);

    boolean existsByWidgetKey(String widgetKey);
}
