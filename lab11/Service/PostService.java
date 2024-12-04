package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Post;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {
     private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

//    public List<Post> getPostsByUserId(Integer userId) {
//        return postRepo.findByUserId(userId);
//    }

    public Post addPost(Post post) {
        if (userRepository.findById(post.getUserId()).isEmpty()) {
            throw new ApiException("User not found");
        }
        return postRepository.save(post);
    }

    public List<Post> getPostsByTitle(String title) {
       List <Post> postList= postRepository.findByTitleContaining(title);
       if (postList==null){
           throw new ApiException("title not found");
       }
       return postList;
    }

    public List<Post> getPostsBeforeDate(LocalDate date) {
        List<Post> postListDate= postRepository.findByPublishDateBefore(date);
        if (postListDate==null){
            throw new ApiException("there are no post in that date");
        }
        return postListDate;
    }

    public List<Post> getPostsByPublishDateRange(LocalDate startDate, LocalDate endDate) {

        List<Post>startAndEndDate= postRepository.findPostsByPublishDateRange(startDate, endDate);
        if (startAndEndDate==null){
            throw new ApiException("there are no post in that date");
        }
        return startAndEndDate;
    }




}
