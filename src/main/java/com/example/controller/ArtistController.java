package com.example.controller;

import com.example.entity.Artist;
import com.example.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists/{artistId}")
    public String getArtistDetail(@PathVariable Long artistId, @RequestParam int circleId, Model model) {
        Artist artist = artistService.getArtistById(artistId);
        model.addAttribute("artist", artist);
        model.addAttribute("circleId", circleId);
        return "artist_detail";
    }

}
