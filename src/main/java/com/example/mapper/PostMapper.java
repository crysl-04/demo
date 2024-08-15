package com.example.mapper;

import com.example.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {
    // 获取兴趣圈的所有帖子
    @Select("SELECT * FROM post WHERE circle_id = #{circleId}")
    List<Post> findByCircleId(int circleId);

    @Select("SELECT * FROM post WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "imageUrl", column = "image_url")  // 确保映射正确
    })
    Post findById(int id);

    @Insert("INSERT INTO post(title, content, created_at, circle_id, image_url,user_id) " +
            "VALUES(#{title}, #{content}, #{createdAt}, #{circleId}, #{imageUrl},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Post post);

    // 获取帖子的用户
    @Select("SELECT user_id FROM post WHERE id = #{postId}")
    Long findUserIdByPostId(int postId);

    // 新增的方法
    @Select("SELECT circle_id FROM post WHERE id = #{postId}")
    Integer findCircleIdByPostId(@Param("postId") int postId);

    @Select("SELECT p.* FROM post p " +
            "JOIN posttag pt ON p.id = pt.post_id " +
            "WHERE pt.tag_id = #{tagId}")
    List<Post> findPostsByTagId(int tagId);
}
