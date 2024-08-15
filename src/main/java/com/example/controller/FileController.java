//package com.example.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.CacheControl;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Controller
//public class FileController {
//
//    @Autowired
//    private ResourceHttpRequestHandler resourceHttpRequestHandler;
//
//    @GetMapping("/uploads/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {
//        Path file = Paths.get("src/main/resources/static/uploads/").resolve(filename);
//        Resource resource = new UrlResource(file.toUri());
//
//        if (resource.exists() || resource.isReadable()) {
//            return ResponseEntity.ok()
//                    .cacheControl(CacheControl.noStore()) // 禁用缓存
//                    .body(resource);
//        } else {
//            throw new RuntimeException("Could not read file: " + filename);
//        }
//    }
//}
