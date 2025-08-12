package com.shop.ShopApplication.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopDto {
    private String shopName;
    private String phoneNumber;
    private String description;
    private String shopImg;
}
