package com.shop.ShopApplication.Controller;

import com.shop.ShopApplication.Dto.RefreshTokenDto;
import com.shop.ShopApplication.Dto.UserCredentialsDto;
import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public UserCredentialsDto register(@RequestBody Users user){
        return authService.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserCredentialsDto user){
        return authService.verify(user);
    }

    @PostMapping("/refresh")
    public Map<String, String> getAccessToken(@RequestBody RefreshTokenDto refreshToken){
        return authService.verifyRefreshToken(refreshToken.getRefreshToken()); // make the AuthService return access token which is in Map form
    }

}
