package com.example.mapper;

import com.example.entity.Album;
import com.example.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AlbumMapper {

    @Select("SELECT * FROM album WHERE circle_id = #{circleId} ORDER BY description DESC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "circleId", column = "circle_id"),
            @Result(property = "imageUrl", column = "image_url"),
            @Result(property = "description", column = "description")
            // 如果有其他字段，请添加它们的映射
    })    List<Album> getAlbumsByCircleId(int circleId);


    @Insert("INSERT INTO album (name, circle_id,description,image_url) VALUES (#{name}, #{circleId},#{description},#{imageUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertAlbum(Album album);

    @Select("SELECT * FROM album WHERE id = #{id}")
    Album getAlbumById(int id);
}
