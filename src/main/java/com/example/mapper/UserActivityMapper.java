package com.example.mapper;

import com.example.controller.CircleController;
import com.example.entity.UserActivity;
//import com.example.entity.UserActivityDetail;
//import com.example.entity.UserActivitySummary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserActivityMapper {

    @Insert("INSERT INTO user_activity (user_id, circle_id, activity_type, activity_time) " +
            "VALUES (#{userActivity.userId}, #{userActivity.circleId}, #{userActivity.activityType}, #{userActivity.activityTime})")
    void insertUserActivity(@Param("userActivity") UserActivity userActivity);

    @Select("SELECT user_id as userId, circle_id as circleId, activity_type as activityType, activity_time as activityTime FROM user_activity")
    List<UserActivity> getUserActivities();

    @Select("SELECT user_id as userId, circle_id as circleId, activity_type as activityType, activity_time as activityTime FROM user_activity WHERE user_id = #{userId}")
    List<UserActivity> getActivitiesByUserId(@Param("userId") long userId);

    @Select("SELECT user_id as userId, circle_id as circleId, activity_type as activityType, activity_time as activityTime FROM user_activity WHERE circle_id = #{circleId}")
    List<UserActivity> getActivitiesByCircleId(@Param("circleId") int circleId);

    @Delete("DELETE FROM user_activity")
    void deleteAllUserActivities();

}

