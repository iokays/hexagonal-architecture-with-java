package com.iokays.authorization.config.security;

import com.iokays.authorization.config.security.adapter.persistence.MyClientRegistrationRepositoryAdapter;
import com.iokays.authorization.config.security.handler.FederatedIdentityAuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

import java.util.HashMap;

@Slf4j
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class MySecurityClientConfig {

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   MyClientRegistrationRepositoryAdapter clientRegistrationRepositoryAdapter,
                                                   FederatedIdentityAuthenticationSuccessHandler federatedIdentityAuthenticationSuccessHandler
    ) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/assets/**", "favicon.ico", ".well-known/jwks.json", DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(v -> {
                    v.loginPage(DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL);

                    final var handler = new SavedRequestAwareAuthenticationSuccessHandler();
                    handler.setDefaultTargetUrl("/");
                    handler.setTargetUrlParameter("targetUrl");
                    v.successHandler(handler);
                })
                .oauth2Login(v -> v
                        .loginPage(DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL)
                        .successHandler(federatedIdentityAuthenticationSuccessHandler))
                .exceptionHandling(
                        v -> v.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(v -> v.maximumSessions(1))
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        final var encoders = new HashMap<String, PasswordEncoder>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_5());
        encoders.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v4_1());
        encoders.put("scrypt@SpringSecurity_v5_8", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_2());
        encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("sha256", new StandardPasswordEncoder());

        return new DelegatingPasswordEncoder("noop", encoders);
    }

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }


}
