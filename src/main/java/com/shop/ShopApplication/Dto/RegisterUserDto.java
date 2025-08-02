package com.shop.ShopApplication.Dto;

import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Interface.Registerable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterUserDto implements Registerable {
    @NotEmpty(message="Username should not be empty")
    private String username;
    @NotEmpty(message="Password should not be empty")
    private String password;
    @NotEmpty(message="Please include a role")
    private String role;

    // Move to a mapper
    public static RegisterUserDto toUser(Users user){
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());

        return userDto;
    }

    public static Users toUser(RegisterUserDto user) {
        Users registerUser = new Users();
        registerUser.setUsername(user.getUsername());
        registerUser.setPassword(user.getPassword());
        registerUser.setRole(user.getRole());

        return registerUser;
    }
}
