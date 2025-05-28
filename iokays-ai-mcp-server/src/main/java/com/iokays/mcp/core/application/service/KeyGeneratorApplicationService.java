package com.iokays.mcp.core.application.service;

import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @see com.nimbusds.jose.JWSAlgorithm.Family
 */
@Service
@AllArgsConstructor
public class KeyGeneratorApplicationService {

    public ResponseModel keyGenerator(String type, Integer size) {
        final var result = ResponseModel.builder();

        if (StringUtils.equalsAnyIgnoreCase(type, "ras")) {
            final var keyId = UUID.randomUUID().toString();
            result.keyId(keyId);
            this.rsa(keyId, size,
                    (key) -> Try.run(() -> {
                        result.publicKey(key.toPublicKey().getEncoded());
                        result.privateKey(key.toPrivateKey().getEncoded());
                    }).onFailure(throwable -> {
                        throw new RuntimeException(throwable);
                    }));
        }

        return result.build();
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
    public record ResponseModel(String keyId, byte[] publicKey, byte[] privateKey) implements Serializable { }

}