//package com.example.service;
//
//import com.example.mapper.UserActivityMapper;
//import com.example.entity.UserActivity;
//import com.example.entity.UserActivityDetail;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class UserActivityServiceTest {
//
//    @Mock
//    private UserActivityMapper userActivityMapper;
//
//    @InjectMocks
//    private UserActivityService userActivityService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetActivityDetailsByCircleId() {
//        Long circleId = 1L;
//
//        // Mock data
//        List<UserActivity> mockActivities = Arrays.asList(
//                new UserActivity(1L, circleId, "POST_CREATE", null),
//                new UserActivity(2L, circleId, "COMMENT_CREATE", null)
//        );
//
//        // Mock the behavior of the mapper
//        when(userActivityMapper.getActivitiesByCircleId(circleId)).thenReturn(mockActivities);
//
//        // Call the service method
//        List<UserActivityDetail> result = userActivityService.getActivityDetailsByCircleId(circleId);
//
//        // Validate the result
//        assertEquals(2, result.size());
//        assertEquals(10, result.get(0).getScore()); // Assuming POST_CREATE score is 10
//        assertEquals(5, result.get(1).getScore()); // Assuming COMMENT_CREATE score is 5
//    }
//}
