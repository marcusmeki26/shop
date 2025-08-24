package com.shop.ShopApplication.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String imagePath;
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private LocalDateTime dateAdded;

    @Column(name = "owner_id", insertable = false, updatable = false)
    private Integer ownerId;
    // Creates a column for the owner_id FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonManagedReference
    private Users owner;

    // Creates a column for category_id FK
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonManagedReference
    private Category categoryId;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<Favorites> favorites;
}
