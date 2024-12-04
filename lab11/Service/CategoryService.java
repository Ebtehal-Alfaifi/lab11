package com.example.lab11.Service;

import com.example.lab11.ApiResponse.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category>getAll(){
        return categoryRepository.findAll();
    }

    public Category addNew(Category category){
        return categoryRepository.save(category);

    }

    public Category updateAll(Integer id,Category category){
        Category old= categoryRepository.getByid(id);
        if (old==null){
            throw new ApiException("category not found");
        }
        old.setName(category.getName());
        return categoryRepository.save(old);

    }

public void delete(Integer id){
        Category category= categoryRepository.getByid(id);
        if (category==null){
            throw new ApiException(" category not found");
        }
        categoryRepository.delete(category);
}


public List<Category> findByname(String name){
        List<Category>categoryList= categoryRepository.findCategoriesByName(name);
        if (categoryList==null){
            throw new ApiException("not founded");
        }
        return categoryList;


}

}
