package com.example.mapper;

import com.example.entity.Artist;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArtistMapper {
    @Select("SELECT * FROM artist")
    List<Artist> getAllArtists();

    @Insert("INSERT INTO artist(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertArtist(Artist artist);

    @Select("SELECT * FROM artist WHERE id = #{id}")
    Artist getArtistById(Long id);

    @Select("SELECT a.* FROM artist a " +
            "JOIN circle_artist ca ON a.id = ca.artist_id " +
            "WHERE ca.circle_id = #{circleId}")
    List<Artist> getArtistsByCircleId(int circleId);
}
