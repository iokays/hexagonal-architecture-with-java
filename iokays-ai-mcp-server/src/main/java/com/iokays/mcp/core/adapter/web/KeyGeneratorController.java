package com.iokays.mcp.core.adapter.web;

import com.iokays.mcp.core.application.service.KeyGeneratorApplicationService;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @see com.nimbusds.jose.JWSAlgorithm.Family
 */
@RestController
@RequestMapping("/keyGenerator")
@AllArgsConstructor
public class KeyGeneratorController {

    private final KeyGeneratorApplicationService keyGeneratorApplicationService;


    @RequestMapping
    public KeyGeneratorApplicationService.ResponseModel keyGenerator(@RequestBody RequestModel model) {
        return keyGeneratorApplicationService.keyGenerator(model.type(), model.size());
    }

    private void rsa(final String keyId, final Integer size, Consumer<RSAKey> customizer) {
        Try.run(() -> {
            final var rsaKey = new RSAKeyGenerator(size)
                    .keyID(keyId)
                    .generate();
            customizer.accept(rsaKey);
        }).onFailure(throwable -> {
            throw new RuntimeException(throwable);
        });
    }

    @Builder
    public record RequestModel(String type, Integer size) implements Serializable { }

}