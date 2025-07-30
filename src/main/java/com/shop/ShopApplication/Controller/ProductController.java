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
    public List<ProductDto> getProducts(@RequestParam(required = false) String keyword){
        if(keyword != null){
            return productService.getProductsByName(keyword);   
        }else{
            return productService.getProducts();
        }
    }
}
