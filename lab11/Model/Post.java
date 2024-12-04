package com.example.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;


    @NotNull(message = " category id can not be null")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;


    @NotEmpty(message = "title can not be empty")
    @Size(min = 5,max = 20,message = " title length at lest should has 5 character ,not more than 20 ")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;

    @NotEmpty(message = "Content cannot be empty")
    @Size(max = 50,message = " you can not enter content mor than 50")
    @Column(columnDefinition = "varchar(50) not null")
    private String content;


    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE not null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    @Column(columnDefinition = "int not null")
    private Integer userId;

    public Post(Integer postId, Integer categoryId, String title, String content, LocalDate publishDate, Integer userId) {
        this.postId = postId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public @NotNull(message = " category id can not be null") Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull(message = " category id can not be null") Integer categoryId) {
        this.categoryId = categoryId;
    }

    public @NotEmpty(message = "title can not be empty") @Size(min = 5, max = 20, message = " title length at lest should has 5 character ,not more than 20 ") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "title can not be empty") @Size(min = 5, max = 20, message = " title length at lest should has 5 character ,not more than 20 ") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "Content cannot be empty") @Size(max = 50, message = " you can not enter content mor than 50") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "Content cannot be empty") @Size(max = 50, message = " you can not enter content mor than 50") String content) {
        this.content = content;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Post(){

    }

    @PrePersist
    public void prePersist() {
        if (this.publishDate == null) {
            this.publishDate = LocalDate.now();
        }
    }

}
