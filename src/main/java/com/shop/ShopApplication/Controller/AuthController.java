package com.shop.ShopApplication.Controller;

import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Service.AuthService;
import com.shop.ShopApplication.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register") // move this to AuthController
    public Users register(@RequestBody Users user){
        return authService.register(user);
    }

    @PostMapping("/login") // move this to AuthController
    public String login(@RequestBody Users user){
        return authService.verify(user);
    }
}
