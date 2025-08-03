package com.shop.ShopApplication.Strategies.Class;

import com.shop.ShopApplication.Dto.RegisterUserDto;
import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Interface.Registerable;
import com.shop.ShopApplication.Mapper.UserMapper;
import com.shop.ShopApplication.Repository.JpaRepository.UserRepository;
import com.shop.ShopApplication.Strategies.Interface.RegisterableStrategy;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserStrategy implements RegisterableStrategy {

    private UserRepository repository;
    private UserMapper mapper;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public boolean supports(String user) {
        return "user".equalsIgnoreCase(user);
    }

    @Override
    public Registerable process(Registerable user) {
        RegisterUserDto registerUser = (RegisterUserDto) user;
        registerUser.setPassword(encoder.encode(registerUser.getPassword()));
        return registerUser;
    }

    @Override
    public Registerable save(Registerable user) {
        RegisterUserDto registerUser = (RegisterUserDto) user; // Cast to RegisterUserDto
        repository.save(mapper.RegisterUserDtoToUser(registerUser)); // Maps and saves the user
        return registerUser;
    }
}
