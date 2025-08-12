package com.shop.ShopApplication.Repository.JpaRepository;

import com.shop.ShopApplication.Entity.Shops;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shops, Integer> {
    Optional<Shops> findByUserId(Integer ownerId);
}
