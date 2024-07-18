package com.example.mapper;

import com.example.entity.InterestCircleMember;
import org.apache.ibatis.annotations.*;

@Mapper
public interface InterestCircleMemberMapper {
    @Select("SELECT * FROM interest_circle_member WHERE id = #{id}")
    InterestCircleMember findById(@Param("id") int id);

    @Select("SELECT * FROM interest_circle_member WHERE nickname = #{nickname}")
    InterestCircleMember findByNickname(@Param("nickname") String nickname);

    @Select("SELECT * FROM interest_circle_member WHERE user_id = #{userId} AND circle_id = #{circleId}")
    InterestCircleMember existsByUserAndCircle(@Param("userId") Long userId, @Param("circleId") int circleId);

    @Insert("INSERT INTO interest_circle_member (circle_id, user_id, nickname) " +
            "VALUES (#{circleId}, #{userId},#{nickname})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(InterestCircleMember member);
}
