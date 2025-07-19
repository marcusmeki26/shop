package com.shop.ShopApplication.Controller;

import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/user") // this returns all the user from the database. Move to UserController
    public List<Users> getUsers(){
        return userService.getUsers();
    }
}
