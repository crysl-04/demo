package com.example.mapper;

import com.example.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO comment (content, created_at, post_id, user_id) " +
            "VALUES (#{comment.content}, #{comment.createdAt}, #{comment.postId}, #{comment.userId})")
    void insertComment(@Param("comment") Comment comment);

    @Select("SELECT id, content, created_at as createdAt, post_id as postId, user_id as userId " +
            "FROM comment WHERE post_id = #{postId}")
    List<Comment> getCommentsByPostId(@Param("postId") int postId);

    @Select("SELECT * FROM comment WHERE post_id IN (SELECT id FROM post WHERE circle_id = #{circleId})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "postId", column = "post_id"),
            @Result(property = "userId", column = "user_id")
    })
    List<Comment> findByCircleId(int circleId);

    @Select("SELECT user_id FROM comment WHERE id = #{commentId}")
    Long findUserIdByCommentId(@Param("commentId") long commentId);

    @Select("SELECT * FROM comment WHERE post_id = #{postId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "postId", column = "post_id"),
            @Result(property = "userId", column = "user_id"),
    })
    List<Comment> findByPostId(int postId);


}
