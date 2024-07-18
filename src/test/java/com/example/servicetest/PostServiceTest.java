package com.example.servicetest;

import com.example.entity.InterestCirclePost;
import com.example.mapper.InterestCircleMapper;
import com.example.service.InterestCircleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private InterestCircleService interestCircleService;

    @Autowired
    private InterestCircleMapper interestCircleMapper;

    @Test
    public void testAddPost() {
        // Create a sample InterestCirclePost object
        InterestCirclePost post = new InterestCirclePost();
        // Set up post properties as needed for your test
        // Example:
        //post.setTitle("Sample Post Title");
        post.setContent("Sample Post Content");
        post.setCircleId(1);
        post.setUserId(32L);

        // Call the addPost method in your service
        interestCircleService.addPost(post);

        // Assert that the post was added successfully (you can customize this assertion based on your application logic)
        //assertNotNull(post.getId());

        // Optionally, you can verify using the mapper if needed
        // Example:
        //InterestCirclePost savedPost = interestCircleMapper.findById(post.getId());
       // assertNotNull(savedPost);
        //assertEquals(post.getTitle(), savedPost.getTitle());
        //assertEquals(post.getContent(), savedPost.getContent());
    }
}
