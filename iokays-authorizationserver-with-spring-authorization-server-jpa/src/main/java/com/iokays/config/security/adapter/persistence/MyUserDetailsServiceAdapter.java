package com.iokays.config.security.adapter.persistence;

import com.iokays.core.application.service.UserApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MyUserDetailsServiceAdapter implements UserDetailsService {

    private final UserApplicationService userApplicationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userApplicationService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户没有找到");
        }

        return User.withUsername(user.username())
                .password(user.password().getValue())
                .roles("USER")
                .disabled(!user.enabled())
                .build();
    }
}
