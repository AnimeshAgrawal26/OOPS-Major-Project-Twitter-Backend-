package com.example.socialmediabackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        Optional<User> optionalUser = userRepository.findById(post.getUserId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        }
        post.setDate(LocalDateTime.now());
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    @GetMapping
    public ResponseEntity<?> getPost(@RequestParam int postID) {
        Optional<Post> optionalPost = postRepository.findById(postID);
        if (optionalPost.isPresent()) {
            return ResponseEntity.ok(optionalPost.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }

    @PatchMapping
    public ResponseEntity<String> editPost(@RequestBody Post post) {
        System.out.println(post.getPostId());
        Optional<Post> optionalPost = postRepository.findById(post.getPostId());
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setPostBody(post.getPostBody());
            postRepository.save(existingPost);
            return ResponseEntity.ok("Post edited successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletePost(@RequestParam int postID) {
        if (postRepository.existsById(postID)) {
            postRepository.deleteById(postID);
            return ResponseEntity.ok("Post deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    }
}
