package com.example.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;




    @NotEmpty(message = "  user name can not be null")
    @Size(min = 5,max = 10,message = " your  user name at least should  has 5 character ")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String userName;


    @NotEmpty(message = " password can not be null")
    @Size(min = 8,max = 10,message = " you can not enter password less than 8 or more than 10 ")
    @Column(columnDefinition = "varchar(10) not null")
    private String password;


    @NotEmpty(message = " email can not be empty")
    @Email(message = " you should enter valid email")
    @Column(columnDefinition = "varchar(20) not null unique ")
    private String email;


    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE not null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    public User(String userName, String password, String email, LocalDate registrationDate) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public @NotEmpty(message = "  user name can not be null") @Size(min = 5, max = 10, message = " your  user name at least should  has 5 character ") String getUserName() {
        return userName;
    }

    public void setUserName(@NotEmpty(message = "  user name can not be null") @Size(min = 5, max = 10, message = " your  user name at least should  has 5 character ") String userName) {
        this.userName = userName;
    }

    public @NotEmpty(message = " password can not be null") @Size(min = 8, max = 10, message = " you can not enter password less than 8 or more than 10 ") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = " password can not be null") @Size(min = 8, max = 10, message = " you can not enter password less than 8 or more than 10 ") String password) {
        this.password = password;
    }

    public @NotEmpty(message = " email can not be empty") @Email(message = " you should enter valid email") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = " email can not be empty") @Email(message = " you should enter valid email") String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @PrePersist
    public void prePersist() {
        if (this.registrationDate == null) {
            this.registrationDate = LocalDate.now();  //  عينت التاريخ الحالي
        }


    }
public User(){

}





}
