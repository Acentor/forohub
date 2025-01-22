package com.forohub.api.domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "topics")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String message;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    
    @Enumerated(EnumType.STRING)
    private TopicStatus status;
    
    private String author;
    private String course;

    public TopicEntity(String title, String message, String author, String course) {
        this.title = title;
        this.message = message;
        this.author = author;
        this.course = course;
        this.creationDate = LocalDateTime.now();
        this.status = TopicStatus.NOT_ANSWERED;
    }
}
