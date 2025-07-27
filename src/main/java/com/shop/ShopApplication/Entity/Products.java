package com.shop.ShopApplication.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String imagePath;
    private Float price;

    // Creates a column for the owner_id FK
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonManagedReference
    private Users ownerId;

    // Creates a column for category_id FK
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category categoryId;
}
