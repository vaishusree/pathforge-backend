package com.example.demo.service;


import com.example.demo.dto.RoadmapRequest;
import com.example.demo.dto.RoadmapRespond;
import com.example.demo.entity.Roadmap;
import com.example.demo.entity.User;
import com.example.demo.repository.RoadmapRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadmapService {
    private final RoadmapRepository roadmapRepository;
    private final UserRepository userRepository;

    public RoadmapService(RoadmapRepository roadmapRepository, UserRepository userRepository) {
        this.roadmapRepository = roadmapRepository;
        this.userRepository = userRepository;
    }

    public List<RoadmapRespond> getRoadmapsByUser(Long userId)
    {
        User user=userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("User not found"));
        return roadmapRepository.findByUser(user);
    }
    public RoadmapRespond createRoadmap(Long userId, RoadmapRequest request)
    {
        if(userId==null) throw new IllegalArgumentException("User not found");
        if(request==null) throw new IllegalArgumentException("Roadmap cannot be null");
        if(request.getTitle()==null || request.getTitle().isBlank()) throw new IllegalArgumentException("Title cannot be null");
        User user=userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User cannot be found"));
        //if(roadmap.getDescription()!=null && roadmap.getDescription().isBlank()) roadmap.setDescription(null);
        String description=request.getDescription();
        if(description!=null && description.isBlank())
        {
            description=null;
        }
        // it is ideal for dto to remain immutable during service processing

        Roadmap map=new Roadmap(request.getTitle(), description, user);
        roadmapRepository.save(map);

        return new RoadmapRespond(map.getId(), map.getTitle(), map.getDescription(),map.getCreatedAt(),map.getUpdatedAt(),map.getUser().getId());
    }

    public void deleteRoadmap(Long userId, Long roadmapId)
    {
        if(userId==null) throw new IllegalArgumentException("User Id does not exist");
        if(roadmapId==null) throw new IllegalArgumentException("Invalid Roadmap Id");

        Roadmap roadmap=roadmapRepository.findById(roadmapId).orElseThrow(()->new IllegalArgumentException("Roadmap doesn't exist"));
        if(!roadmap.getUser().getId().equals(userId))
        {
            throw new IllegalStateException("Cannot delete roadmap of another user");
        }
        roadmapRepository.delete(roadmap);
    }

    public RoadmapRespond updateRoadmap(Long userId, Long roadmapId, RoadmapRequest updateRoadmap)
    {
        if(userId==null) throw new IllegalArgumentException("User Id does not exist");
        if(roadmapId==null) throw new IllegalArgumentException("Invalid Roadmap Id");
        if (updateRoadmap == null)
            throw new IllegalArgumentException("Update request cannot be null");
        Roadmap roadmap=roadmapRepository.findById(roadmapId).orElseThrow(()->new IllegalArgumentException("Roadmap doesn't Exist"));
        if(!roadmap.getUser().getId().equals(userId))
        {
            throw new IllegalStateException("Cannot update roadmap of another user");
        }
        if(updateRoadmap.getTitle()!=null)
        {
            if(updateRoadmap.getTitle().isBlank()) throw new IllegalArgumentException("Title cannot be blank");
            roadmap.setTitle(updateRoadmap.getTitle());
        }

        if(updateRoadmap.getDescription()!=null)
        {
            String description=updateRoadmap.getDescription().isBlank()?null: updateRoadmap.getDescription();
            roadmap.setDescription(description);
        }

        Roadmap map= roadmapRepository.save(roadmap);
        return new RoadmapRespond(map.getId(), map.getTitle(), map.getDescription(),map.getCreatedAt(),map.getUpdatedAt(),map.getUser().getId());
    }
}
