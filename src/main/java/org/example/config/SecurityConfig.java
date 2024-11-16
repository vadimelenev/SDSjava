package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")  // Настраиваем кастомную страницу входа
                        .defaultSuccessUrl("/students", true)  // Перенаправление после успешной аутентификации
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/students/**").authenticated()
                        .anyRequest().permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}