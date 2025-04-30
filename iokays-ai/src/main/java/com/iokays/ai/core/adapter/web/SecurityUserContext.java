package com.iokays.ai.core.adapter.web;

import com.iokays.common.core.security.AuthenticatedUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUserContext {

    public static AuthenticatedUser authenticatedUser() {
        return () -> SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

}
