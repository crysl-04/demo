package com.example.service;

import com.example.entity.Comment;
import com.example.entity.InterestCircleMember;
import com.example.entity.Post;
import com.example.mapper.InterestCircleMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mapper.PostMapper;
import com.example.mapper.CommentMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private InterestCircleMemberMapper memberMapper;

    public Map<Long, Double> calculateActivity(int circleId) {
        Map<Long, Double> activitySummary = new HashMap<>();

        // 获取所有帖子和评论
        List<Post> posts = postMapper.findByCircleId(circleId);
        List<Comment> comments = commentMapper.findByCircleId(circleId);

        // 计算每个用户的活跃度
        Map<Long, Integer> userActivity = new HashMap<>();

        // 计算帖子的活跃度
        for (Post post : posts) {
            Long userId = postMapper.findUserIdByPostId(post.getId());
            userActivity.merge(userId, 1, Integer::sum);
        }

        // 计算评论的活跃度
        for (Comment comment : comments) {
            Long userId = commentMapper.findUserIdByCommentId(comment.getId());
            userActivity.merge(userId, 1, Integer::sum);
        }

        // 将结果转换为百分比或其他指标
        userActivity.forEach((userId, count) -> {
            activitySummary.put(userId, count.doubleValue());
        });

        return activitySummary;
    }
}
