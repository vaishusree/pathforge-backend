package com.example.demo.repository;

import com.example.demo.entity.RequestStatus;
import com.example.demo.entity.Roadmap;
import com.example.demo.entity.RoadmapAccessRequest;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadmapAccessRequestRepository extends JpaRepository<RoadmapAccessRequest,Long> {//entitiy and id type
    List<RoadmapAccessRequest> findByToUserAndStatus(User user, RequestStatus status);
    boolean existsByFromUserAndRoadmapAndStatus(
            User fromUser,
            Roadmap roadmap,
            RequestStatus status
    );//validation

    // prevents a user from having duplicate request , checks if the request is already present
}
