package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; //使用 return "home" 来返回模板文件名，Spring Boot 会根据配置找到对应的模板文件并渲染
    }
}
