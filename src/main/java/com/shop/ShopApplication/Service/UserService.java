package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repo;

    // used for fetching all user
    public List<Users> getUsers() {
        return repo.findAll();
    }

    // used for fetching a user by id
    public Users getUserById(String userId) {
        return repo.findByUserId(userId);
    }
}
