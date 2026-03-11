package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="roadmap_access_request")
public class RoadmapAccessRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="from_user_id", nullable=false)
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="to_user_id", nullable=false)
    private User toUser;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="roadmap_id", nullable=false)
    private Roadmap roadmap;

    @PrePersist
    public void createAt(){
        this.createdAt = LocalDateTime.now();
        this.status = RequestStatus.PENDING;
    }

    public RoadmapAccessRequest() {
    }

    //@PrePersist sets the status hence no need to initiate it inside constructor
    public RoadmapAccessRequest(User fromUser, User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;

    }

    public Long getId() {
        return id;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


}
