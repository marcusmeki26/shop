package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Exception.ResourceNotFoundException;
import com.shop.ShopApplication.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repo;

    // used for fetching all user
    public List<Users> getUsers(String role){
        if(!role.contains("ROLE_")){
            role = "ROLE_" + role;
        }

        return repo.findAllByRole(role);
    }

    // used for fetching a user by id
    public Users getUserById(String input, String role) {
        if(!role.contains("ROLE_")){
            role = "ROLE_" + role;
        }

        try{
            Integer id = Integer.parseInt(input);
            return repo.findByIdAndRole(id, role)
                    .orElseThrow(() -> new ResourceNotFoundException("No users found", HttpStatus.NOT_FOUND.value()));
        }catch(NumberFormatException e){
            return repo.findByUsernameAndRole(input, role)
                    .orElseThrow(() -> new ResourceNotFoundException("No users found", HttpStatus.NOT_FOUND.value()));
        }
    }
}
