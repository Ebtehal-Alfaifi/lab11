package com.example.lab11.Repository;

import com.example.lab11.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

//3 of 8
   @Query("select c from Category c where c.categoryId=?1 ")
Category getByid(Integer id);

   // 4 of 8
   List <Category>findCategoriesByName(String name);
}
