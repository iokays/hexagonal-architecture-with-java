package com.iokays.authorization.core.adapter.web;

import com.iokays.authorization.core.adapter.web.mapping.ClientRegistrationModelMapper;
import com.iokays.authorization.core.adapter.web.model.CreateClientRegistrationModel;
import com.iokays.authorization.core.adapter.web.model.PageClientRegistrationModel;
import com.iokays.authorization.core.application.service.ClientRegistrationApplicationService;
import com.iokays.authorization.core.application.service.ClientRegistrationQueryApplicationService;
import com.iokays.authorization.core.utils.Pages;
import com.iokays.common.core.adapter.DriverAdapter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@DriverAdapter
@RestController
@AllArgsConstructor
@RequestMapping("/client/registrations")
public class ClientRegistrationController {

    private final ClientRegistrationQueryApplicationService clientRegistrationQueryApplicationService;
    private final ClientRegistrationApplicationService clientRegistrationApplicationService;
    private final ClientRegistrationModelMapper clientRegistrationModelMapper;

    @GetMapping
    public Page<PageClientRegistrationModel> page(final Pageable pageable) {
        return Pages.toNewPage(
                pageable,
                clientRegistrationQueryApplicationService.page(pageable),
                clientRegistrationModelMapper::toPageClientRegistration
        );
    }

    @PostMapping
    public void create(@RequestBody CreateClientRegistrationModel model) {
        final var createClientRegistration = clientRegistrationModelMapper.toCreateClientRegistration(model);
        clientRegistrationApplicationService.createClientRegistration(createClientRegistration);
    }

    @DeleteMapping("/{registrationId}")
    public void delete(@PathVariable String registrationId) {
        clientRegistrationApplicationService.deleteRegistrationId(registrationId);
    }

}
