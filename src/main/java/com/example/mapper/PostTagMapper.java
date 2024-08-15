package com.example.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface PostTagMapper {

    @Insert("INSERT INTO PostTag(post_id, tag_id) VALUES(#{postId}, #{tagId})")
    void insertPostTag(@Param("postId") int postId, @Param("tagId") int tagId);

    @Delete("DELETE FROM PostTag WHERE post_id = #{postId}")
    void deletePostTagsByPostId(int postId);
}
