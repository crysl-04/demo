package com.example.mapper;

import com.example.entity.InterestCirclePost;
import org.apache.ibatis.annotations.*;

@Mapper
public interface InterestCirclePostMapper {
    @Select("SELECT * FROM interest_circle_post WHERE id = #{id}")
    InterestCirclePost findById(@Param("id") int id);

    @Insert("INSERT INTO interest_circle_post (circle_id, user_id, content, created_at) " +
            "VALUES (#{circleId}, #{userId}, #{content}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(InterestCirclePost post);

}
