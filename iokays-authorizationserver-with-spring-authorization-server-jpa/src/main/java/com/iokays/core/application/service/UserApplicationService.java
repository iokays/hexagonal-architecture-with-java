package com.iokays.core.application.service;

import com.iokays.core.application.service.usecase.user.UserRegistration;
import com.iokays.core.domain.user.User;
import com.iokays.core.domain.user.UserId;
import com.iokays.core.domain.user.UserInfo;
import com.iokays.core.domain.user.UserRepository;
import com.iokays.core.domain.user.command.RegisterUser;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserApplicationService implements UserRegistration {

    private final UserRepository userRepository;

    @Cacheable(value = "UserInfoByUsername", key = "#username")
    public UserInfo findByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findByUsername(username);
        if (null == user) {
            return null;
        }
        return user.toUserInfo();
    }

    @Override
    @CacheEvict(cacheNames = "UserInfoByUsername", key = "#cmd.username")
    public UserId registerUser(RegisterUser cmd) {
        final var username = cmd.username();
        this.checkAlreadyExists(username);

        final var newUser = new User(username, cmd.password());
        userRepository.save(newUser);

        return newUser.getUserId();
    }

    private void checkAlreadyExists(final String username) {
        final var user = userRepository.findByUsername(username);
        Validate.isTrue(user == null, "用户名已存在");
    }


}
