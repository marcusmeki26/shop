package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Entity.UserPrincipal;
import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repo;

    // used for fetching the user
    public List<Users> getUsers() {
        return repo.findAll();
    }

}
