package com.iokays.ai.core.adapter.web;

import com.iokays.common.core.security.AuthenticatedUser;

public class SecurityUserContext {

    public static AuthenticatedUser authenticatedUser() {
        return () -> "test";
    }

}
