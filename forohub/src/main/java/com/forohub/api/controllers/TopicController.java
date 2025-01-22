package com.forohub.api.controllers;

import com.forohub.api.dtos.topic.TopicRequestDTO;
import com.forohub.api.dtos.topic.TopicResponseDTO;
import com.forohub.api.dtos.topic.TopicUpdateDTO;
import com.forohub.api.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService service;

    @PostMapping
    public ResponseEntity<TopicResponseDTO> createTopic(
            @RequestBody @Valid TopicRequestDTO topicData,
            UriComponentsBuilder uriBuilder
    ) {
        TopicResponseDTO topic = service.createTopic(topicData);
        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.id()).toUri();
        return ResponseEntity.created(uri).body(topic);
    }

    @GetMapping
    public ResponseEntity<List<TopicResponseDTO>> listTopics() {
        return ResponseEntity.ok(service.listTopics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> getTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTopicById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> updateTopic(
            @PathVariable Long id,
            @RequestBody @Valid TopicUpdateDTO topicData
    ) {
        return ResponseEntity.ok(service.updateTopic(id, topicData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        service.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}   