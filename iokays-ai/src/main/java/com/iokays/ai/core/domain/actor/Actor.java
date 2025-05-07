package com.iokays.ai.core.domain.actor;

import com.iokays.common.core.domain.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public class Actor implements Entity<Actor> {

    @Id
    private Long id;
    private String act;
    private String prompt;
    @Version
    private Long version;

    @Override
    public boolean sameIdentityAs(Actor other) {
        return false;
    }
}