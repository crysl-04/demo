package com.example.mapper;

import com.example.entity.Song;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SongMapper {
    @Select("SELECT * FROM song WHERE album_id = #{albumId}")
    List<Song> getSongsByAlbumId(int albumId);

    @Insert("INSERT INTO song(title, lyricist, composer, original_lyrics, chinese_lyrics, pinyin_lyrics, album_id) VALUES(#{title}, #{lyricist}, #{composer}, #{originalLyrics}, #{chineseLyrics}, #{pinyinLyrics}, #{albumId})")
    void insertSong(Song song);

    @Select("SELECT * FROM song WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "lyricist", column = "lyricist"),
            @Result(property = "composer", column = "composer"),
            @Result(property = "originalLyrics", column = "original_lyrics"),
            @Result(property = "chineseLyrics", column = "chinese_lyrics"),
            @Result(property = "pinyinLyrics", column = "pinyin_lyrics"),
            @Result(property = "albumId", column = "album_id")
    })
    Song getSongById(int id);
}
