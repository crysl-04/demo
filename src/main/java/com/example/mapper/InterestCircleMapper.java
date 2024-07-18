package com.example.mapper;

import com.example.entity.InterestCircle;
import com.example.entity.InterestCircleMember;
import com.example.entity.InterestCirclePost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InterestCircleMapper {
    @Select("SELECT * FROM interest_circle")
    List<InterestCircle> getAllCircles();

    @Select("SELECT * FROM interest_circle WHERE id = #{id}")
    InterestCircle getCircleById(int id);

    @Insert("INSERT INTO interest_circle (name, description) VALUES (#{name}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addCircle(InterestCircle circle);

    @Update("UPDATE interest_circle SET name = #{name}, description = #{description} WHERE id = #{id}")
    void updateCircle(InterestCircle circle);

    @Select("SELECT * FROM interest_circle_member WHERE circle_id = #{circleId}")
    List<InterestCircleMember> getMembersByCircleId(int circleId);

    @Select("SELECT * FROM interest_circle_post WHERE circle_id = #{circleId}")
    List<InterestCirclePost> getPostsByCircleId(int circleId);

    @Insert("INSERT INTO interest_circle_member (circle_id, user_id,nickname) VALUES (#{circleId}, #{userId},#{nickname})")
    void addMember(InterestCircleMember member);

    @Insert("INSERT INTO interest_circle_post (circle_id, user_id, content) VALUES (#{circleId}, #{userId}, #{content})")
    void addPost(InterestCirclePost post);

    @Select("SELECT nickname From interest_circle_member WHERE circle_id = #{circleId}")
    List<String> findNicknameByCircle(@Param("circleId") int circleId);
}
