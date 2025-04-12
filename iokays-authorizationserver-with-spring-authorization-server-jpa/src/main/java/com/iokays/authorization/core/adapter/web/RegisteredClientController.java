package com.iokays.authorization.core.adapter.web;

import com.iokays.authorization.core.adapter.web.api.RegisteredClientApi;
import com.iokays.authorization.core.adapter.web.mapping.RegisteredClientModelMapper;
import com.iokays.authorization.core.adapter.web.model.CreateRegisteredClientModel;
import com.iokays.authorization.core.adapter.web.model.PageRegisteredClientModel;
import com.iokays.authorization.core.application.service.RegisteredClientApplicationService;
import com.iokays.authorization.core.application.service.RegisteredClientQueryApplicationService;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientId;
import com.iokays.authorization.core.utils.Pages;
import com.iokays.common.core.adapter.DriverAdapter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@DriverAdapter
@RestController
@AllArgsConstructor
public class RegisteredClientController implements RegisteredClientApi {

    private final RegisteredClientApplicationService applicationService;
    private final RegisteredClientQueryApplicationService registeredClientQueryApplicationService;

    private final RegisteredClientModelMapper registeredClientModelMapper;

    @Override
    public ResponseEntity<String> create(CreateRegisteredClientModel model) {
        final var registerClient = registeredClientModelMapper.toRegisterClient(model);
        final var registeredClientId = applicationService.save(registerClient);
        return ResponseEntity.ok(registeredClientId.id());
    }

    @GetMapping
    public Page<PageRegisteredClientModel> page(final Pageable pageable) {
        return Pages.toNewPage(
                pageable,
                registeredClientQueryApplicationService.page(pageable),
                registeredClientModelMapper::toPageRegisteredClientModel
        );
    }

    @DeleteMapping("/{registrationId}")
    public void delete(@PathVariable String registrationId) {
        applicationService.delete(new RegisteredClientId(registrationId));
    }

}
