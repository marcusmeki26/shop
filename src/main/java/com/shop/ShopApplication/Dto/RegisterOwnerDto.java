package com.shop.ShopApplication.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.ShopApplication.Interface.Registerable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterOwnerDto extends RegisterUserDto implements Registerable {

    @NotEmpty(message = "Shop name should not be empty")
    private String shopName;
    @NotEmpty(message = "Shop's description should not be empty")
    private String description;
    @NotEmpty(message = "Phone number should not be empty")
    private String phoneNumber;
    @JsonIgnore
    private String shopImg;
}
