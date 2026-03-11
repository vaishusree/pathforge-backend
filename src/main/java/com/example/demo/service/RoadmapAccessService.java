package com.example.demo.service;

import com.example.demo.entity.RequestStatus;
import com.example.demo.entity.Roadmap;
import com.example.demo.entity.RoadmapAccessRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.RoadmapAccessRequestRepository;
import com.example.demo.repository.RoadmapRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadmapAccessService {
    private final RoadmapAccessRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final RoadmapRepository roadmapRepository;

    public RoadmapAccessService(RoadmapAccessRequestRepository requestRepository, UserRepository userRepository, RoadmapRepository roadmapRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.roadmapRepository = roadmapRepository;
    }

    //post- sending request
    public void requestAccess(Long fromUserId, Long roadmapId)
    {
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Roadmap roadmap = roadmapRepository.findById(roadmapId)
                .orElseThrow(() -> new IllegalArgumentException("Roadmap not found"));

        User toUser = roadmap.getUser();

        if(fromUser.getId().equals(toUser.getId())){
            throw new IllegalStateException("Cannot request your own roadmap");
        }
        boolean alreadyRequested =
                requestRepository.existsByFromUserAndRoadmapAndStatus(
                        fromUser,
                        roadmap,
                        RequestStatus.PENDING
                );
        if(alreadyRequested){
            throw new IllegalStateException("Request already sent");
        }
        RoadmapAccessRequest request =
                new RoadmapAccessRequest(fromUser, toUser,roadmap);
        // entity already sets status in @prepersist hence no need to set manually
        requestRepository.save(request);
    }
    //put operation for accepting
    public void acceptRequest(Long requestId, Long userId)
    {
        RoadmapAccessRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        if(!request.getToUser().getId().equals(userId)){
            throw new IllegalStateException("Only owner can accept request");
        }
        //prevents accepting already accepted requests
        if(request.getStatus() != RequestStatus.PENDING){
            throw new IllegalStateException("Request already processed");
        }

        request.setStatus(RequestStatus.ACCEPTED);

        requestRepository.save(request);
    }
    //put-rejecting
    public void rejectRequest(Long requestId, Long userId)
    {
        RoadmapAccessRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        if(!request.getToUser().getId().equals(userId)){
            throw new IllegalStateException("Only owner can decline request");
        }
        if(request.getStatus() != RequestStatus.PENDING){
            throw new IllegalStateException("Request already processed");
        }//declined cant be
        request.setStatus(RequestStatus.DECLINED);

        requestRepository.save(request);
    }
    //delete-unsend request
    public void cancelRequest(Long requestId, Long userId)
    {
        RoadmapAccessRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        if(!request.getFromUser().getId().equals(userId)){
            throw new IllegalStateException("Only sender can cancel request");
        }

        if(request.getStatus() != RequestStatus.PENDING){
            throw new IllegalStateException("Cannot cancel processed request");
        }

        requestRepository.delete(request);
    }
    public List<RoadmapAccessRequest> getIncomingRequests(Long userId)
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return requestRepository.findByToUserAndStatus(user, RequestStatus.PENDING);
    }

}
