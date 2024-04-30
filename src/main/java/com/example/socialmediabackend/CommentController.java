package com.example.socialmediabackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentController(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentRequest request) {
        Optional<Post> optionalPost = postRepository.findById(request.getPostID());
        if (optionalPost.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post does not exist");
        }

        Optional<User> optionalUser = userRepository.findById(request.getUserID());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
        }

        Comment comment = new Comment();
        comment.setCommentBody(request.getCommentBody());
        comment.setPost(optionalPost.get());
        comment.setUser(optionalUser.get());
        commentRepository.save(comment);

        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully");
    }

    @GetMapping
    public ResponseEntity<?> getComment(@RequestParam int commentID) {
        Optional<Comment> optionalComment = commentRepository.findById(commentID);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            CommentCreatorDTO commentCreatorDTO = new CommentCreatorDTO(comment.getUser().getId(), comment.getUser().getName());
            CommentDTO commentDTO = new CommentDTO(comment.getCommentID(), comment.getCommentBody(), commentCreatorDTO);
            return ResponseEntity.ok(commentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
    }


    @PatchMapping
    public ResponseEntity<String> editComment(@RequestBody CommentRequest request) {
        Optional<Comment> optionalComment = commentRepository.findById(request.getCommentID());
        if (optionalComment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }

        Comment comment = optionalComment.get();
        comment.setCommentBody(request.getCommentBody());
        commentRepository.save(comment);

        return ResponseEntity.ok("Comment edited successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteComment(@RequestParam int commentID) {
        if (commentRepository.existsById(commentID)) {
            commentRepository.deleteById(commentID);
            return ResponseEntity.ok("Comment deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
        }
    }
}
