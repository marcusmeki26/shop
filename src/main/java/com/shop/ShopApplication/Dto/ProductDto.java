package com.shop.ShopApplication.Dto;

import com.shop.ShopApplication.Entity.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Integer productId;
    private String productName;
    private String imagePath;
    private Float price;
    private Integer ownerId;
    private Integer categoryId;
}
