package com.shop.ShopApplication.Dto.Products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class forOwnerProductDto extends ProductDto{
    private Integer quantity;
    private LocalDateTime dateAdded;
}
