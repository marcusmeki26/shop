package com.shop.ShopApplication.Controller;

import com.shop.ShopApplication.Dto.RefreshTokenDto;
import com.shop.ShopApplication.Dto.RegisterOwnerDto;
import com.shop.ShopApplication.Dto.RegisterUserDto;
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

    @PostMapping("/register/user")
    public RegisterUserDto registerUser(@Valid @RequestBody RegisterUserDto user){
        return (RegisterUserDto) authService.register(user);
    }

    @PostMapping("/register/owner")
    public RegisterOwnerDto registerOwner(@Valid @RequestBody RegisterOwnerDto user){
        return (RegisterOwnerDto) authService.register(user);
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
