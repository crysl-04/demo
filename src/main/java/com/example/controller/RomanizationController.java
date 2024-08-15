//package com.example.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import com.example.service.RomanizationService;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class RomanizationController {
//
//    @Autowired
//    private RomanizationService romanizationService;
//
//    @PostMapping("/romanize")
//    public Map<String, String> romanize(@RequestBody Map<String, String> request) {
//        String originalLyrics = request.get("original_lyrics");
//        String romanizedLyrics = romanizationService.romanizeKorean(originalLyrics);
//        return Map.of("pinyin_lyrics", romanizedLyrics);
//    }
//}
