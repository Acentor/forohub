package com.forohub.api.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
    
    boolean existsByTitleAndMessage(String title, String message);
    
    @Query("""
        select t from TopicEntity t
        where t.id = :id
        and t.status <> 'CLOSED'
    """)
    Optional<TopicEntity> findActiveById(Long id);
}