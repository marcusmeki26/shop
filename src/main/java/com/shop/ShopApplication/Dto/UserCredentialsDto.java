package com.shop.ShopApplication.Dto;

import com.shop.ShopApplication.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserCredentialsDto {
    private String username;
    private String password;

    public static UserCredentialsDto toUserDto(Users user){
        UserCredentialsDto userDto = new UserCredentialsDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        return userDto;
    }
}
