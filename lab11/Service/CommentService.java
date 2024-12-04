package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Comment> getAll() {
       return commentRepository.findAll();
    }



    public Comment addComment(Comment comment) {
        if (userRepository.findById(comment.getUserId()).isEmpty()) {
            throw new ApiException("User not found");
        }
        if (postRepository.findById(comment.getPostId()).isEmpty()) {
            throw new ApiException("Post not found");
        }
        return commentRepository.save(comment);
    }


    public Comment updateComment(Integer id, Comment updatedComment) {
        Comment oldComment = commentRepository.findById(id).orElse(null);
        if (oldComment == null) {
            throw new ApiException("Comment not found");
        }

        if (!userRepository.existsById(updatedComment.getUserId())) {
            throw new ApiException("User not found");
        }

        if (!postRepository.existsById(updatedComment.getPostId())) {
            throw new ApiException("Post not found");
        }

        oldComment.setContent(updatedComment.getContent());
        oldComment.setCommentDate(updatedComment.getCommentDate());

        return commentRepository.save(oldComment);
    }

    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElse(null);

        if (comment == null) {
            throw new ApiException("Comment not found");
        }

        commentRepository.delete(comment);
    }


    public List<Comment> getCommentsByPostId(Integer postId) {
        if (postRepository.findById(postId).isEmpty()) {
            throw new ApiException("Post not found");
        }
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> getCommentsByCommentDate(LocalDate commentDate) {
        List<Comment> comments= commentRepository.findByCommentDate(commentDate);
        if (comments==null){
            throw new ApiException("there are not comment on that date");
        }
        return commentRepository.findByCommentDate(commentDate);
    }

    public Comment findCommentByUserId(Integer userId) {
        Comment comment = commentRepository.findCommentByUserId(userId);
        if (comment == null) {
            throw new ApiException("Comment not found");
        }
        return comment;
    }

}
