package com.shop.ShopApplication.Controller;

import com.shop.ShopApplication.Dto.RefreshTokenDto;
import com.shop.ShopApplication.Dto.RegisterOwnerDto;
import com.shop.ShopApplication.Dto.RegisterUserDto;
import com.shop.ShopApplication.Dto.LoginDto;
import com.shop.ShopApplication.Service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register/user")
    public RegisterUserDto registerUser(@Valid @RequestBody RegisterUserDto user){
        return authService.registerUser(user);
    }

    @PostMapping(value = "/register/owner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> registerOwner(
            @RequestParam("file") MultipartFile file,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role,
            @RequestParam("shopName") String shopName,
            @RequestParam("description") String description,
            @RequestParam("phoneNumber") String phoneNumber,
            @Valid RegisterOwnerDto user,
            BindingResult bindingResult){

        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setShopName(shopName);
        user.setDescription(description);
        user.setPhoneNumber(phoneNumber);

        if(!bindingResult.hasErrors()) {
            return authService.registerOwner(file, user);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
