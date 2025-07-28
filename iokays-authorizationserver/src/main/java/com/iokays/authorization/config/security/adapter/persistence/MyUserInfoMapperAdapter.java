package com.iokays.authorization.config.security.adapter.persistence;

import com.iokays.authorization.core.application.service.OauthUserApplicationService;
import com.iokays.authorization.core.application.service.UserApplicationService;
import com.iokays.authorization.core.domain.clientregistration.RegistrationCode;
import com.iokays.authorization.core.domain.oauth2user.OauthUserInfo;
import com.iokays.authorization.core.domain.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Slf4j
@Component
@AllArgsConstructor
public class MyUserInfoMapperAdapter implements Function<OidcUserInfoAuthenticationContext, OidcUserInfo>, OAuth2TokenCustomizer<JwtEncodingContext> {

    private static final Set<String> ID_TOKEN_CLAIMS = Set.of(
            IdTokenClaimNames.ISS,
            IdTokenClaimNames.SUB,
            IdTokenClaimNames.AUD,
            IdTokenClaimNames.EXP,
            IdTokenClaimNames.IAT,
            IdTokenClaimNames.AUTH_TIME,
            IdTokenClaimNames.NONCE,
            IdTokenClaimNames.ACR,
            IdTokenClaimNames.AMR,
            IdTokenClaimNames.AZP,
            IdTokenClaimNames.AT_HASH,
            IdTokenClaimNames.C_HASH
    );

    private final UserApplicationService userApplicationService;
    private final OauthUserApplicationService oauthUserApplicationService;

    @Override
    public OidcUserInfo apply(OidcUserInfoAuthenticationContext context) {
        final OAuth2Authorization authorization = context.getAuthorization();

        final OidcUserInfoAuthenticationToken authentication = context.getAuthentication();
        final JwtAuthenticationToken principal = (JwtAuthenticationToken) authentication.getPrincipal();

        final var formLogin = authorization.getAttributes().values().stream().map(this::formLogin).filter(Objects::nonNull).findAny().orElse(null);
        final var oauthLogin = authorization.getAttributes().values().stream().map(this::oauthLogin).filter(Objects::nonNull).findAny().orElse(null);

        log.info("principal.class: {}, formLogin: {}, oauthLogin: {}", ClassUtils.getName(principal), formLogin, oauthLogin);

        if (Objects.nonNull(formLogin)) {
            //加载为业务的用户信息
            return toOidcUserInfo(userApplicationService.findByUsername(principal.getName()));
        }

        if (Objects.nonNull(oauthLogin)) {
            //本业务系统的用户信息
            return toOidcUserInfo(oauthUserApplicationService.findBySubjectAndClientRegistrationId(principal.getName(), new RegistrationCode(oauthLogin.getAuthorizedClientRegistrationId())));

            //第三方的用户信息
//            Map<String, Object> thirdPartyClaims = extractClaims(oauthLogin);
//            return new OidcUserInfo(thirdPartyClaims);
        }

        throw new IllegalArgumentException("Unsupported authentication type: " + principal.getClass().getName());
    }


    public OidcUserInfo toOidcUserInfo(UserInfo userInfo) {
        return OidcUserInfo.builder()
                .subject(userInfo.username().code())
                .name(userInfo.username().code())
                .build();
    }

    public OidcUserInfo toOidcUserInfo(OauthUserInfo userInfo) {
        return OidcUserInfo.builder()
                .subject(userInfo.oauthUserId().code())
                .name(userInfo.name())
                .givenName(userInfo.givenName())
                .familyName(userInfo.familyName())
                .middleName(userInfo.middleName())
                .nickname(userInfo.nickname())
                .preferredUsername(userInfo.preferredUsername())
                .profile(userInfo.profile())
                .picture(userInfo.picture())
                .website(userInfo.website())
                .email(userInfo.email())
                .emailVerified(userInfo.emailVerified())
                .gender(userInfo.gender())
                .birthdate(userInfo.birthdate())
                .zoneinfo(userInfo.zoneinfo())
                .locale(userInfo.locale())
                .phoneNumber(userInfo.phoneNumber())
                .phoneNumberVerified(userInfo.phoneNumberVerified())
                .build();
    }

    @Override
    public void customize(JwtEncodingContext context) {
        final Authentication principal = context.getPrincipal();
        log.debug("context.principal: {}, name: {}", principal, principal.getName());

        final var formLogin = this.formLogin(principal);
        final var oauthLogin = this.oauthLogin(principal);

        log.debug("context.principal: {}, name: {}, formLogin: {}, oauthLogin: {}", principal, principal.getName(), formLogin, oauthLogin);

        if (!OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
            return;
        }

        if (Objects.nonNull(formLogin)) {
            OidcUserInfo oidcUserInfo = toOidcUserInfo(userApplicationService.findByUsername(principal.getName()));
            context.getClaims().claims(claims -> claims.putAll(oidcUserInfo.getClaims()));
            return;
        }

        if (Objects.nonNull(oauthLogin)) {
            //第三方用户信息
//            Map<String, Object> thirdPartyClaims = extractClaims(principal);
//            context.getClaims().claims(existingClaims -> {
//                // Remove conflicting claims set by this authorization server
//                existingClaims.keySet().forEach(thirdPartyClaims::remove);
//
//                // Remove standard id_token claims that could cause problems with clients
//                ID_TOKEN_CLAIMS.forEach(thirdPartyClaims::remove);
//
//                // Add all other claims directly to id_token
//                existingClaims.putAll(thirdPartyClaims);
//            });

            //本业务系统的用户信息
            final OidcUserInfo oidcUserInfo = toOidcUserInfo(oauthUserApplicationService.findBySubjectAndClientRegistrationId(principal.getName(), new RegistrationCode(oauthLogin.getAuthorizedClientRegistrationId())));
            context.getClaims().claims(claims -> claims.putAll(oidcUserInfo.getClaims()));
            return;
        }

        throw new IllegalArgumentException("Unsupported authentication type: " + principal.getClass().getName());
    }

    private Map<String, Object> extractClaims(Authentication principal) {
        Map<String, Object> claims = Collections.emptyMap();
        if (principal.getPrincipal() instanceof OidcUser oidcUser) {
            OidcIdToken idToken = oidcUser.getIdToken();
            claims = idToken.getClaims();
        } else if (principal.getPrincipal() instanceof OAuth2User oauth2User) {
            claims = oauth2User.getAttributes();
        }

        return new HashMap<>(claims);
    }


    private UsernamePasswordAuthenticationToken formLogin(Object authentication) {
        if (authentication instanceof UsernamePasswordAuthenticationToken target) {
            return target;
        }
        return null;
    }

    private OAuth2AuthenticationToken oauthLogin(Object authentication) {
        if (authentication instanceof OAuth2AuthenticationToken target) {
            return target;
        }
        return null;
    }


}
