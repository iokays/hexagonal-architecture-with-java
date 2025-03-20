package com.iokays.authorization.core.adapter.web;

import com.iokays.authorization.core.adapter.web.mapping.ClientRegistrationModelMapper;
import com.iokays.authorization.core.adapter.web.model.PageClientRegistrationModel;
import com.iokays.authorization.core.application.service.ClientRegistrationQueryApplicationService;
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
@RequestMapping("/client/registrations")
public class ClientRegistrationController {

    private final ClientRegistrationQueryApplicationService clientRegistrationQueryApplicationService;
    private final ClientRegistrationModelMapper clientRegistrationModelMapper;

    @GetMapping
    public Page<PageClientRegistrationModel> page(final Pageable pageable) {
        return Pages.toNewPage(
                pageable,
                clientRegistrationQueryApplicationService.page(pageable),
                clientRegistrationModelMapper::toPageClientRegistration
        );
    }

}
