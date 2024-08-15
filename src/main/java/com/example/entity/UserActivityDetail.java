package com.example.entity;


public class UserActivityDetail {
    private Long userId;
    private int score; // 活跃度分数

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "UserActivityDetail{" +
                "userId=" + userId +
                ", score=" + score +
                '}';
    }
}
