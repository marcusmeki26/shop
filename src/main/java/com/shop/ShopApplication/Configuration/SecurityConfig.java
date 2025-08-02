package com.shop.ShopApplication.Configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtFilter jwtFilter;

    // Overriding the default filter chain. Creating a custom configuration for filter chain.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable) // disables the csrf
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/login", "/register/user", "/register/user", "/refresh") // This removes the need for the user to be authenticated first before logging in. Basically you can perform a POST method without the need of being authenticated.
                        .permitAll()
                        .anyRequest().authenticated()) // tells the spring that every http request should be authenticated
                .httpBasic(Customizer.withDefaults()) // this lets you test endpoints quickly from tools like postman.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // this makes every http request stateless, meaning every request should contain a token.
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // before using the UsernamePasswordAuthenticationFilter go to JWTFilter first
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // include the cors configuration
                .build();
    }

    // This two beans identifies how you authenticate the username and password of the user.
    // Overriding the default Authentication. Creating a custom way on how to authenticate a user using DaoAuthenticationProvider.
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService); // Injecting UserDetailsService to retrieve user information like username, password, roles and, etc. Basically this acts like a bridge for the Dao to authenticate the user's data store.
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // This identifies how you should decrypt the hash for the password to be able to verify if the user is existing. Should match how you encrypt the password.

        return provider;
    }

    // Behind the scenes AuthenticationManager is calling AuthenticationProvider. This allows you to have login option.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // gets the object of AuthenticationManager.
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200")); // Allow your frontend origin
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // List of allowed methods
        config.setAllowedHeaders(List.of("*")); // Allowed headers
        config.setAllowCredentials(true); // Required for Authorization header or cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply to all endpoints.
        return source;
    }
}
