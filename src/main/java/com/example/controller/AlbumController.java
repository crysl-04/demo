package com.example.controller;

import com.example.entity.Album;
import com.example.mapper.AlbumMapper;
import com.example.service.AlbumService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.io.IOException;

@Controller
@RequestMapping("/circles/{circleId}/albums")
public class AlbumController {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserService userService;

    // 处理创建专辑的表单提交
    @PostMapping("/save")
    public String saveAlbum(
            @PathVariable("circleId") int circleId,
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("description") String description,
            Model model) {
        try {
            // 创建专辑对象并设置属性
            Album album = new Album();
            album.setName(name);
            album.setCircleId(circleId);
            album.setDescription(description);

            // 调用 AlbumService 来处理专辑的保存和图片上传
            albumService.saveAlbum(album, imageFile);


            // 重定向到专辑列表页面
            return "redirect:/circles/" + circleId+ "?tab=albums";

        } catch (IOException e) {
            // 处理文件上传时的异常
            model.addAttribute("error", "Error saving album: " + e.getMessage());
            return "redirect:/circles/" + "?tab=albums"; // 如果出错，返回创建专辑表单并显示错误信息
        }
    }
}
