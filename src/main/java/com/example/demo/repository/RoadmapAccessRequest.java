package com.example.demo.repository;

import com.example.demo.entity.RequestStatus;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadmapAccessRequest  extends JpaRepository<RoadmapAccessRequest,Long> {
    List<RoadmapAccessRequest> findByToUserAndStatus(User user, RequestStatus status);
}
