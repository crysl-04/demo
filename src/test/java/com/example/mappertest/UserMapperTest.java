package com.example.mappertest;

import com.example.mapper.UserMapper;
import com.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        // 清理数据库
        userMapper.deleteAll();

        // 插入两个用户数据作为测试数据
        user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setEmail("user1@example.com");
        userMapper.insert(user1);

        user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setEmail("user2@example.com");
        userMapper.insert(user2);
    }

    @Test
    public void testFindById() {
        User user = userMapper.findById(user1.getId());
        assertNotNull(user);
        assertEquals("user1", user.getUsername());
    }

    @Test
    public void testFindByUsername() {
        User user = userMapper.findByUsername("user2");
        assertNotNull(user);
        assertEquals("user2@example.com", user.getEmail());
    }

    @Test
    public void testInsert() {
        User newUser = new User();
        newUser.setUsername("user3");
        newUser.setPassword("password3");
        newUser.setEmail("user3@example.com");
        userMapper.insert(newUser);
        User found = userMapper.findById(newUser.getId());
        assertNotNull(found);
        assertEquals("user3", found.getUsername());
    }

    @Test
    public void testUpdate() {
        User user = userMapper.findByUsername("user1");
        user.setEmail("newemail@example.com");
        userMapper.update(user);
        User updatedUser = userMapper.findById(user.getId());
        assertEquals("newemail@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDelete() {
        User user = userMapper.findByUsername("user2");
        userMapper.deleteById(user.getId());
        User deletedUser = userMapper.findById(user.getId());
        assertNull(deletedUser);
    }

    @Test
    public void testFindAll() {
        List<User> users = userMapper.findAll();
        assertEquals(2, users.size());
    }
}
