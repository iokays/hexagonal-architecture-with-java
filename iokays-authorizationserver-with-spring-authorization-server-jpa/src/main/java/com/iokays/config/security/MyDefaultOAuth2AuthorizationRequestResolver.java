package com.iokays.config.security;

import com.iokays.config.security.adapter.persistence.MyClientRegistrationRepositoryAdapter;
import com.iokays.core.application.service.ClientRegistrationApplicationService;
import com.iokays.core.domain.clientregistration.ClientRegistrationType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@Component
public class MyDefaultOAuth2AuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private final ClientRegistrationApplicationService clientRegistrationApplicationService;
    private final DefaultOAuth2AuthorizationRequestResolver requestResolver;

    public MyDefaultOAuth2AuthorizationRequestResolver(final MyClientRegistrationRepositoryAdapter clientRegistrationRepository,
                                                       final ClientRegistrationApplicationService clientRegistrationApplicationService) {
        this.clientRegistrationApplicationService = clientRegistrationApplicationService;

        this.requestResolver = new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository, OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI);
        this.requestResolver.setAuthorizationRequestCustomizer(this.authorizationRequestCustomizer());
    }


    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        return requestResolver.resolve(request);
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        return requestResolver.resolve(request, clientRegistrationId);
    }

    /**
     * 自定义授权请求
     *
     * @return
     */
    private Consumer<OAuth2AuthorizationRequest.Builder> authorizationRequestCustomizer() {
        return builder -> builder.attributes(attributes -> {
            final var registrationId = (String) Objects.requireNonNull(attributes.get("registration_id"));
            final var clientRegistrationInfo = clientRegistrationApplicationService.findByRegistrationId(registrationId);

            //企业微信WEB登录[因为要授权，只能走到这一步]
            if (clientRegistrationInfo.clientRegistrationType() == ClientRegistrationType.WORK_WEIXIN) {
                builder.additionalParameters(Map.of(
                                "appid", clientRegistrationInfo.clientId(),
                                "agentid", "1000002"
                        )
                );
                builder.authorizationRequestUri(authorizationRequestUriFunction -> authorizationRequestUriFunction.fragment("wechat_redirect").build());
            }

        });
    }
}
