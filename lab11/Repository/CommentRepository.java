package com.example.lab11.Repository;

import com.example.lab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
//8 of 8
List<Comment> findByPostId(Integer postId);

//9 end point
    @Query("SELECT c FROM Comment c WHERE c.commentDate = ?1")
    List<Comment> findByCommentDate(LocalDate commentDate);

    //10 end point
    @Query("SELECT c FROM Comment c WHERE c.userId = ?1")
    Comment findCommentByUserId(Integer userId);


}
