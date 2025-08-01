package com.shop.ShopApplication.Service;

import com.shop.ShopApplication.Entity.UserPrincipal;
import com.shop.ShopApplication.Entity.Users;
import com.shop.ShopApplication.Repository.JpaRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
// Spring automatically detects that this is the implementation of your UserDetailsService because of @Service
public class UserDetailService implements UserDetailsService {

    private UserRepository repo;

    // The spring will look for this method to check how the user should be verified.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
        // We created a class of UserPrincipal since UserDetails is an interface.
        return new UserPrincipal(user);
    }
}
