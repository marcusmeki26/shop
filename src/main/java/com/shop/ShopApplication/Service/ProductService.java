package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Dto.ProductDto;
import com.shop.ShopApplication.Entity.Products;
import com.shop.ShopApplication.Mapper.ProductMapper;
import com.shop.ShopApplication.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository repository;
    private ProductMapper prodMapper;

    // Fetches all the products
    public List<ProductDto> getProducts(){
        List<Products> products =  repository.findAll(); // Fetches all the product
        return prodMapper.toProductDto(products); // Converts the List<Products> into List<ProductDto>
    }

    // Fetches all the products with matching name
    public List<ProductDto> getProductsByName(String productName) {
        List<Products> products;

        String searchPattern = "%" + productName + "%";

        // If the passed input is plural
        if(productName.endsWith("s")){
            String singularForm = "%" + productName.substring(0, productName.length()-1) + "%";
            products = repository.findByProductNameLikeOrProductNameLike(singularForm, searchPattern);
        }else if(productName.contains("-")){
            String newForm = "%" + productName.replace("-", "") + "%";
            products = repository.findByProductNameLikeOrProductNameLike(newForm, searchPattern);
        }else{
            // If the passed input is not plural
            products = repository.findByProductNameLike(searchPattern);
        }


        return prodMapper.toProductDto(products);
    }
}
