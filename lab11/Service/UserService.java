package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public List<User>getAll(){
        return userRepository.findAll();
    }


    public User addUser(User user) {

        return userRepository.save(user);
    }


    public User update(Integer id,User user){
        User old= userRepository.findUserById(id);
        if (old==null){
            throw new  ApiException("id not found");
        }

        old.setEmail(user.getEmail());
        old.setPassword(user.getPassword());
        old.setUserName(user.getUserName());
       return userRepository.save(old);

    }


    public void delete(Integer id){
        User user= userRepository.findUserById(id);
        if (user==null){
            throw new ApiException(" user not found");
        }

        userRepository.delete(user);
    }

    //2
public User getUserEmail(String email){
        User user= userRepository.getMebyEmail(email);
        if (user==null){
            throw new ApiException("user not found");
        }
        return user;

}

}
