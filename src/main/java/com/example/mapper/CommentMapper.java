package com.example.mapper;

import com.example.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM comment WHERE post_id = #{postId}")
    List<Comment> findByPostId(int postId);

    @Insert("INSERT INTO comment(content, created_at, post_id) " +
            "VALUES(#{content}, #{createdAt}, #{postId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Comment comment);
}
