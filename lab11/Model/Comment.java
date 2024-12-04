package com.example.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @NotNull(message = "user id can not be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "post id can not be null")
    @Column(columnDefinition = "int not null")
    private Integer postId;

    @NotEmpty(message = "Comment content cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String content;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE not null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate commentDate;

    public Comment(Integer commentId, Integer userId, Integer postId, String content, LocalDate commentDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.commentDate = commentDate;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public @NotNull(message = "user id can not be null") Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "user id can not be null") Integer userId) {
        this.userId = userId;
    }

    public @NotNull(message = "post id can not be null") Integer getPostId() {
        return postId;
    }

    public void setPostId(@NotNull(message = "post id can not be null") Integer postId) {
        this.postId = postId;
    }

    public @NotEmpty(message = "Comment content cannot be empty") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "Comment content cannot be empty") String content) {
        this.content = content;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }



    public Comment(){

    }


    @PrePersist
    public void prePersist() {
        if (this.commentDate == null) {
            this.commentDate = LocalDate.now();
        }
    }
}
