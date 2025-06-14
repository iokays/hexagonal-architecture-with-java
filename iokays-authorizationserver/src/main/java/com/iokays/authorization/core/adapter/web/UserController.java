package com.iokays.authorization.core.adapter.web;

import com.iokays.authorization.core.adapter.web.mapping.UserModelMapper;
import com.iokays.authorization.core.adapter.web.model.*;
import com.iokays.authorization.core.application.service.GroupApplicationService;
import com.iokays.authorization.core.application.service.UserApplicationService;
import com.iokays.authorization.core.application.service.UserQueryApplicationService;
import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.authorization.core.domain.user.command.RegisterUser;
import com.iokays.authorization.core.utils.Pages;
import com.iokays.common.core.adapter.DriverAdapter;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DriverAdapter
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserApplicationService userApplicationService;
    private final UserQueryApplicationService userQueryApplicationService;
    private final GroupApplicationService groupApplicationService;
    private final UserModelMapper userModelMapper;


    @PostMapping
    public void registerUser(@RequestBody RegisterUserModel model) {
        this.userApplicationService.registerUser(RegisterUser.builder()
                .username(model.username())
                .password(model.password())
                .build());
    }

    @GetMapping
    public Page<PageUserModel> page(final Pageable pageable) {
        return Pages.toNewPage(
                pageable,
                userQueryApplicationService.page(pageable),
                userModelMapper::toUserPageModel
        );
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable("username") final String username) {
        userApplicationService.delete(username);
    }

    @PostMapping("/groups")
    public void addGroup(@RequestBody final CreateMemberGroupsModel model) {
        userApplicationService.addGroup(
                Username.of(model.username()),
                model.groupIds().stream().map(GroupId::of).toList()
        );
    }

    @GetMapping("/groups")
    public List<UserGroupModel> groups(@RequestParam("username") final String username) {
        final var groups = groupApplicationService.findAll(Pageable.unpaged())
                .getContent();

        if (CollectionUtils.isEmpty(groups)) {
            return List.of();
        }

        final var authorized = Stream.ofNullable(userApplicationService.findUserAuthInfoByUsername(username))
                .flatMap(v -> v.groupIds().stream()).collect(Collectors.toSet());

        return groups.stream().map(group ->
                UserGroupModel.builder()
                        .groupId(group.groupId().id())
                        .groupName(group.groupName())
                        .authorized(authorized.contains(group.groupId()))
                        .build()
        ).toList();
    }

}
