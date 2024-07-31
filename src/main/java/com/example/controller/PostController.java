package com.example.controller;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.service.CommentService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.service.PostService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @GetMapping("/circles/{circleId}/posts")
    public String getPosts(@PathVariable int circleId, Model model) {
        model.addAttribute("posts", postService.getPostsByCircleId(circleId));
        return "posts";
    }

    @GetMapping("/posts/{postId}")
    public String getPost(@PathVariable int postId, Model model) {
        model.addAttribute("post", postService.getPostById(postId));
        model.addAttribute("comments", commentService.getCommentsByPostId(postId));
        return "post_detail";
    }

    @PostMapping("/posts/{postId}/comments")
    public String addComment(@PathVariable int postId,
                             @RequestParam("content") String content,
                              HttpSession session) {
        String username = (String) session.getAttribute("username");
        Long userId = userService.getUserIdByUsername(username);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setCreatedAt(LocalDateTime.now());
        commentService.addComment(comment);

        return "redirect:/posts/" + postId;
    }

    @GetMapping("/circles/{circleId}/posts/new")
    public String newPostForm(@PathVariable int circleId, Model model) {
        model.addAttribute("circleId", circleId);
        return "new_post";
    }



    @PostMapping("/circles/{circleId}/posts")
    public String createPost(@PathVariable int circleId,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        // 创建一个新的 Post 实例
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCircleId(circleId);
        post.setCreatedAt(LocalDateTime.now());

        try {
            // 调用服务层保存帖子并处理图片
            postService.savePost(post, imageFile);
        } catch (IOException e) {
            // 处理文件上传错误
            e.printStackTrace();
            // 可以添加一个错误消息到模型或者重定向到一个错误页面
            return "error"; // 假设有一个 error 页面
        }

        // 成功后重定向到圈子详情页面
        return "redirect:/circles/" + circleId;
    }


}
