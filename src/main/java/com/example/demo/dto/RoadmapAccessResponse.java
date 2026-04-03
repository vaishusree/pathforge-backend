package com.example.demo.dto;

import com.example.demo.entity.RequestStatus;

import java.time.LocalDateTime;

public class RoadmapAccessResponse {

    private final Long id;
    private final Long fromUserId;
    private final Long toUserId;
    private final Long roadmapId;
    private final String status;
    private final LocalDateTime createdAt;

    public RoadmapAccessResponse(Long id, Long fromUserId, Long toUserId, Long roadmapId, String status, LocalDateTime createdAt) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.roadmapId = roadmapId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public Long getRoadmapId() {
        return roadmapId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}