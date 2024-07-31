package com.example.service;

import com.example.entity.Artist;
import com.example.mapper.ArtistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    @Autowired
    private ArtistMapper artistMapper;

    public List<Artist> getAllArtists() {
        return artistMapper.getAllArtists();
    }

    public void saveArtist(Artist artist) {
        artistMapper.insertArtist(artist);
    }

    public Artist getArtistById(Long id) {
        return artistMapper.getArtistById(id);
    }

    public List<Artist> getArtistsByCircleId(int circleId) {
        return artistMapper.getArtistsByCircleId(circleId);
    }
}
