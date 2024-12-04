package com.example.lab11.Controller;

import com.example.lab11.ApiResponse.Api;
import com.example.lab11.Model.Category;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/add")
    public ResponseEntity addNew(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addNew(category);
        return ResponseEntity.status(200).body(new Api("add success"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.updateAll(id, category);
        return ResponseEntity.status(200).body(new Api("update success"));
    }

@DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.status(200).body(new Api("delete success"));
    }


    public ResponseEntity getByName(@PathVariable String name){
        List<Category>categories=categoryService.findByname(name);
        return ResponseEntity.status(200).body(categories);
    }




}
