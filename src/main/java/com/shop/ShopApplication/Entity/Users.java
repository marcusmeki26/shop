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
    private String userId;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;

    // To enable derived method naming convention
    @OneToMany(mappedBy = "ownerId")
    @JsonBackReference
    private List<Products> productsList;

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
