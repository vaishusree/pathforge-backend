package com.example.demo.controller;

import com.example.demo.dto.RoadmapAccessRequestDto;
import com.example.demo.dto.RoadmapAccessResponse;
import com.example.demo.service.RoadmapAccessService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roadmap-requests")
class RoadmapAccessController {

    private final RoadmapAccessService roadmapAccessService;

    public RoadmapAccessController(RoadmapAccessService roadmapAccessService) {
        this.roadmapAccessService = roadmapAccessService;
    }


    @PostMapping("/{fromUserId}")
    public ResponseEntity<Void> sendRequest(
            @PathVariable Long fromUserId,
            @Valid @RequestBody RoadmapAccessRequestDto request) {

        roadmapAccessService.requestAccess(fromUserId, request.getRoadmapId());

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{requestId}/accept")
    public ResponseEntity<Void> acceptRequest(
            @PathVariable Long requestId,
            @RequestParam Long userId) {

        roadmapAccessService.acceptRequest(requestId, userId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{requestId}/decline")
    public ResponseEntity<Void> declineRequest(
            @PathVariable Long requestId,
            @RequestParam Long userId) {

        roadmapAccessService.rejectRequest(requestId, userId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/incoming/{userId}")
    public ResponseEntity<List<RoadmapAccessResponse>> getIncomingRequests(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                roadmapAccessService.getIncomingRequests(userId)
        );
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> cancelRequest(
            @PathVariable Long requestId,
            @RequestParam Long userId) {

        roadmapAccessService.cancelRequest(requestId, userId);

        return ResponseEntity.noContent().build();
    }
}