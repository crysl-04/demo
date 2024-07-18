package com.example.service;

import com.example.entity.InterestCircle;
import com.example.entity.InterestCircleMember;
import com.example.entity.InterestCirclePost;
import com.example.entity.User;
import com.example.mapper.InterestCircleMapper;
import com.example.mapper.InterestCircleMemberMapper;
import com.example.mapper.InterestCirclePostMapper;
import com.example.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InterestCircleMapper interestCircleMapper;

    @Autowired
    private InterestCircleMemberMapper memberMapper;

    @Autowired
    private InterestCirclePostMapper postMapper;

    public User findById(Long id) {
        return userMapper.findById(id);
    }

    public User findByName(String username) {
        return userMapper.findByUsername(username);
    }

    public void saveUser(User user) {
        userMapper.insert(user);
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }

    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public void setUserMapper(UserMapper mockUserMapper) {
        this.userMapper = mockUserMapper;
    }

    public void joinCircle(Long userId, int circleId) {
        User user = userMapper.findById(userId); // Assume findById method is implemented in UserMapper
        InterestCircle circle = interestCircleMapper.getCircleById(circleId); // Assume findById method is implemented in InterestCircleMapper

        // Check if the user is already a member of the circle
        if (memberMapper.existsByUserAndCircle(userId, circleId) != null) {
            throw new IllegalStateException("User is already a member of this circle");
        }

        // Create new member entry
        InterestCircleMember member = new InterestCircleMember();
        member.setUserId(userId);
        member.setCircleId(circleId);
        member.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        memberMapper.save(member); // Assume save method is implemented in InterestCircleMemberMapper
    }

    public void createPost(Long userId, int circleId, String content) {
        User user = userMapper.findById(userId); // Assume findById method is implemented in UserMapper
        InterestCircle circle = interestCircleMapper.getCircleById(circleId); // Assume findById method is implemented in InterestCircleMapper

        InterestCirclePost post = new InterestCirclePost();
        post.setUserId(userId);
        post.setCircleId(circleId);
        post.setContent(content);
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        postMapper.save(post); // Assume save method is implemented in InterestCirclePostMapper
    }
}
