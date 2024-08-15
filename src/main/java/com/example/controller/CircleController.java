package com.example.controller;

import com.example.entity.*;
//import com.example.entity.InterestCirclePost;
import com.example.mapper.AlbumMapper;
import com.example.mapper.InterestCircleMemberMapper;
//import com.example.mapper.InterestCirclePostMapper;
import com.example.mapper.PostMapper;
import com.example.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CircleController {

    @Autowired
    private InterestCircleService interestCircleService;

    @Autowired
    private UserService userService;
    @Autowired
    private PostMapper interestCirclePostMapper;
    @Autowired
    private InterestCircleMemberMapper interestCircleMemberMapper;

    @Autowired
    private ArtistService artistService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private TagService tagService;

    @Autowired
    private SongService songService;

    /**
     * 获取所有兴趣圈列表
     *
     * @param model  用于存储传递到视图的数据
     * @return 视图名称 (circles.html)
     */
    @GetMapping("/circles")
    public String getAllCircles(Model model) {
        List<InterestCircle> circles = interestCircleService.getAllCircles();
        model.addAttribute("circles", circles);
        return "circles";
    }

    /**
     * 创建新的兴趣圈
     *
     * @param circleName 兴趣圈名称
     * @param description 兴趣圈描述
     * @return 重定向到所有兴趣圈列表 (/circles)
     */
    @PostMapping("/circles/create")
    public String createCircle(@RequestParam String circleName, @RequestParam String description, HttpSession session, RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // 如果用户未登录，设置提示信息并重定向到圈子列表页面
            redirectAttributes.addFlashAttribute("errorMessage", "需要登录才能创建兴趣圈！");
            return "redirect:/circles";
        }

        InterestCircle newCircle = new InterestCircle();
        newCircle.setName(circleName);
        newCircle.setDescription(description);
        interestCircleService.addCircle(newCircle);
        return "redirect:/circles";
    }

    /**
     * 获取指定 ID 的兴趣圈详情用于编辑
     *
     * @param id 兴趣圈 ID
     * @param model 用于存储传递到视图的数据
     * @return 视图名称 (edit_circle.html)
     */
    @GetMapping("/circles/edit/{id}")
    public String editCircle(@PathVariable int id, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // 如果用户未登录，设置提示信息并重定向到圈子列表页面
            redirectAttributes.addFlashAttribute("errorMessage", "需要登录才能修改兴趣圈！");
            return "redirect:/circles";
        }
        InterestCircle circle = interestCircleService.getCircleById(id);
        model.addAttribute("circle", circle);
        return "edit_circle";
    }

    /**
     * 更新指定 ID 的兴趣圈信息
     *
     * @param id 兴趣圈 ID
     * @param circleName 兴趣圈名称
     * @param description 兴趣圈描述
     * @return 重定向到所有兴趣圈列表 (/circles)
     */
    @PostMapping("/circles/update")
    public String updateCircle(@RequestParam int id, @RequestParam String circleName, @RequestParam String description) {
        InterestCircle circle = new InterestCircle();
        circle.setId(id);
        circle.setName(circleName);
        circle.setDescription(description);
        interestCircleService.updateCircle(circle);
        return "redirect:/circles";
    }

    /**
     * 获取指定 ID 的兴趣圈详细信息
     *
     * @param circleId 兴趣圈 ID
     * @param model 用于存储传递到视图的数据
     * @return 视图名称 (circle_detail.html)
     */
    @GetMapping("/circles/{id}")
    public String getCircleDetail(@PathVariable("id") int circleId, Model model) {
        InterestCircle circle = interestCircleService.getCircleById(circleId);
        List<String> members = interestCircleService.getAllNicknames(circleId);
        List<Post> posts = postService.getPostsByCircleId(circleId);
        List<Artist> artists = artistService.getArtistsByCircleId(circleId);

        // 查询并传递与该圈子相关的标签
        List<Tag> tags = tagService.getTagsByCircleId(circleId);
        model.addAttribute("tags", tags);

        List<Album> albums = albumService.getAlbumsByCircleId(circleId);
//        for (Album album : albums) {
//            System.out.println("Album Image URL: " + album.getImageUrl());
//        }
        model.addAttribute("albums", albums);

        model.addAttribute("circle", circle);
        model.addAttribute("members", members);
        model.addAttribute("posts", posts);
        model.addAttribute("artists", artists);
        return "circle_detail";
    }

    /**
     * 显示加入兴趣圈的表单
     *
     * @param circleId 兴趣圈 ID
     * @param model 用于存储传递到视图的数据
     * @return 视图名称 (joinForm.html)
     */
    @GetMapping("/circles/joinForm/{circleId}")
    public String showJoinForm(@PathVariable("circleId") int circleId, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // 如果用户未登录，设置提示信息并重定向到圈子列表页面
            redirectAttributes.addFlashAttribute("errorMessage", "需要登录才能加入兴趣圈！");
            return "redirect:/circles/" + circleId + "?tab=join";
        }

        model.addAttribute("circleId", circleId);
        return "joinForm";
    }

    /**
     * 加入指定的兴趣圈
     *
     * @param circleId 兴趣圈 ID
     * @param nickname 用户昵称
     * @param session 用于获取当前登录用户名 (可根据需求修改)
     * @param model 用于存储传递到视图的数据
     * @return 重定向到兴趣圈详情页 (/circles/{id})
     */
    @PostMapping("/circles/{circleId}/join")
    public String joinCircle(@PathVariable("circleId") int circleId, @RequestParam("nickname") String nickname, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // 如果用户未登录，设置提示信息并重定向到圈子列表页面
            redirectAttributes.addFlashAttribute("errorMessage", "需要登录才能加入兴趣圈！");
            return "redirect:/circles/" + circleId;
        }

        username = session.getAttribute("username").toString();
        Long userId = userService.findByName(username).getId();

        // 检查用户是否已经是兴趣圈的成员
        boolean isMember = interestCircleService.isMember(circleId, userId);
        if (isMember) {
            model.addAttribute("message", "您已经加入了这个兴趣圈，无需再次加入。");
            model.addAttribute("circleId", circleId);
            return "joinForm"; // 返回表单页面并提示信息
        }

        InterestCircleMember newMember = new InterestCircleMember();
        newMember.setNickname(nickname);
        newMember.setCircleId(circleId);
        newMember.setUserId(userId);

        interestCircleService.addMember(newMember);

        return "redirect:/circles/" + circleId;
    }

    // 显示专辑详情
    @GetMapping("/circles/{circleId}/albums/{albumId}")
    public String getAlbumDetail(@PathVariable("circleId") int circleId,
                                 @PathVariable("albumId") int albumId,
                                 Model model) {
        model.addAttribute("circle_id",circleId);
        model.addAttribute("album", albumService.getAlbumById(albumId));
        model.addAttribute("songs", songService.getSongsByAlbumId(albumId));
        return "album_detail"; // 返回视图模板名称
    }

    @GetMapping("/circles/{circleId}/tags/{tagId}/posts")
    public String getPostsByTag(@PathVariable("circleId") int circleId, @PathVariable int tagId, Model model) {
        //System.out.println("circleId: " + circleId); // 日志输出
        //System.out.println("tagId: " + tagId); // 日志输出

        InterestCircle circle = interestCircleService.getCircleById(circleId);
        model.addAttribute("circle", circle);

        Tag tag = tagService.getTagById(tagId);
        model.addAttribute("tag", tag);

        List<Post> posts = postService.getPostsByTagId(tagId);
        model.addAttribute("posts", posts);

        return "posts_by_tag";
    }

    @GetMapping("/circles/{circleId}/tags/new")
    public String newTagForm(@PathVariable int circleId, Model model,HttpSession session,RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // 如果用户未登录，设置提示信息并重定向到圈子列表页面
            redirectAttributes.addFlashAttribute("errorMessage", "需要登录才能创建标签！");
            return "redirect:/circles/" + circleId + "?tab=posts";
        }

        model.addAttribute("circleId", circleId);
//        System.out.println("circleId: " + circleId);
        return "new_tag";
    }

    @PostMapping("/circles/{circleId}/tags/new")
    public String createTag(@PathVariable("circleId") int circleId, @RequestParam String tagName, Model model,RedirectAttributes redirectAttributes) {

        model.addAttribute("circleId", circleId);
        System.out.println("circleId: " + circleId);

        // 创建新标签的逻辑
        Tag newTag = new Tag();
        newTag.setName(tagName);
        newTag.setCircleId(circleId);
        tagService.createTag(newTag); // 实现 createTag 方法在你的 TagService

        redirectAttributes.addFlashAttribute("message", "Tag created successfully!");
        return "redirect:/circles/" + circleId +"?tab=posts";
    }

    // 显示创建专辑的表单页面
    @GetMapping("/circles/{circleId}/albums/create")
    public String showCreateAlbumForm(@PathVariable("circleId") int circleId, Model model,HttpSession session, RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // 如果用户未登录，设置提示信息并重定向到圈子列表页面
            redirectAttributes.addFlashAttribute("errorMessage", "需要登录才能添加专辑！");
            return "redirect:/circles/"+ circleId +  "?tab=albums";
        }

        model.addAttribute("circleId", circleId);
        return "album_create"; // 返回包含表单的视图名称
    }
}
