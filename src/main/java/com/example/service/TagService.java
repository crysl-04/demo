package com.example.service;

import com.example.entity.Tag;
import com.example.entity.Post;
import com.example.mapper.TagMapper;
import com.example.mapper.PostTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private PostTagMapper postTagMapper;

    public void createTag(Tag tag) {
        tagMapper.insertTag(tag);
    }

    public List<Tag> getTagsByCircleId(int circleId) {
        return tagMapper.getTagsByCircleId(circleId);
    }

    public List<Post> getPostsByTagId(int tagId) {
        return tagMapper.getPostsByTagId(tagId);
    }

    public void assignTagsToPost(int postId, List<Integer> tagIds) {
        postTagMapper.deletePostTagsByPostId(postId); // 先删除旧的关联
        for (int tagId : tagIds) {
            postTagMapper.insertPostTag(postId, tagId); // 插入新的关联
        }
    }

    public Tag getTagById(int tagId) {
        return tagMapper.findTagById(tagId);
    }

    public List<Tag> getAllTagsByCircleId(int circleId) {
        return tagMapper.getTagsByCircleId(circleId);
    }
}
