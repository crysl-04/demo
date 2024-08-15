package com.example.mappertest;

import static org.junit.jupiter.api.Assertions.*;

import com.example.entity.UserActivity;
import com.example.mapper.UserActivityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.sql.Timestamp;
import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserActivityMapperTest {

    @Autowired
    private UserActivityMapper userActivityMapper;

    @BeforeEach
    public void setUp() {
        // 清除表中的数据
        userActivityMapper.deleteAllUserActivities();

        // 在测试数据库中插入一些测试数据
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        userActivityMapper.insertUserActivity(new UserActivity(40, 2, "POST_CREATE", timestamp));
        userActivityMapper.insertUserActivity(new UserActivity(40, 2, "POST_CREATE", timestamp));
        userActivityMapper.insertUserActivity(new UserActivity(40, 2, "COMMENT_CREATE", timestamp));

        // 打印插入后的数据
        List<UserActivity> userActivities = userActivityMapper.getUserActivities();
        for (UserActivity userActivity : userActivities) {
            //System.out.println(userActivity);
        }
    }

    @Test
    public void testGetActivitiesByCircleId() {
        int circleId = 2;
        List<UserActivity> activities = userActivityMapper.getActivitiesByCircleId(circleId);
        assertNotNull(activities);
        assertEquals(3, activities.size());

        // 打印活动列表
        for (UserActivity activity : activities) {
            System.out.println(activity);
        }

        // 验证活动类型
        assertEquals("POST_CREATE", activities.get(0).getActivityType());
        assertEquals("POST_CREATE", activities.get(1).getActivityType());
        assertEquals("COMMENT_CREATE", activities.get(2).getActivityType());
    }
}
