package com.shop.ShopApplication.Controller;

import com.shop.ShopApplication.Dto.RefreshTokenDto;
import com.shop.ShopApplication.Dto.RegisterDto;
import com.shop.ShopApplication.Dto.LoginDto;
import com.shop.ShopApplication.Service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public RegisterDto register(@Valid @RequestBody RegisterDto user){
        return authService.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginDto user){
        return authService.verify(user);
    }

    @PostMapping("/refresh")
    public Map<String, String> getAccessToken(@RequestBody RefreshTokenDto refreshToken){
        return authService.verifyRefreshToken(refreshToken.getRefreshToken()); // make the AuthService return access token which is in Map form
    }

}
