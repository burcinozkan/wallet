package com.burcin.wallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Postman/Swagger testleri için CSRF'i kapatıyoruz
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // ŞİMDİLİK HER ŞEYE İZİN VER (Login ekranı gelmez)
            )
            .headers(headers -> headers.frameOptions(frame -> frame.disable())); // H2 konsol kullanırsan lazım olur
            
        return http.build();
    }
}