package com.shop.ShopApplication.Mapper;

import com.shop.ShopApplication.Dto.ProductDto;
import com.shop.ShopApplication.Entity.Products;
import com.shop.ShopApplication.Resolver.CategoryResolver;
import com.shop.ShopApplication.Resolver.UserResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        CategoryResolver.class,
        UserResolver.class
})
public interface ProductMapper {

    List<ProductDto> toProductDto(List<Products> products);
}
