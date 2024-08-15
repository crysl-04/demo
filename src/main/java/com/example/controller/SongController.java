package com.example.controller;

import com.example.entity.Album;
import com.example.entity.InterestCircle;
import com.example.entity.Song;
import com.example.service.SongService;
import com.example.service.AlbumService;
import com.example.service.InterestCircleService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SongController {
    @Autowired
    private SongService songService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private InterestCircleService interestCircleService;


    @GetMapping("/circles/{circleId}/albums/{albumId}/songs/{songId}")
    public String getSongDetail(@PathVariable("circleId") int circleId,
                                @PathVariable("albumId") int albumId,
                                @PathVariable("songId") int songId,
                                Model model) {
        // 从服务层获取相关数据
        Song song = songService.getSongById(songId);
        Album album = albumService.getAlbumById(albumId);
        InterestCircle circle = interestCircleService.getCircleById(circleId);

        // 将数据添加到模型中
        model.addAttribute("circle", circle);
        model.addAttribute("album", album);
        model.addAttribute("song", song);

        return "song_detail"; // 返回视图模板名称
    }

    @GetMapping("/circles/{circleId}/albums/{albumId}/addsong")
    public String showAddSongPage(@PathVariable("circleId") int circleId,
                                  @PathVariable("albumId") int albumId,
                                  Model model,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // 如果用户未登录，设置提示信息并重定向到专辑列表页面
            redirectAttributes.addFlashAttribute("errorMessage", "需要登录才能添加歌曲！");
            return "redirect:/circles/" + circleId + "/albums/"+ albumId;
        }

        model.addAttribute("circle_id", circleId);
        model.addAttribute("album_id", albumId);
        return "add_song"; // 返回添加歌曲页面的视图模板名称
    }

    @PostMapping("/circles/{circleId}/albums/{albumId}/addsong")
    public String addSong(@PathVariable("circleId") int circleId,
                          @PathVariable("albumId") int albumId,
                          @RequestParam("title") String title,
                          @RequestParam(value = "lyricist", required = false) String lyricist,
                          @RequestParam(value = "composer", required = false) String composer,
                          @RequestParam(value = "original_lyrics", required = false) String originalLyrics,
                          @RequestParam(value = "chinese_lyrics", required = false) String chineseLyrics,
                          @RequestParam(value = "pinyin_lyrics", required = false) String pinyinLyrics) {

        Song newSong = new Song();
        newSong.setTitle(title);
        newSong.setLyricist(lyricist);
        newSong.setComposer(composer);
        newSong.setOriginalLyrics(originalLyrics);
        newSong.setChineseLyrics(chineseLyrics);
        newSong.setPinyinLyrics(pinyinLyrics);
        newSong.setAlbumId(albumId);

        System.out.println(newSong.getChineseLyrics());

        songService.saveSong(newSong);

        return "redirect:/circles/" + circleId + "/albums/" + albumId;
    }


}

