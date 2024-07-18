//package com.example;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//public class RedisConnectionTest {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Test
//    public void testRedisConnection() {
//        // 测试Redis连接
//        boolean connected = redisTemplate.getConnectionFactory().getConnection().isPipelined();
//        assertTrue(connected, "Redis连接成功");
//    }
//}
