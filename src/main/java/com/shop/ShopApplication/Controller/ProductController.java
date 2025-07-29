package com.shop.ShopApplication.Controller;

import com.shop.ShopApplication.Dto.ProductDto;
import com.shop.ShopApplication.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    // Used to fetch the products
    @GetMapping
    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{productName}")
    public List<ProductDto> getProductsByName(@PathVariable String productName){
        return productService.getProductsByName(productName);
    }
}
