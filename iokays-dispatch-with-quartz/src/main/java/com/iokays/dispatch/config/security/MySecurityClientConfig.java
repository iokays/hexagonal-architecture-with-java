package com.iokays.dispatch.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableJdbcHttpSession(tableName = "SPRING_SESSION", maxInactiveIntervalInSeconds = 1800)
public class MySecurityClientConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable)
                .oauth2Login(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF 保护 [如果不是统一访问(代理)入口，需要禁用]
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // 公开访问的路径
                        .anyRequest().authenticated() // 其他路径需要认证
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) // 启用 JWT 验证
        ;
        return http.build();
    }

    @Bean
    public WebClient webClient(ClientRegistrationRepository clients,
                               OAuth2AuthorizedClientRepository authClients) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, authClients);
        oauth2.setDefaultClientRegistrationId("my-client"); // 对应配置中的 registration ID

        return WebClient.builder()
                .apply(oauth2.oauth2Configuration())
                .build();
    }


    // 配置 CORS 规则（允许前端域名）
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true); // 允许跨域 Cookie
//        config.addAllowedOrigin("http://localhost:3000"); // Vue 前端地址
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

}
