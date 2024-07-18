package com.example.servicetest;

import com.example.entity.InterestCircleMember;
import com.example.service.InterestCircleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class MemberServiceTest {

    @Mock
    private InterestCircleService interestCircleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // 初始化Mock对象
    }

    @Test
    public void testAddMember() {
        // 准备测试数据
        InterestCircleMember member = new InterestCircleMember();
        member.setCircleId(1);
        member.setUserId(3L);
        member.setNickname("JS");

        // Mock掉InterestCircleService的addMember方法
        doNothing().when(interestCircleService).addMember(member);

        // 调用InterestCircleService的addMember方法
        interestCircleService.addMember(member);

        // 验证addMember方法是否被调用了一次，并且传入的参数是预期的member对象
        verify(interestCircleService).addMember(member);
    }
}
