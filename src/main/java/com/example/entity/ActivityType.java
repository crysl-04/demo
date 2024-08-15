package com.example.entity;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.RequestEntity.put;

public class ActivityType {
    public static final Map<String, Integer> WEIGHTS = Map.of(
            "POST", 2,
            "COMMENT", 1,
            "LIKE", 1
            // 添加其他活动类型及其权重
    );
}
