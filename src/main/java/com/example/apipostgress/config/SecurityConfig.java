package com.example.apipostgress.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.apipostgress.config.Jwt.JwtAuthenticationFilter;
import com.example.apipostgress.config.Jwt.JwtAutorizationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    private JwtAutorizationFilter jwtAutorizationFilter;
    
    // @Bean
    // CorsConfigurationSource corsConfigurationSource() {
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.applyPermitDefaultValues();
    //     config.setAllowedOrigins(Arrays.asList("https://www.fivedrive.com.ar/"));
    //     config.setAllowedOrigins(Arrays.asList("http://127.0.0.1:8080"));
    //     config.addAllowedMethod("*");
    //     config.addAllowedHeader("*");
    //     source.registerCorsConfiguration("/**", config);
    //     return source;
    // }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/auth");

        return http
                .csrf().disable()
                .authorizeRequests()
                    .requestMatchers("/api/users", "/api/posts", "/api/auth").permitAll() 
                    .anyRequest().authenticated()
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAutorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors()
                .and()
                .build();
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

}
