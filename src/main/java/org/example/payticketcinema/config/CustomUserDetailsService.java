package org.example.payticketcinema.config;

import lombok.RequiredArgsConstructor;
import org.example.payticketcinema.model.entity.User;
import org.example.payticketcinema.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final static String prefix = "ROLE_";


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        List<GrantedAuthority> roles = new ArrayList<>();

        String role = prefix + user.getRole();
        roles.add(new SimpleGrantedAuthority(role));

        CustomUserDetails customUserDetails =
                new CustomUserDetails(
                        roles,
                        user.getPassword(),
                        user.getUsername()
                        );

        return customUserDetails;
    }
}
