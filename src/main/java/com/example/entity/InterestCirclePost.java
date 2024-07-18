package com.example.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "interest_circle_post")
public class InterestCirclePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "circle_id", referencedColumnName = "id")
    private InterestCircle interestCircle;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // Getters and setters
    // Constructors

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InterestCircle getInterestCircle() {
        return interestCircle;
    }

    public void setInterestCircle(InterestCircle interestCircle) {
        this.interestCircle = interestCircle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUserId(Long userId) {
        if (this.user == null) {
            this.user = new User();
        }
        this.user.setId(userId);
    }

    public void setCircleId(int circleId) {
        if (this.interestCircle == null) {
            this.interestCircle = new InterestCircle();
        }
        this.interestCircle.setId(circleId);
    }
}
