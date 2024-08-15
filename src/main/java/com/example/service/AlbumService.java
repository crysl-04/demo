package com.example.service;

import com.example.entity.UserActivity;
import com.example.mapper.InterestCircleMemberMapper;
import com.example.mapper.UserActivityMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.mapper.AlbumMapper;
import com.example.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private InterestCircleMemberMapper interestCircleMemberMapper;


    @Autowired
    private UserActivityMapper userActivityMapper;

    @Autowired
    private HttpSession httpSession;

    public List<Album> getAlbumsByCircleId(int circleId) {
        return albumMapper.getAlbumsByCircleId(circleId);
    }

    public void createAlbum(Album album) {
        albumMapper.insertAlbum(album);
    }

    public Album getAlbumById(int id) {
        return albumMapper.getAlbumById(id);
    }

    private Long getCurrentUserId() {
        // 从 HttpSession 获取当前用户的 ID
        Long userId = (Long) httpSession.getAttribute("userid");
        if (userId == null) {
            throw new RuntimeException("User not logged in.");
        }
        return userId;
    }

    public void saveAlbum(Album album, MultipartFile imageFile) throws IOException {
        // 获取当前用户ID
        Long userId = getCurrentUserId();

        // 检查用户是否在兴趣圈中
        if (!interestCircleMemberMapper.isUserInCircle(userId, album.getCircleId())) {
            throw new RuntimeException("You must join the interest circle to create an album.");
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            // 使用唯一标识符来生成文件名
            String originalFilename = imageFile.getOriginalFilename();

            // 替换非字母和数字字符为下划线
            String sanitizedFilename = originalFilename.replaceAll("[^a-zA-Z0-9\\.]", "_");

            // 使用唯一标识符来生成文件名
            String filename = System.currentTimeMillis() + "_" + sanitizedFilename;
//            String filename = sanitizedFilename;


            String relativePath = "/uploads/" + sanitizedFilename;
            Path path = Paths.get("src/main/resources/static/uploads/" + filename);

            // 确保保存路径存在
            Files.createDirectories(path.getParent());

            // 保存文件到磁盘
            Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // 设置专辑的图片 URL
            album.setImageUrl(relativePath);
        } else {
            album.setImageUrl(null);
        }

        // 保存专辑到数据库
        albumMapper.insertAlbum(album);

        // 创建用户活动记录
        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(userId);
        userActivity.setCircleId(album.getCircleId());
        userActivity.setActivityType("ALBUM_CREATE");
        userActivity.setActivityTime(new Timestamp(System.currentTimeMillis()));

        userActivityMapper.insertUserActivity(userActivity);
    }



}

