package com.example.controller;

import com.example.entity.InterestCircle;
import com.example.entity.InterestCircleMember;
import com.example.entity.InterestCirclePost;
import com.example.mapper.InterestCircleMemberMapper;
import com.example.mapper.InterestCirclePostMapper;
import com.example.service.InterestCircleService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CircleController {

    @Autowired
    private InterestCircleService interestCircleService;

    @Autowired
    private UserService userService;
    @Autowired
    private InterestCirclePostMapper interestCirclePostMapper;
    @Autowired
    private InterestCircleMemberMapper interestCircleMemberMapper;

    /**
     * 获取所有兴趣圈列表
     *
     * @param model  用于存储传递到视图的数据
     * @return 视图名称 (circles.html)
     */
    @GetMapping("/circles")
    public String getAllCircles(Model model) {
        List<InterestCircle> circles = interestCircleService.getAllCircles();
        model.addAttribute("circles", circles);
        return "circles";
    }

    /**
     * 创建新的兴趣圈
     *
     * @param circleName 兴趣圈名称
     * @param description 兴趣圈描述
     * @return 重定向到所有兴趣圈列表 (/circles)
     */
    @PostMapping("/circles/create")
    public String createCircle(@RequestParam String circleName, @RequestParam String description) {
        InterestCircle newCircle = new InterestCircle();
        newCircle.setName(circleName);
        newCircle.setDescription(description);
        interestCircleService.addCircle(newCircle);
        return "redirect:/circles";
    }

    /**
     * 获取指定 ID 的兴趣圈详情用于编辑
     *
     * @param id 兴趣圈 ID
     * @param model 用于存储传递到视图的数据
     * @return 视图名称 (edit_circle.html)
     */
    @GetMapping("/circles/edit/{id}")
    public String editCircle(@PathVariable int id, Model model) {
        InterestCircle circle = interestCircleService.getCircleById(id);
        model.addAttribute("circle", circle);
        return "edit_circle";
    }

    /**
     * 更新指定 ID 的兴趣圈信息
     *
     * @param id 兴趣圈 ID
     * @param circleName 兴趣圈名称
     * @param description 兴趣圈描述
     * @return 重定向到所有兴趣圈列表 (/circles)
     */
    @PostMapping("/circles/update")
    public String updateCircle(@RequestParam int id, @RequestParam String circleName, @RequestParam String description) {
        InterestCircle circle = new InterestCircle();
        circle.setId(id);
        circle.setName(circleName);
        circle.setDescription(description);
        interestCircleService.updateCircle(circle);
        return "redirect:/circles";
    }

    /**
     * 获取指定 ID 的兴趣圈详细信息
     *
     * @param circleId 兴趣圈 ID
     * @param model 用于存储传递到视图的数据
     * @return 视图名称 (circle_detail.html)
     */
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
//    @GetMapping("/circles/{id}")
//    public String getCircleDetail(@PathVariable("id") int circleId,
//                                  @RequestParam(defaultValue = "1") int page,
//                                  @RequestParam(defaultValue = "3") int size,
//                                  Model model) {
//        InterestCircle circle = interestCircleService.getCircleById(circleId);
//        Page<InterestCirclePost> postsPage = interestCircleService.getPostsByCircleId(circleId, page, size);
//        Page<String> membersPage = interestCircleService.getMembersByCircleId(circleId, page, size);
//
//        model.addAttribute("circle", circle);
//        model.addAttribute("members", membersPage.getContent()); // 当前页的成员列表
//        model.addAttribute("posts", postsPage.getContent());
//        model.addAttribute("currentMembersPage", membersPage.getNumber() + 1); // 当前成员列表页码（从1开始）
//        model.addAttribute("currentPostsPage", postsPage.getNumber() + 1); // 当前帖子列表页码（从1开始）
//        model.addAttribute("membersPageSize", membersPage.getSize()); // 成员列表每页大小
//        model.addAttribute("postsPageSize", postsPage.getSize()); // 帖子列表每页大小
//        model.addAttribute("totalMembersPages", membersPage.getTotalPages()); // 总成员列表页数
//        model.addAttribute("totalPostsPages", postsPage.getTotalPages()); // 总帖子列表页数
//
//        return "circle_detail";
//    }



    /**
     * 显示加入兴趣圈的表单
     *
     * @param circleId 兴趣圈 ID
     * @param model 用于存储传递到视图的数据
     * @return 视图名称 (joinForm.html)
     */
    @GetMapping("/circles/joinForm/{circleId}")
    public String showJoinForm(@PathVariable("circleId") int circleId, Model model) {
        model.addAttribute("circleId", circleId);
        return "joinForm";
    }

    /**
     * 加入指定的兴趣圈
     *
     * @param circleId 兴趣圈 ID
     * @param nickname 用户昵称
     * @param session 用于获取当前登录用户名 (可根据需求修改)
     * @return 重定向到兴趣圈详情页 (/circles/{id})
     */
    @PostMapping("/circles/{id}/join")
    public String joinCircle(@PathVariable("id") int circleId, @RequestParam("nickname") String nickname, HttpSession session) {
        InterestCircleMember newMember = new InterestCircleMember();
        newMember.setNickname(nickname);
        newMember.setCircleId(circleId);

        String username = session.getAttribute("username").toString();
        Long userId = userService.findByName(username).getId();
        newMember.setUserId(userId);

        interestCircleService.addMember(newMember);

        return "redirect:/circles/" + circleId;
    }

    /**
     * 处理添加帖子请求的方法
     *
     * @param circleId 兴趣圈 ID
     * @param content 帖子内容
     * @return 重定向到兴趣圈详情页 (/circles/{circleId})
     */    @PostMapping("/circles/post/{circleId}")
    public String addPost(@PathVariable int circleId, @RequestParam String content) {
        InterestCirclePost post = new InterestCirclePost();
        post.setCircleId(circleId);
        post.setContent(content);

        interestCircleService.addPost(post);

        return "redirect:/circles/" + circleId;
    }

}
