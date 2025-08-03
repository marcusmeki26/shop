package com.shop.ShopApplication.Strategies.Class;

import com.shop.ShopApplication.Dto.RegisterOwnerDto;
import com.shop.ShopApplication.Entity.Shops;
import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Interface.Registerable;
import com.shop.ShopApplication.Mapper.UserMapper;
import com.shop.ShopApplication.Repository.JpaRepository.ShopRepository;
import com.shop.ShopApplication.Repository.JpaRepository.UserRepository;
import com.shop.ShopApplication.Strategies.Interface.RegisterableStrategy;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OwnerStrategy implements RegisterableStrategy {

    private UserRepository userRepository;
    private ShopRepository shopRepository;
    private UserMapper mapper;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public boolean supports(String user) {
        return "owner".equalsIgnoreCase(user);
    }

    @Override
    public Registerable process(Registerable user) {
        RegisterOwnerDto registerOwner = (RegisterOwnerDto) user;
        registerOwner.setPassword(encoder.encode(registerOwner.getPassword()));
        return registerOwner;
    }

    @Override
    public Registerable save(Registerable owner) {
        RegisterOwnerDto registerOwner = (RegisterOwnerDto) owner; // creates a RegisterOwnerDto object

        // Maps to user and saves it
        Users registerUser = mapper.RegisterOwnerDetailsToUser(registerOwner);
        Users savedUser = userRepository.save(registerUser);

        // Maps to shop and saves it
        Shops shopDetails = mapper.RegisterOwnerShopDetailsToShop(registerOwner);
        shopDetails.setUser(savedUser);
        shopRepository.save(shopDetails);

        return registerOwner;
    }
}
