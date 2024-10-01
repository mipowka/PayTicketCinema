package org.example.payticketcinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/login", "/register", "/").permitAll()
                .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()

                .requestMatchers(HttpMethod.GET, "/lk", "/lk/my-ticket").authenticated()
                .requestMatchers(HttpMethod.POST, "/lk", "/lk/my-ticket").authenticated()

                .requestMatchers(HttpMethod.GET, "/login-admin").hasRole("ADMIN")
                .anyRequest().permitAll()
        );

        http.csrf(AbstractHttpConfigurer::disable);


        http.formLogin(login ->
                login.loginPage("/login")
                        .loginProcessingUrl("/lk")
                        .failureUrl("/login-error")
                        .permitAll()
        );

        return http.build();
    }

/*
cinema list to do
add ticket to do 
 */
}
