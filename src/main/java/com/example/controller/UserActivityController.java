package com.example.controller;

import com.example.entity.UserActivity;
import com.example.entity.UserActivityDetail;
import com.example.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserActivityController {
    @Autowired
    private UserActivityService userActivityService;

    @GetMapping("/circles/{circleId}/activity")
    @ResponseBody
    public List<UserActivityDetail> getActivitySummary(@PathVariable("circleId") int circleId) {
        //System.out.println("CircleId: " + circleId);
        return userActivityService.getActivityDetailsByCircleId(circleId);
    }
}
