package com.shop.ShopApplication.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;

    // To enable derived method naming convention
    @OneToMany(mappedBy = "ownerId")
    @JsonBackReference // Prevents Infinite recursion
    private List<Products> productsList;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private Shops shop;

    // Called when .save() method is used
    @PrePersist
    public void prePersist(){
        if(userId == null){
            userId = UUID.randomUUID().toString();
        }

        if(!role.startsWith("ROLE_")){
            role = "ROLE_" + role.toUpperCase();
        }
    }
}
