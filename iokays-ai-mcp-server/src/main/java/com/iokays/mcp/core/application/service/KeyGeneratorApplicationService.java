package com.iokays.mcp.core.application.service;

import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @see com.nimbusds.jose.JWSAlgorithm.Family
 */
@Slf4j
@Service
@AllArgsConstructor
public class KeyGeneratorApplicationService {

    public ResponseModel keyGenerator(String type, Integer size) {
        log.info("keyGenerator type: {}, size: {}", type, size);
        final var resultBuilder = ResponseModel.builder();

        if (StringUtils.equalsAnyIgnoreCase(type, "rsa")) {
            final var keyId = UUID.randomUUID().toString();
            resultBuilder.keyId(keyId);
            this.rsa(keyId, size,
                    (key) -> Try.run(() -> {
                        resultBuilder.publicKey(key.toPublicKey().getEncoded());
                        resultBuilder.privateKey(key.toPrivateKey().getEncoded());
                    }).onFailure(throwable -> {
                        throw new RuntimeException(throwable);
                    }));
        }

        final var result = resultBuilder.build();
        log.info("keyGenerator type: {}, size: {}, result: {}", type, size, result);
        return result;
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
    public record ResponseModel(String keyId, byte[] publicKey, byte[] privateKey) implements Serializable {
    }

}