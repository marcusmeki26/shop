package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Dto.ProductDto;
import com.shop.ShopApplication.Entity.Products;
import com.shop.ShopApplication.Mapper.ProductMapper;
import com.shop.ShopApplication.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository repository;
    private ProductMapper prodMapper;

    public List<ProductDto> getProducts(){
        List<Products> products =  repository.findAll(); // Fetches all the product
        return prodMapper.toProductDto(products); // Converts the List<Products> into List<ProductDto>
    }
}
