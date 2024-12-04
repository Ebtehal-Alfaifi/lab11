package com.example.lab11.Controller;

import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAll();
    }

//    @GetMapping("/user/{userId}")
//    public List<Post> getPostsByUserId(@PathVariable Integer userId) {
//        return postService.getPostsByUserId(userId);
//    }

    @GetMapping("/title/{title}")
    public List<Post> getPostsByTitle(@PathVariable String title) {
        return postService.getPostsByTitle(title);
    }

    @GetMapping("/before/{date}")
    public List<Post> getPostsBeforeDate(@PathVariable String date) {
        return postService.getPostsBeforeDate(LocalDate.parse(date));
    }

    @PostMapping("/get-post")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        return ResponseEntity.status(200).body(postService.addPost(post));
    }
    @GetMapping("/by-date-range/{startDate}/{endDate}")
    public ResponseEntity<List<Post>> getPostsByDateRange(
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {

        List<Post> posts = postService.getPostsByPublishDateRange(startDate, endDate);
        return ResponseEntity.ok(posts);
    }
}
