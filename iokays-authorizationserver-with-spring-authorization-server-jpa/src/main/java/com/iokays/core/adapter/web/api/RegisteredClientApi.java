package com.iokays.core.adapter.web.api;

import com.iokays.core.adapter.web.model.CreateRegisteredClientModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/registeredClients")
public interface RegisteredClientApi {

    @PostMapping("/create")
    ResponseEntity<String> create(@RequestBody final CreateRegisteredClientModel model);


}