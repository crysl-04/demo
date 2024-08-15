package com.example.service;

import com.example.entity.Comment;
import com.example.entity.InterestCircleMember;
import com.example.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostService postService;

    @Autowired
    private InterestCircleService circleService;

    public void addComment(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        commentMapper.insertComment(comment);
    }

//    public List<Comment> getCommentsByPostId(int postId) {
//        return commentMapper.getCommentsByPostId(postId);
//    }

    public List<Comment> getCommentsByPostId(int postId) {
        // 获取评论列表
        List<Comment> comments = commentMapper.findByPostId(postId);
        //System.out.println("Fetched comments: " + comments);

        // 为每个评论填充 membername
        for (Comment comment : comments) {
            Long userId = comment.getUserId();
            int circleId = postService.getCircleIdByPostId(postId);
            System.out.println("userId: " + userId + " circleId: "+circleId);
            InterestCircleMember member = circleService.findByUserIdAndCircleId(userId, circleId);

            if (member != null) {
                comment.setMembername(member.getNickname());
            } else {
                comment.setMembername("Unknown"); // 如果找不到，设置默认值
            }
        }

        return comments;
    }
}
