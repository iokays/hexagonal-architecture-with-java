package com.iokays.config;

import com.iokays.config.adapter.persistence.MyClientRegistrationRepositoryAdapter;
import com.iokays.config.handler.FederatedIdentityAuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(v -> v.successHandler(federatedIdentityAuthenticationSuccessHandler))
                .sessionManagement(v -> v.maximumSessions(1))
        ;

        final SecurityFilterChain securityFilterChain = http.build();

        //这种方式不可取，应该在build之前配置。
        for (var filter : securityFilterChain.getFilters()) {
            if (filter instanceof DefaultLoginPageGeneratingFilter targetFile) {
                log.info("更新默认登录页的Oauth2登录选项为动态实现: ");
                targetFile.setOauth2AuthenticationUrlToClientName(clientRegistrationRepositoryAdapter.loginUrlToClientName());
            }
        }

        return securityFilterChain;
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
