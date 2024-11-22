//package com.iokays.sample;
//
//import com.iokays.sample.captcha.UsernameCaptchaAuthenticationFilter;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationEventPublisher;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class MyDefaultSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http,
//                                                   AuthenticationManager authenticationManager
//    ) throws Exception {
//
//        http
//                .sessionManagement(session -> session.maximumSessions(1))
//                .formLogin(Customizer.withDefaults())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/ping").hasAuthority("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(new UsernameCaptchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//
//        //添加 oauth2Login 支持
//        http.oauth2Login(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//
//        //验证用户密码
//        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//        ProviderManager providerManager = new ProviderManager(authenticationProvider);
//
//        providerManager.setEraseCredentialsAfterAuthentication(false);
//
//        return providerManager;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        var result = new InMemoryUserDetailsManager();
//
//        result.createUser(User.withUsername("admin").password("123456").authorities("ADMIN").build());
//        result.createUser(User.withUsername("user").password("123456").authorities("READ").build());
//
//        return result;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    public AuthenticationEventPublisher authenticationEventPublisher
//            (ApplicationEventPublisher applicationEventPublisher) {
//        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
//    }
//
//}
