package com.example.mappertest;

import com.example.entity.InterestCircleMember;
import com.example.mapper.InterestCircleMemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberMapperTest {

    @Autowired
    private InterestCircleMemberMapper memberMapper;

    @Test
    public void testSaveMember() {
        // 创建一个 InterestCircleMember 对象
        InterestCircleMember member = new InterestCircleMember();
        member.setCircleId(1);
        member.setUserId(3L);
        member.setNickname("JS");

        // 保存 member 到数据库
        memberMapper.save(member);

        // 验证保存后的 member 对象是否有自动生成的 id
        assertNotNull(member.getId());
    }

    @Test
    public void testFindById() {
        // 测试根据 id 查找 member
        InterestCircleMember savedMember = memberMapper.findById(1);
        assertNotNull(savedMember);
        assertEquals(1, savedMember.getId());
    }

    @Test
    public void testFindByNickname() {
        // 测试根据 nickname 查找 member
        InterestCircleMember savedMember = memberMapper.findByNickname("JS");
        assertNotNull(savedMember);
        assertEquals("JS", savedMember.getNickname());
    }

    @Test
    public void testExistsByUserAndCircle() {
        // 测试根据 userId 和 circleId 判断 member 是否存在
        InterestCircleMember member = memberMapper.existsByUserAndCircle(3L, 1);
        assertNotNull(member);
        assertEquals(3L, member.getId());
        assertEquals(1, member.getCircleId());
    }
}
