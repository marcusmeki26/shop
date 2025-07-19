package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        ); // The authenticate method will call the AuthenticationProvider and verify if the user is valid. The Authentication object will receive the UserPrincipal if there is a user.

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername()); // calling the method to generate the token.

        return "Failed";
    }
}
