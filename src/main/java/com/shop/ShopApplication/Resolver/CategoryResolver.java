package com.shop.ShopApplication.Resolver;

import com.shop.ShopApplication.Entity.Category;
import com.shop.ShopApplication.Exception.ResourceNotFoundException;
import com.shop.ShopApplication.Repository.JpaRepository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryResolver {
    CategoryRepository repository;

    public Integer map(Category category){
        Category fetchedCategory = repository.findById(category.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category does not exist", HttpStatus.NOT_FOUND.value()));

        return fetchedCategory.getId();
    }
}
