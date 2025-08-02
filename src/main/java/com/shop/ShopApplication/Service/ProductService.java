package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Dto.ProductDto;
import com.shop.ShopApplication.Entity.Category;
import com.shop.ShopApplication.Entity.Document.ProductDocument;
import com.shop.ShopApplication.Entity.Products;
import com.shop.ShopApplication.Exception.ResourceNotFoundException;
import com.shop.ShopApplication.Mapper.ProductMapper;
import com.shop.ShopApplication.Repository.ElasticRepository.ProductElasticRepository;
import com.shop.ShopApplication.Repository.JpaRepository.CategoryRepository;
import com.shop.ShopApplication.Repository.JpaRepository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper prodMapper;
    private CategoryRepository categoryRepository;
    private ProductElasticRepository productElasticRepository;

    // Fetches all the products
    public List<ProductDto> getProducts(){
        List<Products> products = productRepository.findAll(); // Fetches all the product
        return prodMapper.fromProductToProductDto(products); // Converts the List<Products> into List<ProductDto>
    }

    // Fetches all the products with matching name
    public List<ProductDto> getProductsByName(String productName) {
        List<ProductDocument> fuzzyData = productElasticRepository.findByProductNameFuzzy(productName);

        return prodMapper.fromProductDocumentToProductDto(fuzzyData);
    }

    // Fetches products based on their category
    public List<ProductDto> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);

        if(category == null){
            throw new ResourceNotFoundException("No Category Found", HttpStatus.NOT_FOUND.value());
        }

        List<Products> products = productRepository.findByCategoryId_Id(category.getId());

        return prodMapper.fromProductToProductDto(products);
    }

    // Fetches products based on their category and product name
    public List<ProductDto> getProductsByCategoryAndName(String categoryName, String keyword) {
        List<ProductDocument> fuzzyData = productElasticRepository.findByCategoryNameAndProductNameFuzzy(keyword, categoryName);
        return prodMapper.fromProductDocumentToProductDto(fuzzyData);
    }
}
