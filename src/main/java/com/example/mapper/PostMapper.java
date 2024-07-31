package com.example.mapper;

import com.example.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT * FROM post WHERE circle_id = #{circleId}")
    List<Post> findByCircleId(int circleId);

    @Select("SELECT * FROM post WHERE id = #{id}")
    Post findById(int id);

    @Insert("INSERT INTO post(title, content, created_at, circle_id, image_url) " +
            "VALUES(#{title}, #{content}, #{createdAt}, #{circleId}, #{imageUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Post post);
}
