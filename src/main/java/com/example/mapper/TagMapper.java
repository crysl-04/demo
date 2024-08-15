package com.example.mapper;

import com.example.entity.Tag;
import com.example.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {

    // 插入标签
    @Insert("INSERT INTO tag (name, circle_id) VALUES (#{name}, #{circleId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertTag(Tag tag);

    @Select("SELECT * FROM Tag WHERE circle_id = #{circleId}")
    List<Tag> getTagsByCircleId(int circleId);

    @Select("SELECT p.* FROM Post p " +
            "INNER JOIN PostTag pt ON p.id = pt.post_id " +
            "WHERE pt.tag_id = #{tagId}")
    List<Post> getPostsByTagId(int tagId);


    @Select("SELECT * FROM Tag WHERE id = #{tagId}")
    Tag findTagById(int tagId);
}
