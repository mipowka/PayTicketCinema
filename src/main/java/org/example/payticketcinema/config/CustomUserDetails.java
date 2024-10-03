package org.example.payticketcinema.config;


import org.example.payticketcinema.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    List<GrantedAuthority> grantedRoles;

    public CustomUserDetails(List<GrantedAuthority> grantedRoles, String password, String username) {
        this.grantedRoles = grantedRoles;
        this.password = password;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedRoles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
