package com.ballistic.security.Securitytest.service;

import com.ballistic.security.Securitytest.dto.UserDetailsDTO;
import com.ballistic.security.Securitytest.model.Role;
import com.ballistic.security.Securitytest.model.User;
import com.ballistic.security.Securitytest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lycus 01 on 6/29/2017.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserDetailsDTO(userRepository.findByEmail(email));
    }
}
