package com.shop.ShopApplication.Repository;

import com.shop.ShopApplication.Dto.RoleDto;
import com.shop.ShopApplication.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    Users findByUserId(String userId);
    List<Users> findAllByRole(String role);
    Optional<Users> findByIdAndRole(Integer id, String role);
    Optional<Users> findByUsernameAndRole(String input, String role);
}

