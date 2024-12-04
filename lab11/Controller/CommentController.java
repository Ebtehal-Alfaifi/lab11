package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.Api;
import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        List<Comment> comments = commentService.getAll();
        return ResponseEntity.status(200).body(comments);
    }

    @PostMapping("/add")
    public ResponseEntity addNew(@RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
       commentService.addComment(comment);
        return ResponseEntity.status(200).body(new Api("add success"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Comment updatedComment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.updateComment(id, updatedComment);
        return ResponseEntity.status(200).body(new Api("update success"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new Api("delete success"));
    }

    @GetMapping("/getByPostId/{postId}")
    public ResponseEntity getByPostId(@PathVariable Integer postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.status(200).body(comments);
    }

    @GetMapping("/getByDate/{commentDate}")
    public ResponseEntity getByCommentDate(@PathVariable LocalDate commentDate) {
        List<Comment> comments = commentService.getCommentsByCommentDate(commentDate);
        return ResponseEntity.status(200).body(comments);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity getByUserId(@PathVariable Integer userId) {
        Comment comment = commentService.findCommentByUserId(userId);
        return ResponseEntity.status(200).body(comment);
    }
}


