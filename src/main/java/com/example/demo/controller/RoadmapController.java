package com.example.demo.controller;

import com.example.demo.dto.RoadmapRequest;
import com.example.demo.dto.RoadmapRespond;

import com.example.demo.service.RoadmapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/users/{userId}/roadmaps")
public class RoadmapController {

    private final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @GetMapping
    public List<RoadmapRespond> getRoadmapByUser(@PathVariable Long userId)
    {
        return roadmapService.getRoadmapsByUser(userId);
    }

    @PostMapping
    //anything that comes from request body is untrusted
    public RoadmapRespond createRoadmap(@PathVariable Long userId, @RequestBody RoadmapRequest request )
    {
        return roadmapService.createRoadmap(userId,request);
    }

    @PutMapping("/{roadmapId}")
    public RoadmapRespond updateRoadmap(@PathVariable Long userId, @PathVariable Long roadmapId, @RequestBody RoadmapRequest updateRoadmap)
    {
        return roadmapService.updateRoadmap(userId,roadmapId,updateRoadmap);

    }
    @DeleteMapping("/{roadmapId}")
    public void deleteRoadmap(@PathVariable Long userId,@PathVariable Long roadmapId)
    {
        roadmapService.deleteRoadmap(userId,roadmapId);
    }
}
