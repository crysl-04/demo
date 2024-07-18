package com.example.servicetest;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    private UserService userService;
    private UserMapper userMapper;
    @BeforeEach
    public void setUp() {
        userMapper = Mockito.mock(UserMapper.class);
        userService = new UserService();
        userService.setUserMapper(userMapper);
    }

    @Test
    public void testFindById(){
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("admin");
        mockUser.setPassword("123456");
        mockUser.setEmail("admin@example.com");
        //当调用 userService.findById(1L) 方法时，实际上并不会真正访问数据库执行查询操作，而是直接返回预先定义好的 mockUser 对象
        Mockito.when(userMapper.findById(1L)).thenReturn(mockUser);

        User foundUser = userService.findById(1L);

        Assertions.assertEquals("admin", foundUser.getUsername());
    }

    @Test
    public void testFindByName(){
        User mockUser = new User();
        mockUser.setId(2L);
        mockUser.setUsername("admin2");
        mockUser.setPassword("123456");
        mockUser.setEmail("admin2@example.com");
        Mockito.when(userMapper.findByUsername("admin2")).thenReturn(mockUser);

        User foundUser = userService.findByName("admin2");

        Assertions.assertEquals("admin2", foundUser.getUsername());
    }

    @Test
    public void testSaveUser(){
        User newUser = new User();
        newUser.setUsername("admin3");

        userService.saveUser(newUser);

        //确认在测试过程中，服务方法中的数据操作确实执行了预期的数据库操作
        Mockito.verify(userMapper,Mockito.times(1)).insert(newUser);

    }

    @Test
    public void testUpdateUser(){
        User updateUser = new User();
        updateUser.setId(1L);
        updateUser.setUsername("admin4");

        userService.updateUser(updateUser);

        Mockito.verify(userMapper,Mockito.times(1)).update(updateUser);

    }

    @Test
    public void testDeleteUser(){
        userService.deleteUser(1L);

        Mockito.verify(userMapper,Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testFindAll(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1","123456","user1@example.com"));
        userList.add(new User("user2","123456","user2@example.com"));
        Mockito.when(userMapper.findAll()).thenReturn(userList);

        List<User> foundUserList = userService.findAll();

        Assertions.assertEquals(2, foundUserList.size());
        Assertions.assertEquals("user1", foundUserList.get(0).getUsername());
        Assertions.assertEquals("user2", foundUserList.get(1).getUsername());

    }
}
