package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Dto.RegisterUserDto;
import com.shop.ShopApplication.Dto.LoginDto;
import com.shop.ShopApplication.Entity.UserPrincipal;
import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Interface.Registerable;
import com.shop.ShopApplication.Repository.JpaRepository.UserRepository;
import com.shop.ShopApplication.Strategies.Interface.RegisterableStrategy;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    
    private AuthenticationManager authManager;
    private JWTService jwtService;
    private UserRepository repo;
    private List<RegisterableStrategy> strategies; // used final to be excluded from the @AllArgsConstructor

    // used for inserting a user
    public Registerable register(Registerable user) {
        RegisterableStrategy strategy = strategies.stream()
                .filter(s -> s.supports(user.getRole()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported Role"));

        Registerable processedUser = strategy.process(user);
        return strategy.save(processedUser);
    }

    // used for verifying a user during login
    public Map<String, String> verify(LoginDto user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        ); // The authenticate method will call the AuthenticationProvider and verify if the user is valid. The Authentication object will receive the UserPrincipal if there is a user. Will go to UserDetailService

        if(authentication.isAuthenticated()){ // if the user exist and valid
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            return jwtService.generateToken(userPrincipal.getUsername(), userPrincipal.getUserId(), userPrincipal.getAuthorities()); // calling the method to generate the token.
        }

        return Map.of("Failed", "Wrong User");
    }

    // make this method return a fresh access token in map form.
    public Map<String, String> verifyRefreshToken(String refreshToken) {
        String userId = jwtService.validateRefreshToken(refreshToken); // fetches the user id first to avoid null exceptions

        // checks if the userId is null
        if(userId == null)
            return Map.of("Failed", "Invalid Refresh Token");

        Users user = repo.findByUserId(userId);
        if(user == null)
            return Map.of("Failed", "No Users Found");

        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
        return Map.of("accessToken", jwtService.createAccessToken(user.getUsername(), authorities));
    }
}
