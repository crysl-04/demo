//package com.example.controller;
//
//import com.example.entity.Tag;
//import com.example.service.TagService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class TagController {
//
//    @Autowired
//    private TagService tagService;
//
//    // 显示创建标签的页面
//    @GetMapping("/new")
//    public String showCreateTagForm(Model model) {
//        model.addAttribute("tag", new Tag());
//        return "tag_create"; // 这里指向你用于创建标签的HTML页面
//    }
//
////    // 处理创建标签的表单提交
////    @PostMapping("/create")
////    public String createTag(@ModelAttribute Tag tag) {
////        tagService.createTag(tag);
////        return "redirect:/tags"; // 创建完成后重定向到标签列表或其他页面
////    }
//}
//
