package com.shop.ShopApplication.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Shops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private Users user;

    @Column(nullable = false, unique = true)
    private String shopName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String shopImg;
    @Column(nullable = false)
    private LocalDateTime dateJoined;

    @OneToMany(mappedBy = "shop")
    @JsonBackReference
    private List<UserFollowers> shopFollowing;

    @OneToMany(mappedBy = "followerShop")
    @JsonBackReference
    private List<ShopFollowers> followerShop;

    @OneToMany(mappedBy = "followedShop")
    @JsonBackReference
    private List<ShopFollowers> followingShop;
}
