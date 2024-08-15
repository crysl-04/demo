package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "song")
public class Song {
    @Id
    private int id;
    private String title;
    private String lyricist;
    private String composer;
    @Column(name = "original_lyrics")
    private String originalLyrics;
    @Column(name = "chinese_lyrics")
    private String chineseLyrics;
    @Column(name = "pinyin_lyrics")
    private String pinyinLyrics;
    private int albumId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyricist() {
        return lyricist;
    }

    public void setLyricist(String lyricist) {
        this.lyricist = lyricist;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getOriginalLyrics() {
        return originalLyrics;
    }

    public void setOriginalLyrics(String originalLyrics) {
        this.originalLyrics = originalLyrics;
    }

    public String getChineseLyrics() {
        return chineseLyrics;
    }

    public void setChineseLyrics(String chineseLyrics) {
        this.chineseLyrics = chineseLyrics;
    }

    public String getPinyinLyrics() {
        return pinyinLyrics;
    }

    public void setPinyinLyrics(String pinyinLyrics) {
        this.pinyinLyrics = pinyinLyrics;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    // Getters and Setters
}
