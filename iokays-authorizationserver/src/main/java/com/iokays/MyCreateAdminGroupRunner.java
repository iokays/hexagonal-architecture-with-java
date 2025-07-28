package com.iokays;

import com.iokays.authorization.core.application.service.GroupApplicationService;
import com.iokays.authorization.core.application.service.UserApplicationService;
import com.iokays.authorization.core.domain.group.GroupAuthInfo;
import com.iokays.authorization.core.domain.group.GroupCode;
import com.iokays.authorization.core.domain.user.Username;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
@Order(Integer.MAX_VALUE)
public class MyCreateAdminGroupRunner implements CommandLineRunner {

    private final GroupApplicationService groupApplicationService;
    private final UserApplicationService userApplicationService;

    @Override
    public void run(String... args) {
        groupApplicationService.save("游客", List.of("authorization:users:page"));

        final var groupName = "管理员";

        final GroupAuthInfo groupInfo = groupApplicationService.findGroupInfo(groupName);
        GroupCode groupId = null != groupInfo ? groupInfo.groupId() : null;
        if (groupId == null) {
            groupId = groupApplicationService.save(groupName, List.of("authorization:users:page", "authorization:groups:page"));
        }

        userApplicationService.addGroup(Username.of("admin"), List.of(groupId));
    }
}
