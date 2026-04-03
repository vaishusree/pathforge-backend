package com.example.demo.controller;

import com.example.demo.dto.RoadmapAccessRequestdto;
import com.example.demo.dto.RoadmapAccessResponse;
import com.example.demo.entity.RoadmapAccessRequest;
import com.example.demo.service.RoadmapAccessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roadmap-requests")
class RoadmapAccessRequestController {

    private final RoadmapAccessService roadmapAccessService;

    public RoadmapAccessRequestController(RoadmapAccessService roadmapAccessService) {
        this.roadmapAccessService = roadmapAccessService;
    }

    @PostMapping
    public void sendRequest(@PathVariable Long fromUserId,
                            @RequestBody RoadmapAccessRequestdto request) {

        roadmapAccessService.requestAccess(fromUserId, request.getRoadmapId());
    }

    @PutMapping("/{requestId}/accept")
    public void acceptRequest(@PathVariable Long requestId,
                              @PathVariable Long userId) {

        roadmapAccessService.acceptRequest(requestId, userId);
    }

    @PutMapping("/{requestId}/decline")
    public void declineRequest(@PathVariable Long requestId,
                               @PathVariable Long userId) {

        roadmapAccessService.rejectRequest(requestId, userId);
    }

    @GetMapping("/incoming/{userId}")
    public List<RoadmapAccessResponse> getIncomingRequests(@PathVariable Long userId) {

        return roadmapAccessService.getIncomingRequests(userId);
    }

    @DeleteMapping("/{requestId}")
    public void cancelRequest(@PathVariable Long requestId,
                              @PathVariable Long userId)
    {
        roadmapAccessService.cancelRequest(requestId, userId);
    }
}

