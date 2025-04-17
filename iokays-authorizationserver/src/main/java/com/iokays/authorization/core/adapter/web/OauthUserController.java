package com.iokays.authorization.core.adapter.web;

import com.iokays.authorization.core.adapter.web.mapping.OauthUserModelMapper;
import com.iokays.authorization.core.adapter.web.model.PageOauthUserModel;
import com.iokays.authorization.core.application.service.OauthUserQueryApplicationService;
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
@RequestMapping("/oauthUsers")
public class OauthUserController {

    private final OauthUserQueryApplicationService oauthUserQueryApplicationService;
    private final OauthUserModelMapper oauthUserModelMapper;

    @GetMapping
    public Page<PageOauthUserModel> page(final Pageable pageable) {
        return Pages.toNewPage(
                pageable,
                oauthUserQueryApplicationService.page(pageable),
                oauthUserModelMapper::toOauthUserPageModel
        );
    }

}
