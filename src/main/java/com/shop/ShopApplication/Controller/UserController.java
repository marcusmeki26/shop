package com.shop.ShopApplication.Controller;

import com.shop.ShopApplication.Dto.RoleDto;
import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Users> getUsers(@RequestParam String role){
        return userService.getUsers(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable("userId") String input,
                            @RequestParam String role){
        return userService.getUserById(input, role);
    }
}
