package com.example.service;

import com.example.entity.Post;
import com.example.mapper.PostMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    private final Path uploadDirectory = Paths.get("uploads");

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Post> getPostsByCircleId(int circleId) {
        return postMapper.findByCircleId(circleId);
    }

    public Post getPostById(int postId) {
        return postMapper.findById(postId);
    }

    public void savePost(Post post, MultipartFile imageFile) throws IOException {
        // 处理文件上传
        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = imageFile.getOriginalFilename();
            Path filePath = uploadDirectory.resolve(filename);
            imageFile.transferTo(filePath.toFile());
            post.setImageUrl(filename);
        }

        post.setCreatedAt(LocalDateTime.now());
        postMapper.save(post);
    }


}
