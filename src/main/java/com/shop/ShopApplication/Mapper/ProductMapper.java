package com.shop.ShopApplication.Mapper;

import com.shop.ShopApplication.Dto.ProductDto;
import com.shop.ShopApplication.Entity.Document.ProductDocument;
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

    List<ProductDto> fromProductToProductDto(List<Products> products);

    @Mapping(target = "id", expression = "java(String.valueOf(product.getProductId()))")
    @Mapping(target = "categoryName", expression = "java(product.getProductId() != null ? product.getCategoryId().getCategoryName() : null)")
    ProductDocument fromProductToProductDocument(Products product);

    List<ProductDocument> fromProductToProductDocument(List<Products> products);

    @Mapping(target = "productId", expression = "java(Integer.parseInt(productDocuments.getId()))")
    ProductDto fromProductDocumentToProductDto(ProductDocument productDocuments);
    List<ProductDto> fromProductDocumentToProductDto(List<ProductDocument> productDocuments);
}
