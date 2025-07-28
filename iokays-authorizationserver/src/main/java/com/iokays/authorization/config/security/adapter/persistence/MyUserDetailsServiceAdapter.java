package com.iokays.authorization.config.security.adapter.persistence;

import com.iokays.authorization.core.application.service.UserApplicationService;
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
        final var user = userApplicationService.findUserAuthInfoByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户没有找到");
        }
        return User.withUsername(user.username().code())
                .password(user.password().getValue())
                .roles("USER")
                .disabled(!user.enabled())
                .authorities(user.authorities().toArray(new String[]{}))
                .build();
    }
}
