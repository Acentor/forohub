package com.forohub.api.services;

import com.forohub.api.domain.topic.TopicEntity;
import com.forohub.api.domain.topic.TopicRepository;
import com.forohub.api.dtos.topic.TopicRequestDTO;
import com.forohub.api.dtos.topic.TopicResponseDTO;
import com.forohub.api.dtos.topic.TopicUpdateDTO;
import com.forohub.api.infra.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;

    @Transactional
    public TopicResponseDTO createTopic(TopicRequestDTO data) {
        if (repository.existsByTitleAndMessage(data.title(), data.message())) {
            throw new BusinessException("Ya existe un tópico con el mismo título y mensaje");
        }

        var topic = new TopicEntity(data.title(), data.message(), data.author(), data.course());
        repository.save(topic);

        return new TopicResponseDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus().toString(),
                topic.getAuthor(),
                topic.getCourse()
        );
    }

    public List<TopicResponseDTO> listTopics() {
        return repository.findAll().stream().map(topic -> new TopicResponseDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus().toString(),
                topic.getAuthor(),
                topic.getCourse()
        )).toList();
    }

    public TopicResponseDTO getTopicById(Long id) {
        var topic = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Tópico no encontrado"));

        return new TopicResponseDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus().toString(),
                topic.getAuthor(),
                topic.getCourse()
        );
    }

    @Transactional
    public TopicResponseDTO updateTopic(Long id, TopicUpdateDTO data) {
        var topic = repository.findActiveById(id)
                .orElseThrow(() -> new BusinessException("Tópico no encontrado o cerrado"));

        topic.setTitle(data.title());
        topic.setMessage(data.message());

        return new TopicResponseDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus().toString(),
                topic.getAuthor(),
                topic.getCourse()
        );
    }

    @Transactional
    public void deleteTopic(Long id) {
        var topic = repository.findActiveById(id)
                .orElseThrow(() -> new BusinessException("Tópico no encontrado o cerrado"));

        repository.delete(topic);
    }
}
