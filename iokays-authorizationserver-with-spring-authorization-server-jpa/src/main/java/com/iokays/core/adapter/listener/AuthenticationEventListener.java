package com.iokays.core.adapter.listener;

import com.iokays.common.core.adapter.DrivenAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.stereotype.Component;

/**
 * 登录认证事件监听器
 */
@Slf4j
@Component
@DrivenAdapter
class AuthenticationEventListener {

    @Async
    @EventListener
    public void handle(final AbstractAuthenticationEvent evt) {
        log.info("认证事件: {}", evt);
    }
}
