package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.application.service.usecase.user.UserRegistration;
import com.iokays.authorization.core.domain.user.*;
import com.iokays.authorization.core.domain.user.command.RegisterUser;
import com.iokays.common.core.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserApplicationService implements ApplicationService, UserRegistration {

    private final UserRepository userRepository;

    public UserInfo findByUsername(String username) {
        final var user = userRepository.findByUsername(Username.of(username));
        if (null == user) {
            return null;
        }
        return user.toUserInfo();
    }

    public UserAuthorityInfo findUserAuthInfoByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findByUsername(Username.of(username));
        if (null == user) {
            return null;
        }
        return user.toUserAuthInfo();
    }

    @Override
    @CacheEvict(cacheNames = "UserInfoByUsername", key = "#cmd.username")
    public Username registerUser(RegisterUser cmd) {
        final var username = cmd.username();
        this.checkAlreadyExists(username);

        final var newUser = new User(Username.of(username), cmd.password());
        userRepository.save(newUser);

        return newUser.getUserName();
    }

    private void checkAlreadyExists(final String username) {
        final var user = userRepository.findByUsername(Username.of(username));
        Validate.isTrue(user == null, "用户名已存在");
    }


}
