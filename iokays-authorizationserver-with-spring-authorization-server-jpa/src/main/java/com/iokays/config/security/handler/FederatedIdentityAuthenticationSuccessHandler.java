/*
 * Copyright 2020-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iokays.config.security.handler;

// tag::imports[]

import com.iokays.common.core.command.CommandId;
import com.iokays.core.application.service.OauthUserApplicationService;
import com.iokays.core.domain.clientregistration.ClientRegistrationId;
import com.iokays.core.domain.oauth2user.command.SaveOauthUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
// end::imports[]

/**
 * An {@link AuthenticationSuccessHandler} for capturing the {@link OidcUser} or
 * {@link OAuth2User} for Federated Account Linking or JIT Account Provisioning.
 *
 * @author Steve Riesenberg
 * @since 1.1
 */
@Slf4j
@Component
@AllArgsConstructor
public final class FederatedIdentityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final OauthUserApplicationService oauthUserApplicationService;

    private final AuthenticationSuccessHandler delegate = new SavedRequestAwareAuthenticationSuccessHandler();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken) {
            final var clientRegistrationId = new ClientRegistrationId(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());

            if (oAuth2AuthenticationToken.getPrincipal() instanceof OidcUser oidcUser) {
                this.oauthUserApplicationService.save(toSaveOauthUser(clientRegistrationId, oidcUser));
            } else if (oAuth2AuthenticationToken.getPrincipal() instanceof OAuth2User oAuth2User) {
                this.oauthUserApplicationService.save(toSaveOauthUser(clientRegistrationId, oAuth2User));
            }
        }

        this.delegate.onAuthenticationSuccess(request, response, authentication);
    }

    private SaveOauthUser toSaveOauthUser(final ClientRegistrationId clientRegistrationId, OAuth2User oauth2User) {
        return SaveOauthUser.builder()
                .id(CommandId.generate())
                .clientRegistrationId(clientRegistrationId)
                .subject(oauth2User.getName())
                .name(oauth2User.getAttribute("name"))
                .givenName(oauth2User.getAttribute("given_name"))
                .familyName(oauth2User.getAttribute("family_name"))
                .middleName(oauth2User.getAttribute("middle_name"))
                .nickname(oauth2User.getAttribute("nickname"))
                .preferredUsername(oauth2User.getAttribute("preferred_username"))
                .profile(oauth2User.getAttribute("profile"))
                .picture(oauth2User.getAttribute("picture"))
                .website(oauth2User.getAttribute("website"))
                .email(oauth2User.getAttribute("email"))
                .emailVerified(oauth2User.getAttribute("email_verified"))
                .gender(oauth2User.getAttribute("gender"))
                .birthdate(oauth2User.getAttribute("birthdate"))
                .zoneinfo(oauth2User.getAttribute("zoneinfo"))
                .locale(oauth2User.getAttribute("locale"))
                .phoneNumber(oauth2User.getAttribute("phone_number"))
                .phoneNumberVerified(oauth2User.getAttribute("phone_number_verified"))
                .address(oauth2User.getAttribute("address"))
                .updatedAt(oauth2User.getAttribute("updated_at"))
                .claim(oauth2User.getAttribute("claim"))
                .build();
    }

}
