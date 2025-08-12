package com.shop.ShopApplication.Mapper;


import com.shop.ShopApplication.Dto.RegisterOwnerDto;
import com.shop.ShopApplication.Dto.RegisterUserDto;
import com.shop.ShopApplication.Entity.Shops;
import com.shop.ShopApplication.Entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Users RegisterUserDtoToUser(RegisterUserDto user);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    Users RegisterOwnerDetailsToUser(RegisterOwnerDto owner);

    @Mapping(target = "shopName", source = "shopName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "shopImg", source = "shopImg")
    Shops RegisterOwnerShopDetailsToShop(RegisterOwnerDto registerOwner);
}
