package com.shop.ShopApplication.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String categoryName;
    @Column(nullable = false)
    private String imagePath;

    @OneToMany(mappedBy = "categoryId")
    @JsonBackReference
    private List<Products> productsList;
}
