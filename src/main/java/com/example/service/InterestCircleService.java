package com.example.service;

import com.example.entity.InterestCircle;
import com.example.entity.InterestCircleMember;
import com.example.entity.InterestCirclePost;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InterestCircleService {
    List<InterestCircle> getAllCircles();
    InterestCircle getCircleById(int id);
    void addCircle(InterestCircle circle);
    void updateCircle(InterestCircle circle);
    List<InterestCircleMember> getMembersByCircleId(int circleId);
    List<InterestCirclePost> getPostsByCircleId(int circleId);
    void addMember(InterestCircleMember member);
    void addPost(InterestCirclePost post);
    List<String> getAllNicknames(int circleId);
    InterestCircleMember findByNickname(String nickname);
//    Page<InterestCirclePost> getPostsByCircleId(int circleId, int page, int size);
//    // 获取指定兴趣圈子的成员列表（分页）
//    Page<String> getMembersByCircleId(int circleId, int page, int size);


    }
