package com.shop.ShopApplication.Dto;

import com.shop.ShopApplication.Entity.Users;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterDto {
    @NotEmpty(message="Username should not be empty")
    private String username;
    @NotEmpty(message="Password should not be empty")
    private String password;
    @NotEmpty(message="Please include a role")
    private String role;

    public static RegisterDto toUser(Users user){
        RegisterDto userDto = new RegisterDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());

        return userDto;
    }

    public static Users toUser(RegisterDto user) {
        Users registerUser = new Users();
        registerUser.setUsername(user.getUsername());
        registerUser.setPassword(user.getPassword());
        registerUser.setRole(user.getRole());

        return registerUser;
    }
}
