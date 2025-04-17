package com.iokays.authorization.core.adapter.web;

import com.iokays.authorization.core.adapter.web.mapping.UserModelMapper;
import com.iokays.authorization.core.adapter.web.model.PageUserModel;
import com.iokays.authorization.core.application.service.UserQueryApplicationService;
import com.iokays.authorization.core.utils.Pages;
import com.iokays.common.core.adapter.DriverAdapter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@DriverAdapter
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserQueryApplicationService userQueryApplicationService;
    private final UserModelMapper userModelMapper;

    @GetMapping
    public Page<PageUserModel> page(final Pageable pageable) {
        return Pages.toNewPage(
                pageable,
                userQueryApplicationService.page(pageable),
                userModelMapper::toUserPageModel
        );
    }

}
