package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.Api;
import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<User>getAll=userService.getAll();
        return ResponseEntity.status(200).body(getAll);
    }
    @PostMapping("/add")
    public ResponseEntity addNew(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new Api("add success"));

    }

@PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody @Valid User user,Errors errors){
        if (errors.hasErrors()){
            String mwssage=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(mwssage);
        }
        userService.update(id, user);
        return ResponseEntity.status(200).body(new Api("update success"));
    }

    @GetMapping("/get-by-email/{email}")
    public ResponseEntity getByEmail(@PathVariable String email){
        User user=userService.getUserEmail(email);
        return ResponseEntity.status(200).body(user);
    }


}
