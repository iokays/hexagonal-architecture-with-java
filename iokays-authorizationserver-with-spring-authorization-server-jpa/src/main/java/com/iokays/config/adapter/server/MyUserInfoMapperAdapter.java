package com.iokays.config.adapter.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class MyUserInfoMapperAdapter implements Function<OidcUserInfoAuthenticationContext, OidcUserInfo>, OAuth2TokenCustomizer<JwtEncodingContext> {

    private final UserInfoRepository userInfoRepository = new UserInfoRepository();

    @Override
    public OidcUserInfo apply(OidcUserInfoAuthenticationContext context) {
        final OidcUserInfoAuthenticationToken authentication = context.getAuthentication();
        final JwtAuthenticationToken principal = (JwtAuthenticationToken) authentication.getPrincipal();

        log.info("principal: {}", principal);

        //加载为业务的用户信息
        return new OidcUserInfo(userInfoRepository.findByUsername(principal.getName()));
    }

    @Override
    public void customize(JwtEncodingContext context) {
        log.info("context: {}", context);
        if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
            final Map<String, Object> map = userInfoRepository.findByUsername(context.getPrincipal().getName());
            context.getClaims().claims(claims -> claims.putAll(map));
        }
    }


    static class UserInfoRepository {

        private final Map<String, Map<String, Object>> userInfo = new HashMap<>();

        public UserInfoRepository() {
            this.userInfo.put("admin", createUser("user-iokays"));
            this.userInfo.put("user1", createUser("user1"));
            this.userInfo.put("user2", createUser("user2"));
        }

        private static Map<String, Object> createUser(String username) {
            return OidcUserInfo.builder()
                    .subject(username)
                    .name("First Last")
                    .givenName("First")
                    .familyName("Last")
                    .middleName("Middle")
                    .nickname("User")
                    .preferredUsername(username)
                    .profile("https://example.com/" + username)
                    .picture("https://example.com/" + username + ".jpg")
                    .website("https://example.com")
                    .email(username + "@example.com")
                    .emailVerified(true)
                    .gender("female")
                    .birthdate("1970-01-01")
                    .zoneinfo("Europe/Paris")
                    .locale("en-US")
                    .phoneNumber("+1 (604) 555-1234;ext=5678")
                    .phoneNumberVerified(false)
                    .claim("address", Collections.singletonMap("formatted", "Champ de Mars\n5 Av. Anatole France\n75007 Paris\nFrance"))
                    .updatedAt("1970-01-01T00:00:00Z")
                    .build()
                    .getClaims();
        }

        public Map<String, Object> findByUsername(String username) {
            return this.userInfo.get(username);
        }
    }


}
