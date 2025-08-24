package com.shop.ShopApplication.Controller;

import com.shop.ShopApplication.Dto.Products.ProductDto;
import com.shop.ShopApplication.Dto.Products.forOwnerProductDto;
import com.shop.ShopApplication.Entity.UserPrincipal;
import com.shop.ShopApplication.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    // Used to fetch the products
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public List<ProductDto> getProducts(@RequestParam(required = false) String keyword){
        if(keyword != null){
            return productService.getProductsByName(keyword);
        }else{
            return productService.getProducts();
        }
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/owner")
    public List<forOwnerProductDto> getProductsPerOwner(){
        return productService.getProductsPerOwner();
    }

    @GetMapping("/{categoryName}")
    public List<ProductDto> getProductsByCategory(@PathVariable String categoryName,
                                                  @RequestParam(required = false) String keyword){
        if(keyword != null){
            return productService.getProductsByCategoryAndName(categoryName, keyword);
        }else {
            return productService.getProductsByCategory(categoryName);
        }
    }
}
