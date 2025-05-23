package com.iokays.ai.core.domain.actor;

import com.iokays.common.core.domain.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.Objects;

public class Actor implements Entity<Actor> {

    @Id
    private Long id;
    private String act;
    private String prompt;
    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final Actor actor = (Actor) o;
        return Objects.equals(id, actor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}