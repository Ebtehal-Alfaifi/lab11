package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "name IN ('Lifestyle', 'Education')")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @NotEmpty(message = "name can not be null")
    @Pattern(regexp = "^(Lifestyle|Education)",message = " name can be only Lifestyle and Education")
    @Size(min = 9,max = 10,message =  " you can not enter name less than 9 or mor than 10")
    @Column(columnDefinition = "varchar(10) not null")
    private String name;



    public Category(Integer categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public @NotEmpty(message = "name can not be null") @Pattern(regexp = "^(Lifestyle|Education)", message = " name can be only Lifestyle and Education") @Size(min = 9, max = 10, message = " you can not enter name less than 9 or mor than 10") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "name can not be null") @Pattern(regexp = "^(Lifestyle|Education)", message = " name can be only Lifestyle and Education") @Size(min = 9, max = 10, message = " you can not enter name less than 9 or mor than 10") String name) {
        this.name = name;
    }

    public Category(){

    }
}
