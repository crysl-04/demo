package com.example.service;

import com.example.entity.UserActivity;
import com.example.entity.UserActivityDetail;
import com.example.mapper.UserActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserActivityService {

    @Autowired
    private UserActivityMapper userActivityMapper;

    public List<UserActivity> getActivitiesByCircleId(int circleId) {
        return userActivityMapper.getActivitiesByCircleId(circleId);
    }

    // 定义活动类型与分值的映射
    private static final int POST_CREATE_SCORE = 10;
    private static final int COMMENT_CREATE_SCORE = 5;

    public int calculateUserActivityScore(Long userId) {
        List<UserActivity> activities = userActivityMapper.getActivitiesByUserId(userId);

        // 添加日志输出
        //System.out.println("Calculating score for user ID: " + userId);
        //System.out.println("Activities: " + activities);

        int totalScore = 0;

        for (UserActivity activity : activities) {
            switch (activity.getActivityType()) {
                case "POST_CREATE":
                    totalScore += POST_CREATE_SCORE;
                    break;
                case "COMMENT_CREATE":
                    totalScore += COMMENT_CREATE_SCORE;
                    break;
                // 可以添加更多活动类型及其对应的分值
            }
        }
        //      System.out.println("Total score for user ID " + userId + ": " + totalScore);

        return totalScore;
    }

    public List<UserActivityDetail> getActivityDetailsByCircleId(int circleId) {
        // 1. 获取兴趣圈内所有用户的活动记录
        List<UserActivity> activities = userActivityMapper.getActivitiesByCircleId(circleId);
//        System.out.println("Activities for circle ID " + circleId + ": " + activities);

        // 2. 创建一个映射，存储每个用户的活动分数
        Map<Long, Integer> userScores = new HashMap<>();

        // 3. 计算每个用户的活跃度分数
        for (UserActivity activity : activities) {
            Long userId = activity.getUserId();
            //System.out.println("Processing user ID: " + userId);
            if (userId != null) {
                int score = calculateUserActivityScore(userId);
               // System.out.println("Score for user ID " + userId + ": " + score);
                userScores.put(userId, score);
            } else {
                System.out.println("User ID is null for activity: " + activity);
            }
        }

        // 4. 将用户及其活跃度信息封装到 UserActivityDetail 对象中
        List<UserActivityDetail> userActivityDetails = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : userScores.entrySet()) {
            Long userId = entry.getKey();
            Integer score = entry.getValue();

            UserActivityDetail detail = new UserActivityDetail();
            detail.setUserId(userId);
            detail.setScore(score);

            userActivityDetails.add(detail);
        }

        //System.out.println("User activity details: " + userActivityDetails);

        return userActivityDetails;
    }


}
