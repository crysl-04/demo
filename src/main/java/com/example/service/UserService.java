package com.example.service;

import com.example.entity.User;
import com.example.mapper.InterestCircleMapper;
import com.example.mapper.InterestCircleMemberMapper;
import com.example.mapper.InterestCirclePostMapper;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

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
}
