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
    @PostMapping
    public List<Users> getUsers(@RequestBody RoleDto role){
        return userService.getUsers(role.getRole());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{userId}")
    public Users getUserById(@PathVariable("userId") String input,
                            @RequestBody RoleDto role){
        return userService.getUserById(input, role.getRole());
    }
}
