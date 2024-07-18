package com.example.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "interest_circle_member")
public class InterestCircleMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "circle_id", referencedColumnName = "id")
    private InterestCircle interestCircle;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "created_at")
    private Timestamp createdAt;

    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Additional methods to get userId and circleId

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public Integer getCircleId() {
        return interestCircle != null ? interestCircle.getId() : null;
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
