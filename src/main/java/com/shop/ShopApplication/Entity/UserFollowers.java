package com.shop.ShopApplication.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shop.ShopApplication.Entity.Embedded.UserFollowShop;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_followers")
@Getter
@Setter
public class UserFollowers {

    // Used for composite PK
    @EmbeddedId
    private UserFollowShop id;

    @Column(nullable = false)
    private LocalDateTime followedAt;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @JsonManagedReference
    private Users user;

    @MapsId("shopId")
    @ManyToOne
    @JoinColumn(name="shop_id", nullable = false)
    @JsonManagedReference
    private Shops shop;
}
