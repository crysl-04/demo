package com.example.controller;

import com.example.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * 显示注册页面
     * @param model 模型对象，用于传递数据到视图
     * @return 注册页面模板名称
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // 添加新用户对象到模型
        return "registerForm"; // 返回注册页面模板名称
    }

    /**
     * 处理注册请求
     * @param user 用户对象，包含注册信息
     * @param model 模型对象，用于传递数据到视图
     * @return 视图名称
     */
    @PostMapping("/register")
    public String processRegistrationForm(@Valid @ModelAttribute("user") User user, Model model) {
        // 检查是否存在用户名重复
        if(userService.findByName(user.getUsername()) != null) {
            model.addAttribute("failureMessage", "Registration failed! Please try again.");
            return "registerForm"; // 返回注册页面，显示错误信息
        }

        // 如果没有错误，则保存用户
        userService.saveUser(user);

        return "registerSuccess";
    }

    /**
     * 显示登录页面
     * @param model 模型对象，用于传递数据到视图
     * @return 登录页面模板名称
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user1", new User());
        return "loginForm";
    }

    /**
     * 处理登录请求
     * @param user 用户对象，包含登录信息
//     * @param model 模型对象，用于传递数据到视图
     * @param session HttpSession 对象，用于管理会话
     * @return 视图名称
     */
    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("user1") User user,HttpSession session) {
        // 查找数据库中是否存在该用户
        User foundUser = userService.findByName(user.getUsername());

        if (foundUser == null || !foundUser.getPassword().equals(user.getPassword())) {
            // 用户不存在或密码不匹配，返回登录页面并显示错误消息
            return "loginForm";
       }
        // 用户登录成功，将用户名存储在会话中
        session.setAttribute("user", foundUser);
        session.setAttribute("username", foundUser.getUsername());
        session.setAttribute("userid", foundUser.getId());

        return "redirect:/?loginSuccess=true";
    }

    /**
     * 退出登录
     * @param session HttpSession 对象，用于管理会话
     * @return 重定向到主页
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
