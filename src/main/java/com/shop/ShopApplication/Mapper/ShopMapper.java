package com.shop.ShopApplication.Mapper;

import com.shop.ShopApplication.Dto.ShopDto;
import com.shop.ShopApplication.Entity.Shops;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    @Mapping(target = "shopName", source = "shopName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "shopImg", source = "shopImg")
    ShopDto shopToShopDto(Shops shop);
}
