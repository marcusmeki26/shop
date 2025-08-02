package com.shop.ShopApplication.Configuration;

import com.shop.ShopApplication.Entity.Document.ProductDocument;
import com.shop.ShopApplication.Entity.Products;
import com.shop.ShopApplication.Mapper.ProductMapper;
import com.shop.ShopApplication.Repository.ElasticRepository.ProductElasticRepository;
import com.shop.ShopApplication.Repository.JpaRepository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ElasticsearchDataSync {
    private ProductRepository productRepository;
    private ProductElasticRepository productElasticRepository;
    private ProductMapper productMapper;

    @EventListener(ApplicationReadyEvent.class)
    public void syncProducts(){
        List<Products> products = productRepository.findAll();

        List<ProductDocument> productDocuments = productMapper.fromProductToProductDocument(products);
            
        productElasticRepository.saveAll(productDocuments);
    }
}
