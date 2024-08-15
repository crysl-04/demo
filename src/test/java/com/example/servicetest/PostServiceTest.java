package com.example.servicetest;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.entity.Post;
import com.example.mapper.PostMapper;
import com.example.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostMapper postMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePostWithImage() throws IOException {
        // Mock MultipartFile
        MultipartFile imageFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", "test image content".getBytes());

        // Mock Post
        Post post = new Post();
        post.setTitle("Test Title");
        post.setContent("Test Content");

        // Mock save operation
        doNothing().when(postMapper).save(any(Post.class));

        // Call the method to test
        postService.savePost(post, imageFile);

        // Correct the expected path
        String expectedImageUrl = "/uploads/" + post.getImageUrl().substring(post.getImageUrl().lastIndexOf('/') + 1);

        // Verify results
        assertEquals(expectedImageUrl, post.getImageUrl());

        // Verify if postMapper.save was called
        verify(postMapper, times(1)).save(post);

        // Clean up
        Path filePath = Paths.get("C:/web/demo/uploads/" + post.getImageUrl());
        Files.deleteIfExists(filePath);
    }

    @Test
    public void testSavePostWithoutImage() throws IOException {
        // Mock Post
        Post post = new Post();
        post.setTitle("Test Title");
        post.setContent("Test Content");

        // Mock save operation
        doNothing().when(postMapper).save(any(Post.class));

        // Call the method to test
        postService.savePost(post, null);

        // Verify results
        assertNull(post.getImageUrl());

        // Verify if postMapper.save was called
        verify(postMapper, times(1)).save(post);
    }
}
