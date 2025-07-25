package com.shop.ShopApplication.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String userId;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;

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
