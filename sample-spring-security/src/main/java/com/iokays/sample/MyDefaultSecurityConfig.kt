package com.iokays.sample

import com.iokays.sample.captcha.UsernameCaptchaAuthenticationFilter
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationEventPublisher
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class MyDefaultSecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity, authenticationManager: AuthenticationManager?): SecurityFilterChain {
        http
            .sessionManagement { session -> session.maximumSessions(1) }
            .formLogin(Customizer.withDefaults())
            .authorizeHttpRequests(Customizer { auth ->
                auth
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/ping").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
            })
            .addFilterBefore(UsernameCaptchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)


        //添加 oauth2Login 支持
        http.oauth2Login(Customizer.withDefaults())

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val result = InMemoryUserDetailsManager()
        result.createUser(User.withUsername("admin").password("123456").authorities("ADMIN").build())
        result.createUser(User.withUsername("user").password("123456").authorities("READ").build())
        return result
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    fun authenticationEventPublisher(applicationEventPublisher : ApplicationEventPublisher): AuthenticationEventPublisher {
        return DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

    @Bean
    fun authenticationManager(userDetailsService: UserDetailsService, passwordEncoder: PasswordEncoder): AuthenticationManager {
        //验证用户密码
        val authenticationProvider = DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        val providerManager = ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }


}