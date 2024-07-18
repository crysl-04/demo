package com.example.service.impl;

import com.example.entity.InterestCircleMember;
import com.example.entity.InterestCirclePost;
import com.example.mapper.InterestCircleMapper;
import com.example.entity.InterestCircle;
import com.example.mapper.InterestCircleMemberMapper;
import com.example.service.InterestCircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestCircleServiceImpl implements InterestCircleService {

    @Autowired
    private InterestCircleMapper interestCircleMapper;
    @Autowired
    private InterestCircleMemberMapper interestCircleMemberMapper;

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
}
