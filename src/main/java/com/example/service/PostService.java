package com.example.service;

import com.example.entity.Post;
import com.example.entity.UserActivity;
import com.example.mapper.InterestCircleMemberMapper;
import com.example.mapper.PostMapper;
import com.example.mapper.UserActivityMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private InterestCircleMemberMapper interestCircleMemberMapper;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserActivityMapper userActivityMapper;

    private Long getCurrentUserId() {
        // 从 HttpSession 获取当前用户的 ID
        Long userId = (Long) httpSession.getAttribute("userid");
        if (userId == null) {
            throw new RuntimeException("User not logged in.");
        }
        return userId;
    }

    public List<Post> getPostsByCircleId(int circleId) {
        return postMapper.findByCircleId(circleId);
    }

    public Post getPostById(int postId) {
        Post post = postMapper.findById(postId);
        //System.out.println("Fetched Post: " + post);
        //System.out.println("URL: " + post.getImageUrl());
        return post;
    }

    public void savePost(Post post, MultipartFile imageFile) throws IOException {
        Long userId = getCurrentUserId();

        // 检查用户是否在兴趣圈中
        if (!interestCircleMemberMapper.isUserInCircle(userId, post.getCircleId())) {
            throw new RuntimeException("You must join the interest circle to post.");
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            // 使用唯一标识符来生成文件名
            String originalFilename = imageFile.getOriginalFilename();

            // 替换非字母和数字字符为下划线
            String sanitizedFilename = originalFilename.replaceAll("[^a-zA-Z0-9\\.]", "_");

            // 使用唯一标识符来生成文件名
//            String filename = System.currentTimeMillis() + "_" + sanitizedFilename;
            String filename = sanitizedFilename;

            String relativePath = "/uploads/" + filename;
            Path path = Paths.get("src/main/resources/static/uploads/" + filename);

            // 确保保存路径存在
            Files.createDirectories(path.getParent());

            // 保存文件到磁盘
            Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // 设置帖子的图片 URL
            post.setImageUrl(relativePath);


        } else {
            post.setImageUrl(null);
        }

        // 保存帖子到数据库
        postMapper.save(post);

        // 创建用户活动记录
        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(userId);
        userActivity.setCircleId(post.getCircleId());
        userActivity.setActivityType("POST_CREATE");
        userActivity.setActivityTime(new Timestamp(System.currentTimeMillis()));

        userActivityMapper.insertUserActivity(userActivity);
    }

    // 新增的方法
    public Integer getCircleIdByPostId(int postId) {
        return postMapper.findCircleIdByPostId(postId);
    }

    public List<Post> getPostsByTagId(int tagId) {
        return postMapper.findPostsByTagId(tagId);
    }
}
