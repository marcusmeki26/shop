package com.shop.ShopApplication.Entity.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Setter
@Getter
@ToString
@Document(indexName = "products")
    public class ProductDocument {
        @Id
        private String id;
        private String productName;
        private String imagePath;
        private Float price;
        private Integer ownerId; // denormalized owner id
        private Integer categoryId; // denormalized category id
        private String categoryName; // denormalized category name
    }
