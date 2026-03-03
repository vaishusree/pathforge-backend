package com.example.demo.dto;

import java.time.LocalDateTime;

public class RoadmapRespond {

    private final  Long id;
    private final String title;

    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Long userId;

    public RoadmapRespond(Long id, String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getUserId() {
        return userId;
    }
}
