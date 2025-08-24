package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Dto.ShopDto;
import com.shop.ShopApplication.Entity.Shops;
import com.shop.ShopApplication.Entity.UserPrincipal;
import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Exception.ResourceNotFoundException;
import com.shop.ShopApplication.Mapper.ShopMapper;
import com.shop.ShopApplication.Repository.JpaRepository.ShopRepository;
import com.shop.ShopApplication.Repository.JpaRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@Service
public class ShopService {

    private ShopRepository shopRepository;
    private UserRepository userRepository;
    private ShopMapper shopMapper;

    public ShopDto getShop() {

        // if everything is correct return the shop
        return shopMapper.shopToShopDto(this.verifyUser());
    }

    public ResponseEntity<Void> updateShop(ShopDto shop) {
        Shops existingShop = this.verifyUser();
        existingShop.setShopName(shop.getShopName());
        existingShop.setDescription(shop.getDescription());
        existingShop.setPhoneNumber(shop.getPhoneNumber());
        existingShop.setShopImg(shop.getShopImg());

        shopRepository.save(existingShop);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Shops verifyUser(){
        // gets the id from the incoming request made by the user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Integer currentUser = userPrincipal.getId();

        Users user = userRepository.findById(currentUser) // looks for a user
                .orElseThrow(() ->  new ResourceNotFoundException("No Resource Found", HttpStatus.NOT_FOUND.value()));

        return shopRepository.findByUserId(user.getId()) // looks for a shop that has the user id & returns if there is
                .orElseThrow(() -> new ResourceNotFoundException("No Resource Found", HttpStatus.NOT_FOUND.value()));
    }

    public ResponseEntity<Resource> getImage(String filename) {
        String path = "E:\\upload\\shop-image";
        try{
            Path imagePath = Paths.get(path).resolve(filename).normalize();
            Resource resource = new UrlResource(imagePath.toUri());
            String contentType = Files.probeContentType(imagePath);
            if(resource.exists()){
                return ResponseEntity.ok()
                        .contentType(new MediaType(MediaType.parseMediaType(contentType)))
                        .body(resource);
            }
            return ResponseEntity.ok().build();
        }catch (RuntimeException | IOException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
