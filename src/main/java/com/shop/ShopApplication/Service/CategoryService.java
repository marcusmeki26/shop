package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Entity.Category;
import com.shop.ShopApplication.Repository.JpaRepository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository repository;

    // Used for fetching all categories
    public List<Category> getCategories() {
        return repository.findAll();
    }
}
