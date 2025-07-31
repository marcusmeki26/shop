package com.shop.ShopApplication.Repository;

import com.shop.ShopApplication.Dto.ProductDto;
import com.shop.ShopApplication.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
//    List<Products> findByUser_id(Integer id); // findByUser the 'User' should match the implement object name inside Product entity and the name after _ should match the column in which entity it has relationship.
    List<Products> findByProductNameLikeOrProductNameLike(String productName1, String productName2);
    List<Products> findByProductNameLike(String productName);
    List<Products> findByCategoryId_Id(Integer id);
    @Query("SELECT new com.shop.ShopApplication.Dto.ProductDto(p.productId, p.productName, p.imagePath, p.price, o.id, c.id) " +
            "FROM Products p " +
            "LEFT JOIN p.ownerId o " +
            "JOIN p.categoryId c " +
            "WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "AND c.categoryName = :categoryName")
    List<ProductDto> findByCategoryIdAndProductId(@Param("keyword") String keyword,@Param("categoryName") String categoryName);
}
