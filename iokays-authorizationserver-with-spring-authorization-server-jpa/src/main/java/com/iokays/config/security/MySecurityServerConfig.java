package com.iokays.config.security;

import com.iokays.config.security.adapter.persistence.MyRegisteredClientRepositoryAdapter;
import com.iokays.config.security.adapter.persistence.MyUserInfoMapperAdapter;
import com.iokays.config.security.device.MyDeviceClientAuthenticationConverter;
import com.iokays.config.security.device.MyDeviceClientAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import static com.iokays.config.security.adapter.web.AuthorizationConsentController.CUSTOM_CONSENT_PAGE_URI;
import static org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer.authorizationServer;

@Configuration(proxyBeanMethods = false)
public class MySecurityServerConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(
            HttpSecurity http,
            AuthorizationServerSettings authorizationServerSettings,
            MyUserInfoMapperAdapter userInfoMapper,
            MyRegisteredClientRepositoryAdapter registeredClientRepository
    ) throws Exception {

        final var deviceClientAuthenticationConverter = new MyDeviceClientAuthenticationConverter(authorizationServerSettings.getDeviceAuthorizationEndpoint());
        final var deviceClientAuthenticationProvider = new MyDeviceClientAuthenticationProvider(registeredClientRepository);

        final var authorizationServerConfigurer = authorizationServer();

        http
                .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
                .with(authorizationServerConfigurer,
                        v -> v
                                .oidc(oidc -> oidc.userInfoEndpoint(userInfo -> userInfo.userInfoMapper(userInfoMapper)))
                                .deviceAuthorizationEndpoint(deviceAuthorizationEndpoint ->
                                        deviceAuthorizationEndpoint
                                                .verificationUri("/activate")
                                )
                                .deviceVerificationEndpoint(deviceVerificationEndpoint ->
                                        deviceVerificationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI)
                                )
                                .clientAuthentication(clientAuthentication ->
                                        clientAuthentication
                                                .authenticationConverter(deviceClientAuthenticationConverter)
                                                .authenticationProvider(deviceClientAuthenticationProvider)
                                )
                                .authorizationEndpoint(authorizationEndpoint ->
                                        authorizationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI))

                )

                .csrf(csrf -> csrf.ignoringRequestMatchers(authorizationServerConfigurer.getEndpointsMatcher()))
                .oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()))
                // Redirect to the /login page when not authenticated from the authorization endpoint
                // NOTE: DefaultSecurityConfig is configured with formLogin.loginPage("/login")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .exceptionHandling(exceptions -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint(DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
        ;

        return http.build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

}