package com.shop.ShopApplication.Repository.JPA;

import com.shop.ShopApplication.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
//    List<Products> findByUser_id(Integer id); // findByUser the 'User' should match the implement object name inside Product entity and the name after _ should match the column in which entity it has relationship.
}
