package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Dto.RegisterOwnerDto;
import com.shop.ShopApplication.Dto.RegisterUserDto;
import com.shop.ShopApplication.Dto.LoginDto;
import com.shop.ShopApplication.Entity.UserPrincipal;
import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Exception.ResourceNotFoundException;
import com.shop.ShopApplication.Interface.Registerable;
import com.shop.ShopApplication.Mapper.UserMapper;
import com.shop.ShopApplication.Repository.JpaRepository.ShopRepository;
import com.shop.ShopApplication.Repository.JpaRepository.UserRepository;
import com.shop.ShopApplication.Strategies.Interface.RegisterableStrategy;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authManager;
    private JWTService jwtService;
    private UserRepository userRepo;
    private List<RegisterableStrategy> strategies; // used final to be excluded from the @AllArgsConstructor

    // used for inserting a user
    public RegisterUserDto registerUser(RegisterUserDto user) {
        return (RegisterUserDto) this.register(user);
    }

    // used for inserting an owner
    public ResponseEntity<Void> registerOwner(MultipartFile file, RegisterOwnerDto user){
        if(file.isEmpty()){
            throw new ResourceNotFoundException("No field exists", HttpStatus.NO_CONTENT.value());
        }

        try {
            // Ensure the upload directory exists
            String UPLOAD_DIR = "/upload/shop-image";
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Get the original filename and create a unique filename to avoid conflicts
            String originalFilename = file.getOriginalFilename();
            String uniqueFilename = user.getUsername() + "_"  + System.currentTimeMillis() + "_" + originalFilename;
            Path filePath = uploadPath.resolve(uniqueFilename);

            // Save the file to the local storage
            file.transferTo(filePath.toAbsolutePath());

            // sets the file name and passing to register method
            user.setShopImg(uniqueFilename);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        this.registerUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Registerable register(Registerable user) {
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

        Users user = userRepo.findByUserId(userId);
        if(user == null)
            return Map.of("Failed", "No Users Found");

        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
        return Map.of("accessToken", jwtService.createAccessToken(user.getUsername(), authorities));
    }
}
