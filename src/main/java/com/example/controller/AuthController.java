package com.example.controller;

import com.example.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import com.example.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

//    @Autowired
//    public HttpSession session; // 注入会话对象

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registerForm";
    }

    // 处理注册请求
    @PostMapping("/register")
    public String processRegistrationForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        // 检查是否存在用户名重复
        if(userService.findByName(user.getUsername()) != null) {
            //bindingResult.rejectValue("username", "error.user", "Username already exists");
            model.addAttribute("failureMessage", "Registration failed! Please try again.");
            return "registerForm"; // 返回注册页面，显示错误信息
        }

        // 如果没有错误，则保存用户
        userService.saveUser(user);
        model.addAttribute("successMessage", "Registration successful! Please login.");

        return "registerSuccess";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user1", new User());
        return "loginForm";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("user1") User user, Model model,HttpSession session) {
        // 查找数据库中是否存在该用户
        User foundUser = userService.findByName(user.getUsername());

        if (foundUser == null || !foundUser.getPassword().equals(user.getPassword())) {
            // 用户不存在或密码不匹配，返回登录页面并显示错误消息
            //model.addAttribute("error", "Invalid username or password");
            return "loginForm";
       }
        model.addAttribute("username", foundUser.getUsername());
        //return "loginSuccess";
        // 用户登录成功，将用户名存储在会话中
        session.setAttribute("user", foundUser);
        session.setAttribute("username", foundUser.getUsername());


        return "redirect:/?loginSuccess=true";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
