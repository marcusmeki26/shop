package com.shop.ShopApplication.Controller;

import com.shop.ShopApplication.Dto.ShopDto;
import com.shop.ShopApplication.Entity.Shops;
import com.shop.ShopApplication.Service.ShopService;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/shop")
public class ShopController {

    private ShopService shopService;

    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    @GetMapping
    public ShopDto getShop(){
        return shopService.getShop(); 
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/image/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename){
        return shopService.getImage(filename);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping
    public ResponseEntity<Void> updateShop(@RequestBody ShopDto shop){
        return shopService.updateShop(shop);
    }
}
