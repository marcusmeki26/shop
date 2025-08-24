package com.shop.ShopApplication.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shop.ShopApplication.Entity.Embedded.ShopFollowShop;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shop_followers")
@Setter
@Getter
public class ShopFollowers {
    @EmbeddedId
    private ShopFollowShop id;

    @Column(nullable = false)
    private LocalDateTime followedAt;

    @MapsId("followerShopId")
    @ManyToOne
    @JoinColumn(name="follower_shop_id", nullable = false)
    @JsonManagedReference
    private Shops followerShop;

    @MapsId("followedShopId")
    @ManyToOne
    @JoinColumn(name="followed_shop_id", nullable = false)
    @JsonManagedReference
    private Shops followedShop;
}
