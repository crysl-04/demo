package com.example.mapper;

import com.example.entity.InterestCircle;
import com.example.entity.InterestCircleMember;
//import com.example.entity.InterestCirclePost;
import com.example.entity.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    @Insert("INSERT INTO interest_circle_member (circle_id, user_id,nickname) VALUES (#{circleId}, #{userId},#{nickname})")
    void addMember(InterestCircleMember member);

    @Insert("INSERT INTO interest_circle_post (circle_id, nickname, title, content) VALUES (#{circleId}, #{nickname},#{title}, #{content})")
    void addPost(Post post);

    @Select("SELECT nickname From interest_circle_member WHERE circle_id = #{circleId}")
    List<String> findNicknameByCircle(@Param("circleId") int circleId);

    @Select("SELECT * FROM interest_circle_member WHERE circle_id = #{circleId}")
    List<InterestCircleMember> getMembersByCircleId(@Param("circleId") int circleId);

    @Select("SELECT * FROM interest_circle_post WHERE circle_id = #{circleId}")
    //List<InterestCirclePost> getPostsByCircleId(@Param("circleId") int circleId);
    List<Post> getPostsByCircleId(@Param("circleId") int circleId);

    @Select("SELECT c.* FROM interest_circle c " +
            "JOIN circle_artist ca ON c.id = ca.circle_id " +
            "WHERE ca.artist_id = #{artistId}")
    List<InterestCircle> getCirclesByArtistId(Long artistId);


//    @Select("SELECT * FROM interest_circle_member WHERE circle_id = #{circleId}")
//    Page<String> getMembersByCircleId(@Param("circleId") int circleId, Pageable pageable);
//
//    @Select("SELECT * FROM interest_circle_post WHERE circle_id = #{circleId}")
//    Page<InterestCirclePost> getPostsByCircleId(@Param("circleId") int circleId, Pageable pageable);

//    Object getPostsByCircleId(int circleId, int page, int size);
}
