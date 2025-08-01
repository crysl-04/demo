package com.example.service;

import com.example.entity.InterestCircle;
import com.example.entity.InterestCircleMember;
//import com.example.entity.InterestCirclePost;
import com.example.entity.Post;
import org.springframework.data.domain.Page;
import com.example.entity.Artist;


import java.util.List;

public interface InterestCircleService {
    List<InterestCircle> getAllCircles();
    InterestCircle getCircleById(int id);
    void addCircle(InterestCircle circle);
    void updateCircle(InterestCircle circle);
    List<InterestCircleMember> getMembersByCircleId(int circleId);
    //List<InterestCirclePost> getPostsByCircleId(int circleId);
    List<Post> getPostsByCircleId(int circleId);
    void addMember(InterestCircleMember member);
    void addPost(Post post);
    List<String> getAllNicknames(int circleId);
    InterestCircleMember findByNickname(String nickname);

    InterestCircleMember findByUserIdAndCircleId(Long userId,int circleId);
//    String getNicknameByUserId(Long userId);
    boolean isMember(int circleId, Long userId);

    List<Artist> getArtistsByCircleId(int circleId);


}
