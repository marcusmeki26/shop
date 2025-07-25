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
public class UserCredentialsDto {
    @NotEmpty(message="Username should not be empty")
    private String username;
    @NotEmpty(message="Password should not be empty")
    private String password;
    @NotEmpty(message="Please include a role")
    private String role;

    public static UserCredentialsDto toUserDto(Users user){
        UserCredentialsDto userDto = new UserCredentialsDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public static Users toUser(UserCredentialsDto user) {
        Users registerUser = new Users();
        registerUser.setUsername(user.getUsername());
        registerUser.setPassword(user.getPassword());
        registerUser.setRole(user.getRole());

        return registerUser;
    }
}
