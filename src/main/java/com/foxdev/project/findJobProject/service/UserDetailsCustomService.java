package com.foxdev.project.findJobProject.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component("userDetailsService")
public class UserDetailsCustomService implements UserDetailsService {
    private final UserService userService;

    public UserDetailsCustomService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.foxdev.project.findJobProject.domain.User currentUser = this.userService.getUserByEmail(username);

        if (currentUser == null) {
            throw new UsernameNotFoundException("Bad credentials.");
        }

        return new User(
                currentUser.getName(),
                currentUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
