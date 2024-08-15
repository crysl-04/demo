package com.example.controller;

import com.example.entity.Comment;
import com.example.entity.InterestCircleMember;
import com.example.entity.Post;
import com.example.entity.Tag;
import com.example.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private InterestCircleService circleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/circles/{circleId}/posts")
    public String getPosts(@PathVariable int circleId, Model model) {
        model.addAttribute("posts", postService.getPostsByCircleId(circleId));
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable int id, Model model) {
        Post post = postService.getPostById(id);
        List<Comment> comments = commentService.getCommentsByPostId(id);
//        System.out.println(comments);
//        System.out.println("Post Title: " + post.getTitle());
//        System.out.println("Post Content: " + post.getContent());
//        System.out.println("Post Image URL: " + post.getImageUrl());
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        return "post_detail";
    }


    @PostMapping("/posts/{postId}/comments")
    public String addComment(@PathVariable int postId,
                             @RequestParam("content") String content,
                              HttpSession session,RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        System.out.println("username: " + username);
        if(username == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "需要登录才能发表评论！");
            return "redirect:/posts/" + postId;
        }

        Long userId = userService.getUserIdByUsername(username);
        Post post = postService.getPostById(postId);
        //int circleId = post.getCircleId();
        int circleId = postService.getCircleIdByPostId(postId);
        //System.out.println("postId: "+postId + " circleId: " + circleId );
        InterestCircleMember member = circleService.findByUserIdAndCircleId(userId, circleId);

        if(member == null) {
            System.out.println("member is null");
        }
        else{
            String membername = circleService.findByUserIdAndCircleId(userId,circleId).getNickname();
            System.out.println("userId = " + userId + "membername = " + membername);
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setPostId(postId);
            comment.setUserId(userId);
            comment.setCreatedAt(LocalDateTime.now());
            comment.setMembername(membername);
            commentService.addComment(comment);
        }


        return "redirect:/posts/" + postId;
    }

    @GetMapping("/circles/{circleId}/posts/new")
    public String newPostForm(@PathVariable int circleId, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // 如果用户未登录，设置提示信息并重定向到圈子列表页面
            redirectAttributes.addFlashAttribute("errorMessage", "需要登录才能创建帖子！");
            return "redirect:/circles/"+ circleId +  "?tab=posts";
        }

        List<Tag> tags = tagService.getAllTagsByCircleId(circleId);  // 获取所有可用的标签
        model.addAttribute("circleId", circleId);
        model.addAttribute("tags", tags);  // 将标签列表添加到模型中
        return "new_post";
    }

    @PostMapping("/circles/{circleId}/posts")
    public String createPost(@PathVariable int circleId,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam(value = "image", required = false) MultipartFile imageFile,
                             @RequestParam(value = "tagIds", required = false) List<Integer> tagIds,

                             HttpSession session) throws IOException {



        // 创建一个新的 Post 实例
        String username = session.getAttribute("username").toString();
        Long userId = userService.getUserIdByUsername(username);
        //System.out.println("username: "+username);
        //System.out.println("userId: "+userId);
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCircleId(circleId);
        post.setCreatedAt(LocalDateTime.now());
        post.setUserId(userId);

        try {
            // 调用服务层保存帖子并处理图片
            postService.savePost(post, imageFile);

            // 打印调试信息，确保在保存后URL依然存在
            //System.out.println("Post created with image URL: " + post.getImageUrl());
        } catch (IOException e) {
            // 处理文件上传错误
            e.printStackTrace();
            // 可以添加一个错误消息到模型或者重定向到一个错误页面
            return "error"; // 假设有一个 error 页面
        }

        // 为帖子分配标签
        if (tagIds != null && !tagIds.isEmpty()) {
            tagService.assignTagsToPost(post.getId(), tagIds);
        }

        // 成功后重定向到帖子展区
        return "redirect:/circles/" + circleId +  "?tab=posts";
    }

    @GetMapping("/circles/{circleId}/tags")
    public String getTagsByCircle(@PathVariable int circleId, Model model) {
        List<Tag> tags = tagService.getTagsByCircleId(circleId);
        model.addAttribute("tags", tags);
        return "tags";
    }

    @GetMapping("/tags/{tagId}/posts")
    public String getPostsByTag(@PathVariable int tagId, Model model) {
        List<Post> posts = tagService.getPostsByTagId(tagId);
        model.addAttribute("posts", posts);
        return "posts_by_tag";
    }

}
