package com.example.controller;

import com.example.entity.InterestCircle;
import com.example.entity.InterestCircleMember;
import com.example.entity.InterestCirclePost;
import com.example.entity.User;
import com.example.service.InterestCircleService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CircleController {

    @Autowired
    private InterestCircleService interestCircleService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private HttpSession session; // 注入会话对象

    @GetMapping("/circles")
    public String getAllCircles(HttpSession session, Model model) {
        List<InterestCircle> circles = interestCircleService.getAllCircles();
        String username = (String) session.getAttribute("username");
        if(username != null){
            model.addAttribute("username", username);
        }
        model.addAttribute("circles", circles);
        return "circles";
    }

    @PostMapping("/circles/create")
    public String createCircle(@RequestParam String circleName, @RequestParam String description) {
        InterestCircle newCircle = new InterestCircle();
        newCircle.setName(circleName);
        newCircle.setDescription(description);
        interestCircleService.addCircle(newCircle);
        return "redirect:/circles";
    }

    @GetMapping("/circles/edit/{id}")
    public String editCircle(@PathVariable int id, Model model) {
        InterestCircle circle = interestCircleService.getCircleById(id);
        model.addAttribute("circle", circle);
        return "edit_circle";
    }

    @PostMapping("/circles/update")
    public String updateCircle(@RequestParam int id, @RequestParam String circleName, @RequestParam String description) {
        InterestCircle circle = new InterestCircle();
        circle.setId(id);
        circle.setName(circleName);
        circle.setDescription(description);
        interestCircleService.updateCircle(circle);
        return "redirect:/circles";
    }

    @GetMapping("/circles/{id}")
    public String getCircleDetail(@PathVariable("id") int circleId, Model model) {
        InterestCircle circle = interestCircleService.getCircleById(circleId);
        List<String> members = interestCircleService.getAllNicknames(circleId);
        List<InterestCirclePost> posts = interestCircleService.getPostsByCircleId(circleId);
        model.addAttribute("circle", circle);
        model.addAttribute("members", members);
        model.addAttribute("posts", posts);
        return "circle_detail";
    }

    @GetMapping("/circles/joinForm/{circleId}")
    public String showJoinForm(@PathVariable("circleId") Long circleId, Model model) {
        model.addAttribute("circleId", circleId);
        return "joinForm";
    }

    @PostMapping("/circles/{id}/join")
    public String joinCircle(@PathVariable("id") int circleId, @RequestParam("nickname") String nickname, HttpSession session) {
        InterestCircleMember newMember = new InterestCircleMember();
        newMember.setNickname(nickname);
        newMember.setCircleId(circleId);

        String username = session.getAttribute("username").toString();
        Long userId = userService.findByName(username).getId();
        newMember.setUserId(userId);

        interestCircleService.addMember(newMember);

        // 重定向到对应兴趣圈的详情页面
        return "redirect:/circles/" + circleId;
    }

//
//    @GetMapping("/circles/{id}/createMember")
//    public String createMember(@PathVariable("id") int circleId, Model model,HttpSession session) {
//        model.addAttribute("member", new InterestCircleMember());
//        return "joinForm";
//    }
////
//    @PostMapping("/circles/{id}/createMember")
//    public String createMember(@ModelAttribute("member") InterestCircleMember member,@PathVariable("id") int circleId, HttpSession session, Model model) {
//        // 检查是否存在用户名重复
//        if(interestCircleService.findByNickname(member.getNickname()) != null) {
//            //bindingResult.rejectValue("username", "error.user", "Username already exists");
//            model.addAttribute("failureMessage", "Registration failed! Please try again.");
//            return "joinForm"; // 返回注册页面，显示错误信息
//        }
//
//        // 如果没有错误，则保存用户
//        interestCircleService.addMember(member);
//        model.addAttribute("successMessage", "Registration successful! Please login.");
//
//        return "redirect:/circles/{id}";
//
//    }



    @PostMapping("/{circleId}/post")
    public String createPost(@PathVariable int circleId, @RequestParam String content, HttpSession session) {
//        // Retrieve the current user's ID from the session
//        Long userId = (Long) session.getAttribute("userId"); // Assuming you have stored the userId in the session
//        if (userId == null) {
//            // If the user is not logged in, handle the situation accordingly, such as redirecting to the login page
//            return "redirect:/login";
//        }
//
//        // Create a new post using the current user's ID, circle ID, and provided content
//        userService.createPost(userId, circleId, content);
//
//        // Redirect to the circles page after creating the post successfully
        return "redirect:/circles";
    }

}
