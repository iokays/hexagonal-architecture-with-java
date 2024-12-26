package com.iokays.core.adapter.web;

import com.iokays.common.core.adapter.DriverAdapter;
import com.iokays.core.adapter.web.api.RegisteredClientApi;
import com.iokays.core.adapter.web.mapping.RegisteredClientMapper;
import com.iokays.core.adapter.web.model.CreateRegisteredClientModel;
import com.iokays.core.application.service.RegisteredClientApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@DriverAdapter
@RestController
@AllArgsConstructor
public class RegisteredClientController implements RegisteredClientApi {

    private final RegisteredClientApplicationService applicationService;

    private final RegisteredClientMapper registeredClientMapper;

    @Override
    public ResponseEntity<String> create(CreateRegisteredClientModel model) {
        final var registerClient = registeredClientMapper.toRegisterClient(model);
        final var registeredClientId = applicationService.save(registerClient);
        return ResponseEntity.ok(registeredClientId.id());
    }
}
