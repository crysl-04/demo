package com.example.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;


public class UserActivity {


    private long id;

    private long userId;

    private int circleId;

    private String activityType;

    private Timestamp activityTime;

    public UserActivity() {}

    public UserActivity( long userId, int circleId, String activityType,Timestamp activityTime) {
        this.userId = userId;
        this.circleId = circleId;
        this.activityType = activityType;
        this.activityTime = activityTime;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Timestamp getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Timestamp activityTime) {
        this.activityTime = activityTime;
    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "userId=" + userId +
                ", circleId=" + circleId +
                ", activityType='" + activityType + '\'' +
                ", activityTime=" + activityTime +
                '}';
    }
}

