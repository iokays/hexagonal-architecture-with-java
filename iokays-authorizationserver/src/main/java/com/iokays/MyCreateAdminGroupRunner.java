package com.iokays;

import com.iokays.authorization.core.application.service.GroupApplicationService;
import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.group.GroupInfo;
import com.iokays.authorization.core.domain.user.Username;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Order(0)
@Component
@AllArgsConstructor
public class MyCreateAdminGroupRunner implements CommandLineRunner {

    private final GroupApplicationService groupApplicationService;

    @Override
    public void run(String... args) {
        final var groupName = "admin";

        final GroupInfo groupInfo = groupApplicationService.findGroupInfo(groupName);
        GroupId groupId = null != groupInfo ? groupInfo.groupId() : null;
        if (groupId == null) {
            groupId = groupApplicationService.save(groupName, List.of());
        }
        groupApplicationService.addAuthority(groupId, "authorization:users:page");
        groupApplicationService.addMember(groupId, Username.of("admin"));
    }
}
