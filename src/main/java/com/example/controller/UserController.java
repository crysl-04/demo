/*处理与用户数据操作相关的请求，包括获取用户信息、创建用户、更新用户、删除用户等*/

package com.example.controller;

import com.example.dto.ApiResponse;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ApiResponse getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if(user == null){
            return new ApiResponse("404",false,"User not found",null);
        }
        return new ApiResponse("200",true,"User retrieved successfully",user);
    }

    @GetMapping("/username/{username}")
    public ApiResponse getUserByUsername(@RequestParam("name") String username) {
        User user = userService.findByName(username);
        if(user == null){
            return new ApiResponse("404",false,"User not found",null);
        }
        return new ApiResponse("200",true,"User retrieved successfully",user);
    }

    @PostMapping
    public ApiResponse createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ApiResponse("200",true,"User created successfully",user);
    }

    @PutMapping("/{id}")
    public ApiResponse updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return new ApiResponse("200",true,"User updated successfully",user);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ApiResponse("200",true,"User deleted successfully",null);
    }

    @GetMapping
    public ApiResponse getAllUsers() {
        List<User> users = userService.findAll();
        return new ApiResponse("200",true,"Users retrieved successfully",users);
    }
}
