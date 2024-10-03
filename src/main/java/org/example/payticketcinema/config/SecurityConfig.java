package org.example.payticketcinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private static final String [] publicMatchers = {
//            "",
//            "/",
//            "/login"
//            ,"/register"
//    };

    @Autowired
    private DataSource dataSource;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET,"/login","/register").permitAll()
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

        http.logout(login ->
                login.logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("remove")
                        .permitAll()
        );


        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService){
        var daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

/*
cinema list to do
add ticket to do
 */
}
