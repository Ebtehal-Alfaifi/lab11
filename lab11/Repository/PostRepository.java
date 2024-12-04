package com.example.lab11.Repository;

import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {


//    List<Post> findByUserId(Integer userId);

    List<Post> findByTitleContaining(String title);

    List<Post> findByPublishDateBefore(LocalDate date);

    @Query("SELECT p FROM Post p WHERE p.publishDate BETWEEN :startDate AND :endDate")
    List<Post> findPostsByPublishDateRange(LocalDate startDate, LocalDate endDate);

}
