package com.shop.ShopApplication.Resolver;

import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Exception.ResourceNotFoundException;
import com.shop.ShopApplication.Repository.JpaRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserResolver {
    UserRepository repository;

    public Integer map(Users user){
        Users fetchedUser = repository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist", HttpStatus.NOT_FOUND.value()));

        return fetchedUser.getId();
    }
}
