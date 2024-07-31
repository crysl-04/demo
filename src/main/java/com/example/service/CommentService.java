package com.example.service;

import com.example.entity.Comment;
import com.example.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getCommentsByPostId(int postId) {
        return commentMapper.findByPostId(postId);
    }

    public void addComment(Comment comment) {
        commentMapper.save(comment);
    }
}
