package com.iokays.core.application.service;

import com.iokays.core.domain.authorization.Authorization;
import com.iokays.core.domain.authorization.AuthorizationId;
import com.iokays.core.domain.authorization.AuthorizationInfo;
import com.iokays.core.domain.authorization.AuthorizationRepository;
import com.iokays.core.domain.authorization.command.SaveAuthorization;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class AuthorizationApplicationService {

    private final AuthorizationRepository authorizationRepository;

    public void save(final SaveAuthorization command) {
        final var authorizationId = command.authorizationId();

        authorizationRepository.findByAuthorizationId(authorizationId)
                .ifPresentOrElse(
                        entity -> entity.update(command),
                        () -> authorizationRepository.save(new Authorization(command))
                );
    }

    public void remove(final AuthorizationId authorizationId) {
        authorizationRepository.deleteByAuthorizationId(authorizationId);
    }

    public AuthorizationInfo findByAuthorizationId(final AuthorizationId authorizationId) {
        return authorizationRepository.findByAuthorizationId(authorizationId).map(Authorization::info).orElse(null);
    }

    public AuthorizationInfo findByToken(final String tokenValue, final String tokenType) {
        final Optional<Authorization> entity = switch (tokenType) {
            case "code" -> authorizationRepository.findByAuthorizationCodeValue(tokenValue);
            case "state" -> authorizationRepository.findByState(tokenValue);
            case "access_token" -> authorizationRepository.findByAccessTokenValue(tokenValue);
            case "refresh_token" -> authorizationRepository.findByRefreshTokenValue(tokenValue);
            case "id_token" -> authorizationRepository.findByOidcIdTokenValue(tokenValue);
            case "user_code" -> authorizationRepository.findByUserCodeValue(tokenValue);
            case "device_code" -> authorizationRepository.findByDeviceCodeValue(tokenValue);
            default -> Optional.empty();
        };

        return entity.map(Authorization::info).orElse(null);
    }


}
