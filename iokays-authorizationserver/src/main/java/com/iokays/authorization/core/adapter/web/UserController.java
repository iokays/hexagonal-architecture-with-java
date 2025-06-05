package com.iokays.authorization.core.adapter.web;

import com.iokays.authorization.core.adapter.web.mapping.UserModelMapper;
import com.iokays.authorization.core.adapter.web.model.CreateMemberGroupsModel;
import com.iokays.authorization.core.adapter.web.model.PageUserModel;
import com.iokays.authorization.core.application.service.UserApplicationService;
import com.iokays.authorization.core.application.service.UserQueryApplicationService;
import com.iokays.authorization.core.domain.group.GroupId;
import com.iokays.authorization.core.domain.user.Username;
import com.iokays.authorization.core.utils.Pages;
import com.iokays.common.core.adapter.DriverAdapter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@DriverAdapter
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserApplicationService userApplicationService;
    private final UserQueryApplicationService userQueryApplicationService;
    private final UserModelMapper userModelMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('authorization:users:page')")
    public Page<PageUserModel> page(final Pageable pageable) {
        return Pages.toNewPage(
                pageable,
                userQueryApplicationService.page(pageable),
                userModelMapper::toUserPageModel
        );
    }

    @PostMapping("/groups")
    public void addGroup(final CreateMemberGroupsModel model) {
        userApplicationService.addGroup(
                Username.of(model.username()),
                model.groupIds().stream().map(GroupId::of).toList()
        );
    }


}
