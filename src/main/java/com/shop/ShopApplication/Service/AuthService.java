package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    
    private AuthenticationManager authManager;
    private JWTService jwtService;
    private UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // used final to be excluded from the @AllArgsConstructor

    // used for inserting a user
    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword())); // This sets the password field as the encrypted password.
        return repo.save(user);
    }

    // used for verifying a user during login
    public Map<String, String> verify(Users user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        ); // The authenticate method will call the AuthenticationProvider and verify if the user is valid. The Authentication object will receive the UserPrincipal if there is a user.

        if(authentication.isAuthenticated()){
            Users userData = repo.findByUsername(user.getUsername());
            return jwtService.generateToken(userData.getUsername(), userData.getId()); // calling the method to generate the token.
        }

        return Map.of("Failed", "Wrong User");
    }

    // make this method return a fresh access token in map form.
    public Map<String, String> verifyRefreshToken(String refreshToken) {
        String userId = jwtService.validateRefreshToken(refreshToken); // fetches the user id first to avoid null exceptions

        // checks if the userId is null
        if(userId == null)
            return Map.of("Failed", "Invalid Refresh Token");

        Users user = repo.findById(userId);
        if(user == null)
            return Map.of("Failed", "No Users Found");

        return Map.of("access_token", jwtService.createAccessToken(user.getUsername()));
    }
}
