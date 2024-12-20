package com.iokays.security.user.application.service;

import com.iokays.common.core.command.CommandId;
import com.iokays.core.application.service.UserApplicationService;
import com.iokays.core.domain.user.Password;
import com.iokays.core.domain.user.UserInfo;
import com.iokays.core.domain.user.command.RegisterUser;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserApplicationServiceTest {

    @Resource
    private UserApplicationService userApplicationService;

    @Test
    void testLoadUserByUsername() {
        log.info("start testLoadUserByUsername");
        final UserInfo info = userApplicationService.findByUsername("admin");
        Assertions.assertEquals("admin", info.username());
    }

    @Test
    void testRegisterUser() {
        log.info("start testRegisterUser");
        final RegisterUser registerUser = new RegisterUser(CommandId.generate(), "iokays", new Password("123456"));
        final var userId = userApplicationService.registerUser(registerUser);
        Assertions.assertNotNull(userId);
    }

}