package com.example.service.impl;

import com.example.entity.InterestCircleMember;
import com.example.entity.InterestCirclePost;
import com.example.mapper.InterestCircleMapper;
import com.example.entity.InterestCircle;
import com.example.mapper.InterestCircleMemberMapper;
import com.example.mapper.InterestCirclePostMapper;
import com.example.service.InterestCircleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestCircleServiceImpl implements InterestCircleService {

    @Autowired
    private InterestCircleMapper interestCircleMapper;

    @Autowired
    private InterestCircleMemberMapper interestCircleMemberMapper;

    @Autowired
    private InterestCirclePostMapper interestCirclePostMapper;

    @Override
    public List<InterestCircle> getAllCircles() {
        return interestCircleMapper.getAllCircles();
    }

    @Override
    public InterestCircle getCircleById(int id) {
        return interestCircleMapper.getCircleById(id);
    }

    @Override
    public void addCircle(InterestCircle circle) {
        interestCircleMapper.addCircle(circle);
    }

    @Override
    public void updateCircle(InterestCircle circle) {
        interestCircleMapper.updateCircle(circle);
    }

    @Override
    public List<InterestCircleMember> getMembersByCircleId(int circleId) {
        return interestCircleMapper.getMembersByCircleId(circleId);
    }

    @Override
    public List<InterestCirclePost> getPostsByCircleId(int circleId) {
        return interestCircleMapper.getPostsByCircleId(circleId);
    }

    @Override
    public void addMember(InterestCircleMember member) {
        interestCircleMapper.addMember(member);
    }

    @Override
    public void addPost(InterestCirclePost post) {
        interestCircleMapper.addPost(post);
    }

    @Override
    public List<String> getAllNicknames(int circleId){
        return interestCircleMapper.findNicknameByCircle(circleId);
    }

    public InterestCircleMember findByNickname(String nickname) {
        return interestCircleMemberMapper.findByNickname(nickname);
    }

//    @Override
//    public Page<InterestCirclePost> getPostsByCircleId(int circleId, int page, int size) {
//        Pageable pageable = PageRequest.of(page - 1, size); // 将页面从1开始转换为从0开始
//        return interestCircleMapper.getPostsByCircleId(circleId, pageable);
//    }
//
//    @Override
//    public Page<String> getMembersByCircleId(int circleId, int page, int size) {
//        Pageable pageable = PageRequest.of(page - 1, size); // 将页面从1开始转换为从0开始
//        return interestCircleMapper.getMembersByCircleId(circleId, pageable);
//    }

}
