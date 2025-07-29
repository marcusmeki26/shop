package com.shop.ShopApplication.Repository;

import com.shop.ShopApplication.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
//    List<Products> findByUser_id(Integer id); // findByUser the 'User' should match the implement object name inside Product entity and the name after _ should match the column in which entity it has relationship.
    List<Products> findByProductNameLikeOrProductNameLike(String productName1, String productName2);
    List<Products> findByProductNameLike(String productName);
}
