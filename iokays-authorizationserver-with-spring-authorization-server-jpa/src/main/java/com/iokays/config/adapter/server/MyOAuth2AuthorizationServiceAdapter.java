package com.iokays.config.adapter.server;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.iokays.common.core.command.CommandId;
import com.iokays.core.application.service.AuthorizationApplicationService;
import com.iokays.core.application.service.RegisteredClientApplicationService;
import com.iokays.core.domain.authorization.AuthorizationId;
import com.iokays.core.domain.authorization.AuthorizationInfo;
import com.iokays.core.domain.authorization.command.SaveAuthorization;
import com.iokays.core.domain.registeredclient.RegisteredClientId;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.function.Consumer;

import static com.iokays.config.adapter.server.MyRegisteredClientRepositoryAdapter.build;

@Slf4j
@Component
public class MyOAuth2AuthorizationServiceAdapter implements OAuth2AuthorizationService {

    private final AuthorizationApplicationService authorizationApplicationService;
    private final RegisteredClientApplicationService registeredClientApplicationService;

    private final ObjectMapper objectMapper;

    public MyOAuth2AuthorizationServiceAdapter(AuthorizationApplicationService authorizationApplicationService, RegisteredClientApplicationService registeredClientApplicationService) {
        this.authorizationApplicationService = authorizationApplicationService;
        this.registeredClientApplicationService = registeredClientApplicationService;

        this.objectMapper = new ObjectMapper();
        ClassLoader classLoader = MyOAuth2AuthorizationServiceAdapter.class.getClassLoader();
        List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
        this.objectMapper.registerModules(securityModules);
        this.objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
        this.objectMapper.addMixIn(Collections.singletonMap(String.class, Object.class).getClass(), SingletonMapMixin.class);
    }

    private static AuthorizationGrantType resolveAuthorizationGrantType(String authorizationGrantType) {
        if (AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.AUTHORIZATION_CODE;
        } else if (AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.CLIENT_CREDENTIALS;
        } else if (AuthorizationGrantType.REFRESH_TOKEN.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.REFRESH_TOKEN;
        } else if (AuthorizationGrantType.DEVICE_CODE.getValue().equals(authorizationGrantType)) {
            return AuthorizationGrantType.DEVICE_CODE;
        }
        return new AuthorizationGrantType(authorizationGrantType);              // Custom authorization grant type
    }

    @Override
    public void save(OAuth2Authorization authorization) {
        Validate.notNull(authorization, "authorization cannot be null");
        final var command = toCreateAuthorization(authorization);
        authorizationApplicationService.save(command);
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        Validate.notNull(authorization, "authorization cannot be null");
        this.authorizationApplicationService.remove(new AuthorizationId(authorization.getId()));
    }

    @Override
    public OAuth2Authorization findById(String id) {
        final var info = authorizationApplicationService.findByAuthorizationId(new AuthorizationId(id));
        return toOAuth2Authorization(info);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        final var info = authorizationApplicationService.findByToken(token, Objects.requireNonNull(tokenType).getValue());
        return toOAuth2Authorization(info);
    }

    private OAuth2Authorization toOAuth2Authorization(AuthorizationInfo info) {
        final var registeredClient = this.registeredClientApplicationService.findById(info.registeredClientId());
        if (registeredClient == null) {
            throw new DataRetrievalFailureException(
                    "The RegisteredClient with id '" + info.registeredClientId() + "' was not found in the RegisteredClientRepository.");
        }

        OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(build(registeredClient))
                .id(info.authorizationId().id())
                .principalName(info.principalName())
                .authorizationGrantType(resolveAuthorizationGrantType(info.authorizationGrantType()))
                .authorizedScopes(StringUtils.commaDelimitedListToSet(info.authorizedScopes()))
                .attributes(attributes -> attributes.putAll(parseMap(info.attributes())));
        if (info.state() != null) {
            builder.attribute(OAuth2ParameterNames.STATE, info.state());
        }

        if (info.authorizationCodeValue() != null) {
            OAuth2AuthorizationCode authorizationCode = new OAuth2AuthorizationCode(
                    info.authorizationCodeValue(),
                    info.authorizationCodeIssuedAt(),
                    info.authorizationCodeExpiresAt());
            builder.token(authorizationCode, metadata -> metadata.putAll(parseMap(info.authorizationCodeMetadata())));
        }

        if (info.accessTokenValue() != null) {
            OAuth2AccessToken accessToken = new OAuth2AccessToken(
                    OAuth2AccessToken.TokenType.BEARER,
                    info.accessTokenValue(),
                    info.accessTokenIssuedAt(),
                    info.accessTokenExpiresAt(),
                    StringUtils.commaDelimitedListToSet(info.accessTokenScopes()));
            builder.token(accessToken, metadata -> metadata.putAll(parseMap(info.accessTokenMetadata())));
        }

        if (info.refreshTokenValue() != null) {
            OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                    info.refreshTokenValue(),
                    info.refreshTokenIssuedAt(),
                    info.refreshTokenExpiresAt());
            builder.token(refreshToken, metadata -> metadata.putAll(parseMap(info.refreshTokenMetadata())));
        }

        if (info.oidcIdTokenValue() != null) {
            OidcIdToken idToken = new OidcIdToken(
                    info.oidcIdTokenValue(),
                    info.oidcIdTokenIssuedAt(),
                    info.oidcIdTokenExpiresAt(),
                    parseMap(info.oidcIdTokenClaims()));
            builder.token(idToken, metadata -> metadata.putAll(parseMap(info.oidcIdTokenMetadata())));
        }

        if (info.userCodeValue() != null) {
            OAuth2UserCode userCode = new OAuth2UserCode(
                    info.userCodeValue(),
                    info.userCodeIssuedAt(),
                    info.userCodeExpiresAt());
            builder.token(userCode, metadata -> metadata.putAll(parseMap(info.userCodeMetadata())));
        }

        if (info.deviceCodeValue() != null) {
            OAuth2DeviceCode deviceCode = new OAuth2DeviceCode(
                    info.deviceCodeValue(),
                    info.deviceCodeIssuedAt(),
                    info.deviceCodeExpiresAt());
            builder.token(deviceCode, metadata -> metadata.putAll(parseMap(info.deviceCodeMetadata())));
        }

        return builder.build();
    }

    private SaveAuthorization toCreateAuthorization(OAuth2Authorization authorization) {
        final var builder = SaveAuthorization.builder().id(CommandId.generate());
        builder.authorizationId(new AuthorizationId(authorization.getId()));
        builder.registeredClientId(new RegisteredClientId(authorization.getRegisteredClientId()));
        builder.principalName(authorization.getPrincipalName());
        builder.authorizationGrantType(authorization.getAuthorizationGrantType().getValue());
        builder.authorizedScopes(StringUtils.collectionToDelimitedString(authorization.getAuthorizedScopes(), ","));
        builder.attributes(writeMap(authorization.getAttributes()));
        builder.state(authorization.getAttribute(OAuth2ParameterNames.STATE));

        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode =
                authorization.getToken(OAuth2AuthorizationCode.class);
        setTokenValues(
                authorizationCode,
                builder::authorizationCodeValue,
                builder::authorizationCodeIssuedAt,
                builder::authorizationCodeExpiresAt,
                builder::authorizationCodeMetadata
        );

        OAuth2Authorization.Token<OAuth2AccessToken> accessToken =
                authorization.getToken(OAuth2AccessToken.class);
        setTokenValues(
                accessToken,
                builder::accessTokenValue,
                builder::accessTokenIssuedAt,
                builder::accessTokenExpiresAt,
                builder::accessTokenMetadata
        );
        if (accessToken != null && accessToken.getToken().getScopes() != null) {
            builder.accessTokenScopes(StringUtils.collectionToDelimitedString(accessToken.getToken().getScopes(), ","));
        }

        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken =
                authorization.getToken(OAuth2RefreshToken.class);
        setTokenValues(
                refreshToken,
                builder::refreshTokenValue,
                builder::refreshTokenIssuedAt,
                builder::refreshTokenExpiresAt,
                builder::refreshTokenMetadata
        );

        OAuth2Authorization.Token<OidcIdToken> oidcIdToken =
                authorization.getToken(OidcIdToken.class);
        setTokenValues(
                oidcIdToken,
                builder::oidcIdTokenValue,
                builder::oidcIdTokenIssuedAt,
                builder::oidcIdTokenExpiresAt,
                builder::oidcIdTokenMetadata
        );
        if (oidcIdToken != null) {
            builder.oidcIdTokenClaims(writeMap(oidcIdToken.getClaims()));
        }

        OAuth2Authorization.Token<OAuth2UserCode> userCode =
                authorization.getToken(OAuth2UserCode.class);
        setTokenValues(
                userCode,
                builder::userCodeValue,
                builder::userCodeIssuedAt,
                builder::userCodeExpiresAt,
                builder::userCodeMetadata
        );

        OAuth2Authorization.Token<OAuth2DeviceCode> deviceCode =
                authorization.getToken(OAuth2DeviceCode.class);
        setTokenValues(
                deviceCode,
                builder::deviceCodeValue,
                builder::deviceCodeIssuedAt,
                builder::deviceCodeExpiresAt,
                builder::deviceCodeMetadata
        );

        return builder.build();
    }

    private void setTokenValues(
            OAuth2Authorization.Token<?> token,
            Consumer<String> tokenValueConsumer,
            Consumer<Instant> issuedAtConsumer,
            Consumer<Instant> expiresAtConsumer,
            Consumer<String> metadataConsumer) {
        if (token != null) {
            OAuth2Token oAuth2Token = token.getToken();
            tokenValueConsumer.accept(oAuth2Token.getTokenValue());
            issuedAtConsumer.accept(oAuth2Token.getIssuedAt());
            expiresAtConsumer.accept(oAuth2Token.getExpiresAt());
            metadataConsumer.accept(writeMap(token.getMetadata()));
        }
    }

    private Map<String, Object> parseMap(String data) {
        try {
            return this.objectMapper.readValue(data, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    private String writeMap(Map<String, Object> metadata) {
        try {
            return this.objectMapper.writeValueAsString(metadata);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

}

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonDeserialize(using = SingletonMapDeserializer.class)
abstract class SingletonMapMixin {

    @JsonCreator
    SingletonMapMixin(Map<?, ?> map) {
    }
}

final class SingletonMapDeserializer extends JsonDeserializer<Map<?, ?>> {

    @Override
    public Map<?, ?> deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        JsonNode mapNode = mapper.readTree(parser);
        Map<String, Object> result = new LinkedHashMap<>();
        if (mapNode != null && mapNode.isObject()) {
            Iterable<Map.Entry<String, JsonNode>> fields = mapNode::fields;
            for (Map.Entry<String, JsonNode> field : fields) {
                result.put(field.getKey(), mapper.readValue(field.getValue().traverse(mapper), Object.class));
            }
        }
        if (result.size() == 1) {
            for (Map.Entry<String, Object> entry : result.entrySet()) {
                return Collections.singletonMap(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

}
