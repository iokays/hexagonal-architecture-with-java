package com.iokays.webflux.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.session.ReactiveSessionRegistry;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.SessionLimit;
import org.springframework.session.security.SpringSessionBackedReactiveSessionRegistry;

@Configuration
@EnableWebFluxSecurity
public class MySecurityClientConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http,
                                                      R2dbcReactiveSessionRepository sessionRepository,
                                                      R2dbcReactiveFindByIndexNameSessionRepository indexedSessionRepository
    ) throws Exception {
        http
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .oauth2Login(ServerHttpSecurity.OAuth2LoginSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // 禁用 CSRF 保护
                .authorizeExchange(auth -> auth
                        .pathMatchers("/public/**").permitAll() // 公开访问的路径
                        .pathMatchers("/session/**").permitAll()
                        .anyExchange().authenticated() // 其他路径需要认证
                )
                .sessionManagement(session ->
                        session
                                .concurrentSessions(concurrent -> concurrent
                                        .maximumSessions(SessionLimit.UNLIMITED)
//                                        .sessionRegistry(new SpringSessionBackedReactiveSessionRegistry<>(sessionRepository, indexedSessionRepository))
                                )

                )
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
