package com.iokays.webflux.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.session.ReactiveSessionRegistry;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.SessionLimit;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
import org.springframework.session.security.SpringSessionBackedReactiveSessionRegistry;

@Configuration
@EnableWebFluxSecurity
@EnableSpringWebSession
public class MySecurityClientConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http,
                                                      ReactiveSessionRegistry reactiveSessionRegistry) {
        http
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .logout(ServerHttpSecurity.LogoutSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // 禁用 CSRF 保护
                .authorizeExchange(auth -> auth
                        .pathMatchers("/public/**").permitAll() // 公开访问的路径
                        .pathMatchers("/session/**").permitAll()
                        .anyExchange().authenticated()
                )
                .sessionManagement(session ->
                        session
                                .concurrentSessions(concurrent -> concurrent
                                        .maximumSessions(SessionLimit.UNLIMITED)
                                        .sessionRegistry(reactiveSessionRegistry)
                                )
                )
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .exceptionHandling(eh ->
                        eh.authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED)))

        ;
        return http.build();
    }

    @Bean
    public ReactiveSessionRegistry reactiveSessionRegistry(
            R2dbcReactiveSessionRepository sessionRepository,
            R2dbcReactiveFindByIndexNameSessionRepository indexedSessionRepository
    ) {
        return new SpringSessionBackedReactiveSessionRegistry<>(sessionRepository, indexedSessionRepository);
    }
}
