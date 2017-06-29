package com.ballistic.security.Securitytest.dto;

import com.ballistic.security.Securitytest.model.Role;
import com.ballistic.security.Securitytest.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lycus 01 on 6/29/2017.
 */
public class UserDetailsDTO implements UserDetails {

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsDTO( User byUsername) {
        this.email = byUsername.getEmail();
        this.password = byUsername.getPassword();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : byUsername.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().toUpperCase()));
        }
        this.authorities = grantedAuthorities;

        return;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
