package com.example.service;

import com.example.entity.Song;
import com.example.mapper.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    @Autowired
    private SongMapper songMapper;

    public void createSong(Song song) {
        songMapper.insertSong(song);
    }

    public Object getSongsByAlbumId(int albumId) {
        return songMapper.getSongsByAlbumId(albumId);
    }

    public Song getSongById(int id) {
        Song s = songMapper.getSongById(id);
//        System.out.println(s);
//        System.out.println(s.getOriginalLyrics());
//        System.out.println(s.getChineseLyrics());
//        System.out.println(s.getPinyinLyrics());
        return songMapper.getSongById(id);
    }


    public void saveSong(Song newSong) {
        songMapper.insertSong(newSong);
    }
}

