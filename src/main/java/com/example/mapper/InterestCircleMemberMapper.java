package com.example.mapper;

import com.example.entity.InterestCircleMember;
//import com.example.entity.InterestCirclePost;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

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

    @Select("SELECT * FROM interest_circle_member WHERE user_id = #{userId} AND circle_id = #{circleId}")
    InterestCircleMember findByUserIdAndCircleId(@Param("userId") Long userId, @Param("circleId") int circleId);

    @Select("SELECT COUNT(*) > 0 FROM interest_circle_member WHERE user_id = #{userId} AND circle_id = #{circleId}")
    boolean isUserInCircle(Long userId, Integer circleId);


    @Select("SELECT * FROM interest_circle_member WHERE circle_id = #{circleId}")
    List<InterestCircleMember> findMembersByCircleId(@Param("circleId") int circleId);



}
