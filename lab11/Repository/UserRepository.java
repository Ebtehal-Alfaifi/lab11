package com.example.lab11.Repository;

import com.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>{
//1 of 8 end point
    User findUserById(Integer userId);

    //2 of 8 end point
    @Query("select u from User u where u.email=?1")
    User getMebyEmail(String email);

}
