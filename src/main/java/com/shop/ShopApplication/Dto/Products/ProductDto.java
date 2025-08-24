package com.shop.ShopApplication.Dto.Products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Integer productId;
    private String productName;
    private String imagePath;
    private BigDecimal price;
    private Integer ownerId;
    private Integer categoryId;
}
