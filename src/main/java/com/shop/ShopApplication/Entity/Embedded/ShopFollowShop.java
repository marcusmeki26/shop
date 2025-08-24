package com.shop.ShopApplication.Entity.Embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopFollowShop {
    @Column(name = "follower_shop_id")
    private Integer followerShopId;
    @Column(name = "followed_shop_id")
    private Integer followedShopId;
}
