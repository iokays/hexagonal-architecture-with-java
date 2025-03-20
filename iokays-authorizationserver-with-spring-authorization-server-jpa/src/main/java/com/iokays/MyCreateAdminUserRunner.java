package com.iokays;

import com.iokays.authorization.core.application.service.UserApplicationService;
import com.iokays.authorization.core.domain.user.Password;
import com.iokays.authorization.core.domain.user.UserInfo;
import com.iokays.authorization.core.domain.user.command.RegisterUser;
import com.iokays.common.core.command.CommandId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order
@Component
@AllArgsConstructor
public class MyCreateAdminUserRunner implements CommandLineRunner {

    private final UserApplicationService userApplicationService;

    @Override
    public void run(String... args) {
        final var username = "admin";
        UserInfo info = userApplicationService.findByUsername(username);
        if (info != null) {
            log.info("用户: {}, 已存在", username);
            return;
        }
        log.warn("用户: {}, 不存在，创建用户", username);
        userApplicationService.registerUser(new RegisterUser(CommandId.generate(), username, new Password("admin")));
    }
}
