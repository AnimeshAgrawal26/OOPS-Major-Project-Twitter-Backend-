package com.example.socialmediabackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class BasicController {

    private PostRepository postRepository;

    @Autowired
    public void AllPostsController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        // Retrieve all posts sorted by date in descending order
        List<Post> posts = postRepository.findAllByOrderByDateDesc();

        // Map posts to desired response structure
        List<Object> formattedPosts = posts.stream()
                .map(this::formatPostResponse)
                .collect(Collectors.toList());

        // Construct the response object
        return ResponseEntity.ok(formattedPosts);
    }

    // Helper method to format post response
    private Object formatPostResponse(Post post) {
        return Map.of(
                "postID", post.getPostId(),
                "postBody", post.getPostBody(),
                "date", post.getDate(),
                "comments", formatComments(post.getComments())
        );
    }

    // Helper method to format comments within a post
    private List<Object> formatComments(List<Comment> comments) {
        return comments.stream()
                .map(comment -> Map.of(
                        "commentID", comment.getCommentID(),
                        "commentBody", comment.getCommentBody(),
                        "commentCreator", Map.of(
                                "userID", comment.getUser().getId(),
                                "name", comment.getUser().getName()
                        )
                ))
                .collect(Collectors.toList());
    }
}
