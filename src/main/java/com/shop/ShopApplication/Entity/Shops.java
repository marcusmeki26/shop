package com.shop.ShopApplication.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private Users userId;

    @Column(nullable = false, unique = true)
    private String shopName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String shopImg;
}
