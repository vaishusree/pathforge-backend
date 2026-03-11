package com.example.demo.controller;

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
        public void sendRequest(@RequestParam Long fromUserId,
                                @RequestParam Long roadmapId) {

            roadmapAccessService.requestAccess(fromUserId, roadmapId);
        }

        @PutMapping("/{requestId}/accept")
        public void acceptRequest(@PathVariable Long requestId,
                                  @RequestParam Long userId) {

            roadmapAccessService.acceptRequest(requestId, userId);
        }

        @PutMapping("/{requestId}/decline")
        public void declineRequest(@PathVariable Long requestId,
                                   @RequestParam Long userId) {

            roadmapAccessService.rejectRequest(requestId, userId);
        }

        @GetMapping("/incoming/{userId}")
        public List<RoadmapAccessRequest> getIncomingRequests(@PathVariable Long userId) {

            return roadmapAccessService.getIncomingRequests(userId);
        }

        @DeleteMapping("/{requestId}")
        public void cancelRequest(@PathVariable Long requestId,
                                  @RequestParam Long userId)
        {
            roadmapAccessService.cancelRequest(requestId, userId);
        }
    }

